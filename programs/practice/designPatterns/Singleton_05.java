public class Singleton_05 {
    /*
     * Initialize the object only when required and delay it till that time.
     * To avoid entire method synchronization and subsequent threads waiting
     * for the lock even though instance is available, we as double check lock
     */

    private String name;
    // To instruct JVM to not reorder the constructor instructions
    // mark this as volatile or the subsequent threads may get a not null
    // reference but empty object with fields set to null as constructor is
    // not called.
    private static volatile Singleton_05 ref;

    private Singleton_05() {
        name = new String();
    }

    public static Singleton_05 getRef() {
        if (ref == null) {
            synchronized (Singleton_05.class) {
                if (ref == null) {
                    System.out.println("Instance created");
                    ref = new Singleton_05();
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
                Singleton_05 s = Singleton_05.getRef();
                System.out.println("Name " + s.getName());
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                Singleton_05 s = Singleton_05.getRef();
                System.out.println("Name " + s.getName());
            }
        });

        t1.start();
        t2.start();
    }
}
