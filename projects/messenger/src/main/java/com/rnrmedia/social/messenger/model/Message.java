package com.rnrmedia.social.messenger.model;

import com.rnrmedia.social.messenger.model.Link;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class Message {
    private long id;
    private String message;
    private Date created;
    private String author;
    private Map<Long, Comment> comments;
    private List<Link> links = new LinkedList<Link>();


    public Message() {

    }

    public Message (long id, String message, Date date, String author) {
        this.id = id;
        this.message = message;
        this.created = date;
        this.author = author;
        this.comments = new HashMap<>();
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

    @XmlTransient
    public Map<Long, Comment> getComments() {
        return this.comments;
    }

    public void setComments(Map<Long, Comment> comments) {
        this.comments = comments;
    }


    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public List<Link> getLinks() {
        return this.links;
    }

    public void addLink(String url, String rel) {
        Link link = new Link(url, rel);
        this.links.add(link);
    }
}
