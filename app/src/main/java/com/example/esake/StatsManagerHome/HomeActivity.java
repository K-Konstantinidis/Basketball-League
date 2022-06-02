package com.example.esake.StatsManagerHome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.esake.FragmentUserHome;
import com.example.esake.R;
import com.example.esake.Stats_Manager_Pages;
import com.example.esake.ui.login.LoginActivity;

public class HomeActivity extends AppCompatActivity {

    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        HomeFragment homefrag = new HomeFragment();
        FragmentUserHome userfrag = new FragmentUserHome();

        FragmentManager manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.add(R.id.homefrag, homefrag,null);
        transaction.add(R.id.userfrag, userfrag, null);
        transaction.commit();

        FrameLayout layout = findViewById(R.id.homefrag);
        FrameLayout layout2 = findViewById(R.id.userfrag);

        Bundle extras = getIntent().getExtras();
        if(extras!= null){
            String value = extras.getString("flag");
            if(value.equals("User Button"))
                layout.setVisibility(View.GONE);
            else if(value.equals("Manager Button"))
                layout2.setVisibility(View.GONE);
        }

    }
}