package com.rnrmedia.rest.client;

import okhttp3.*;

import java.io.IOException;

public class TwillioClient {
    public static const String TWILIO_ACCOUNT_SID = 
    public static void main (String[] args) throws IOException {

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder().url("https://api.twilio.com/2010-04-01/Accounts/{{TWILIO_ACCOUNT_SID}}/AvailablePhoneNumbers/US/Local.json").method("GET", body).build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().toString());

    }
}
