package com.rnrmedia.social.messenger.model;

import java.util.Date;

public class Comment {
    private long id;
    private Date created;
    private String author;
    private String message;

    public Comment() {

    }

    public Comment(long id, String author, String message) {
        this.id = id;
        this.author = author;
        this.message = message;
        this.created = new Date();
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setDate(Date date) {
        this.created = date;
    }
}
