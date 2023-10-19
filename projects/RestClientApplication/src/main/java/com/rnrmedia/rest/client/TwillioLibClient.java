package com.rnrmedia.rest.client;

import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.net.URI;
import java.math.BigDecimal;


public class TwillioLibClient {
    // Find your Account Sid and Token at twilio.com/console
    public static final String ACCOUNT_SID = "AC5a29821f9074ee80d32fc768da1afcca";
    public static final String AUTH_TOKEN = "d785c9cb6a23179fd22a6d6221e31e6d";

    public static void main (String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+16127471828"),
                new com.twilio.type.PhoneNumber("+18443867902"),
                "Hey SMS Try".toString()).create();
        System.out.println(message.getSid());
    }
}
