package com.rnrmedia.social.messenger.model;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Message {
    private long id;
    private String message;
    private Date created;
    private String author;


    public Message() {

    }
    public Message (long id, String message, Date date, String author) {
        this.id = id;
        this.message = message;
        this.created = date;
        this.author = author;
    }

    public void setId( long id  ) {
        this.id = id;

    }

    public void setMessage( String message  ) {
        this.message = message;
    }

    public void setCreated( Date date ) {
        this.created = date;

    }

    public void setAuthor( String author ) {
        this.author = author;

    }

    public long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public Date getCreated() {
        return created;
    }

    public String getAuthor() {
        return author;
    }
}
