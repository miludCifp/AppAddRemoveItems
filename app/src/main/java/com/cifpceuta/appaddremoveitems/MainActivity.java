package com.cifpceuta.appaddremoveitems;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {
    private ItemAdapter adapter;
    private RecyclerView rvListadoItems;
    private ArrayList<String> list_items;
    private Toolbar miToolbar;
    private boolean estadoOrden = true;
    private boolean iconoGridLayout = true;
    private SearchView barraBusqueda;
    private FloatingActionButton fabAñadir;

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

        fabAñadir = findViewById(R.id.fabAñadir);

        fabAñadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    public void onClick(View v) {

        //Creamos el DIALOG asociado al ActivityMAIN
        Dialog dialog = new Dialog(MainActivity.this);

        //Le asociamos el layout correspondiente
        dialog.setContentView(R.layout.dialogo_emergente);

        //Recuperamos los views dentro de dicho layout para recuperar sus valores posteriormente
        EditText etTextoItem = dialog.findViewById(R.id.etTextoItem);
        Button btnDialogAñadir = dialog.findViewById(R.id.btnDialogAñadir);


        //Establecemos el listener para capturar datos y realizar acción de añadir
        btnDialogAñadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = etTextoItem.getText().toString();
                // tb se puede realizar una validación de campo no vacio...
                list_items.add(item);
                //añadimos el item al final
                adapter.notifyItemInserted(list_items.size()-1);

                //hacemos desplazar la lista de items hasta dicho valor
                rvListadoItems.scrollToPosition(list_items.size()-1);

                //Esta llamada cierra el dialogo
                dialog.dismiss();
            }
        });


        //Esta llamada abre el diálogo
        dialog.show();

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

        if(id == R.id.opcionAscendente){
            //Ascendente
            list_items.sort(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.compareTo(o2);
                }
            });
            estadoOrden = true;
        }
        else if(id == R.id.opcionDescendente){
            //Descendente
            list_items.sort(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return (o1.compareTo(o2))*(-1);
                }
            });
            estadoOrden = false;
        }
        else if(id == R.id.opcionOrdenar){
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
        }else if (id== R.id.opcionGrid){
            if (iconoGridLayout){
                rvListadoItems.setLayoutManager(new GridLayoutManager(this, 2));
                iconoGridLayout = false;
            } else{
                rvListadoItems.setLayoutManager(new LinearLayoutManager(this));
                iconoGridLayout = true;
            }
        }else if (id== R.id.opcionPar){

        }else if (id== R.id.opcionImPar){

        }
        adapter.setList_items(list_items);


        return true;
    }
}