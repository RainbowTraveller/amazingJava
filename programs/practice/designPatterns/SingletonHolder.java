public class SingletonHolder {

    private static class Holder {
        static final SingletonHolder INSTANCE = new SingletonHolder();
    }

    public static SingletonHolder getRef() {
        return Holder.INSTANCE;
    }

    private SingletonHolder() {

    }
}
