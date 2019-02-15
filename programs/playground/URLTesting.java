import java.net.URL;
import java.net.MalformedURLException;
import java.io.IOException;
import java.net.URLConnection;
import java.net.HttpURLConnection;

public class URLTesting {

    public static void main(String args[]) {
		try {
			String hostname = "saavn.com";
			String uid = "uid1234";
			String directive = "yes";
			//"http://{$hostname}/api.php?__call=subscription.restore&_marker=0&uid={$uid}&directive={$directive}&ctx=android"
			String urlDetails = "/api.php?__call=subscription.restore&_marker=0&uid=" + uid + "&directive=" + directive +"&ctx=android";
			//URL restoreURL = new URL("http", hostname, urlDetails);
			URL restoreURL = new URL("https://www.google.com");
			//System.out.println(myURL);
			HttpURLConnection httpConnection = (HttpURLConnection)restoreURL.openConnection();
			if(httpConnection != null) {
				httpConnection.connect();
				int status = httpConnection.getResponseCode();
				System.out.println("Response code : " + status);
			}
		} catch (MalformedURLException e) {
			System.out.println("URL parsing error :" + e.getMessage());
		} catch (IOException e) {
			System.out.println("URL connection error :" + e.getMessage());
		}
    }
}
