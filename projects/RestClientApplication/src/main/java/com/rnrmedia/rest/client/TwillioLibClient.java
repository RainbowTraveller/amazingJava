package com.rnrmedia.rest.client;

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;

public class TwillioLibClient {
    // Find your Account Sid and Token at twilio.com/console
    public static final String ACCOUNT_SID = "AC5a29821f9074ee80d32fc768da1afcca";
    public static final String AUTH_TOKEN = "AUTH";

    public static void main (String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        //Send SMS : Working with
//        Message message = Message.creator(
//                new com.twilio.type.PhoneNumber(toNumber),
//                new com.twilio.type.PhoneNumber("+18443867902"),
//                "Hey There, Howdie...!").create();
//        System.out.println(message.getSid());

        //Fetching all messages
        ResourceSet<Message> messages = Message.reader().read();
        /* not tested
        // Fetch message for a given id
        message = Message.fetcher(ACCOUNT_SID)
                .fetch();
        */

        for(Message currMessage : messages) {
            System.out.println(currMessage.getBody());
        }
    }

}