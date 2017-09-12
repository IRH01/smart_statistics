package com.hhly.smartdata.service.authentication;

public class ServiceException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    /**

     * 带自定义错误信息的输出

     * @param message

     */

    public ServiceException(String message){

        super(message);

    }

    /**

     * 自定义错误信息和异常抛出

     * @param message

     * @param cause

     */

    public ServiceException(String message,Throwable cause){

        super(message,cause);

    }

    /**

     * 只有异常抛出

     * @param cause

     */

    public ServiceException(Throwable cause){

        super(cause);

    }

}

