package com.miraie.springboot.startup;

public class Course {

    private int id;
    private String name;
    private String author;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public Course(int id, String name, String author) {
        super();
        this.id = id;
        this.name = name;
        this.author = author;
    }
}
