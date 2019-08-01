package com.rnrmedia.social.messenger.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.rnrmedia.social.messenger.model.Message;
import com.rnrmedia.social.messenger.resources.bean.MessageFilterBean;
import com.rnrmedia.social.messenger.service.MessageService;

/**
 * Root resource (exposed at "messages" path)
 */
@Path("messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

    MessageService msgService = new MessageService();

    /**
     * Method handling HTTP GET requests. The returned object will be sent to the
     * client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    // public List<Message> getIt(@QueryParam("year") int year, @QueryParam("from")
    // int from, @QueryParam("size") int size) {
    public List<Message> getIt(@BeanParam MessageFilterBean bean) {
        if (bean.getYear() > 0) {
            return msgService.getMessagesForYear(bean.getYear());
        } else if (bean.getFrom() >= 0 && bean.getSize() >= 0) {
            return msgService.getPaginatedMessages(bean.getFrom(), bean.getSize());

        }
        return msgService.getAllMessages();
    }

    @GET
    @Path("/{messageId}")
    public Message getMessage(@PathParam("messageId") long messageId, @Context UriInfo uriInfo) {
        Message message = msgService.getMessage(messageId);
        String uri = uriInfo.getBaseUriBuilder()
            .path(MessageResource.class)
            .path(Long.toString(message.getId()))
            .build()
            .toString();
        message.addLink(uri, "self");
        return message;
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
    public Response addMessage(Message message, @Context UriInfo uriInfo) throws URISyntaxException {
        Message newMessage = msgService.addMessage(message);
        String newId = String.valueOf(newMessage.getId());
        URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
        return Response.created(uri).entity(newMessage).build();
    }

    @Path("/{messageId}/comments")
    public CommentResource redirectToCommentResource() {
        return new CommentResource();
    }
}
