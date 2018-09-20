package com.ed2.joseherrera.lab1_ed2.avldictionary;

public class SortableCharacter implements Sortable {
    protected Character s;

    public SortableCharacter(Character x) {
        s = x;
    } // SortableString constructor

    public int compareTo(Sortable other) {
        return s.compareTo(((SortableCharacter) other).s);
    } // compareTo method

    public Character toCharacter() {
        return s;
    } // toString method
}
