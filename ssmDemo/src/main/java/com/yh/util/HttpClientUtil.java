package com.yh.util;

import com.alibaba.fastjson.JSON;
import com.yh.pojo.User;
import com.yh.util.exception.YhSimpleException;
import com.yh.util.exception.YhSystemException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.Closeable;
import java.util.Map;

/**
 * @author yh create on 2019/6/28
 * （1）创建CloseHttpClient对象；
 * （2）拼接URL，设置config；
 * （3）创建HttpGet或HttpPost请求；
 * （4）处理返回结果；
 * （5）释放资源。
 */
@Slf4j
public class HttpClientUtil {

    private final static String DEFAULT_CHARSET = "UTF-8";

    /**
     * 无参的GET请求
     */
    public static String httpGet(String url) throws Exception {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String result = null;
        try {
            httpClient = HttpClientBuilder.create().build();
            HttpGet httpGet = new HttpGet(url);
            RequestConfig config = setConfig();
            httpGet.setConfig(config);
            log.info("httpClient-get-begin！");
            long beginTime = System.currentTimeMillis();
            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, DEFAULT_CHARSET);
            int code = response.getStatusLine().getStatusCode();
            if (code == 200) {
                log.info("httpClient-get-success! 耗时{}ms，响应结果；{}", System.currentTimeMillis() - beginTime, result);
            } else {
                log.info("httpClient-get-error!返回响应码：{}, 耗时{}ms，响应结果{}", code, System.currentTimeMillis() - beginTime, result);
                return null;
            }
        } catch (Exception e) {
            log.error("httpClient-get-系统异常：{}", e);
            throw new YhSystemException("系统异常");
        } finally {
            close(response);
            close(httpClient);
        }
        return result;
    }

    /**
     * 有参数的Get请求
     *
     * @param url
     * @param params 参数
     */
    public static String httpGet(String url, Map<String, Object> params) throws Exception {
        url = spellUrlWithParams(url, params);
        return httpGet(url);
    }

    /**
     * 无参的POST请求
     */
    public static String httpPost(String url) throws Exception {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String result = null;
        try {
            log.info("httpClient-post-begin！url：{}", url);
            httpClient = HttpClientBuilder.create().build();
            HttpPost httpPost = new HttpPost(url);
            //设置请求参数
            RequestConfig config = setConfig();
            httpPost.setConfig(config);
            //设置请求格式信息Content-Type : application/json;charset=utf8
            httpPost.setHeader("Content-Type", ContentType.APPLICATION_JSON.toString());
            long beginTime = System.currentTimeMillis();
            //执行post
            response = httpClient.execute(httpPost);
            HttpEntity entry = response.getEntity();
            result = EntityUtils.toString(entry, DEFAULT_CHARSET);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                log.info("httpClient-post-success！耗时{}ms，返回结果：{}", System.currentTimeMillis() - beginTime, result);
            } else {
                log.info("httpClient-post-eror！耗时{}ms，返回结果：{}", System.currentTimeMillis() - beginTime, result);
                return null;
            }
        } catch (Exception e) {
            log.error("httpClient-post-发生异常：{}", e);
            throw new YhSystemException("系统异常！");
        } finally {
            close(response);
            close(httpClient);
        }
        return result;
    }

    /**
     * 有参数的POST（普通参数）
     */
    public static String httpPost(String url, Map<String, Object> params) throws Exception {
        url = spellUrlWithParams(url, params);
        return httpPost(url);
    }

    /**
     * 有参数的POST（对象参数）
     */
    public static String httpPost(String url, Object object) throws Exception {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String result = null;
        try {
            log.info("httpClient-post-begin，url:{}", url);
            httpClient = HttpClientBuilder.create().build();
            //设置请求信息
            HttpPost httpPost = new HttpPost(url);
            String objStr = JSON.toJSONString(object);
            StringEntity entityStr = new StringEntity(objStr, DEFAULT_CHARSET);
            httpPost.setEntity(entityStr);
            httpPost.setHeader("Content-type", ContentType.APPLICATION_JSON.toString());
            long beginTime = System.currentTimeMillis();
            response = httpClient.execute(httpPost);
            HttpEntity httpEntity = response.getEntity();
            result = EntityUtils.toString(httpEntity);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                log.info("httpClient-post-success！耗时{}ms，返回结果：{}", System.currentTimeMillis() - beginTime, result);
            } else {
                log.info("httpClient-post-error！耗时{}ms，返回结果：{}", System.currentTimeMillis() - beginTime, result);
            }
        } catch (Exception e) {
            log.error("httpClient-post-发生异常：{}", e);
            throw new YhSystemException("系统异常！");
        } finally {
            close(response);
            close(httpClient);
        }
        return result;
    }

    /**
     * 拼接url和参数
     */
    public static String spellUrlWithParams(String url, Map<String, Object> params) {
        StringBuffer stringBuffer = new StringBuffer(url);
        try {
            stringBuffer.append("?");
            for (String key : params.keySet()) {
                String value = (String) params.get(key);
                if (value.contains("&")) {
                    throw new YhSimpleException("参数含有特殊字符&");
                }
                if (value.contains("?")) {
                    throw new YhSimpleException("参数含有特殊字符?");
                }
                stringBuffer.append(key).append("=").append(value)
                        .append("&");
            }
            url = new String(stringBuffer.substring(0, stringBuffer.length() - 1).getBytes(), DEFAULT_CHARSET);
            log.info("httpClient-url:{}", url);
        } catch (Exception e) {
            log.info("系统异常：" + e);
        }
        return url;
    }

    /**
     * 设置请求参数
     */
    public static RequestConfig setConfig() {
        RequestConfig config = RequestConfig.custom()
                //设置连接超时时间
                .setConnectTimeout(60000)
                //设置请求超时时间
                .setConnectionRequestTimeout(60000)
                //设置读写超时时间
                .setSocketTimeout(60000)
                //设置是否可以重定向
                .setRedirectsEnabled(true)
                .build();
        return config;
    }

    /**
     * 释放资源
     */
    public static void close(Closeable c) throws Exception {
        try {
            if (c != null) {
                c.close();
            }
        } catch (Exception e) {
            throw new YhSystemException("释放资源失败！");
        }
    }

    public static void main(String[] args) {
        String url = "http://localhost:8080/ssmDemo/user/testHttpPost";
        try {
            User user = new User();
            user.setUsername("yh");
            user.setPassword("123456");
            httpPost(url, user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
