package com.miraie.webapp.mocking.mockitodemo.business;

import java.util.Arrays;

public class BusinessImpl {

    private DataService service;

    public BusinessImpl (DataService service) {
        this.service = service;
    }

    public Integer findGreatestNumber() {

        Integer largest = null;
        int[] data = service.retrieveAllData();
        if(data != null &&  data.length > 0) {
            largest = Arrays.stream(data).max().getAsInt();
        }
        return largest;

    }

}


interface DataService {
    int[] retrieveAllData();
}
