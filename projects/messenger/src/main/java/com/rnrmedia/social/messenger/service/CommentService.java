package com.rnrmedia.social.messenger.service;

import java.util.LinkedList;
import java.util.Map;
import java.util.List;

import com.rnrmedia.social.messenger.database.DatabaseUtil;
import com.rnrmedia.social.messenger.model.Comment;
import com.rnrmedia.social.messenger.model.Message;

public class CommentService {
    private Map<Long, Message> messages = DatabaseUtil.getMessages();

    public List<Comment> getAllComments(long messageId) {
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        return new LinkedList<Comment>(comments.values());
    }

    public Comment getComment(long messageId, long commentId) {
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        return comments.get(commentId);
    }

    public Comment addComment(long messageId, Comment comment) {
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        comment.setId(comments.size() + 1);
        comments.put(comment.getId(), comment);
        return comment;
    }

    public Comment updateComment(long messageId, Comment comment) {
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        if(comment.getId() <= 0) {
            return null;
        }
        comments.put(comment.getId(), comment);
        return comment;
    }

    public Comment removeComment(long messageId, long commentId) {
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        return comments.remove(commentId);
    }
}

