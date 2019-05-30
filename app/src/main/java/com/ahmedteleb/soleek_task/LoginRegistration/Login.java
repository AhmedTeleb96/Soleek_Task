package com.ahmedteleb.soleek_task.LoginRegistration;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ahmedteleb.soleek_task.CountriesList;
import com.ahmedteleb.soleek_task.MainActivity;
import com.ahmedteleb.soleek_task.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    Button login_btn,registerActivity_btn;
    EditText email_et , password_et;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener firebaseAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        registerActivity_btn = findViewById(R.id.loginActivity_register_btn);
        registerActivity_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Registration.class);
                startActivity(intent);
            }
        });

        login_btn = findViewById(R.id.loginActivity_login_btn);
        email_et = findViewById(R.id.loginActivity_email_edt);
        password_et = findViewById(R.id.loginActivity_password_edt);

        mAuth = FirebaseAuth.getInstance();

        firebaseAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user != null)
                {
                    Intent main_intent =new Intent(getApplication(), CountriesList.class);
                    main_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(main_intent);
                    finish();
                    return;
                }
            }
        };

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = email_et.getText().toString();
                final String password = password_et.getText().toString();
                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful())
                        {
                            Toast.makeText(Login.this,"Sign in Error",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(firebaseAuthStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(firebaseAuthStateListener);
    }
}
