package com.ed2.joseherrera.lab1_ed2;

import android.Manifest;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import  android.content.Intent;
import com.ed2.joseherrera.lab1_ed2.Huffman.Huffman;
import com.ed2.joseherrera.lab1_ed2.Huffman.Nodo;
import android.Manifest.*;
import java.io.OutputStreamWriter;
import java.io.*;
import java.security.acl.*;
import android.os.Environment;
import android.support.v4.content.*;
import android.support.v4.app.*;
import android.content.pm.*;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
private EditText nombre;
private EditText ruta;
private Button comprimir;
    private static final int SOLICITUD_PERMISO_CALL_PHONE = 1;
 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


     if (ActivityCompat.checkSelfPermission(this, permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {


         Toast.makeText(this, "1 Permiso Concedido", Toast.LENGTH_SHORT).show();

     } else {


         explicarUsoPermiso();
         solicitarPermisoHacerLlamada();
     }


        nombre = (EditText) findViewById(R.id.nombre);
        ruta = (EditText) findViewById(R.id.ruta);
        comprimir=(Button)findViewById(R.id.button);
        final String entrada="hjdgashdgasdhsajg ashdjgsadhasgdjhs hgdasjhdgsajhdgsa jfdsajgdgsajhd hgsajdgdhsgd \n" +
                "hdgsahdgasjhdgsajgdh\n" +
                "khgshdgsahdgskdhkj jhsgdsahdg" +
                "dhsgdjsgad" +
                "kjhjkhjk";

String prueba="";
      /*  Nodo letra1=new Nodo();
        letra1.setPorcentaje(0.236);
        letras.add(letra1);

        Nodo letra2=new Nodo();
        letra2.setPorcentaje(0.963);
        letras.add(letra2);

        Nodo letra3=new Nodo();
        letra3.setPorcentaje(0.321);
        letras.add(letra3);

        Nodo letra4=new Nodo();
        letra4.setPorcentaje(0.896);
        letras.add(letra4);

        Nodo letra5=new Nodo();
        letra5.setPorcentaje(0.520);
        letras.add(letra5);

        Nodo letra6=new Nodo();
        letra6.setPorcentaje(0.210);
        letras.add(letra6);

        Nodo letra7=new Nodo();
        letra7.setPorcentaje(0.120);
        letras.add(letra7);

        Nodo letra8=new Nodo();
        letra8.setPorcentaje(0.239);
        letras.add(letra8);

        Nodo letra9=new Nodo();
        letra9.setPorcentaje(0.520);
        letras.add(letra9);

        Nodo letra10=new Nodo();
        letra10.setPorcentaje(0.100);
        letras.add(letra10);

        Nodo letra11=new Nodo();
        letra11.setPorcentaje(0.756);
        letras.add(letra11);

        Nodo letra12=new Nodo();
        letra12.setPorcentaje(0.963);
        letras.add(letra12);

        Nodo letra13=new Nodo();
        letra13.setPorcentaje(0.211);
        letras.add(letra13);

        Nodo letra14=new Nodo();
        letra14.setPorcentaje(0.236);
        letras.add(letra14);*/

      comprimir.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {





              Huffman huffi=new Huffman(entrada);
              huffi.ejecutarHuffman();
              ecribirhuffman( huffi.escribirbinario(entrada));
          }
      });


    }

    private void explicarUsoPermiso() {


        //Este IF es necesario para saber si el usuario ha marcado o no la casilla [] No volver a preguntar
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(this, "Se necesita del almacenamiento para guardar su archivo comprimido", Toast.LENGTH_SHORT).show();

        }
    }


    private void solicitarPermisoHacerLlamada() {


        //Pedimos el permiso o los permisos con un cuadro de dialogo del sistema
        ActivityCompat.requestPermissions(this,
                new String[]{permission.WRITE_EXTERNAL_STORAGE},
                SOLICITUD_PERMISO_CALL_PHONE);

        Toast.makeText(this, "Porfavor concender permisos para almacenar en memoria", Toast.LENGTH_SHORT).show();


    }

    public void ecribirhuffman(String texto){
        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + ruta.getText());
          myDir.mkdirs();
        String fname = nombre.getText() + ".huff";
        File file = new File(myDir, fname);
        if(file.exists()) {
          file.delete();
        }

        try
        {


            FileOutputStream stream = new FileOutputStream(file);
            stream.write(texto.getBytes());
            stream.flush();
            stream.close();
        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error al escribir fichero en la memoria interna "+ex.getMessage());
        }
    }

    public void leerhuffman(String texto){

        try
        {
            OutputStreamWriter fout=
                    new OutputStreamWriter(
                            openFileOutput("archivito.txt", Context.MODE_WORLD_READABLE ));

            fout.write(texto);
            fout.close();
        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error al escribir fichero en la memoria interna");
        }
    }
}
