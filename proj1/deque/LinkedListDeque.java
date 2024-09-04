package deque;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class LinkedListDeque<T> implements Deque<T>{
   // Node节点
    public class Node<T> {
        T items;
        Node<T> next;
        Node<T> prev;

        public Node(T item) {
            this.items = item;
            this.next = null;
            this.prev = null;
        }
   }

    // 链表参数
    private Node<T> head;
    private Node<T> tail;
    private int size;
    private Node<T> sentinel;

    // 空链表的构造函数实现
    public LinkedListDeque() {
        sentinel = new Node<>(null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    // 链表迭代器实现
    private class LinkedListDequeIterator implements Iterator<T> {
        private Node<T> current;

        public LinkedListDequeIterator() {
            current = sentinel.next;
        }

        @Override
        // 检查是否有元素可以遍历
        public boolean hasNext() {
            return current != null;
        }

        @Override
        // 遍历下一个元素
        public T next() {
            if (current == null) {
                throw new NoSuchElementException();
            }
            T items = current.items;
            current = current.next;
            return items;
        }
    }

    // 迭代器接口
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Deque.super.forEach(action);
    }

    // 获取链表大小
    public int getSize() {
        return size;
    }

    // 判断链表是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 链表头部添加元素
    public void addFirst(T items) {
        Node<T> newNode = new Node<>(items);
        newNode.next = sentinel.next;
        newNode.prev = sentinel;
        sentinel.next.prev = newNode.prev;
        sentinel.next = newNode;
        size++;
    }

    // 链表尾部添加元素
    public void addLast(T items) {
        Node<T> newNode = new Node<>(items);
        newNode.next = sentinel;
        newNode.prev = sentinel.prev;
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size++;
    }

    // 链表头部删除元素
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node<T> firstNode = sentinel.next;
        sentinel.next = firstNode.next;
        firstNode.next.prev = sentinel;
        size--;
        return firstNode.items;
    }


    // 链表尾部元素删除
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node<T> lastNode = sentinel.prev;
        sentinel.prev = lastNode.prev;
        lastNode.prev.next = sentinel;
        size--;
        return lastNode.items;
    }

    // 获取链表索引
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> current = sentinel.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.items;
    }

    // 递归调用获取index的辅助方法
    private T getRecursiveHelper(Node<T> node, int index) {
        if (node == sentinel) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            return node.items;
        }
        return getRecursiveHelper(node.next, index - 1);
    }

    // 获取链表索引（递归版本）
    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return getRecursiveHelper(sentinel.next, index);
    }

    // 打印LinledDeque
    public void printDeque() {
        Node<T> current = sentinel.next;
        while (current!= sentinel) {
            System.out.print(current.items + " ");
            current = current.next;
        }
        System.out.println();
    }
}

