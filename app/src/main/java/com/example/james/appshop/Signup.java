package com.example.james.appshop;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private Toolbar mToolbar;
    private EditText RegisterUserName;
    private EditText RegisterUserBirth;
    private EditText RegisterUserPhone;
    private EditText RegisterUserEmail;
    private EditText RegisterUserPass;
    private EditText RegisterUserConpass;
    private Button ConfirmAccount;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button btnCancle = (Button) findViewById(R.id.cancle);
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callback = new Intent(Signup.this, Login.class);
                startActivity(callback);
                finish();
            }
        });

        RegisterUserName = (EditText) findViewById(R.id.sName);
        RegisterUserBirth = (EditText) findViewById(R.id.sDate);
        RegisterUserPhone = (EditText) findViewById(R.id.sPhone);
        RegisterUserEmail = (EditText) findViewById(R.id.sEmail);
        RegisterUserPass = (EditText) findViewById(R.id.sPassword);
        RegisterUserConpass = (EditText) findViewById(R.id.sPasswordcon);

        ConfirmAccount = (Button) findViewById(R.id.sRegister);
        loadingBar =new ProgressDialog(this);

        ConfirmAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = RegisterUserName.getText().toString();
                String birth = RegisterUserBirth.getText().toString();
                String Phone = RegisterUserPhone.getText().toString();
                String Email = RegisterUserEmail.getText().toString();
                String Password = RegisterUserPass.getText().toString();
                String ConfirmPassword = RegisterUserConpass.getText().toString();

                ConfirmAccount(name, birth, Phone, Email, Password, ConfirmPassword);
            }
        });
    }

    private void ConfirmAccount(String name, String birth, String phone, String email, String password, String confirmPassword) {

        if(TextUtils.isEmpty(name)){
            Toast.makeText(Signup.this, "กรุณาใส่ชื่อ-นามสกุล",Toast.LENGTH_LONG).show();
        }
        if(TextUtils.isEmpty(birth)){
            Toast.makeText(Signup.this, "กรุณาใส่วันเกิด",Toast.LENGTH_LONG).show();
        }
        if(TextUtils.isEmpty(phone)){
            Toast.makeText(Signup.this, "กรุณาใส่เบอร์โทรศัพท์",Toast.LENGTH_LONG).show();
        }
        if(TextUtils.isEmpty(email)){
            Toast.makeText(Signup.this, "กรุณาใส่อีเมล",Toast.LENGTH_LONG).show();
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(Signup.this, "กรุณาใส่พาสเวิร์ด",Toast.LENGTH_LONG).show();
        }
        if(TextUtils.isEmpty(confirmPassword)){
                Toast.makeText(Signup.this, "กรุณายืนยันรหัสผ่าน",Toast.LENGTH_LONG).show();
        }
        else {
            loadingBar.setTitle("สมัครสมาชิกใหม่");
            loadingBar.setMessage("กรุณารอสักครู่...");
            loadingBar.show();

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task)
                {
                    if (task.isSuccessful())
                    {
                        Intent mainIntant = new Intent(Signup.this, MainActivity.class);
                        mainIntant.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(mainIntant);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(Signup.this, "ผิดพลาด ลองใหม่อีกครั้ง", Toast.LENGTH_LONG).show();
                    }
                    loadingBar.dismiss();
                }
            });
        }


    }
}
