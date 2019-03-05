package com.example.smartcare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class ConsumptionActivity extends AppCompatActivity {

    private SeekBar sbVolume;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    String foodCal;
    int calNum = 0;

    TextView fCal;
    TextView fName;
    TextView foodAmountText;
    Spinner spFoodTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumption);

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setHomeButtonEnabled(true);

        fName = (TextView) findViewById(R.id.foodName);
        fCal = (TextView) findViewById(R.id.foodCalNum);
        foodAmountText = (TextView) findViewById(R.id.foodAmount);

        sbVolume = (SeekBar) findViewById(R.id.seekBar);
        sbVolume.setMax(10);
        sbVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                calNum = progress;
                foodAmountText.setText("จำนวน "+progress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        Intent intent = getIntent();
        String foodName = intent.getStringExtra("foodName");
        foodCal = intent.getStringExtra("foodCal");

        fName.setText(foodName);
        fCal.setText(foodCal+" แคลอรี่");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    public void onSubmitFood(View view){
        spFoodTime = findViewById(R.id.spFoodTime);
        String dataSelect = "breakfast";

        if(spFoodTime.getSelectedItem().toString().equals("อาหารเช้า")){
            dataSelect = "breakfast";
        } else if(spFoodTime.getSelectedItem().toString().equals("อาหารเที่ยง")){
            dataSelect = "lunch";
        } else if(spFoodTime.getSelectedItem().toString().equals("อาหารเย็น")){
            dataSelect = "dinner";
        } else if(spFoodTime.getSelectedItem().toString().equals("ของว่าง")){
            dataSelect = "snack";
        }

        editor = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE).edit();
        editor.putFloat(dataSelect, Float.parseFloat(foodCal)*calNum);
        editor.apply();

        Toast toast = Toast.makeText(this, "บันทึกการสำเร็จ", Toast.LENGTH_SHORT);
        toast.show();

        onBackPressed();
    }
}
