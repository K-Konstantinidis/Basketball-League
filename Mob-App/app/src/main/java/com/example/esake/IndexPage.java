package com.example.esake;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.esake.StatsManagerHome.HomeActivity;

public class IndexPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_page);

        //Find stats manager button
        Button statsMan = findViewById(R.id.button_stats_manager);
        //If sm button clicked, go to log in page
        statsMan.setOnClickListener(v -> startActivity(new Intent(IndexPage.this, Login.class)));

        //Find user button
        Button userLogin = findViewById(R.id.button_user);
		//If user button clicked, go to home page
        userLogin.setOnClickListener(v -> startActivity(new Intent(IndexPage.this, MainActivity.class)));
    }
}