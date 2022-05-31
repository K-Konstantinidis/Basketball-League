package com.example.esake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.esake.StatsManagerHome.HomeActivity;
import com.example.esake.ui.login.LoginActivity;

public class IndexPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_page);

        Button statsMan = findViewById(R.id.button_stats_manager);

        String value = "User Button";
        Intent intent1 = new Intent(IndexPage.this,HomeActivity.class);


        statsMan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IndexPage.this, LoginActivity.class));
            }
        });

        Button userLogin = findViewById(R.id.button_user);

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent1.putExtra("flag",value);
                startActivity(intent1);
            }
        });
    }
}