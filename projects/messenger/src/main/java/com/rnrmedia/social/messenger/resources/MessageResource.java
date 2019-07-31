package com.rnrmedia.social.messenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.rnrmedia.social.messenger.service.MessageService;
import com.rnrmedia.social.messenger.model.Message;

/**
 * Root resource (exposed at "messages" path)
 */
@Path("messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

    MessageService msgService = new MessageService();
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    public List<Message> getIt(@QueryParam("year") int year, @QueryParam("from") int from, @QueryParam("size") int size) {
        if(year > 0) {
            return msgService.getMessagesForYear(year);
        } else if (from >=0 && size >= 0) {
            return msgService.getPaginatedMessages(from, size);

        }
        return msgService.getAllMessages();
    }

    @GET
    @Path("/{messageId}")
    public Message getMessage(@PathParam("messageId") long messageId) {
        return msgService.getMessage(messageId);
    }


    @PUT
    @Path("/{messageId}")
    public Message updateMessage(@PathParam("messageId") long messageId, Message message) {
        message.setId(messageId);
        return msgService.updateMessage(message);
    }

    @DELETE
    @Path("/{messageId}")
    public void deleteMessage(@PathParam("messageId") long messageId) {
        msgService.deleteMessage(messageId);
    }



    @POST
    public Message  addMessage(Message message) {
        return msgService.addMessage(message);
    }
 }
