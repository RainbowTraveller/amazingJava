// You are given a “file” with statements describing a data message format (similar to Google’s Protocol Buffers, or ROS .msg files). We are going to write a parser for this file that parses the messages into a data class on which we will query.

// Messages can be made up of floats, ints, strings. Assume strings are 256 bytes, ints 4 bytes, and floats 4 bytes.

// Example Input:

// // Begin Input
// Message Vehicle
// float x_position
// float y_position
// float velocity
// float acceleration
// int num_wheels
// string name

// Message Vector2d
// float x_position
// float y_position
// // End Input

// Please write a parser that parses this data into a data structure that supports the following query:

// Given a message type, output its size in bytes.

// Expected Outputs:

// get_size("Vehicle")  -> 276
// get_size("Vector2d") -> 8
// get_size("float")    -> 4

// Our goals in this interview are:

// - to produce working, running object-oriented code
// - make good time/cleanliness/completeness tradeoffs

/*
 * Click `Run` to execute the snippet below!
 */

import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

public class ObjectParser {

    private static String INPUT = """
            Message Vehicle
            float x_position
            float y_position
            float velocity
            float acceleration
            int num_wheels
            string name

            Message Vector2d
            float x_position
            float y_position
              """;

    static class Parser {

        private String inputString;
        private Map<String, Integer> sizeTracker;

        public Parser (String input) {
            this.inputString = input;
            this.sizeTracker = new HashMap<>();
            this.sizeTracker.put("float", 4);
            this.sizeTracker.put("int", 4);
            this.sizeTracker.put("string", 256);
        }

        public int getSize (String element) {
            return sizeTracker.getOrDefault(element, 0);
        }

        private void parseCurrentMessage (String name, List<String> eachMessage) {
            int size = 0;
//            System.out.println("List size : " + eachMessage.size());
            for (String field : eachMessage) {
                String[] variable = field.split("\\s+");
                String type = variable[0];
//                System.out.println("Curr Size : " + this.sizeTracker.getOrDefault(type, 0));
                size += this.sizeTracker.getOrDefault(type, 0);
            }
//            System.out.println("Name " + name + "  Size : " + size);
            sizeTracker.put(name, size);
        }

        public void parseInput () {
            String[] lines = INPUT.split("\\n");
            List<String> eachMessage = new LinkedList<>();
            String name = "";
            for (String line : lines) {
                if (!line.isBlank()) {
                    if (line.indexOf("Message") >= 0) {
                        if (eachMessage.size() > 0) {
                            parseCurrentMessage(name, eachMessage);
                        }
                        String[] parts = line.split("\\s+");
                        name = parts[1];
                        eachMessage = new LinkedList<>();
                    } else {
                        eachMessage.add(line);
                    }
                }
            }
            parseCurrentMessage(name, eachMessage);
        }
    }

    public static void main (String[] args) {
        Parser parser = new Parser(INPUT);
        parser.parseInput();
        System.out.println(parser.getSize("Vehicle"));
        System.out.println(parser.getSize("Vector2d"));
        System.out.println(parser.getSize("float"));
        System.out.println(parser.getSize("string"));
        System.out.println(parser.getSize("int"));
    }
}

