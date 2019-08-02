package com.rnrmedia.social.messenger.model;

public class Link {

    private String link;
    private String relation;

    public Link() {

    }

    public Link(String link, String rel) {
        this.link = link;
        this.relation = rel;
    }

    public void setLink( String link ) {
        this.link = link;
    }

    public String getLink() {
        return this.link;
    }

    public String getRelation() {
        return this.relation;
    }

    public void setRelation( String rel ) {
        this.relation = rel;
    }
}

