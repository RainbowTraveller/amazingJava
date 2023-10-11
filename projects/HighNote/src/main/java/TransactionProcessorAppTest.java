import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

// Test Class
public class TransactionProcessorAppTest {
    @Test
    public void testProcessor() throws FileNotFoundException {
        String[] inputFileLines = loadFileLines();
        TransactionProcessorApp app = new TransactionProcessorApp();
        app.processTransactions(inputFileLines);
    }

    @Test
    public void testGetTransactionId() {
        String transaction = "10201088888888880000010000";
        String actualId = TransactionProcessorApp.getTransactionId(transaction);
        String expectedId = "1020";
        assertEquals(expectedId,actualId);
    }
    @Test
    public void testGetAccountNoLength() {
        String transaction = "10201088888888880000010000";
        int actualLength = TransactionProcessorApp.getAccountNoLength(transaction);
        int expectedLength = 10;
        assertEquals(expectedLength, actualLength);
    }

    @Test
    public void testGetAccountNo() {
        String transaction = "10201088888888880000010000";
        String actualNo = TransactionProcessorApp.getAccountNo(transaction, 10);
        String expectedNo = "8888888888";
        assertEquals(expectedNo, actualNo);
    }
    @Test
    public void testGetAmountInDollars() {
        String transaction = "10201088888888880000010000";
        int actualAmount = TransactionProcessorApp.getAmountInDollars(transaction, 10);
        int expectedAmount = 100;
        assertEquals(expectedAmount, actualAmount);
    }

    @Test
    public void testGetDestinationAccountNoLength () {
        String transaction = "20100812345678061234560000010000";
        int actualDestinationAccountLength = TransactionProcessorApp.getDestinationAccountLength(transaction, 8);
        int expectedDestinationAccountLength = 6;
        assertEquals(expectedDestinationAccountLength, actualDestinationAccountLength);
    }



    @Test
    public void testGetDestinationAccountNo () {
        String transaction = "20100812345678061234560000010000";
        String actualNo = TransactionProcessorApp.getDestinationAccountNo(transaction, 6,8);
        String expectedNo = "123456";
        assertEquals(expectedNo, actualNo);
    }

    @Test
    public void testGetTransferAmountInDollars () {
        String transaction = "20100812345678061234560000010000";
        int actualAmount = TransactionProcessorApp.getTransferAmountInDollars(transaction, 6,8);
        int expectedAmount = 100;
        assertEquals(expectedAmount, actualAmount);
    }

    private static String[] loadFileLines() throws FileNotFoundException {
        try {
            BufferedReader in = new BufferedReader(
                    new FileReader("src/test/resources/input.txt"));
            String str;
            List<String> list = new ArrayList<String>();
            while ((str = in.readLine()) != null) {
                list.add(str);
            }
            String[] stringArr = list.toArray(new String[0]);
            return stringArr;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}