package com.rnrmedia.rest;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/{pathParam}")
public class MyResource {

    @PathParam("pathparam") private String path;
    @QueryParam("queryparam") private String query;


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String testMethod() {
        return "Works : with path param  " + path + " and query param : "  + query;
    }
}

