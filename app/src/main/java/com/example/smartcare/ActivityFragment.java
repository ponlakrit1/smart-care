package com.example.smartcare;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class ActivityFragment extends Fragment implements AdapterView.OnItemClickListener{

    View view;
    String[] actNumArray = {"100","200","300"};
    String[] actTextArray = {"วิ่ง","ว่ายน้ำ","ปั่นจักรยาน"};

    private ArrayList<String> tempActArray;
    private ArrayAdapter<String> activityAdapter;

    Button searchActivity;
    EditText txtActivity;
    ListView lvActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_activity, container, false);
        txtActivity = (EditText) view.findViewById(R.id.txtActivity);

        initItem();

        activityAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, tempActArray);
        lvActivity = (ListView) view.findViewById(R.id.lvActivity);
        lvActivity.setAdapter(activityAdapter);
        lvActivity.setOnItemClickListener(this);

        searchActivity = (Button) view.findViewById(R.id.searchActivity);
        searchActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSearch();
            }
        });

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        String act = adapterView.getItemAtPosition(position).toString();
        Toast.makeText(getActivity(), "คุณเลือก "+act, Toast.LENGTH_SHORT).show();
    }

    public void initItem(){
        tempActArray = new ArrayList<String>();
        for (String val : actTextArray){
            tempActArray.add(val);
        }
        Collections.sort(tempActArray);
    }

    public void onSearch(){
        String temp = txtActivity.getText().toString();
        tempActArray = new ArrayList<String>();

        if(temp.equals("")){
            initItem();
        } else {
            for (String val : actTextArray){
                if (val.contains(temp)){
                    tempActArray.add(val);
                }
            }
        }

        Collections.sort(tempActArray);
        activityAdapter.clear();
        activityAdapter.addAll(tempActArray);
        activityAdapter.notifyDataSetChanged();
    }
}
