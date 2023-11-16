package com.cifpceuta.appaddremoveitems;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ItemAdapter adapter;
    private RecyclerView rvListadoItems;
    private ArrayList<String> list_items;
    private Toolbar miToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list_items = new ArrayList<>();
        list_items.add("Item 1");
        list_items.add("Item 2");
        list_items.add("Item 3");
        list_items.add("Item 4");
        list_items.add("Item 5");
        list_items.add("Item 6");
        list_items.add("Item 7");
        list_items.add("Item 8");
        list_items.add("Item 9");
        list_items.add("Item 10");
        if (adapter == null){
            adapter = new ItemAdapter(list_items);
        }

        rvListadoItems = (RecyclerView) findViewById(R.id.rvListadoItems);
        rvListadoItems.setAdapter(adapter);
        rvListadoItems.setLayoutManager(new LinearLayoutManager(this));

        miToolbar = (Toolbar) findViewById(R.id.miToolbar);
        setSupportActionBar(miToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.opcion1){
            //Ascendente

        }
        else if(id == R.id.opcion2){
            //Descendente

        }
        else if(id == R.id.opcion3){
            //Interactivo

        }

        return true;
    }
}