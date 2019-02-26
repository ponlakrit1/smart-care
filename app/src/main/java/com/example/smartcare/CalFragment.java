package com.example.smartcare;

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
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalFragment extends Fragment {

    View view;
    Calendar calendar;
    String timeTemp;

    TextView calDate;
    ImageView leftBtn;
    ImageView rigthBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_cal, container, false);
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
        Fragment newFragment = new FoodFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container_main, newFragment);
        transaction.commit();
    }
}
