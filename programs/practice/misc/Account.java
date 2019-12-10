import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class Account {

    private int id;
    private int accountBalance;

    public Account(int id, int accountBalance) {
        this.id = id;
        this.accountBalance = accountBalance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(int balance) {
        this.accountBalance = balance;
    }

    public boolean deposite(int amount) {
        synchronized(this) {
            accountBalance += amount;
        }
        return true;
    }

    public boolean deduct(int amount) {
        synchronized(this) {
            if(accountBalance >= amount) {
                accountBalance -= amount;
                return true;
            }
            return false;
        }
    }

    // TODO: Make the transferFunds function below thread safe.
    public static boolean transferFunds(Account source, Account destination, int amount) {
        return source.deduct(amount) && destination.deposite(amount);
    }

    // This method is used by the test. No need to make this method thread safe for this exercise.
    public static int sum(Account[] accounts) {
        int sum = 0;

        for (Account account : accounts) {
            sum += account.getAccountBalance();
        }

        return sum;
    }

    public static void main(String[] args) {
        int numberOfAccounts = 5;
        int numTransactions = 50000;
        int numThreads = 5;
        Random random = new Random();
        Account[] bankAccounts = new Account[numberOfAccounts];

        for (int i = 0; i < numberOfAccounts; i++) {
            // initialize acccounts with a random initial value
            bankAccounts[i] = new Account(i, random.nextInt(1000));
        }

        int totalBefore = sum(bankAccounts);

        Runnable thread = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < numTransactions; i++) {
                    int from = random.nextInt(numberOfAccounts);
                    int to = random.nextInt(numberOfAccounts);
                    int transferAmount = random.nextInt(200);
                    transferFunds(bankAccounts[from], bankAccounts[to], transferAmount);
                }
            }
        };

        Thread[] threads = new Thread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(thread, Integer.toString(i));
            threads[i].start();
        }

        for (int i = 0; i < numThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                System.out.println("Failed to join thread: " + e.getMessage());
            }
        }

        int totalAfter = sum(bankAccounts);

        if (totalBefore != totalAfter) {
            System.out.println("Error: the bank accounts are not consistent. The total amount of money changed from $"
              + totalBefore + " to $" + totalAfter);
        } else {
            System.out.println("The bank accounts are consistent");
        }
    }
}
