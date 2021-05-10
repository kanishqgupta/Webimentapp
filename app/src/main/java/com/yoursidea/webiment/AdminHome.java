package com.yoursidea.webiment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class AdminHome extends AppCompatActivity {
    private FirebaseAuth mFirebaseAuth;
    Button logout;
    BottomNavigationView bottomNavigationView;
    FrameLayout frameLayout;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        mFirebaseAuth = FirebaseAuth.getInstance();
        bottomNavigationView = findViewById(R.id.bottom_nav);
        frameLayout = findViewById(R.id.frameLayout);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new HomeFragment()).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(bottomMethod);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener bottomMethod =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment fragment = null;
                    switch (item.getItemId())
                    {
                        case R.id.bottom_nav_home:
                            fragment=new HomeFragment();
                            Bundle data = new Bundle();//Use bundle to pass data
                            data.putString("Sname", name);//put string, int, etc in bundle with a key value
                            fragment.setArguments(data);
                            break;
                        case R.id.bottom_nav_issue:
                            fragment=new ServicesFragment();
                            Bundle dataI = new Bundle();
                            dataI.putString("Sname", name);//put string, int, etc in bundle with a key value
                            fragment.setArguments(dataI);
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,fragment).commit();

                    return true;
                }
            };
}