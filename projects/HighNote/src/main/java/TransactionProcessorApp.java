import java.sql.SQLOutput;
import java.util.*;

// Main Class
public class TransactionProcessorApp {
    /**
     * Inner class representing the processing element
     */
    private static class Account {
        /**
         * Id of the account
         */
        String no;
        /**
         * Balance
         */
        int amount;
        /**
         * Limit on daily transaction
         */
        int dailyTransactionAmount;

        @Override
        public int hashCode () {
            return super.hashCode();
        }

        public Account (int amount, String no) {
            this.amount = amount;
            this.no = no;
        }

        @Override
        public String toString () {
            return "Account No : " + no + " Balance : " + amount * 100;
        }

        public void deposite (int amount) {
            this.amount += amount;
        }

        public boolean withdraw (int amount) {
            if (amount <= 200 && dailyTransactionAmount + amount <= 500 && this.amount >= amount) {
                this.amount -= amount;
                this.dailyTransactionAmount += amount;
                return true;
            }
            return false;
        }
    }

    /**
     * Tracking the transactions using key value pairs
     * The key being the account number and the value being actual Account
     */
    Map<String, Account> accounts;

    public TransactionProcessorApp () {
        accounts = new HashMap<>();
    }

    public void processTransactions (String[] transactions) {

        System.out.println("Starting Transaction Processor:");
        System.out.println("Start Inputs:");
        for (String transaction : transactions) {
            processTransaction(transaction);
        }
        for (String accountNo : accounts.keySet()) {
            System.out.println(accounts.get(accountNo));
        }
        System.out.println("End Inputs:");
    }

    private void processTransaction (String transaction) {
        // Parse Transaction Data
        String transactionId = getTransactionId(transaction);
        int accountNoLength = 0, destinationAccountLength = 0;
        String accountNo = "", destinationAccountNo = "";
        int amount = 0, transferAmount = 0;

        //Process Transaction

        if (transactionId.equalsIgnoreCase("1010")) {
            accountNoLength = getAccountNoLength(transaction);
            accountNo = getAccountNo(transaction, accountNoLength);
            amount = getAmountInDollars(transaction, accountNoLength);
            // Check transaction id
            // Deposite :
            // Check transaction amount
            // get account if not create  add the amount
            if (amount <= 1000) {
                Account currAccount = accounts.getOrDefault(accountNo, new Account(0, accountNo));
                currAccount.deposite(amount);
                accounts.put(accountNo, currAccount);
//                System.out.println("Added Amount : " + amount + " : " + currAccount);
            }
        } else if (transactionId.equalsIgnoreCase("1020")) {
            //Withdrawal
            // If account present : check for amount
            // if amount : check balance
            // if negative balance : do not proceed
            // if limit reached : do not proceed
            // If ok : deduct and update daily limit
            accountNoLength = getAccountNoLength(transaction);
            accountNo = getAccountNo(transaction, accountNoLength);
            amount = getAmountInDollars(transaction, accountNoLength);
            if (accounts.containsKey(accountNo)) {
                Account currAccount = accounts.get(accountNo);
                currAccount.withdraw(amount);
                accounts.put(accountNo, currAccount);
            }
//                System.out.println("Removed Amount : " + amount + " : " + currAccount);
        } else if (transactionId.equalsIgnoreCase("2010")) {
            accountNoLength = getAccountNoLength(transaction);
            accountNo = getAccountNo(transaction, accountNoLength);
            destinationAccountLength = getDestinationAccountLength(transaction,accountNoLength);
            destinationAccountNo = getDestinationAccountNo(transaction, destinationAccountLength,accountNoLength);
            transferAmount = getTransferAmountInDollars(transaction, destinationAccountLength, accountNoLength);
            /*
                1. Check if both accounts are in place
                2. Check if source has enough funds
                3. amount < 200
                4. if limit reached : do not proceed
             */
            if(accounts.containsKey(accountNo) && accounts.containsKey(destinationAccountNo)) {
                Account source = accounts.get(accountNo);
                Account destination = accounts.get(destinationAccountNo);
                if(source.withdraw(transferAmount)) {
                    destination.deposite(transferAmount);
                }
            }
        }
    }


    public static String getTransactionId (String transaction) {
        return transaction.substring(0, 4);
    }

    public static int getAccountNoLength (String transaction) {
        return Integer.valueOf(transaction.substring(4, 6));
    }

    public static String getAccountNo (String transaction, int accountNoLength) {
        return transaction.substring(6, 6 + accountNoLength);
    }

    public static int getAmountInDollars (String transaction, int accountNoLength) {
        String amount = transaction.substring(6 + accountNoLength);
        return Integer.valueOf(amount) / 100;
    }

    public static int getDestinationAccountLength (String transaction, int accountNolength) {
        return Integer.valueOf(transaction.substring(6 + accountNolength, 6 + accountNolength + 2));
    }

    public static String getDestinationAccountNo (String transaction, int destAccountNoLength, int accountNoLength) {
        return transaction.substring( 8 + accountNoLength, 8 + accountNoLength + destAccountNoLength);
    }

    public static int getTransferAmountInDollars (String transaction, int destAccountNoLength, int accountNoLength) {
        String amount = transaction.substring(8 + accountNoLength + destAccountNoLength);
        return Integer.valueOf(amount) / 100;
    }
}

