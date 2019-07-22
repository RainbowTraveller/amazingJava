public class Singleton_02 {
    /*
     * Initialize the object only when required and
     * delay it till that time.
     *
     */

    private String name;
    private static Singleton_02 ref;

    private Singleton_02() {
        name = new String();
    }

    public static Singleton_02 getRef() {
        if(ref == null) {
            System.out.println("Instance created");
            ref = new Singleton_02();
        }
        return ref;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
                public void run() {
                    Singleton_02 s = Singleton_02.getRef();
                }
        });

        Thread t2 = new Thread(new Runnable() {
                public void run() {
                    Singleton_02 s = Singleton_02.getRef();
                }
        });

        t1.start();
        t2.start();
    }
}
