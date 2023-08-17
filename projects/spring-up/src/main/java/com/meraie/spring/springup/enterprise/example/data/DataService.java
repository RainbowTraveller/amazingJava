package com.meraie.spring.springup.enterprise.example.data;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataService {
    public List<Integer> getData() {
        return Arrays.asList(10, 20, 30, 40);
    }
}
