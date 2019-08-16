package com.yh.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yh 2019-6-18 16:34:59
 * */
public class MapUtil extends HashMap {

    private MapUtil(){
        super();
    }

    private MapUtil(int initialCapacity){
        super(initialCapacity);
    }

    public static MapUtil create(){
        return new MapUtil();
    }

    public static MapUtil create(int initialCapacity){
        return new MapUtil(initialCapacity);
    }

    public MapUtil set(String key, String value){
        this.put(key, value);
        return this;
    }
}
