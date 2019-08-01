package com.rnrmedia.social.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import com.rnrmedia.social.messenger.model.ErrorMessage;

@Provider
public class DataNotFoundExceptionMapper implements  ExceptionMapper<DataNotFoundException> {
    @Override
    public Response toResponse(DataNotFoundException ex) {
        ErrorMessage emsg = new ErrorMessage(404, ex.getMessage(), "https://stackoverflow.com/");
        return Response.status(Status.NOT_FOUND).entity(emsg).build();
    }

}

