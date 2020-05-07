public class xortest {
    public static void main(String[] args) {

        for (int i = 0; i <=1 ; ++i ) {
            for (int j = 0; j <=1 ; ++j ) {
                for (int k = 0; k <=1 ; ++k ) {
                    System.out.println(i + " " + j +  " " + k +  " >>> XOR : " + (i ^ j ^ k) + " ::: " + "OR : " + (i & j & k ));
                }
            }
        }
    }
}

