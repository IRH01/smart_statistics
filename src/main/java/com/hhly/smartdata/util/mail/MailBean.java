package com.hhly.smartdata.util.mail;

import java.util.Map;

public class MailBean{
    private String subject;
    private String from;
    private String to;
    private String content;
    private String template;
    private Map<String, Object> data;
    private Integer type;


    public String getSubject(){
        return subject;
    }

    public void setSubject(String subject){
        this.subject = subject;
    }

    public String getFrom(){
        return from;
    }

    public void setFrom(String from){
        this.from = from;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content = content;
    }

    public Integer getType(){
        return type;
    }

    public void setType(Integer type){
        this.type = type;
    }

    public String getTo(){
        return to;
    }

    public void setTo(String to){
        this.to = to;
    }

    public String getTemplate(){
        return template;
    }

    public void setTemplate(String template){
        this.template = template;
    }

    public Map<String, Object> getData(){
        return data;
    }

    public void setData(Map<String, Object> data){
        this.data = data;
    }
}
