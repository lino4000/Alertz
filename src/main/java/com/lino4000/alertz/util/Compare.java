package com.lino4000.alertz.util;

public class Compare {

    public static Boolean notNull(Object... args){
        for (Object arg : args) {
            if (arg == null) {
                return false;
            }
        }
        return true;
    }

    public static Boolean anyNull(Object... args){
        for (Object arg : args) {
            if (arg == null) {
                return true;
            }
        }
        return false;
    }
    
}
