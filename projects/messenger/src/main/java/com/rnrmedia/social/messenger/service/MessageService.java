package com.rnrmedia.social.messenger.service;

import java.util.Date;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;

import com.rnrmedia.social.messenger.model.Message;
import com.rnrmedia.social.messenger.model.Profile;
import com.rnrmedia.social.messenger.database.DatabaseUtil;

public class MessageService {

    private Map<Long, Message> messages = DatabaseUtil.getMessages();
    private Map<Long, Profile> profiles = DatabaseUtil.getProfiles();

    public MessageService () {
        messages.put(1L, new Message(1L, "Hi There", new Date(), "Milo"));
        messages.put(2L, new Message(2L, "Hello", new Date(), "Udertaker"));
        messages.put(3L, new Message(3L, "Suprabhat", new Date(), "Nalavade Kaka"));
        messages.put(4L, new Message(4L, "Tendlya", new Date(), "Baban"));
        messages.put(5L, new Message(5L, "Gheun Taak", new Date(), "Karktaak"));
        messages.put(6L, new Message(6L, "Oyasuminasay", new Date(), "MisalPav"));
        messages.put(7L, new Message(7L, "Sherlock Holms", new Date(), "Gela Tokawar"));
    }

    public List<Message> getAllMessages() {
        /*
        List<Message> allMessages = new LinkedList<Message>();
        allMessages.add(new Message(1L, "Hi There", new Date(), "Milo"));
        allMessages.add(new Message(2L, "Hello", new Date(), "Udertaker"));
        allMessages.add(new Message(3L, "Suprabhat", new Date(), "Nalavade Kaka"));
        allMessages.add(new Message(4L, "Tendlya", new Date(), "Baban"));
        allMessages.add(new Message(5L, "Gheun Taak", new Date(), "Karktaak"));
        allMessages.add(new Message(6L, "Oyasuminasay", new Date(), "MisalPav"));
        allMessages.add(new Message(7L, "Sherlock Holms", new Date(), "Gela Tokawar"));
        return allMessages;
        */

        return new LinkedList<Message>(messages.values());
    }

    public Message addMessage( Message msg ) {
        msg.setId(messages.size() + 1);
        messages.put(msg.getId(), msg);
        return msg;
    }

    public Message updateMessage( Message msg ) {

        if(msg.getId() <= 0) {
            return null;
        }

        messages.put(msg.getId(), msg);
        return msg;
    }

    public Message getMessage(long id) {
        return messages.get(id);
    }


    public Message deleteMessage(long id) {
        return messages.remove(id);
    }
}
