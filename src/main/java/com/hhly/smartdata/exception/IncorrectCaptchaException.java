package com.hhly.smartdata.exception;

import org.apache.shiro.authc.AuthenticationException;

/**
 * Created by Iritchie.ren
 */
public class IncorrectCaptchaException extends AuthenticationException{

    private static final long serialVersionUID = -5159093935924846183L;

    public IncorrectCaptchaException(){
        super();
    }

    public IncorrectCaptchaException(String message, Throwable cause){
        super(message, cause);
    }

    public IncorrectCaptchaException(String message){
        super(message);
    }

    public IncorrectCaptchaException(Throwable cause){
        super(cause);
    }
}