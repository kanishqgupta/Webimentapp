package com.yoursidea.webiment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.yoursidea.webiment.Prevalent.Prevalent;

import io.paperdb.Paper;

public class RequestDetails extends AppCompatActivity {
    TextView service,name,email,date,status;
    FirebaseFirestore firebaseFirestore;
    CollectionReference collectionReference;
    RelativeLayout delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_details);
        Intent intent=getIntent();
        String serv=intent.getStringExtra("service");
        firebaseFirestore=FirebaseFirestore.getInstance();
        service=findViewById(R.id.c_serv);
        name=findViewById(R.id.c_name);
        email=findViewById(R.id.c_email);
        date=findViewById(R.id.c_date);
        status=findViewById(R.id.c_status);
        delete=findViewById(R.id.delete_staff_dialog_btn_dis);
        String AdminPhone= Paper.book().read(Prevalent.AdminPhoneKey);
        collectionReference=firebaseFirestore.collection(AdminPhone);
        collectionReference.document(serv).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot!=null && documentSnapshot.exists()){
                    Requests requests=documentSnapshot.toObject(Requests.class);
                    if (requests.getEmail()!=null){
                        email.setText(requests.getEmail());
                    }
                    if (requests.getName()!=null){
                        name.setText(requests.getName());
                    }
                    if (requests.getService()!=null){
                        service.setText(requests.getService());
                    }
                    if (requests.getDateTime()!=null){
                        date.setText(requests.getDateTime());
                    }
                    if (requests.getStatus()!=null){
                        status.setText(requests.getStatus());
                    }
                }
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collectionReference.document(serv).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        startActivity(new Intent(RequestDetails.this,AdminHome.class));
                        finish();
                        Toast.makeText(RequestDetails.this, "Request deleted successfully ", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}