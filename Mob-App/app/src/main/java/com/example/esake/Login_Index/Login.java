package com.example.esake.Login_Index;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.esake.MainActivity;
import com.example.esake.R;
import com.example.esake.StatsManagerFragments.HomeSmActivity;
import com.example.esake.MyIP;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import okhttp3.*;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

		//Find log in button
		Button successLogin = findViewById(R.id.button_login);
		//If sm button clicked, go to sm home page
		successLogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				//Get username & password
				TextView username = findViewById(R.id.username);
				TextView password = findViewById(R.id.password);

				//Try to log in
				int status = login(username.getText().toString(), password.getText().toString());

				if(username.getText().toString().isEmpty() && password.getText().toString().isEmpty())
					Toast.makeText(getApplicationContext(), "You must insert both a username and a password before login", Toast.LENGTH_SHORT).show();
				else {
					switch (status) {
						case -1:
							Toast.makeText(getApplicationContext(), R.string.server_error, Toast.LENGTH_SHORT).show();
							break;
						case 0:
							Toast.makeText(getApplicationContext(), R.string.invalid_credentials, Toast.LENGTH_SHORT).show();
							break;
						case 1:
							startActivity(new Intent(Login.this, HomeSmActivity.class));
							break;
					}
				}
			}
		});

		//Continue as user text
		TextView userLogin = findViewById(R.id.ContinueAsUser);
		//If user text clicked, go to home page
		userLogin.setOnClickListener(v -> startActivity(new Intent(Login.this, MainActivity.class)));

	}

	private int login(String username, String password) {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);

		int status_code = -1;
		OkHttpClient client = new OkHttpClient().newBuilder().build();

		RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
			.addFormDataPart("username", username)
			.addFormDataPart("password_sha256", hashSHA256(password))
			.build();

		Request request = new Request.Builder()
			.url("http://"+ MyIP.getIp()+"/ws/login.php")
			.method("POST", body)
			.build();

		try {
			Response response = client.newCall(request).execute();
			String data = response.body().string();

			JSONObject json = new JSONObject(data);
			Iterator<String> keys = json.keys();

			if(keys.hasNext())
				status_code = json.getInt("login_status");
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		catch (JSONException e) {
			e.printStackTrace();
		}

		return status_code;
	}

	/**
	 * Hashes the give message with SHA256
	 *
	 * @param msg The message to be hashed
	 * @return The hashed message
	 */
	private String hashSHA256(String msg) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");

			md.update(msg.getBytes(Charset.forName("UTF-8")));
			byte[] digest = md.digest();

			return String.format("%064x", new BigInteger(1, digest));
		}
		catch(NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}

		return null;
	}
}