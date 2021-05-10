package com.yoursidea.webiment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.all.All;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.yoursidea.webiment.Prevalent.Prevalent;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import io.paperdb.Paper;

public class All_serv_details extends AppCompatActivity {
    TextView title;
    EditText phone,Name,Service,Email;
    RelativeLayout request;
    FirebaseFirestore firebaseFirestore;
    CollectionReference collectionReference;
    private String saveCurrentDate,saveCurrentTime,productRandomKey;
    private ProgressDialog mProProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_serv_details);
        mProProgress=new ProgressDialog(this);
        firebaseFirestore= FirebaseFirestore.getInstance();
        String AdminPhone= Paper.book().read(Prevalent.AdminPhoneKey);
        collectionReference=firebaseFirestore.collection(AdminPhone);
        String sub=AdminPhone.substring(3);
        request=findViewById(R.id.add_staff_dialog_btn_dis);
        phone=findViewById(R.id.phone_txt);
        Service=findViewById(R.id.edt_temp_add_staff_tempt);
        Email=findViewById(R.id.edt_desig_add_staff_dis);
        Name=findViewById(R.id.edt_name_add_staff_dis);
        title=findViewById(R.id.title_form);
        Intent intent=getIntent();
        title.setText(intent.getStringExtra("title"));
        Service.setText(intent.getStringExtra("title"));
        phone.setText(sub);
        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name,email,service;
                name=Name.getText().toString();
                email=Email.getText().toString();
                service=Service.getText().toString();
                if (TextUtils.isEmpty(name)){
                    Toast.makeText(All_serv_details.this, "Please enter name ", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(email)){
                    Toast.makeText(All_serv_details.this, "Please enter email", Toast.LENGTH_SHORT).show();
                }else{
                  addproduct(name,email,service);
                }
            }
        });
    }

    private void addproduct(String name, String email, String service) {
        mProProgress.setTitle("Adding New Request");
        mProProgress.setMessage("Please wait while we are update your request !");
        mProProgress.setCanceledOnTouchOutside(false);
        mProProgress.show();
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("name",name);
        hashMap.put("email",email);
        hashMap.put("service",service);
        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat currentDate=new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate=currentDate.format(calendar.getTime());
        SimpleDateFormat currentTime=new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime=currentTime.format(calendar.getTime());
        productRandomKey=saveCurrentDate+saveCurrentTime;
        hashMap.put("dateTime",productRandomKey);
        collectionReference.document(service).set(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    mProProgress.dismiss();
                    startActivity(new Intent(All_serv_details.this,AdminHome.class));
                    finish();
                    Toast.makeText(All_serv_details.this, "Congratulations your request has been updated successfully...", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}