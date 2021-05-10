package com.yoursidea.webiment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.yoursidea.webiment.Prevalent.Prevalent;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {
    private static  int SPLASH_SCREEN=4000;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseFirestore= FirebaseFirestore.getInstance();
        Paper.init(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String AdminPhone= Paper.book().read(Prevalent.AdminPhoneKey);
                String Adminfb= Paper.book().read(Prevalent.AdminFbKey);
                if (AdminPhone!=null){
                    Intent intent=new Intent(MainActivity.this,AdminHome.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(MainActivity.this, "Logged in successfully..", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent splash = new Intent(MainActivity.this, AdminLogin.class);
                    startActivity(splash);
                    finish();
                }
            }
        },SPLASH_SCREEN);
    }
}