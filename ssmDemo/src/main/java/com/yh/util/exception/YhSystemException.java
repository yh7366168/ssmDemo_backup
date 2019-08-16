package com.yh.util.exception;

/**
 * 自定义异常--系统异常
 * @author yh 2019-6-18 15:53:44
 * */
public class YhSystemException extends RuntimeException{

    public YhSystemException(){
        super();
    }

    public YhSystemException(String message){
        super(message);
    }

    public YhSystemException(String message, Throwable cause){
        super(message, cause);
    }

    public YhSystemException(Throwable cause){
        super(cause);
    }

    public YhSystemException(String message, Throwable cause,
                             boolean enableSuppression,
                             boolean writableStackTrace){
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
