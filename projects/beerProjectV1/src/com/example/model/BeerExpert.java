package com.example.model;

import java.util.List;
import java.util.ArrayList;

public class BeerExpert {
    public List<String> getBrands(String color) {
        List<String> brands = new ArrayList<String>();
        if(color.equals("amber")) {
            brands.add("Jack Amber");
            brands.add("Red Moose");
        } else {
            brands.add("Jai Pale Ale");
            brands.add("Gout Stout");
        }
        return brands;
    }
}
