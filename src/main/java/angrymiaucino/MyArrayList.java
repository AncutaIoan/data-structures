package angrymiaucino;

import java.util.Arrays;

public class MyArrayList<T> {
    private int SIZE = 10;
    private int lastIndex = 0;
    private Object[] elementData;

    public MyArrayList() {
        elementData = new Object[SIZE];
    }

    public void add(T element) {
        if (lastIndex >= SIZE) {
            SIZE *= 2;
            elementData = Arrays.copyOf(elementData, SIZE);
        }
        elementData[lastIndex++] = element;
    }

    public void remove(int index) {
        if (index < 0 || index >= lastIndex) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        int numMoved = lastIndex - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        }
        elementData[--lastIndex] = null;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index >= lastIndex) {
            throw new IndexOutOfBoundsException();
        }
        return (T) elementData[index];
    }

    public int size() {
        return lastIndex;
    }

    @Override
    public String toString() {
        return "MyArrayList{" +
                "array=" + Arrays.toString(Arrays.copyOf(elementData, lastIndex)) +
                '}';
    }
}
