package com.emailBackEnd.emailBackEnd;

//Singelton adapter to convert the String to array of String separated by $ sign

public class StringAdapter {
    private static StringAdapter stringAdapter;
    private static String s;
    private static boolean isInstantiated = false;

    private StringAdapter(String s){
        this.s = s;
    }
    public String[] getStringArraySeparated(){
        return s.split("\\$");
    }
    public static StringAdapter getInstance(String string){
        if(!isInstantiated) {
            isInstantiated = true;
            return stringAdapter = new StringAdapter(string);
        }
        else{
            stringAdapter.s = string;
            return stringAdapter;
        }
    }
}
