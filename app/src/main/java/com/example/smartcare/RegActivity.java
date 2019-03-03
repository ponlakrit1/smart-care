package com.example.smartcare;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;

public class RegActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    SharedPreferences.Editor pref;

    EditText username;
    EditText password;
    EditText rePassword;
    EditText nameReg;
    EditText lastnameReg;
    EditText birthdayReg;
    EditText weightReg;
    EditText heightReg;
    Spinner bloodReg;
    Spinner genderReg;
    EditText codeHNReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE).edit();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        birthdayReg = (EditText) findViewById(R.id.BirthdayReg);
        birthdayReg.setText(day+"/"+(month+1)+"/"+year);
    }

    public void selectDate(View view){
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, this, year, month, day);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();
    }

    public void onSubmitReg(View view){
        username = (EditText) findViewById(R.id.txtUsernameReg);
        password = (EditText) findViewById(R.id.txtPasswordReg);
        rePassword = (EditText) findViewById(R.id.txtReRasswordReg);
        nameReg = (EditText) findViewById(R.id.txtNameReg);
        lastnameReg = (EditText) findViewById(R.id.txtLastnameReg);
        birthdayReg = (EditText) findViewById(R.id.BirthdayReg);
        weightReg = (EditText) findViewById(R.id.txtWeightReg);
        heightReg = (EditText) findViewById(R.id.txtHeightReg);
        bloodReg = (Spinner) findViewById(R.id.spBloodReg);
        genderReg = (Spinner) findViewById(R.id.spGenderReg);
        codeHNReg = (EditText) findViewById(R.id.txtCodeHNReg);

        if(!password.getText().toString().equals(rePassword.getText().toString())){
            Toast toast = Toast.makeText(this, "ใส่รหัสผ่านไม่ตรงกัน", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            pref.putString("username", username.getText().toString());
            pref.putString("password", password.getText().toString());
            pref.putString("name", nameReg.getText().toString());
            pref.putString("lastname", lastnameReg.getText().toString());
            pref.putString("birthday", birthdayReg.getText().toString());
            pref.putFloat("weight", Float.parseFloat(weightReg.getText().toString()));
            pref.putFloat("height", Float.parseFloat(heightReg.getText().toString()));
            pref.putString("blood", bloodReg.getSelectedItem().toString());
            pref.putString("gender", genderReg.getSelectedItem().toString());
            pref.putString("codehn", codeHNReg.getText().toString());
            pref.apply();

            Toast toast = Toast.makeText(this, "สมัครสมาชิคสำเร็จ", Toast.LENGTH_SHORT);
            toast.show();

            Intent myIntent = new Intent(RegActivity.this, MainActivity.class);
            RegActivity.this.startActivity(myIntent);
            finish();
        }
    }
}
