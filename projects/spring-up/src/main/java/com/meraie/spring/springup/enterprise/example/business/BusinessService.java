package com.meraie.spring.springup.enterprise.example.business;

import com.meraie.spring.springup.enterprise.example.data.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BusinessService {
    /*
    This is an example of the field based dependency injection
    There is not constructor or a setter method
    This DI uses reflection
     */
    @Autowired
    private DataService dataService;
/*
    This is setter injection
    Notice : The setter method has the annotation @Autowired
    and not the variable

    @Autowired
    public void setDataService(DataService dataService) {
        this.dataService = dataService;
    }
*/
    public long calculateSum() {
        List<Integer> data = dataService.getData();
        return data.stream().reduce(Integer::sum).get();
    }
}
