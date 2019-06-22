package com.rnrmedia.social.messenger.service;

import java.util.Date;
import java.util.List;
import java.util.LinkedList;

import com.rnrmedia.social.messenger.model.Message;

public class MessageService {


    public List<Message> getAllMessages() {
        List<Message> allMessages = new LinkedList<Message>();
        allMessages.add(new Message(1L, "Hi There", new Date(), "Milo"));
        allMessages.add(new Message(2L, "Hello", new Date(), "Udertaker"));
        allMessages.add(new Message(3L, "Suprabhat", new Date(), "Nalavade Kaka"));
        allMessages.add(new Message(4L, "Tendlya", new Date(), "Baban"));
        allMessages.add(new Message(5L, "Gheun Taak", new Date(), "Karktaak"));
        allMessages.add(new Message(6L, "Oyasuminasay", new Date(), "MisalPav"));
        allMessages.add(new Message(7L, "Sherlock Holms", new Date(), "Gela Tokawar"));
        return allMessages;
    }
}
