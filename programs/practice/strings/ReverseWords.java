public class ReverseWords {

    public static void main(String[] args) {
        String input = args[0];
        System.out.println(input);
		reverseWords( input  );
    }

	public static void reverseWords( String input ) {
        if(input != null) {
            char[] arr = input.toCharArray();
            int start = 0;
            int end = input.length();
            int tracker = 0;
            reverse(arr, start, end - 1);
            for(int i = 0; i < end; ++i) {
                if(arr[i] == ' ') {
                    tracker = i - 1;
                    reverse(arr, start, tracker);
                    start = i + 1;
                }
            }
            tracker = end - 1;
            reverse(arr, start, tracker);
            System.out.println("Reversed word string:");
            for(int i = 0; i < end; ++i) {
                System.out.print(arr[i]);
            }
            System.out.println();
        }

	}
    public static void reverse(char[] arr, int s, int e) {
        if(arr != null) {
            while(s < e) {
                char temp = arr[s];
                arr[s] = arr[e];
                arr[e] = temp;
                s++;
                e--;
            }
        }
    }
}
