package com.example.listcity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    SearchView searchBar;
    Button addCityButton, deleteCityButton;
    String selectedCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize
        cityList = findViewById(R.id.city_list);
        searchBar = findViewById(R.id.search_bar);
        addCityButton = findViewById(R.id.add_city);
        deleteCityButton = findViewById(R.id.delete_city);

        // data list
        String[] cities = {"Toronto", "Los Angeles", "Chicago", "Vancouver", "Phoenix","Seattle","Edmonton"};
        dataList = new ArrayList<>();
        for (String city : cities) {
            dataList.add(city);
        }

        // link the adapter to the listview
        cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        cityList.setAdapter(cityAdapter);

        // save the selected city
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedCity = dataList.get(position);
                Toast.makeText(MainActivity.this, "Selected: " + selectedCity, Toast.LENGTH_SHORT).show();
            }
        });

        // add city by clicking add button
        addCityButton.setOnClickListener(v -> {
            String newCity = searchBar.getQuery().toString().trim();
            if (!newCity.isEmpty()) {
                dataList.add(newCity);
                cityAdapter.notifyDataSetChanged(); // Notify adapter to refresh the list
                searchBar.setQuery("", false); // Clear the search field after adding
                Toast.makeText(MainActivity.this, newCity + " added", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Please enter a city name", Toast.LENGTH_SHORT).show();
            }
        });

        // delete ...
        deleteCityButton.setOnClickListener(v -> {
            if (selectedCity != null) {
                dataList.remove(selectedCity); // Remove the selected city from the list
                cityAdapter.notifyDataSetChanged(); // Notify the adapter to update the ListView
                Toast.makeText(MainActivity.this, selectedCity + " deleted", Toast.LENGTH_SHORT).show();
                selectedCity = null; // Reset the selected city
            } else {
                Toast.makeText(MainActivity.this, "No city selected to delete", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
