package com.ed2.joseherrera.lab1_ed2.LZW;


import android.widget.Toast;

import com.ed2.joseherrera.lab1_ed2.MainActivity;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

public class LZW {
    private static String File_Input = null;
    private static double MAX_TABLE_SIZE; //Max Table size is based on the bit length input.
    private static String LZWfilename;


    /** Compress a string to a list of output symbols and then pass it for compress file creation.
     * @param Bit_Length //Provided as user input.
     * @param input_string //Filename that is used for encoding.
     * @throws IOException */

    public static void Encode_string(String input_string, double Bit_Length) throws IOException {

        MAX_TABLE_SIZE = Math.pow(2, Bit_Length);

        double table_Size =  255;

        Map<String, Integer> TABLE = new HashMap<String, Integer>();

        for (int i = 0; i < 255 ; i++)
            TABLE.put("" + (char) i, i);

        String initString = "";

        List<Integer> encoded_values = new ArrayList<Integer>();

        for (char symbol : input_string.toCharArray()) {
            String Str_Symbol = initString + symbol;
            if (TABLE.containsKey(Str_Symbol))
                initString = Str_Symbol;
            else {
                encoded_values.add(TABLE.get(initString));

                if(table_Size < MAX_TABLE_SIZE)
                    TABLE.put(Str_Symbol, (int) table_Size++);
                initString = "" + symbol;
            }
        }

        if (!initString.equals(""))
            encoded_values.add(TABLE.get(initString));

        CreateLZWfile(encoded_values);

    }


/*
@param encoded_values , This hold the encoded text.
@throws IOException
*/

    private static void CreateLZWfile(List<Integer> encoded_values) throws IOException {

        BufferedWriter out = null;

        LZWfilename = File_Input.substring(0,File_Input.indexOf(".")) + ".lzw";

        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(LZWfilename),"UTF_16BE")); //The Charset UTF-16BE is used to write as 16-bit compressed file

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Iterator<Integer> Itr = encoded_values.iterator();
            while (Itr.hasNext()) {
                out.write(Itr.next());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        out.flush();
        out.close();
    }

}
