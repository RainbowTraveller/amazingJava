import java.net.URL;
import java.util.List;
import java.util.LinkedList;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.StringBuffer;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.CompletableFuture;

public class URLCallingAPI {
    public static void main(String[] args) {
        List<String> uriList = new LinkedList<String>();
        uriList.add("https://www.google.com");
        uriList.add("https://www.leetcode.com");
        uriList.add("https://www.linkedin.com");
        uriList.add("https://www.netflix.com");
        uriList.add("https://www.facebook.com");
        uriList.add("https://www.amazon.com");
        uriList.add("https://www.apple.com");

        getURLResponsesJava8(uriList);
    }


    public static void getURLResponsesJava8(List<String> urls) {
        for(String url : urls) {
                CompletableFuture.supplyAsync(()->url)
                    .thenApply(currurl -> handleRequest(currurl))
                    .thenAccept(System.out::println);
        }
    }

    public static String handleRequest(String url) {
        try {
            System.out.println("URL :" + url);
            URL UrlObj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) UrlObj.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer response = new StringBuffer();
            String responseLine;
            while((responseLine = reader.readLine()) != null) {
                response.append(responseLine);
                response.append("\n");
            }
            reader.close();
            //System.out.println("Code : " + responseCode + " Response " + response);
            return "Code : " + responseCode + " Response " + response;
        } catch(IOException e) {
            System.out.println("URL can not be successfully reached :" + e.getMessage());
        }
        return null;
    }

    public static void getURLResponsesJava9(List<String> urls) {
        HttpClient client = HttpClient.newHttpClient();
        for(String url : urls) {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .header("User-agent", "java 9-11")
                .build();

            CompletableFuture<HttpResponse<String>> responseFuture
                = client.sendAsync(
                        request,
                        responseInfo -> responseInfo.statusCode() == 200 ?
                            HttpResponse.BodySubScribers.ofString(UTF_8) :
                            HttpResponse.BodySubScribers.replacing("Something went Wrong"))
                        .thenApply(HttpResponse::body)
                        .exceptionally(ex -> "Something went wrong")
                        .thenAccept(System.out::println);
            /*
            //Can be separated as well
            responseFuture
                .thenApply(HttpResponse::body)
                .exceptionally(ex -> "Something went wrong")
                .thenAccept(System.out::println);
                */
        }
    }
}



