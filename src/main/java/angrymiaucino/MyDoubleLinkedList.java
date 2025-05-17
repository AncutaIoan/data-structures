package angrymiaucino;

import java.util.*;

public class MyDoubleLinkedList<T> implements List<T>, Iterable<T> {

    protected class Node {
        T data;
        Node next;
        Node prev;

        public Node(T data) {
            this.data = data;
        }
    }

    private Node head;
    private Node tail;
    private int size;
    private int modCount; // Modification counter for fail-fast behavior

    public MyDoubleLinkedList() {}

    // Core add
    @Override
    public boolean add(T data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
        modCount++;
        return true;
    }

    @Override
    public void add(int index, T element) {
        checkPositionIndex(index);
        if (index == size) {
            add(element);
        } else if (index == 0) {
            Node newNode = new Node(element);
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
            size++;
            modCount++;
        } else {
            Node curr = node(index);
            Node prevNode = curr.prev;
            Node newNode = new Node(element);
            newNode.next = curr;
            newNode.prev = prevNode;
            if (prevNode != null) prevNode.next = newNode;
            curr.prev = newNode;
            size++;
            modCount++;
        }
    }

    @Override
    public T get(int index) {
        checkElementIndex(index);
        return node(index).data;
    }

    @Override
    public T set(int index, T element) {
        checkElementIndex(index);
        Node target = node(index);
        T oldValue = target.data;
        target.data = element;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        checkElementIndex(index);
        return unlink(node(index));
    }

    @Override
    public boolean remove(Object o) {
        for (Node curr = head; curr != null; curr = curr.next) {
            if (Objects.equals(curr.data, o)) {
                unlink(curr);
                return true;
            }
        }
        return false;
    }

    private T unlink(Node node) {
        T data = node.data;
        Node prev = node.prev;
        Node next = node.next;

        if (prev == null) head = next;
        else prev.next = next;

        if (next == null) tail = prev;
        else next.prev = prev;

        size--;
        modCount++;
        return data;
    }

    private Node node(int index) {
        Node x;
        if (index < (size >> 1)) {
            x = head;
            for (int i = 0; i < index; i++) x = x.next;
        } else {
            x = tail;
            for (int i = size - 1; i > index; i--) x = x.prev;
        }
        return x;
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index)) throw new IndexOutOfBoundsException("Index: " + index);
    }

    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index)) throw new IndexOutOfBoundsException("Index: " + index);
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Node current = head;
            private final int expectedModCount = modCount;
            private boolean canRemove = false;

            public boolean hasNext() {
                return current != null;
            }

            public T next() {
                if (modCount != expectedModCount)
                    throw new ConcurrentModificationException();
                if (current == null)
                    throw new NoSuchElementException();
                T data = current.data;
                current = current.next;
                canRemove = true;
                return data;
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node curr = head;
        while (curr != null) {
            sb.append(curr.data);
            if (curr.next != null) sb.append(", ");
            curr = curr.next;
        }
        sb.append("]");
        return sb.toString();
    }

    // ------- Required methods for List<T> interface -------
    @Override public boolean isEmpty() { return size == 0; }
    @Override public boolean contains(Object o) { return indexOf(o) != -1; }
    @Override public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (Node x = head; x != null; x = x.next)
            result[i++] = x.data;
        return result;
    }
    @Override public <E> E[] toArray(E[] a) {
        if (a.length < size)
            a = Arrays.copyOf(a, size);
        int i = 0;
        Object[] result = a;
        for (Node x = head; x != null; x = x.next)
            result[i++] = x.data;
        if (a.length > size) a[size] = null;
        return a;
    }
    @Override public int indexOf(Object o) {
        int index = 0;
        for (Node x = head; x != null; x = x.next) {
            if (Objects.equals(o, x.data)) return index;
            index++;
        }
        return -1;
    }
    @Override public int lastIndexOf(Object o) {
        int index = size - 1;
        for (Node x = tail; x != null; x = x.prev) {
            if (Objects.equals(o, x.data)) return index;
            index--;
        }
        return -1;
    }
    @Override public void clear() {
        Node x = head;
        while (x != null) {
            Node next = x.next;
            x.data = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        head = tail = null;
        size = 0;
        modCount++;
    }

    // Unsupported for brevity
    @Override public ListIterator<T> listIterator() { throw new UnsupportedOperationException(); }
    @Override public ListIterator<T> listIterator(int index) { throw new UnsupportedOperationException(); }
    @Override public List<T> subList(int fromIndex, int toIndex) { throw new UnsupportedOperationException(); }
    @Override public boolean containsAll(Collection<?> c) { throw new UnsupportedOperationException(); }
    @Override public boolean addAll(Collection<? extends T> c) { throw new UnsupportedOperationException(); }
    @Override public boolean addAll(int index, Collection<? extends T> c) { throw new UnsupportedOperationException(); }
    @Override public boolean removeAll(Collection<?> c) { throw new UnsupportedOperationException(); }
    @Override public boolean retainAll(Collection<?> c) { throw new UnsupportedOperationException(); }
}
