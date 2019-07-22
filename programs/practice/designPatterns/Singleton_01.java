public class Singleton_01 {
    /*
     * This is eager initialization. The instance is created and kept in the memory
     * what is it never gets used and if the class has bunch of other variables
     * making it bulky. Clearly we need to improve this
     */

    private String name;
    private static Singleton_01 ref = new Singleton();

    private Singleton_01() {
        name = new String();
    }

    public static getRef() {
        return ref;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
