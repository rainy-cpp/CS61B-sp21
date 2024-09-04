package deque;

import java.util.Iterator;

public interface Deque<T> extends Iterable<T> {
    void addFirst(T items);
    void addLast(T items);
    T removeFirst();
    T removeLast();
    T get(int index);
    int getSize();
    boolean isEmpty();
    void printDeque();
    Iterator<T> iterator();
}
