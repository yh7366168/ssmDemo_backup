package com.yh.util;

import java.util.UUID;

public class UUIDUtil {

    /**
     * 获取一个随机的UUID
     * */
    public static String newUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

}
