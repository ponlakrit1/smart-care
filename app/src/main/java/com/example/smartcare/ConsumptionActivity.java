package com.example.smartcare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class ConsumptionActivity extends AppCompatActivity {

    private SeekBar sbVolume;

    TextView fCal;
    TextView fName;
    TextView foodAmountText;

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
        String foodCal = intent.getStringExtra("foodCal");

        fName.setText(foodName);
        fCal.setText(foodCal+" แคลอรี่");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
