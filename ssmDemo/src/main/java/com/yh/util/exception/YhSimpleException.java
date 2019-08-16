package com.yh.util.exception;

/**
 * 自定义异常
 * @author yh 2019-6-18 15:44:02
 * */
public class YhSimpleException extends RuntimeException{

    public YhSimpleException(){
        super();
    }

    public YhSimpleException(String message){
        super(message);
    }

    public YhSimpleException(String message, Throwable cause){
        super(message, cause);
    }

    public YhSimpleException(Throwable cause){
        super(cause);
    }

    protected YhSimpleException(String message, Throwable cause,
                                boolean enableSuppression,
                                boolean writableStackTrace){
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
