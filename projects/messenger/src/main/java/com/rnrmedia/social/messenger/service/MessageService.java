package com.rnrmedia.social.messenger.service;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.rnrmedia.social.messenger.database.DatabaseUtil;
import com.rnrmedia.social.messenger.model.Message;
import com.rnrmedia.social.messenger.exception.DataNotFoundException;

public class MessageService {

    private Map<Long, Message> messages = DatabaseUtil.getMessages();

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

    public List<Message> getMessagesForYear(int year) {
        List<Message> yearMessages = new LinkedList<Message>();
        Calendar cal = Calendar.getInstance();
        for(Message msg : messages.values()) {
            cal.setTime(msg.getCreated());
            if(cal.get(Calendar.YEAR) == year) {
                yearMessages.add(msg);
            }
        }
        return yearMessages;
    }


    public List<Message> getPaginatedMessages(int from, int size) {
        LinkedList<Message> list  = new LinkedList<Message>(messages.values());
        if(from + size > list.size()) return new LinkedList<Message>();
        return list.subList(from, from + size);

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
        Message msg = messages.get(id);
        if(msg == null) {
            throw new DataNotFoundException("Message with id " + id + " not found");
        }
        return msg;
    }


    public Message deleteMessage(long id) {
        return messages.remove(id);
    }
}
