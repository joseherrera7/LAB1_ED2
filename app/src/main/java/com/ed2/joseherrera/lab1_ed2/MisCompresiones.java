package com.ed2.joseherrera.lab1_ed2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MisCompresiones extends AppCompatActivity {
    ListView CompresionesLV;
    ArrayList<String> ListaCompresiones;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_compresiones);
        CompresionesLV = (ListView) findViewById(R.id.MisCompresiones_dynamic);
        ArrayAdapter<String> compresionesAdapter = new ArrayAdapter<>(this,R.layout.activity_mis_compresiones,ListaCompresiones );
        CompresionesLV.setAdapter(compresionesAdapter);

    }

}
