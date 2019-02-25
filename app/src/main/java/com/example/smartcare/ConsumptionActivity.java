package com.example.smartcare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class ConsumptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumption);

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setHomeButtonEnabled(true);

        Intent intent = getIntent();
        String foodName = intent.getStringExtra("foodName");
        String foodCal = intent.getStringExtra("foodCal");

        TextView fName = (TextView) findViewById(R.id.foodName);
        TextView fCal = (TextView) findViewById(R.id.foodCalNum);

        fName.setText(foodName);
        fCal.setText(foodCal+" calories");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
