package com.yh.util;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author yh create 2019-6-11 16:04:32
 * 单例模式（饿汉模式）
 * */
@Slf4j
public class RedisUtil {
    private static Jedis jedis = null;

    static{
        init();
    }

    private RedisUtil(){}

    /**
     * 初始化
     * */
    private static void init(){
        jedis = new Jedis("127.0.0.1", 6379);
        log.info("redis连接成功！");
    }

    /**
     * 使用单例模式
     * */
    public static Jedis getJedis(){
        return jedis;
    }

}
