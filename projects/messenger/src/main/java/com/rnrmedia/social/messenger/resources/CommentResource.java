package com.rnrmedia.social.messenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.rnrmedia.social.messenger.model.Comment;
import com.rnrmedia.social.messenger.service.CommentService;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommentResource {

    private CommentService commentservice = new CommentService();

    @GET
    public List<Comment> getAllComments(@PathParam("messageId") long messageId) {
        return commentservice.getAllComments(messageId);
    }

    @POST
    public Comment addCommentToMessage(@PathParam("messageId") long messageId, Comment comment) {
        return commentservice.addComment(messageId, comment);
    }

    @PUT
    @Path("/{commentId}")
    public Comment updateCommentForMessage(@PathParam("messageId") long messageId,
            @PathParam("commentId") long commentId, Comment comment) {
        comment.setId(commentId);
        return commentservice.updateComment(messageId, comment);
    }

    @DELETE
    @Path("/{commentId}")
    public Comment removeComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId) {
        return commentservice.removeComment(messageId, commentId);
    }

    @GET
    @Path("/{commentId}")
    public Comment getComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId) {
        return commentservice.getComment(messageId, commentId);
    }
}
