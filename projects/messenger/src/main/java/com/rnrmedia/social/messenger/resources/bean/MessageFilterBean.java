package com.rnrmedia.social.messenger.resources.bean;

import javax.ws.rs.QueryParam;

public class MessageFilterBean {
    private @QueryParam("year") int year;
    private @QueryParam("from") int from;
    private @QueryParam("size") int size;


    public void setYear(int year) {
        this.year = year;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getYear(){
        return year;
    }

    public int getFrom(){
        return from;
    }

    public int getSize(){
        return size;
    }
}
