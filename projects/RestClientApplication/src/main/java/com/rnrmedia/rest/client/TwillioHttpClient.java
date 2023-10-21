package com.rnrmedia.rest.client;

import okhttp3.*;

import java.io.IOException;

public class TwillioHttpClient {
    public static final String TWILIO_ACCOUNT_SID = "AC5a29821f9074ee80d32fc768da1afcca";
    public static final String TWILIO_AUTH_TOKEN = "AUTH";

    private static OkHttpClient client;

    public static void main (String[] args) throws IOException {
            //sendSMS();
            listSMS();

    }


    public static void sendSMS () throws IOException {
        client = getClientWithAuth(TWILIO_ACCOUNT_SID, TWILIO_AUTH_TOKEN);
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "Body=Hello World!&To={{+PHONE}}&From={{+18443867902}}");
        Request request = new Request.Builder()
//                .url("https://api.twilio.com/2010-04-01/Accounts/{{TWILIO_ACCOUNT_SID}}/Messages.json")
                .url("https://api.twilio.com/2010-04-01/Accounts/" + TWILIO_ACCOUNT_SID + "/Messages.json")
                .method("POST", body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();
        Response response = client.newCall(request).execute();
    }

    public static void listSMS () throws IOException {

        client = getClientWithAuth(TWILIO_ACCOUNT_SID, TWILIO_AUTH_TOKEN);
        Request request = new Request.Builder()
                .url("https://api.twilio.com/2010-04-01/Accounts/" + TWILIO_ACCOUNT_SID  + "/Messages.json")
                .method("GET", null)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }


    private static OkHttpClient getClientWithAuth(String sid, String authToken) {
        return new OkHttpClient().newBuilder()
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

    }
}
