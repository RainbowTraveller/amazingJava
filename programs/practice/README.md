# Common Tricks and tips

#### Split a string into array of string based on space
```java
String[] input =  str.split("\\s+");
```

#### Custom Exception
```java
static class InvalidInputException extends Exception {
    static final long serialVersionUID = 1L;

    InvalidInputException(String message) {
        super(message);
    }
}
```

#### Sum-Carry Problem
```java
int sum = num1 + num2 + carry;
int storedSum = sum % 10; // Store the last digit of the sum
carry = sum / 10; // Calculate the carry for the next iteration
```

#### Fill the array with a specific value
```java
// Single dimensional array
Arrays.fill(array, value);
// Multi-dimensional array
for (int i = 0; i < grid.length; ++i) {
    Arrays.fill(distanceTracker[i], 0);
}
```

#### Convert char to int
```java
int digit = ch - '0'; // where ch is a character representing a digit
// Example: char ch = '5'; int digit = ch - '0'; // digit will be 5
```

#### Convert List to Array
```java
List<Integer> list = new ArrayList<>();
int[] array = list.stream().mapToInt(i -> i).toArray();
```

#### Print an array
```java
- System.out.println(Arrays.toString(array));
- java.util.Arrays.stream(getMaxFromSlidingWindow(input, 3)).forEach(System.out::println);
 ```
#### Array Sum
```java
int sum = Arrays.stream(array).sum();
```
