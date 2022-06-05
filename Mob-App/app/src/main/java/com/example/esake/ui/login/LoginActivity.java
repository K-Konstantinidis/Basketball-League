package com.example.esake.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.esake.R;
import com.example.esake.StatsManagerHome.HomeActivity;
import com.example.esake.databinding.ActivityLoginAdminBinding;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private ActivityLoginAdminBinding binding_admin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding_admin = ActivityLoginAdminBinding.inflate(getLayoutInflater());
        setContentView(binding_admin.getRoot());

        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final EditText usernameEditText = binding_admin.username;
        final EditText passwordEditText = binding_admin.password;
        final Button loginButton = binding_admin.buttonLogin;
        final ProgressBar loadingProgressBar = binding_admin.loading;

        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if(loginFormState == null)
                    return;
                loginButton.setEnabled(loginFormState.isDataValid());
                if(loginFormState.getUsernameError() != null)
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                if(loginFormState.getPasswordError() != null)
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
            }
        });

        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if(loginResult == null)
                    return;
                loadingProgressBar.setVisibility(View.GONE);
                if(loginResult.getError() != null)
                    showLoginFailed(loginResult.getError());
                if(loginResult.getSuccess() != null)
                    updateUiWithUser(loginResult.getSuccess());

                setResult(Activity.RESULT_OK);

                //Complete and destroy login activity once successful
                finish();
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                loginViewModel.login(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
            return false;
        });

        loginButton.setOnClickListener(v -> {
            loadingProgressBar.setVisibility(View.VISIBLE);
            loginViewModel.login(usernameEditText.getText().toString(),
                    passwordEditText.getText().toString());
        });

        Button successLogin = findViewById(R.id.button_login);

        String value = "Manager Button";
        String value2 = "User Button";
        Intent intent1 = new Intent(LoginActivity.this,HomeActivity.class);

        successLogin.setOnClickListener(v -> {
            intent1.putExtra("flag",value);
            startActivity(intent1);
        });

        Intent intent2 = new Intent(LoginActivity.this, HomeActivity.class);
        TextView userLogin = findViewById(R.id.ContinueAsUser);

        userLogin.setOnClickListener(v -> {
            intent2.putExtra("flag",value2);
            startActivity(intent2);
        });

    }

    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        // Successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}