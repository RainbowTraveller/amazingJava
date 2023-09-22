import java.io.RandomAccessFile;

public class NewLineDetector {
    public static void main(String[] args) throws Exception {
        NewLineDetector.simulateTail("./ascii-endocded-file.txt");
    }

    public static void simulateTail(String fileName) throws Exception {
        RandomAccessFile inputFile = new RandomAccessFile(fileName, "r");

        long length = inputFile.length();
        inputFile.seek(length);
        int linesRead = 0;
        int bytesRead = 0;
        char c = '\n';
        //New line byte
        byte newLine = (byte) c;
        long offset = length;

        while(offset > 0 && linesRead < 10 ) {
            //Seek to location before char to read
            // initially just before the last one
            inputFile.seek(offset - 1);
            // Read one char, now the offset has moved to the end of this character
            byte currChar = inputFile.readByte();
            bytesRead++;
            if(currChar == newLine) {
                //Initially if the last char is \n we need to keep processing by ignoring it
                if(bytesRead > 1) {
                    byte[] buffer = new byte[bytesRead - 1]; // -1 because we have already read \n which we don't want
                    // As we have already read the \n the offset is correctly set to the char that is to be read next
                    inputFile.read(buffer);
                    System.out.println(new String(buffer));
                    linesRead++;
                    // Bring offset back to the begining of the valid text
                    inputFile.seek(offset - bytesRead);
                } 
                //1. For ignoring last char 
                //2. After processing read buffer resetting the byte read count
                bytesRead = 0;
            } 
            //THIS IS IMPORTANT offset adjustment to the last \n encountered
            offset--;
        }
    }
}
