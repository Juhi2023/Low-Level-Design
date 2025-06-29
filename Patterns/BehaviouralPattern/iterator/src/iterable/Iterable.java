package iterable;

import iterator.Iterator;

public interface Iterable<T> {
    Iterator<T> getIterator();
}