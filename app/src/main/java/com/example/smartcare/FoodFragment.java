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
import android.widget.ListView;
import android.widget.Toast;

public class FoodFragment extends Fragment implements AdapterView.OnItemClickListener{

    View view;
    String[] calArray = {"100","200","300","400","500"};
    String[] foodArray = {"ไข่เจียว","ข้าวผัด","ก๋วยเตี๋ยว","กล้วย","โค๊ก"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_food, container, false);

        ArrayAdapter foodAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, foodArray);

        ListView lvFood = (ListView) view.findViewById(R.id.lvFood);
        lvFood.setAdapter(foodAdapter);
        lvFood.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        String food = adapterView.getItemAtPosition(position).toString();
//        Toast.makeText(getActivity(), food+" : "+calArray[position]+" calories", Toast.LENGTH_SHORT).show();

        Intent myIntent = new Intent(getContext(), ConsumptionActivity.class);
        myIntent.putExtra("foodName", food);
        myIntent.putExtra("foodCal", calArray[position]);
        getContext().startActivity(myIntent);
    }
}
