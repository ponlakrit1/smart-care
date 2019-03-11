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
import java.util.Arrays;
import java.util.Collections;

public class FoodFragment extends Fragment implements AdapterView.OnItemClickListener{

    View view;
    String[] calArray = {"100","200","300","400","500"};
    String[] foodArray = {"ไข่เจียว","ข้าวผัด","ก๋วยเตี๋ยว","กล้วย","โค๊ก"};

    private ArrayList<String> tempArray;
    private ArrayAdapter<String> foodAdapter;

    Button search_text;
    EditText txtSearch;
    ListView lvFood;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_food, container, false);
        txtSearch = (EditText) view.findViewById(R.id.txtSearch);

        initItem();

        foodAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, tempArray);
        lvFood = (ListView) view.findViewById(R.id.lvFood);
        lvFood.setAdapter(foodAdapter);
        lvFood.setOnItemClickListener(this);

        search_text = (Button) view.findViewById(R.id.search_text);
        search_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSearch();
            }
        });

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        String food = adapterView.getItemAtPosition(position).toString();

        Intent myIntent = new Intent(getContext(), ConsumptionActivity.class);
        myIntent.putExtra("foodName", food);
        myIntent.putExtra("foodCal", calArray[position]);
        getContext().startActivity(myIntent);
    }

    public void initItem(){
        tempArray = new ArrayList<String>();
        for (String val : foodArray){
            tempArray.add(val);
        }
        Collections.sort(tempArray);
    }

    public void onSearch(){
        String temp = txtSearch.getText().toString();
        tempArray = new ArrayList<String>();

        if(temp.equals("")){
            initItem();
        } else {
            for (String val : foodArray){
                if (val.contains(temp)){
                    tempArray.add(val);
                }
            }
        }

        Collections.sort(tempArray);
        foodAdapter.clear();
        foodAdapter.addAll(tempArray);
        foodAdapter.notifyDataSetChanged();
    }
}
