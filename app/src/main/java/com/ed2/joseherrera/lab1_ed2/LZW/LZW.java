package com.ed2.joseherrera.lab1_ed2.LZW;






import android.content.IntentFilter;
import android.support.design.widget.TabLayout;

import java.io.BufferedReader;

import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.io.Reader;
import java.util.*;

public class LZW {





    /** Compress a string to a list of output symbols and then pass it for compress file creation.
     * @param Bit_Length //Provided as user input.
     * @param input_string //Filename that is used for encoding.
      */

    Map<String, Integer> Table = new HashMap<>();
    Map<Integer, String> newTable = new HashMap<>();

    public String Encode_string(String input_string) {


        String c;
        String k;
        String numbers = "";


        List<Character> charList = new ArrayList<>();

        int value = 1;
        for (char item: input_string.toCharArray()
             ) {
            if (!Table.containsKey(String.valueOf(item))){
                Table.put(String.valueOf(item), value);

                value++;
            }
            charList.add(item);
        }
        String out = "";
        List<String> keys = new ArrayList(Table.keySet());
        List<Integer> values = new ArrayList(Table.values());
        for (int i = 0; i<keys.size(); i++){
            out+= keys.get(i) +"|||" + values.get(i) +"||||";
        }

        for (int i = 0; i<charList.size(); i++) {
            c = String.valueOf(charList.remove(0));
            k = String.valueOf(charList.remove(1));
            String ck = c+k;
            if (Table.containsKey(ck)) {
                c = ck;

            }
            else {
                Table.put(ck, value);
                numbers += String.valueOf(convertToChar(Table.get(c)));
                value++;
            }
        }


        out = out + "|||||" + numbers;
        return  out;
    }

    public String Decode_String(String encodedValues) throws IOException {

        String cN;
        String cV;

        String numbers = "";


        List<String> charList = new ArrayList<>();
        // Divide the file into the map and the encoded string
        String[] parts = encodedValues.split("\\|\\|\\|\\|\\|");
        String originalMap = parts[0];
        String encoded = parts[1];
        // Divide the map into key-value
        String[] partsMap = originalMap.split("\\|\\|\\|\\|");
        for (int i = 0; i<partsMap.length;i++){
            String KeyValue[] = partsMap[i].split("\\|\\|\\|");
            Integer key = Integer.parseInt(KeyValue[1]);
            String value = KeyValue[0];
            newTable.put(key, value);
        }
        for (char item: encoded.toCharArray()
                ) {

            charList.add((converToString(item)));
        }
        int value = newTable.size() + 1;
        String out = "";

        cV = charList.remove(4);
        out += newTable.get(Integer.parseInt(cV));

        for (int i = 0; i<encoded.length()-1; i++) {
            cN = charList.remove(4);
           if (newTable.containsKey(Integer.parseInt(cN))){
                out += newTable.get(Integer.parseInt(cN));
                newTable.put(value, cV+cN);
                cV = cN;
                value++;
           }

        }



        return  out;


    }
/*
@param encoded_values , This hold the encoded text.
@throws IOException
*/

    private Map<String, Integer> sortDiccionary(Map<String, Integer> TABLE ){
        Collection<String> strings = TABLE.keySet();
        Map<String, Integer> newTable= new HashMap<>();
        int value = 1;
        List<String> StringList = new ArrayList<>(strings);
        Collections.sort(StringList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        for (String item:StringList
             ) {
            newTable.put(item, value);
            value++;
        }
        return newTable;
    }
    public char convertToChar(Integer i){
        int num = i.intValue();
        char ch = (char) num;
        return ch;
    }
    public String converToString(char ascii){

        int number=(int) ascii;
        return  String.valueOf(number);

    }

}
