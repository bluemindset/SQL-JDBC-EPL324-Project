package com.horses.dbmanage;

/**
 * Created by tomis on 27/11/2017.
 */
public class test {

    public static void main(String args[]) {
        String tokens = "asdd\t \t   \t asdasda";
        String[] strings = tokens.split("\t");
        System.out.println(strings.length);
    }
}
