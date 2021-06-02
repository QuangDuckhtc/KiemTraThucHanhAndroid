package com.example.kiemtrathuchanh1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText pn_Email, pn_password;
    Button btn_Login, btn_exit;
    TextView tv_Exit;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pn_Email = findViewById(R.id.pl_email);
        pn_password = findViewById(R.id.pl_password);
        mAuth = FirebaseAuth.getInstance();
       btn_exit = findViewById(R.id.btn_exit);
        btn_Login = findViewById(R.id.btn_Submit);

        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
        // chức năng

        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = pn_Email.getText().toString();
                String password = pn_password.getText().toString();
                DangNhap(email,password);
                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                    Toast.makeText(MainActivity.this, "Ko duoc rong", Toast.LENGTH_SHORT).show();
                }
                else{
                    DangNhap(email, password);
                }
            }
        });

    }

    private void DangNhap(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>(){
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //Toast.makeText(MainActivity.this, "Sucess", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, Manager.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}