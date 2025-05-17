import angrymiaucino.MyDoubleLinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MyDoubleLinkedListTest {

    private List<Integer> list;

    @BeforeEach
    public void setUp() {
        list = new MyDoubleLinkedList<>();
    }

    @Test
    public void testAddAndGet() {
        list.add(10);
        list.add(20);
        list.add(30);

        assertEquals(3, list.size());
        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
        assertEquals(30, list.get(2));
    }

    @Test
    public void testAddAtIndex() {
        list.add(0, 100);
        list.add(1, 200);
        list.add(1, 150);

        assertEquals(3, list.size());
        assertEquals(100, list.get(0));
        assertEquals(150, list.get(1));
        assertEquals(200, list.get(2));
    }

    @Test
    public void testSet() {
        list.add(1);
        list.add(2);
        list.set(1, 42);

        assertEquals(42, list.get(1));
    }

    @Test
    public void testRemoveByIndex() {
        list.add(5);
        list.add(6);
        list.add(7);

        int removed = list.remove(1);
        assertEquals(6, removed);
        assertEquals(2, list.size());
        assertEquals(7, list.get(1));
    }

    @Test
    public void testRemoveByObject() {
        list.add(1);
        list.add(2);
        list.add(3);

        assertTrue(list.remove(Integer.valueOf(2)));
        assertEquals(2, list.size());
        assertFalse(list.contains(2));
    }

    @Test
    public void testClear() {
        list.add(100);
        list.add(200);
        list.clear();

        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }

    @Test
    public void testContains() {
        list.add(42);
        list.add(99);

        assertTrue(list.contains(42));
        assertFalse(list.contains(100));
    }

    @Test
    public void testIndexOfAndLastIndexOf() {
        list.add(1);
        list.add(2);
        list.add(1);

        assertEquals(0, list.indexOf(1));
        assertEquals(2, list.lastIndexOf(1));
    }

    @Test
    public void testIterator() {
        list.add(10);
        list.add(20);
        list.add(30);

        Iterator<Integer> it = list.iterator();
        assertTrue(it.hasNext());
        assertEquals(10, it.next());
        assertEquals(20, it.next());
        assertEquals(30, it.next());
        assertFalse(it.hasNext());
    }

    @Test
    public void testFailFastIterator() {
        list.add(1);
        list.add(2);

        Iterator<Integer> it = list.iterator();
        list.add(3); // Structural modification

        assertThrows(ConcurrentModificationException.class, it::next);
    }

    @Test
    public void testToString() {
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals("[1, 2, 3]", list.toString());
    }

    @Test
    public void testGetInvalidIndex() {
        list.add(1);
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(2));
    }

    @Test
    public void testAddInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(2, 99));
    }

    @Test
    public void testSetInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(0, 123));
    }

    @Test
    public void testRemoveInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
    }
}
