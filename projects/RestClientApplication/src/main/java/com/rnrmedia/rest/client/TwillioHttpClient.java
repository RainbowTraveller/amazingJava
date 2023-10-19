package com.rnrmedia.rest.client;

import okhttp3.*;

import java.io.IOException;

public class TwillioHttpClient {
    public static final String TWILIO_ACCOUNT_SID = "AC5a29821f9074ee80d32fc768da1afcca";
    public static final String TWILIO_AUTH_TOKEN = "d785c9cb6a23179fd22a6d6221e31e6d";

    public static void main (String[] args) throws IOException {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .authenticator(((route, response) -> {
                    if (response.request().header("Authorization") != null) {
                        return null; // Give up, we've already attempted to authenticate.
                    }

                    System.out.println("Authenticating for response: " + response);
                    System.out.println("Challenges: " + response.challenges());
                    String credential = Credentials.basic(TWILIO_ACCOUNT_SID, TWILIO_AUTH_TOKEN);
                    return response.request().newBuilder()
                            .header("Authorization", credential)
                            .build();

                }))
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "Application/json");
        Request request = new Request.Builder()
                .url("https://api.twilio.com/2010-04-01/Accounts/{{TWILIO_ACCOUNT_SID}}/AvailablePhoneNumbers/US/Local.json")
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().toString());

    }
}
