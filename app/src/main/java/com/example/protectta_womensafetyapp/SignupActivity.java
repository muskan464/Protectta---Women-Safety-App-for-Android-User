package com.example.protectta_womensafetyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    EditText signupName,signupEmail,signupUsername,signupPassword;
    TextView loginRedirectText;
    Button signupButton;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);

        signupName=findViewById(R.id.signup_name);
        signupEmail=findViewById(R.id.signup_mail);
        signupUsername=findViewById(R.id.signup_username);
        signupPassword=findViewById(R.id.signup_password);
        signupButton=findViewById(R.id.signup_btn);
        loginRedirectText=findViewById(R.id.loginRedirectTxt);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database=FirebaseDatabase.getInstance();
                reference=database.getReference("users");

                String name=signupName.getText().toString();
                String email=signupEmail.getText().toString();
                String username=signupUsername.getText().toString();
                String password=signupPassword.getText().toString();

                HelperClass helperClass=new HelperClass(name,email,username,password);
                reference.child(username).setValue(helperClass);

                Toast.makeText(SignupActivity.this, "You have signup successfully", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}