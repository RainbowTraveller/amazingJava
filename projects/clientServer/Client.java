import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JOptionPane;



public class Client{

    public static void main(String[] args) throws IOException, InterruptedException {
        /*String serverAddress = JOptionPane.showInputDialog(
                            "Enter IP Address of a machine that is\n" +
                             "running the date service on port 9090:");
                             */
        Socket s = new Socket("127.0.0.1", 9090);
        System.out.println(s);
        System.out.println("Socket created");
        Thread.sleep(10000);
        BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String answer;
        while((answer = input.readLine()) != null) {
            System.out.println(answer);
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            out.println("Thanks");
            break;
        }
        System.out.println("Before close");
        Thread.sleep(10000);
        s.close();
        //System.exit(0);
    }
}
