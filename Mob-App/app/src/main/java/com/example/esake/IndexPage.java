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

        Button statsMan = findViewById(R.id.button_stats_manager);

        String value = "User Button";
        Intent intent1 = new Intent(IndexPage.this, HomeActivity.class);


        statsMan.setOnClickListener(v -> startActivity(new Intent(IndexPage.this, Login.class)));

        Button userLogin = findViewById(R.id.button_user);

        userLogin.setOnClickListener(v -> {
            intent1.putExtra("flag", value);
            startActivity(intent1);
        });
    }
}