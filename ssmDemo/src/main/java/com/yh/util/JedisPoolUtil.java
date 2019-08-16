package com.yh.util;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author yh create 2019年6月11日20:39:19
 * 使用Redis连接池
 * */
@Slf4j
public class JedisPoolUtil {

    private final static String REDIS_HOST = "127.0.0.1";
    private final static int REDIS_PORT = 6379;
    private static JedisPool pool = null;

    static {
        initPool();
    }

    private JedisPoolUtil(){}

    private static void initPool(){
        JedisPoolConfig config = new JedisPoolConfig();
        //设置最大连接数
        config.setMaxTotal(500);
        //设置空闲连接数
        config.setMaxIdle(30);
        //设置最大等待时间
        config.setMaxWaitMillis(100*1000);
        //检查连接的可用性，Jedis实例
        config.setTestOnBorrow(true);
        //设置JedisPool是否可用
        config.setTestOnReturn(true);

        pool = new JedisPool(config, REDIS_HOST, REDIS_PORT);
    }

    /**
     * 获取一个连接
     * */
    public static Jedis getOnePoolJedis(){
        Jedis jedis = pool.getResource();
        return jedis;
    }

    /**
     * 释放连接
     * */
    public static void jedisClose(Jedis jedis){
        if(jedis != null){
            jedis.close();
        }
    }

}
