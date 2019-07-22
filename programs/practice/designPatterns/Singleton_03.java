public class Singleton_03 {
    /*
     * Initialize the object only when required and
     * delay it till that time and synchronize to avoid race
     * condition due to multithreading as in previous example
     *
     */

    private String name;
    private static Singleton_03 ref;

    private Singleton_03() {
        name = new String();
    }

    public static synchronized Singleton_03 getRef() {
        if(ref == null) {
            System.out.println("Instance created");
            ref = new Singleton_03();
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
                    Singleton_03 s = Singleton_03.getRef();
                }
        });

        Thread t2 = new Thread(new Runnable() {
                public void run() {
                    Singleton_03 s = Singleton_03.getRef();
                }
        });

        t1.start();
        t2.start();
    }
}
