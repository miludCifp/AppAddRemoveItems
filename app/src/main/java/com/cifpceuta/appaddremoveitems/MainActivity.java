package com.cifpceuta.appaddremoveitems;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import java.util.ArrayList;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {
    private ItemAdapter adapter;
    private RecyclerView rvListadoItems;
    private ArrayList<String> list_items;
    private Toolbar miToolbar;
    private boolean estadoOrden = true;
    private SearchView barraBusqueda;

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

        barraBusqueda =  (SearchView) findViewById(R.id.barra_Busqueda);

        barraBusqueda.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filtrado(newText);
                return false;
            }
        });
    }
    private void filtrado(String texto){
        ArrayList<String> filteredList_items = new ArrayList<>();
        for(String item : list_items){
            if(item.toLowerCase().contains(texto.toLowerCase())){
                filteredList_items.add(item);
            }
        }
        adapter.setList_items(filteredList_items);
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
            list_items.sort(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.compareTo(o2);
                }
            });
            estadoOrden = true;
        }
        else if(id == R.id.opcion2){
            //Descendente
            list_items.sort(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return (o1.compareTo(o2))*(-1);
                }
            });
            estadoOrden = false;
        }
        else if(id == R.id.opcion3){
            //Interactivo
            boolean estado = true;
            if (estadoOrden){
                list_items.sort(new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return (o1.compareTo(o2))*(-1);
                    }
                });
                estadoOrden = false;
            }else {
                list_items.sort(new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return o1.compareTo(o2);
                    }
                });
                estadoOrden = true;
            }
        }
        adapter.setList_items(list_items);


        return true;
    }
}