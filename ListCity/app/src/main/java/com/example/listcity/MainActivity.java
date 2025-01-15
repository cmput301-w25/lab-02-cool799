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
    Button add, delete;
    String sc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cityList = findViewById(R.id.city_list);
        searchBar = findViewById(R.id.search_bar);
        add = findViewById(R.id.add_city);
        delete = findViewById(R.id.delete_city);

        String[] cities = {"Toronto","Montreal","Chicago","Vancouver","Calgary","Seattle","Edmonton"};
        dataList = new ArrayList<>();
        for (String city : cities) {
            dataList.add(city);
        }

        // link the adapter to the listview
        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        // save the selected city
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sc = dataList.get(position);
                Toast.makeText(MainActivity.this, "selected: " + sc, Toast.LENGTH_SHORT).show();
            }
        });
        //add ...
        add.setOnClickListener(v -> {
            String New = searchBar.getQuery().toString();
            if (!New.isEmpty()) {
                dataList.add(New);
                cityAdapter.notifyDataSetChanged(); //refresh
                searchBar.setQuery("", false); //clear search bar
                Toast.makeText(MainActivity.this,New+" added", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this,"please try again", Toast.LENGTH_SHORT).show();
            }
        });

        // delete ...
        delete.setOnClickListener(v -> {
            if (sc != null) {
                dataList.remove(sc);
                cityAdapter.notifyDataSetChanged(); //refresh
                Toast.makeText(MainActivity.this, sc+"deleted", Toast.LENGTH_SHORT).show();
                sc = null;//sc is null
            } else {
                Toast.makeText(MainActivity.this,"please click a city name", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
