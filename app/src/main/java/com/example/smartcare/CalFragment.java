package com.example.smartcare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalFragment extends Fragment {

    View view;
    Calendar calendar;
    String timeTemp;
    SharedPreferences prefs;

    TextView calDate;
    ImageView leftBtn;
    ImageView rigthBtn;
    TextView tvBreakfastNum;
    TextView tvLunchNum;
    TextView tvDinnerNum;
    TextView tvSnackNum;
    TextView tvCalNum;
    TextView tvCalSuggest;
    ProgressBar pbCal;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_cal, container, false);
        initCal();

        calDate = (TextView) view.findViewById(R.id.dateCal);
        leftBtn = (ImageView) view.findViewById(R.id.imageViewLeft);
        rigthBtn = (ImageView) view.findViewById(R.id.imageViewRight);

        calendar = Calendar.getInstance();

        timeTemp = new SimpleDateFormat("dd-MM-yyyy").format(calendar.getTime());
        calDate.setText(timeTemp);

        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.DATE, -1);
                timeTemp = new SimpleDateFormat("dd-MM-yyyy").format(calendar.getTime());
                calDate.setText(timeTemp);
            }
        });

        rigthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.DATE, 1);
                timeTemp = new SimpleDateFormat("dd-MM-yyyy").format(calendar.getTime());
                calDate.setText(timeTemp);
            }
        });

        FloatingActionButton myFab = (FloatingActionButton) view.findViewById(R.id.fab);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                changeFragment();
            }
        });

        return view;
    }

    public void changeFragment(){
//        Fragment newFragment = new FoodFragment();
//        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//        transaction.replace(R.id.container_main, newFragment);
//        transaction.commit();

        // Go to camera classifier
        Intent myIntent = new Intent(getActivity(), ClassifierActivity.class);
        getActivity().startActivity(myIntent);
        getActivity().finish();
    }

    public void initCal(){
        prefs = getActivity().getSharedPreferences("MyPref", getActivity().MODE_PRIVATE);
        tvBreakfastNum = (TextView) view.findViewById(R.id.tvBreakfastNum);
        tvLunchNum = (TextView) view.findViewById(R.id.tvLunchNum);
        tvDinnerNum = (TextView) view.findViewById(R.id.tvDinnerNum);
        tvSnackNum = (TextView) view.findViewById(R.id.tvSnackNum);
        tvCalNum = (TextView) view.findViewById(R.id.tvCalNum);
        tvCalSuggest = (TextView) view.findViewById(R.id.tvCalSuggest);

        tvBreakfastNum.setText(""+prefs.getFloat("breakfast", 0));
        tvLunchNum.setText(""+prefs.getFloat("lunch", 0));
        tvDinnerNum.setText(""+prefs.getFloat("dinner", 0));
        tvSnackNum.setText(""+prefs.getFloat("snack", 0));

        // Set total cal value
        tvCalNum.setText(""+(prefs.getFloat("breakfast", 0)
                +prefs.getFloat("lunch", 0)
                +prefs.getFloat("dinner", 0)
                +prefs.getFloat("snack", 0)));

        String sex = prefs.getString("gender", null);
        float weight = prefs.getFloat("weight", 0);
        float height = prefs.getFloat("height", 0);
        double total = 0;
        int yearOfBirth = 0;
        int crrYear = 0;

        if(prefs.getString("birthday", null) != null){
            String[] birthIndex = prefs.getString("birthday", null).split("/");
            yearOfBirth = Integer.parseInt(birthIndex[2]);
            crrYear = Calendar.getInstance().get(Calendar.YEAR);
        }

        if(sex.equals("ชาย")){
            total = Math.round(66 + (13.7 * weight) + (5 * height) - (6.8 * (crrYear - yearOfBirth)));
            tvCalSuggest.setText("ปริมาณที่แนะนำต่อวัน "+total+" แคลอรี่");
        } else if(sex.equals("หญิง")){
            total = Math.round(665 + (9.6 * weight) + (1.8 * height) - (4.7 * (crrYear - yearOfBirth)));
            tvCalSuggest.setText("ปริมาณที่แนะนำต่อวัน "+total+" แคลอรี่");
        } else {
            tvCalSuggest.setText("ปริมาณที่แนะนำต่อวัน 0 แคลอรี่");
        }

        int numberOfCal = (int) Double.parseDouble(tvCalNum.getText().toString());
        int maxOfCal = (int) total;

        int progress = (100 * numberOfCal) / maxOfCal;
        pbCal = (ProgressBar) view.findViewById(R.id.pbCal);
        pbCal.setProgress(progress);
    }
}
