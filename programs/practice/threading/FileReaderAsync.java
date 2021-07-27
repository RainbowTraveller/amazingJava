import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileReaderAsync {
    //Create thread pool
    private ExecutorService service;
    private Set<String> onWire;

    public ExecutorService getService() {
        return service;
    }

    public Set<String> getOnWire() {
        return onWire;
    }

    public FileReaderAsync() {
        onWire = new HashSet<String>();
        service = Executors.newFixedThreadPool(4);
    }

    public void shutdown() {
        service.shutdown();
    }

    public void process(int val) {
        CompletableFuture.supplyAsync(() -> {
            System.out.println(">>>>>>>>>> Square For : " + val);
            return val * val;
        }, service)
            .thenApplyAsync(value -> {
                System.out.println(":::::::Double : " + value);
                return 2 * value;
            }, service)
            .thenAccept(value -> {
                System.out.println("Answer : " + value);
            }).exceptionally(ex -> {
            System.out.println("Exception : " + ex.getMessage());
        })
            .join();
    }

    public static void main(String[] str) {
        BufferedReader reader = null;
        String filepath = str[0];
        FileReaderAsync fileReaderAsync = new FileReaderAsync();
        try {
            reader = new BufferedReader(new FileReader(filepath));
            String line;
            while ((line = reader.readLine()) != null) {
                Integer val = Integer.valueOf(line.trim());
                fileReaderAsync.process(val);
            }
            System.out.println("Finished Reading");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                System.out.println("Closing");
                reader.close();
                fileReaderAsync.shutdown();
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }
}
