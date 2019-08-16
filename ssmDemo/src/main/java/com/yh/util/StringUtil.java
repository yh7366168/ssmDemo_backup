package com.yh.util;

public class StringUtil {

    public static String lowerFirstString(String str){
        return str.substring(0,1).toLowerCase() + str.substring(1);
    }

    public static void main(String[] args) {
        System.out.println( StringUtil.lowerFirstString("MenuDao") );
    }


}
