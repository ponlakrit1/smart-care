package com.example.smartcare;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Calendar;

public class ProfileFragment extends Fragment{

    private DatePickerDialog.OnDateSetListener dateSetListener;
    View view;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    EditText namePro;
    EditText lastnamePro;
    EditText txtAge;
    EditText weightPro;
    EditText heightPro;
    Spinner bloodPro;
    Spinner genderPro;
    EditText codeHNPro;
    Button profileBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_profile, container, false);
        initData();

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                txtAge.setText(day+"/"+(month+1)+"/"+year);
            }
        };

        txtAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(getActivity(), android.R.style.Theme_Holo_Light_Dialog_MinWidth, dateSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog.show();
            }
        });

        profileBtn = (Button) view.findViewById(R.id.profileBtn);
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSubmitChange();
            }
        });

        return view;
    }

    public void initData(){
        txtAge = (EditText) view.findViewById(R.id.BirthdayProfile);
        namePro = (EditText) view.findViewById(R.id.txtNameProfile);
        lastnamePro = (EditText) view.findViewById(R.id.txtLastnameProfile);
        weightPro = (EditText) view.findViewById(R.id.txtWeightProfile);
        heightPro = (EditText) view.findViewById(R.id.txtHeightProfile);
        bloodPro = (Spinner) view.findViewById(R.id.spBloodProfile);
        genderPro = (Spinner) view.findViewById(R.id.spGenderProfile);
        codeHNPro = (EditText) view.findViewById(R.id.txt_codeHNProfile);

        prefs = getActivity().getSharedPreferences("MyPref", getActivity().MODE_PRIVATE);
        namePro.setText(prefs.getString("name", null));
        lastnamePro.setText(prefs.getString("lastname", null));
        weightPro.setText(""+prefs.getFloat("weight", 0));
        heightPro.setText(""+prefs.getFloat("height", 0));
        codeHNPro.setText(prefs.getString("codehn", null));
        txtAge.setText(prefs.getString("birthday", null));

        Resources res = getResources();
        String[] temp1 = res.getStringArray(R.array.blood);
        String[] temp2 = res.getStringArray(R.array.gender);

        int position = 0;

        if(prefs.getString("blood", null) != null){
            position = Arrays.asList(temp1).indexOf(prefs.getString("blood", null));
            bloodPro.setSelection(position);
        }

        if(prefs.getString("gender", null) != null){
            position = Arrays.asList(temp2).indexOf(prefs.getString("gender", null));
            genderPro.setSelection(position);
        }

    }

    public void onSubmitChange(){
        editor = prefs.edit();
        editor.putString("name", namePro.getText().toString());
        editor.putString("lastname", lastnamePro.getText().toString());
        editor.putString("birthday", txtAge.getText().toString());
        editor.putFloat("weight", Float.parseFloat(weightPro.getText().toString()));
        editor.putFloat("height", Float.parseFloat(heightPro.getText().toString()));
        editor.putString("blood", bloodPro.getSelectedItem().toString());
        editor.putString("gender", genderPro.getSelectedItem().toString());
        editor.putString("codehn", codeHNPro.getText().toString());
        editor.apply();

        Toast toast = Toast.makeText(getActivity(), "บันทึกการแก้ไขสำเร็จ", Toast.LENGTH_SHORT);
        toast.show();
    }

}
