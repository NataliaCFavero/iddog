package com.nataliafavero.iddog.ui.menu;

/**
 * Created by nataliafavero on 07/03/18.
 */

public enum Category {

    HUSKY("Husky", "husky"),
    HOUND("Hound", "hound"),
    PUG("Pug", "pug"),
    LABRADOR("Labrador", "labrador");

    private String name;
    private String cod;

    private Category(String toString, String value) {
        name = toString;
        cod = value;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getCod() {
        return cod;
    }
}
