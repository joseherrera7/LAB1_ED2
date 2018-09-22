package com.ed2.joseherrera.lab1_ed2.LZW;






import java.io.BufferedReader;

import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.io.Reader;
import java.util.*;

public class LZW {

    private static double MAX_TABLE_SIZE; //Max Table size is based on the bit length input.



    /** Compress a string to a list of output symbols and then pass it for compress file creation.
     * @param Bit_Length //Provided as user input.
     * @param input_string //Filename that is used for encoding.
      */

    public List<Integer> Encode_string(String input_string, double Bit_Length) {

        MAX_TABLE_SIZE = Math.pow(2, Bit_Length);

        double table_Size =  255;

        Map<String, Integer> TABLE = new HashMap<>();

        for (int i = 0; i < 255 ; i++)
            TABLE.put("" + (char) i, i);

        String initString = "";

        List<Integer> encoded_values = new ArrayList<>();

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


        return encoded_values;

    }

    public StringBuffer Decode_String(String encodedValues, double bit_Length) throws IOException {


        MAX_TABLE_SIZE = Math.pow(2, bit_Length);


        List<Integer> get_compress_values = new ArrayList<>();
        int table_Size = 255;


        Map<Integer, String> TABLE = new HashMap<>();
        for (int i = 0; i < 255; i++)
            TABLE.put(i, "" + (char) i);


        StringBuffer decoded_values = new StringBuffer(encodedValues);

        String get_value_from_table = null;
        for (int check_key : get_compress_values) {

            if (TABLE.containsKey(check_key))
                get_value_from_table = TABLE.get(check_key);
            else if (check_key == table_Size)
                get_value_from_table = encodedValues + encodedValues.charAt(0);

            decoded_values.append(get_value_from_table);

            if(table_Size < MAX_TABLE_SIZE )
                TABLE.put(table_Size++, encodedValues + get_value_from_table.charAt(0));

            encodedValues = get_value_from_table;
        }

        return decoded_values;



    }
/*
@param encoded_values , This hold the encoded text.
@throws IOException
*/



}
