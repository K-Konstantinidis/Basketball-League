package com.example.esake.StatsManagerHome;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.esake.FragmentTop5;
import com.example.esake.FragmentUserHome;
import com.example.esake.R;

public class HomeActivity extends AppCompatActivity {

    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Get a home frag for the stats manager
        HomeFragment homefrag = new HomeFragment();

//      FragmentTop5 top5 = new FragmentTop5();

        FragmentManager manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
		//for top5 fragment
        // transaction.replace(R.id.homefrag, top5, null);
        transaction.replace(R.id.homefrag, homefrag,null); //instead of replace you can also have add
        transaction.commit();
    }
}