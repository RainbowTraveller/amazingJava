package com.rnrmedia.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.rnrmedia.rest.MockDate;

@Path("date/{dateString}")
public class DateResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN )
    public String getDate(@PathParam("dateString") MockDate date) {
        return "Works : " + date.toString();
    }

}

