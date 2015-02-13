import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;
import java.util.List;




public class ClientA implements Runnable {

    private String hostname;
    private int port;
    Socket clientSocket;

    public ClientA(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    public void connect() throws UnknownHostException, IOException {
        System.out.println("Attempting to connect to server....");
        clientSocket = new Socket(hostname, port);
        System.out.println("Connection established: " + clientSocket);
    }

    /*public void sendFile() throws IOException {
        //Read and send file
        try {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            Path path = Paths.get("SampleData.txt");
            try(Stream<String> lines = Files.lines(path)
                                   .onClose(() -> System.out.println("File closed"))) {
                lines.forEachOrdered(line -> out.write(line));
                out.flush();
            }
        } catch (Exception ioe) {
            ioe.printStackTrace();
        }
    }*/

    public void sendFile() throws IOException {
        //Read and send file
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            Path path = Paths.get("SampleData.txt");
            List<String> lines = Files.readAllLines(path);
            for(String line : lines) {
                //System.out.println(line);
                //System.out.println("--------------------");
                out.write(line);
                out.flush();
            }
        } catch (Exception ioe) {
            ioe.printStackTrace();
        } finally {
            /*if(out != null) {
                out.close();
            }*/
        }
    }

    public void getResponse() throws IOException {
        try(BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            String answer = "";
            while(answer != null) {
                System.out.println("GETTING RESPONSE ");
                answer = input.readLine();
                System.out.println("ANSWER:" + answer);
                if(answer != null) {
                    System.out.println("Client A Thread Id :" + Thread.currentThread().getId());
                    System.out.println("Response from Server : " + answer);
                    answer = null;
                } else {
                    break;
                }
            }
        }
    }

    public void run(){
        try {
            connect();
            sendFile();
            Thread.sleep(1000);
            getResponse();
        } catch (UnknownHostException uhe ) {

        } catch(InterruptedException ie) {
            ie.printStackTrace();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException {

        ExecutorService executer = Executors.newFixedThreadPool(20);
        for(int i = 0; i < 1; ++i) {
            executer.execute(new ClientA("127.0.0.1", 9990));
        }
        executer.shutdown();
        while(!executer.isTerminated()) {
        }
        /*ClientA c = new ClientA("127.0.0.1", 9990);
        new Thread(c).start();
        */
        System.out.println("All the ClientA requests processed");
    }
}
