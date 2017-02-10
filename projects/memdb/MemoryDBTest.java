import java.io.Console;
import java.util.Scanner;

public class MemoryDBTest {
    public static void main(String[] args) {
        /*Console c = System.console();
        if(c == null) {
            System.err.println("Error creating Console");
            System.exit(1);
        }*/
        Scanner sc = new Scanner(System.in);
        String input;
        MemoryDB mdb = new MemoryDB();
        System.out.println("MEMORY DB SIMULATION");
        while(sc.hasNextLine()) {
            //input = c.readLine();
            input = sc.nextLine();
            String [] split = input.split("\\s+");
            String command = split[0];
            switch(command) {
                case "SET":
                    if (split.length == 3) {
                        String key = split[1];
                        int value = Integer.parseInt(split[2]);
                        mdb.set(key, value);
                        System.out.println("\tMAP: " + mdb);
                    }
                    break;
                case "GET":
                    if (split.length == 2) {
                        String key = split[1];
                        System.out.println("\tKEY: " + key + " VALUE: " + mdb.get(key));
                    }
                    break;
                case "UNSET":
                    if (split.length == 2) {
                        String key = split[1];
                        mdb.unset(key);
                        System.out.println("\tMAP: " + mdb);
                    }
                    break;
                case "NUMEQUALTO":
                    if (split.length == 2) {
                        int value = Integer.parseInt(split[1]);
                        System.out.println("\tNUMEQUALTO: " + mdb.numEqualTo(value));
                    }
                    break;
                case "BEGIN":
                    mdb.begin();
                    break;
                case "ROLLBACK":
                    mdb.rollback();
                    break;
                case "COMMIT":
                    mdb.commit();
                    break;
                case "END":
                    System.exit(0);
                    break;
            }
        }
    }
}
