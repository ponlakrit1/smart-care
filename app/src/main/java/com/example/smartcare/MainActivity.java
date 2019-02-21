package com.example.smartcare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onLogin(View view) {
        EditText txtUsername = (EditText) findViewById(R.id.username);
        EditText txtPassword = (EditText) findViewById(R.id.password);

        String username = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();

//        if(username.equals("admin") && password.equals("admin")){
//            Intent myIntent = new Intent(MainActivity.this, Main2Activity.class);
//            MainActivity.this.startActivity(myIntent);
//        } else {
//            Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show();
//        }

        Intent myIntent = new Intent(MainActivity.this, Main2Activity.class);
        MainActivity.this.startActivity(myIntent);
        finish();

    }

    public void onRegister(View view) {
        Intent myIntent = new Intent(MainActivity.this, RegActivity.class);
        MainActivity.this.startActivity(myIntent);
    }
}
