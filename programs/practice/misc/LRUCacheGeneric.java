public class LRUCacheGeneric<K, V> {
    class DoubleLLNode<E, T> {
        E key;
        T val;
        DoubleLLNode<E, T> prev;
        DoubleLLNode<E, T> next;


        public DoubleLLNode() {
            key = null;
            val = null;
            prev = null;
            next = null;
        }

        public DoubleLLNode(E key, T Val) {
            this.key = key;
            this.val = val;
            prev = null;
            next = null;
        }

    }

}
