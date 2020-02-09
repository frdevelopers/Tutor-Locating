package com.farhanramzan.tutorlocating;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class registration extends AppCompatActivity {

    EditText userName,userPassword,userMobileNo,userAddress;
    String uName,uPass,uMobNo,uAddress,uRole;
    TextView loginBtn;
    RadioGroup role;
    RadioButton rBtn;
    Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        loginBtn = findViewById(R.id.loginBtn);
        userName = findViewById(R.id.userName);
        userPassword = findViewById(R.id.userPassword);
        userMobileNo = findViewById(R.id.userMobileNo);
        userAddress = findViewById(R.id.userAddress);
        role = findViewById(R.id.role);
        registerBtn = findViewById(R.id.registerBtn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uName = userName.getText().toString();
                uPass = userPassword.getText().toString();
                uMobNo = userMobileNo.getText().toString();
                uAddress = userAddress.getText().toString();
                //Getting Value from Radio Button
                    int selectedRole = role.getCheckedRadioButtonId();
                    rBtn = findViewById(selectedRole);
                uRole = rBtn.getText().toString();

                //Toast.makeText(getApplicationContext(),"Registration Successful as "+uRole,Toast.LENGTH_SHORT).show();

            }
        });



        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
