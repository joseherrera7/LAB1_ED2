package com.ed2.joseherrera.lab1_ed2;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MisCompresiones extends AppCompatActivity {
    ListView CompresionesLV;
    ArrayList<String> ListaCompresiones = new ArrayList<>();
    Button VerCompresiones;
    MainActivity nueva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_mis_compresiones);
            String bitacoraFile= getIntent().getExtras().getString("parametro");
            CompresionesLV = (ListView) findViewById(R.id.MisCompresiones_dynamic);

            try {
                muestraContenido(bitacoraFile);
            }
            catch (Exception e){

            }

            ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,ListaCompresiones);
           CompresionesLV.setAdapter(adapter);

            CompresionesLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(parent.getContext(), "Selecciona: " + parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT);
                }
            });
    }
    void muestraContenido(String archivo) throws FileNotFoundException, IOException {
        String cadena;
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        while((cadena = b.readLine())!=null) {
            ListaCompresiones.add(cadena);
        }
        b.close();
    }

}
