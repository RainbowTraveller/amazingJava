package com.meraie.spring.springup.enterprise.example;

import org.springframework.stereotype.Component;

@Component
public class WebController {
    public long returnValueFromBusinessService() {
        return 200L;
    }
}
