public class Singleton_04 {
    /*
     * Initialize the object only when required and delay it till that time.
     * To avoid entire method synchronization and subsequent threads waiting
     * for the lock even though instance is available, we as double check lock
     */

    private String name;
    private static Singleton_04 ref;

    private Singleton_04() {
        name = new String();
    }

    public static Singleton_04 getRef() {
        if (ref == null) {
            synchronized (Singleton_04.class) {
                if (ref == null) {
                    System.out.println("Instance created");
                    ref = new Singleton_04();
                    ref.setName("Totempole");
                }
            }
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
                Singleton_04 s = Singleton_04.getRef();
                System.out.println("Name " + s.getName());
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                Singleton_04 s = Singleton_04.getRef();
                System.out.println("Name " + s.getName());
            }
        });

        t1.start();
        t2.start();
    }
}
