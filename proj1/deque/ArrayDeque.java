package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>{
    private T[] items;
    private int front;
    private int rear;
    private int size;
    private static final int INITIAL_CAPACITY = 8;

    public ArrayDeque() {
        items = (T[]) new Object[INITIAL_CAPACITY];
        front = 0;
        rear = 1;
        size = 0;
    }

    private int decrementIndex(int index) {
        return (index - 1 + items.length) % items.length;
    }

    private int incrementIndex(int index) {
        return (index + 1) % items.length;
    }

    // getSize实现
    public int getSize() {
        return size;
    }

    // resize实现
    private void resize(int newCapacity) {
        if (newCapacity <= 0) {
            throw new IllegalArgumentException("New capacity must be positive");
        }
        T[] newItems = (T[]) new Object[newCapacity];
        int currentIndex = incrementIndex(front);
        for (int i = 0; i < size; i++) {
            newItems[i] = items[currentIndex];
            currentIndex = incrementIndex(currentIndex);
        }
        front = newCapacity - 1;
        rear = size;
        items = newItems;
    }

    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[front] = item;
        front = decrementIndex(front);
        size++;
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[rear] = item;
        rear = incrementIndex(rear);
        size++;
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        front = incrementIndex(front);
        T removedItem = items[front];
        items[front] = null;
        size--;
        if (size > 0 && size == items.length / 4 && items.length > INITIAL_CAPACITY) {
            resize(items.length / 2);
        }
        return removedItem;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        rear = decrementIndex(rear);
        T removedItem = items[rear];
        items[rear] = null;
        size--;
        if (size > 0 && size == items.length / 4 && items.length > INITIAL_CAPACITY) {
            resize(items.length / 2);
        }
        return removedItem;
    }

    public int size() {
        return size;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return items[(incrementIndex(front) + index) % items.length];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void printDeque() {

    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
