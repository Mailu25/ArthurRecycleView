package com.example.arthurrecycleview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import androidx.appcompat.widget.SearchView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<DataModel> dataset;
    private ArrayList<DataModel> filteredDataset;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private CustomeAddapter addapter;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        recyclerView = findViewById(R.id.recyclerViewResult);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        dataset = new ArrayList<DataModel>();

        for(int i=0; i<Data.nameArray.length; i++){
            dataset.add(new DataModel(
                    Data.nameArray[i],
                    Data.descriptionArray[i],
                    Data.drawableArray[i],
                    Data.id_[i]
                    ));
        }

        //filteredDataset = new ArrayList<>(dataset);
        addapter = new CustomeAddapter (dataset);
        recyclerView.setAdapter(addapter);

        SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                addapter.filter(query.trim());
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                addapter.filter(newText.trim());
                return true;
            }
        });
    }
}
