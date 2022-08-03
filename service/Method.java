package service;

import model.Vehicle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Method<T> {
    public static String getClassName(Object obj){
        String str=obj.getClass().toString();
        str = str.substring(str.lastIndexOf('.') + 1);
        return str;
    }
}
