package com.ed2.joseherrera.lab1_ed2;

import android.Manifest;
import android.content.Context;
import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import  android.content.Intent;
import android.view.View;
import android.widget.Button;

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

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Scanner;
import android.view.View.OnClickListener;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {
private EditText nombre;
private EditText ruta;
    private EditText nombredesc;
    private EditText rutadesc;

    private static final int SOLICITUD_PERMISO_CALL_PHONE = 1;

    private Button btnCargarArchivo;
    private Button Descomprimir; Button buscarArchivo, comprimir; TextView contenido;
    String entry;
    String prueba="";
    String nArchivo = "data.txt";
    Context ctx = this;
    FileOutputStream fos;
    FileInputStream fis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnCompresiones = (Button) findViewById(R.id.btnMisCompresiones);
        buscarArchivo = (Button)findViewById(R.id.buttonBuscarArchivo);
        contenido = (TextView)findViewById(R.id.textviewBuscarArchivo);

        buscarArchivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento = new Intent()
                        .addCategory(Intent.CATEGORY_OPENABLE)
                        .setType("*/*")
                        .setAction(Intent.ACTION_OPEN_DOCUMENT);

                startActivityForResult(Intent.createChooser(intento, "Seleccione un archivo"), 123);
            }
        });

        btnCompresiones.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento =new Intent(MainActivity.this,MisCompresiones.class);
                startActivity(intento);
            }
        });
        if (ActivityCompat.checkSelfPermission(this, permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {


            Toast.makeText(this, "1 Permiso Concedido", Toast.LENGTH_SHORT).show();

        } else {


            explicarUsoPermiso();
            solicitarPermisoHacerLlamada();
        }

       String FILENAME = "data.txt";
        String string = "Llueve en este poema\n" +
                "Eduardo Carranza.\n" +
                "Llueve. La tarde es una\n" +
                "hoja de niebla. Llueve.\n" +
                "La tarde está mojada\n" +
                "de tu misma tristeza.\n" +
                "A veces viene el aire\n" +
                "con su canción. A veces\n" +
                "Siento el alma apretada\n" +
                "contra tu voz ausente.\n" +
                "Llueve. Y estoy pensando\n" +
                "en ti. Y estoy soñando.\n" +
                "Nadie vendrá esta tarde\n" +
                "a mi dolor cerrado.\n" +
                "Nadie. Solo tu ausencia\n" +
                "que me duele en las horas.\n" +
                "Mañana tu presencia regresará en la rosa.\n" +
                "Yo pienso cae la lluvia\n" +
                "nunca como las frutas.\n" +
                "Niña como las frutas,\n" +
                "grata como una fiesta\n" +
                "hoy esta atardeciendo\n" +
                "tu nombre en mi poema.\n" +
                "A veces viene el agua\n" +
                "a mirar la ventana\n" +
                "Y tú no estás\n" +
                "A veces te presiento cercana.\n" +
                "Humildemente vuelve\n" +
                "tu despedida triste.\n" +
                "Humildemente y todo\n" +
                "humilde: los jazmines\n" +
                "los rosales del huerto\n" +
                "y mi llanto en declive.\n" +
                "Oh, corazón ausente:\n" +
                "qué grande es ser humilde.";


        try {

                fos = openFileOutput(FILENAME, Context.MODE_WORLD_READABLE);

            fos.write(string.getBytes());



            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        nombre = (EditText) findViewById(R.id.nombre);
        ruta = (EditText) findViewById(R.id.ruta);
        nombredesc= (EditText) findViewById(R.id.nombredesco);
        rutadesc= (EditText) findViewById(R.id.rutadescomp);
        Descomprimir=(Button) findViewById(R.id.descomprimir);
        final String entrada="hjdgashdgasdhsajg ashdjgsadhasgdjhs hgdasjhdgsajhdgsa jfdsajgdgsajhd hgsajdgdhsgd \n" +
                "hdgsahdgasjhdgsajgdh\n" +
                "khgshdgsahdgskdhkj jhsgdsahdg" +
                "dhsgdjsgad" +
                "kjhjkhjk";
        btnCargarArchivo =  (Button) findViewById(R.id.btnCargarDatos);
        btnCargarArchivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                entry = leerArchivo();
                Huffman huffi=new Huffman(entry);
                huffi.ejecutarHuffman();
                ecribirhuffman( huffi.escribirbinario(entry));
            }
        });


        Descomprimir.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Huffman huffi=new Huffman("a|||77|||||t|||40|||||M|||1|||||ú|||1|||||O|||1|||||é|||1|||||z|||5|||||y|||5|||||L|||6|||||c|||21|||||n|||42|||||s|||42|||||o|||42|||||m|||22|||||j|||3|||||ñ|||3|||||N|||3|||||f|||3|||||h|||7|||||p|||8|||||e|||101||||| |||111|||||d|||30|||||u|||31|||||i|||31|||||l|||32|||||á|||4|||||A|||4|||||Y|||4|||||g|||4|||||b|||2|||||ó|||2|||||S|||2|||||q|||2|||||,|||2|||||H|||2|||||:|||2|||||E|||1|||||C|||1|||||\n" +
                        "|||34|||||r|||35|||||v|||17|||||.|||20|||||ÈC0Zv\u009DkP%Ç\u0088a9¸O(`á\u000F¼Ü\u0001d!\u0098,\u0005\u0093\u00ADá;N\u0091¯\u008A\fÃôv¬¬nN\u0002ÈC0X\u0002ÉÖð\u009D§Z>£0üüGkL¢Z\u008FZ\u0012Ö¼Ü\u0001\u000F ^\u001D Jí¦EÊ\u0016,Mª2Ç¼\u0014m`!ä\u000BÃ¡\n" +
                        "Jïe2.Hõà\u0005ï?\u0016&ô=i\u00903\u0099sS½`\u000B!\fÁ`!Ôëg*\u0080î½\u009EDíi@C©ÖÎUS\n" +
                        "ìò ¡yÊÐ.Î\u0011ôëzÞ\u0013±u\u0012£É$+\f!ç\u0090\u0005\u000BÎV\u0002\u001AÉ%i\u00975;Áx\u0086\u0086j-\u001CÌ\u008D;Dô È} ,ÿ\u0086÷Ö\u0099@\u000B§x/Ac\u0081uÂ>\u009D¢t\u0019® \u0087e@+º\u0095\u008EÑ:!\f\tx¬×\u008E±1\u0094OJ\u0010&ß@\n" +
                        "\u0014°Ýbc(\u009E\u0094 M¾\u0086b\u001C\u000FÞ±1\u0094kê\u0010®·\u008A\fåN·¯¼'x+³È´Êæ#p´íDª\u0004¸ð\u0004<\u0081xt\u0081+¶\u0099\u0017\u001C7\u0017Q(xQ:\u0005Þûâ\u001D[\u009EW)Ö\u008FÐ\u0087\u0090/\u000E\u0096µ .\u008Aïea\u0087\u001Eø\u0002\u0019\u001A%!Ü]ë@\u0099\u0090\u0016-2;¨\få?Z\u0012Ö°\u000424JC¸»Ö²\u00AD\u0093È \u009A%!ØÅ\u0012iCüÑ-º\u0011&\u0090f¹\u001D#²(&aÙ\u0016U\u0012¢\u0013ÞÊv\u008EðB\u0082À\u0016v\bÍbCó\n" +
                        "\u00ADsS½c\u0011\n" +
                        "\n" +
                        "\u009CC\u0081öv\u009D*aPM\u0012\u0090ì ");
                huffi.escribirdescomp("a|||77|||||t|||40|||||M|||1|||||ú|||1|||||O|||1|||||é|||1|||||z|||5|||||y|||5|||||L|||6|||||c|||21|||||n|||42|||||s|||42|||||o|||42|||||m|||22|||||j|||3|||||ñ|||3|||||N|||3|||||f|||3|||||h|||7|||||p|||8|||||e|||101||||| |||111|||||d|||30|||||u|||31|||||i|||31|||||l|||32|||||á|||4|||||A|||4|||||Y|||4|||||g|||4|||||b|||2|||||ó|||2|||||S|||2|||||q|||2|||||,|||2|||||H|||2|||||:|||2|||||E|||1|||||C|||1|||||\n" +
                        "|||34|||||r|||35|||||v|||17|||||.|||20|||||ÈC0Zv\u009DkP%Ç\u0088a9¸O(`á\u000F¼Ü\u0001d!\u0098,\u0005\u0093\u00ADá;N\u0091¯\u008A\fÃôv¬¬nN\u0002ÈC0X\u0002ÉÖð\u009D§Z>£0üüGkL¢Z\u008FZ\u0012Ö¼Ü\u0001\u000F ^\u001D Jí¦EÊ\u0016,Mª2Ç¼\u0014m`!ä\u000BÃ¡\n" +
                        "Jïe2.Hõà\u0005ï?\u0016&ô=i\u00903\u0099sS½`\u000B!\fÁ`!Ôëg*\u0080î½\u009EDíi@C©ÖÎUS\n" +
                        "ìò ¡yÊÐ.Î\u0011ôëzÞ\u0013±u\u0012£É$+\f!ç\u0090\u0005\u000BÎV\u0002\u001AÉ%i\u00975;Áx\u0086\u0086j-\u001CÌ\u008D;Dô È} ,ÿ\u0086÷Ö\u0099@\u000B§x/Ac\u0081uÂ>\u009D¢t\u0019® \u0087e@+º\u0095\u008EÑ:!\f\tx¬×\u008E±1\u0094OJ\u0010&ß@\n" +
                        "\u0014°Ýbc(\u009E\u0094 M¾\u0086b\u001C\u000FÞ±1\u0094kê\u0010®·\u008A\fåN·¯¼'x+³È´Êæ#p´íDª\u0004¸ð\u0004<\u0081xt\u0081+¶\u0099\u0017\u001C7\u0017Q(xQ:\u0005Þûâ\u001D[\u009EW)Ö\u008FÐ\u0087\u0090/\u000E\u0096µ .\u008Aïea\u0087\u001Eø\u0002\u0019\u001A%!Ü]ë@\u0099\u0090\u0016-2;¨\få?Z\u0012Ö°\u000424JC¸»Ö²\u00AD\u0093È \u009A%!ØÅ\u0012iCüÑ-º\u0011&\u0090f¹\u001D#²(&aÙ\u0016U\u0012¢\u0013ÞÊv\u008EðB\u0082À\u0016v\bÍbCó\n" +
                        "\u00ADsS½c\u0011\n" +
                        "\n" +
                        "\u009CC\u0081öv\u009D*aPM\u0012\u0090ì ");
            }
        });
       // ArrayList<Nodo> letras=new ArrayList<Nodo>();







    }
    public String leerArchivo(){
        FileInputStream fis;
        String content = "";
        try {
            fis = openFileInput(nArchivo);
            byte[] input = new byte[fis.available()];
            while (fis.read(input) != -1) {
            }
            content += new String(input);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return content;
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
    Uri archivo;
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123 && resultCode == RESULT_OK){
            archivo = data.getData();
            Toast.makeText(this, archivo.toString(), Toast.LENGTH_LONG).show();
            Toast.makeText(this, archivo.getPath(), Toast.LENGTH_LONG).show();
            try{
                contenido.setText(readTextFromUri(archivo));
            }catch (IOException e){
                Toast.makeText(this, "Hubo un error al obtner el texto del archivo", Toast.LENGTH_LONG).show();
            }
        }
    }

    private String readTextFromUri(Uri uri) throws IOException{
        InputStream inputStream = getContentResolver().openInputStream(uri);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String linea;
        while ((linea = reader.readLine()) != null){
            stringBuilder.append(linea);
        }
        inputStream.close();
        reader.close();
        return stringBuilder.toString();
    }

}

