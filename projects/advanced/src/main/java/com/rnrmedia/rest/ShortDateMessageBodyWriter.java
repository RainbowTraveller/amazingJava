package com.rnrmedia.rest;

import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import java.util.Calendar;
import java.util.Date;
import java.lang.reflect.Type;
import javax.ws.rs.core.MediaType;
import java.lang.annotation.Annotation;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import java.io.OutputStream;
import java.io.IOException;

@Provider
@Produces("text/shortdate")
public class ShortDateMessageBodyWriter implements MessageBodyWriter<Date>{

    @Override
    public long getSize(Date arg0, Class<?> arg1, Type arg2, Annotation[] arg3, MediaType arg4) {
        return -1;
    }

    @Override
    public boolean isWriteable(Class<?> type, Type arg1, Annotation[] arg2, MediaType arg3) {
        return Date.class.isAssignableFrom(type);
    }

    @Override
    public void writeTo(Date date, Class<?> type, Type t, Annotation[] annotation,
            MediaType mt, MultivaluedMap<String, Object> mm, OutputStream out) throws IOException, WebApplicationException {
        String shortdate = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) + "-"
            +  Calendar.getInstance().get(Calendar.MONTH) + "-"
            + Calendar.getInstance().get(Calendar.YEAR);
        out.write(shortdate.getBytes());
    }
}


