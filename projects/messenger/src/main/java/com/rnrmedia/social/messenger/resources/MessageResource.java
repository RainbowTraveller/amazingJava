package com.rnrmedia.social.messenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.rnrmedia.social.messenger.service.MessageService;
import com.rnrmedia.social.messenger.model.Message;

/**
 * Root resource (exposed at "messages" path)
 */
@Path("messages")
public class MessageResource {

    MessageService msgService = new MessageService();
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    //@Produces(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Message> getIt() {
        return msgService.getAllMessages();
    }

    @GET
    @Path("/{messageId}")
    //@Produces(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_JSON)
    public Message getMessage(@PathParam("messageId") long messageId) {
        return msgService.getMessage(messageId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Message  addMessage(Message message) {
        return msgService.addMessage(message);
    }
 }
