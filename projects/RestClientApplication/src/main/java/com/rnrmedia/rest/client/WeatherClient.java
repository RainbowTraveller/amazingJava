package com.rnrmedia.rest.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

public class WeatherClient {
    public static void main(String[] args) {
         Client client = ClientBuilder.newClient();
         Response response = client.target("api.openweathermap.org/data/2.5/weather?id=2172797").request().get();
         String str = response.readEntity(String.class);
         System.out.println("Response : " + str);
    }
}


