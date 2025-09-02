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

#### Priority Queue with custom comparator
```java

private static class Interval {
    int start;
    int end;

    Interval() {
        start = 0;
        end = 0;
    }

    public Interval(int start, int end) {
        if (start < end) {
            this.start = start;
            this.end = end;
        }
    }

    public String toString() {
        return new String(" { start : " + start + " end : " + end + " }\n");
    }
}

PriorityQueue<Interval> sorted =
new PriorityQueue<Interval>(
            (i1, i2) -> i1.start == i2.start ? i1.end - i2.end : i1.start - i2.start);
```

#### Sort any collection
```java
Collections.sort(list, (a, b) -> a - b); // Ascending order
Collections.sort(list, (a, b) -> b - a); // Descending order
// Sort intervals based on start time. If start time is same, sort based on end time.
// Refer Interval class defined above
Collections.sort(
  intervals, (i1, i2) -> (i1.start == i2.start ? i1.end - i2.end : i1.start - i2.start));
// Sorting 2D array based on second column
Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));
```

