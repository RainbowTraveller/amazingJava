package com.rnrmedia.social.messenger.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Profile {

    private long id;
    private String profileName;
    private String firstName;
    private String lastName;
    private Date created;

    public Profile() {

    }

    public Profile( long id, String profileName, String firstName, String lastName ) {
        this.id = id;
        this.profileName = profileName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.created = new Date();
    }


    public void setProfileName( String profileName ) {
        this.profileName = profileName;
    }

    public void setId( long id ) {
        this.id = id;
    }

    public void setFirstName( String firstName ) {
        this.firstName = firstName;
    }

    public void setLastName( String lastName ) {
        this.lastName = lastName;
    }

    public void setCreated( Date created ) {
        this.created = created;
    }

    public long getId() {
        return id;
    }

    public String getProfileName() {
        return this.profileName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return  this.lastName;
    }

    public Date getCreated() {
        return  this.created;
    }
}
