package com.yoursidea.webiment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.hbb20.CountryCodePicker;
import com.yoursidea.webiment.Prevalent.Prevalent;

import io.paperdb.Paper;

public class PhoneLogin extends AppCompatActivity {
    FirebaseFirestore firebaseFirestore;
    CollectionReference collectionReference;
    EditText phoneNo;
    private CountryCodePicker ccp;
    Button submit;
    String id,phone;
    int temp=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_login);
        firebaseFirestore=FirebaseFirestore.getInstance();
        collectionReference=firebaseFirestore.collection("Admin");
        phoneNo=findViewById(R.id.phone_txt);
        ccp=findViewById(R.id.ccp);
        ccp.registerCarrierNumberEditText(phoneNo);
        submit=findViewById(R.id.buttonSubPhone);
        Paper.init(this);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone=phoneNo.getText().toString().trim();
                final String getNo = "+"+ccp.getFullNumber();
                if (TextUtils.isEmpty(phone))
                {
                    Toast.makeText(PhoneLogin.this, "Number field is empty, please enter the phone number", Toast.LENGTH_SHORT).show();
                }
                else if (phone.length()<10)
                {
                    Toast.makeText(PhoneLogin.this, " please enter the phone number with length of 10 digit", Toast.LENGTH_SHORT).show();
                }
                else
                { Intent intent=new Intent(PhoneLogin.this, AdminOtpActivity.class);
                intent.putExtra("phone",getNo);
                Paper.book().write(Prevalent.AdminPhoneKey,phone);
                startActivity(intent);
                }
                }
            });
    }
}