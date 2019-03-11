package com.example.smartcare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SharedPreferences prefs;

    EditText txtUsername;
    EditText txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs = getSharedPreferences("MyPref", MODE_PRIVATE);
        String getUsername = prefs.getString("username", null);
        String getPassword = prefs.getString("password", null);

        txtUsername = (EditText) findViewById(R.id.username);
        txtPassword = (EditText) findViewById(R.id.password);

        txtUsername.setText(getUsername);
        txtPassword.setText(getPassword);
    }

    public void onLogin(View view) {
        if(txtUsername.getText().toString().equals("") && txtPassword.getText().toString().equals("")){
            Toast.makeText(this, "กรุณาสมัครสมาชิกก่อน", Toast.LENGTH_SHORT).show();
        } else {
            Intent myIntent = new Intent(MainActivity.this, Main2Activity.class);
            MainActivity.this.startActivity(myIntent);
            finish();
        }

    }

    public void onRegister(View view) {
        Intent myIntent = new Intent(MainActivity.this, RegActivity.class);
        MainActivity.this.startActivity(myIntent);
    }
}
