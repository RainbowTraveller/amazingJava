package com.miraie.webapp.mocking.mockitodemo.business;

import java.util.Arrays;

public class BusinessImpl {

    private DataService service;

    public BusinessImpl (DataService service) {
        this.service = service;
    }

    public int findGreatestNumber() {

        int[] data = service.retrieveAllData();
        int largest = Arrays.stream(data).max().getAsInt();
        return largest;

    }

}


interface DataService {
    int[] retrieveAllData();
}
