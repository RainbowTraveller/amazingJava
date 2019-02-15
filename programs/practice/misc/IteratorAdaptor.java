class IteratorAdaptor implements Iterable<T> {
    private Iterator<Iterator<T>> nestedIterator;
    private Iterator<T> currentIterator;

    public IteratorAdaptor(Iterator<Iterator<T>> nestedOne) {
        nestedIterator = nestedOne;
    }

    public boolean hasNext() {

    }

    public T next() {

    }
}
