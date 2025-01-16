package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    // my custom variables all here


    //saved city
    List<String> cityListArr = new ArrayList<>();
    // importing the list view
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    // variable which will let my code know if add button is clicked
    boolean add = false;
    int positionOfClick = -1; // negative because its the standard






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });






        // connecting to the layout
        ConstraintLayout cl = findViewById(R.id.constraintLayout2);
        cl.setVisibility(View.INVISIBLE);

        // here we work with the city adapter stuff to connect to the listview
        cityList = findViewById(R.id.ListViewMain);
        cityAdapter = new ArrayAdapter<>(  this, R.layout.content, cityListArr);
        cityList.setAdapter(cityAdapter);

        // when add button is pressed
        Button AddCity = findViewById(R.id.btn_addCity);
        AddCity.setOnClickListener(v -> {
            cl.setVisibility(View.VISIBLE);
            add = true;
        });

        // this code gives us a output when we click on the add button
        Button confirm_button = findViewById(R.id.btn_confirm);
        confirm_button.setOnClickListener(v -> {
            if (add == true){
                EditText inputVar = findViewById(R.id.input_text);
                String text = inputVar.getText().toString();
                cityListArr.add(text);
                cityAdapter.notifyDataSetChanged();
                inputVar.setText("");
                add = false;
                cl.setVisibility(View.INVISIBLE);
            }
        });

        // when you click on the list view stuff
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            positionOfClick = position;
            for (int i = 0; i < cityList.getChildCount(); i++) {
                if (i == position) { // when we find the item

                    // stuff when we do on the clicked object
                    cityList.getChildAt(i).setBackgroundColor(getResources().getColor(android.R.color.darker_gray)); // setting the background color like its given in the lab
                } else {
                    cityList.getChildAt(i).setBackgroundColor(getResources().getColor(android.R.color.transparent)); // this one , because we want
                }
            }
        });

        // now we delete it
        Button DeleteCity = findViewById(R.id.btn_deleteCity);
        DeleteCity.setOnClickListener(v -> {
            if (positionOfClick < cityListArr.size() && positionOfClick >= 0) {
                cityListArr.remove(positionOfClick);
                cityAdapter.notifyDataSetChanged();
                positionOfClick = -1;
            }
        });










        // adding random city

        cityListArr.add("Edmonton");
        cityListArr.add("Montreal");

    }


}

