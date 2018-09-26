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


        String c = "";
        String k = "";
        String numbers = "";


        List<Character> charList = new ArrayList<>();

        int value = 1;
        char[] input = input_string.toCharArray();

        for (char item: input
             ) {

            if (!Table.containsKey(String.valueOf(item))){
                Table.put(String.valueOf(item), value);

                value++;
            }
            charList.add(item);
        }

        String out = "";
        Table = sortDiccionary(Table);
        List<String> keys = new ArrayList(Table.keySet());
        List<Integer> values = new ArrayList(Table.values());
        for (int i = 0; i<keys.size(); i++){
            out+= keys.get(i) +"|||" + values.get(i) +"||||";
        }
        int size = charList.size();
        boolean Activated = false;
        for (int i = 0; i<size; i++) {
            if (!Activated || i ==0){
            c = String.valueOf(charList.get(i));}
            k = "";
            if (!(i==size-1)){
            k = String.valueOf(charList.get(i+1));}
            String ck = c+k;
            if (Table.containsKey(ck)) {
                c = ck;
                Activated = true;
                if (i == size-1){
                    numbers += String.valueOf((char)(int)(Table.get(c)));
                }
            }
            else {
                Table.put(ck, value);
                numbers += String.valueOf((char)(int)(Table.get(c)));
                value++;
                Activated = false;
            }
        }


        out = out + numbers;
        return  out;
    }
    public static Object  getKeyFromValue(Map hm, Object value) {
        for (Object o : hm.keySet()) {
            if (hm.get(o).equals(value)) {
                return o;
            }
        }
        return null;
    }

    public String Decode_String(String encodedValues) throws IOException {
        String cN;
        String cV;
        List<String> charList = new ArrayList<>();
        // Divide the file into the map and the encoded string
        String encoded;
        String KeyValue[] = null;
        // Divide the map into key-value
        String[] partsMap = encodedValues.split("\\|\\|\\|\\|");
        encoded = partsMap[partsMap.length - 1];
        for (String item: partsMap
             ) {
            KeyValue = item.split("\\|\\|\\|");
            if (!(KeyValue.length == 1)){
            Integer key = Integer.parseInt(KeyValue[1]);
            String value = KeyValue[0];
            newTable.put(key, value);}
        }{
        }
        //newTable = sortNewDiccionary(newTable);
        for (char item: encoded.toCharArray()
                ) {

            charList.add((converToString(item)));
        }
        int value = newTable.size() + 1;
        String out = "";

        cV = charList.remove(0);
        out += newTable.get(Integer.parseInt(cV));
        while(!charList.isEmpty()) {
            cN = charList.remove(0);
            if (newTable.containsKey(Integer.parseInt(cN))){
                out += newTable.get(Integer.parseInt(cN));
                String entrada = newTable.get(Integer.valueOf(cV)) + newTable.get(Integer.valueOf(cN)).charAt(0);
                newTable.put(value, entrada);
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
        for (int i = 1; i <= StringList.size(); i++){
            newTable.put(StringList.get(i-1),i);
        }
        return newTable;
    }
    private Map<Integer, String> sortNewDiccionary(Map<Integer, String> TABLE ){
        Collection<String> strings = TABLE.values();
        Map<Integer, String> newTable= new HashMap<>();
        int value = 1;
        List<String> StringList = new ArrayList<>(strings);
        Collections.sort(StringList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        for (int i = 1; i <= StringList.size(); i++){
            newTable.put(i, StringList.get(i-1));
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
