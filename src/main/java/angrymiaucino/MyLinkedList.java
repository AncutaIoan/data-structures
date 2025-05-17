package angrymiaucino;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;

public class MyLinkedList<T> implements Iterable<T> {



    protected class Node {
        T data;
        Node next;

        public Node(T data) {
            this.data = data;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public MyLinkedList() {}

    public void add(T data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        size++;
    }

    public void addFirst(T data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;

        if (tail == null) {
            tail = newNode;
        }
        size++;
    }

    public void addAt(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            addFirst(data);
            return;
        }
        if (index == size) {
            add(data);
            return;
        }

        Node newNode = new Node(data);
        Node prev = head;
        for (int i = 0; i < index - 1; i++) {
            prev = prev.next;
        }
        newNode.next = prev.next;
        prev.next = newNode;
        size++;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    public int indexOf(T data) {
        Node current = head;
        int index = 0;
        while (current != null) {
            if ((data == null && current.data == null) ||
                    (data != null && data.equals(current.data))) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    public T removeFirst() {
        if (head == null) return null;
        T data = head.data;
        head = head.next;
        if (head == null) tail = null;
        size--;
        return data;
    }

    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) return removeFirst();

        Node prev = head;
        for (int i = 0; i < index - 1; i++) {
            prev = prev.next;
        }
        Node removed = prev.next;
        prev.next = removed.next;

        if (removed == tail) {
            tail = prev;
        }

        size--;
        return removed.data;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(T data) {
        Node current = head;
        while (current != null) {
            if ((data == null && current.data == null) ||
                    (data != null && data.equals(current.data))) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public T getFirst() {
        if (head == null) throw new NoSuchElementException();
        return head.data;
    }

    public T getLast() {
        if (tail == null) throw new NoSuchElementException();
        return tail.data;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public void reverse() {
        Node prev = null;
        Node current = head;
        tail = head;
        while (current != null) {
            Node next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("MyLinkedList{");
        Node current = head;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(" -> ");
            }
            current = current.next;
        }
        sb.append('}');
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Node current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Node current = head;
        while (current != null) {
            action.accept(current.data);
            current = current.next;
        }
    }

    @Override
    public Spliterator<T> spliterator() {
        return Spliterators.spliteratorUnknownSize(iterator(), Spliterator.ORDERED);
    }
}
