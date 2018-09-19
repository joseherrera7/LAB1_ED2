package com.ed2.joseherrera.lab1_ed2;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MisCompresiones extends AppCompatActivity {
    ListView CompresionesLV;
    ArrayList<String> ListaCompresiones;
    Button VerCompresiones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_mis_compresiones);

            CompresionesLV = (ListView) findViewById(R.id.MisCompresiones_dynamic);
          ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.activity_mis_compresiones, ListaCompresiones);
            CompresionesLV.setAdapter(adapter);
            VerCompresiones = (Button) findViewById(R.id.btnMisCompresiones);

            VerCompresiones.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                }
            });
    }

}
