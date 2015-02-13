import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


 /**    TCP server that runs on port 9990.
  */


public class Server {

        public static void main(String[] args) throws IOException {

            ServerSocket listener = new ServerSocket(9990);
            int clientNumber = 0;
            ExecutorService executer = Executors.newFixedThreadPool(12);
            try{
                while(true) {
                    RequestHandlerWorker worker  = new RequestHandlerWorker(listener.accept(), clientNumber++);
                    executer.execute(worker);
                }
            } finally {
                executer.shutdown();
                while(!executer.isTerminated()) {

                }
                listener.close();
            }
        }

    private static class RequestHandlerWorker implements Runnable {
        private Socket socket;
        private int clientNumber;


        public RequestHandlerWorker(Socket socket, int number) {
            this.socket = socket;
            this.clientNumber = number;
        }

        public void run() {
            try {
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String fileData = "";
                String lineData = "";
                while ((lineData = input.readLine()) != null ) {
                    fileData += lineData;
                    System.out.println("LOOP CONTINUEs" + lineData);
                }
                System.out.println("LOOP FINISHED");
                //processRequestA(fileData);
                //out.println("Data Received");
                out.write("Data Received");
                out.flush();
            } catch(IOException ioe) {
                System.out.println("Error handling the client request : " + clientNumber);
            } catch(Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException ioe) {
                    System.out.println("Error Closing the Socket");
                }
            }

        }

        private void processRequestA(String data) {
            System.out.println(data);
        }

        private void processRequestB(String data) {

        }
    }
}
