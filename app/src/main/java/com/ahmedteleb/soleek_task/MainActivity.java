package com.ahmedteleb.soleek_task;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ahmedteleb.soleek_task.LoginRegistration.Login;
import com.ahmedteleb.soleek_task.LoginRegistration.Registration;

public class MainActivity extends AppCompatActivity {

    private Button Login_btn , Regiatration_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Login_btn = findViewById(R.id.Login_btn);
        Regiatration_btn = findViewById(R.id.Registration_btn);

        Login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        Regiatration_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Registration.class);
                startActivity(intent);
                finish();
                return;
            }
        });
    }
}
