package com.meraie.spring.springup.enterprise.example.web;

import com.meraie.spring.springup.enterprise.example.business.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class WebController {
    @Autowired
    private BusinessService businessService;
    public long returnValueFromBusinessService() {
        return businessService.calculateSum();
    }
}

