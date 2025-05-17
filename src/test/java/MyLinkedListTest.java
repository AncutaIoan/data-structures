import angrymiaucino.MyLinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class MyLinkedListTest {

    private MyLinkedList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new MyLinkedList<>();
    }

    @Test
    void testAddAndSize() {
        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals(3, list.size());
        assertEquals("MyLinkedList{1 -> 2 -> 3}", list.toString());
    }

    @Test
    void testAddFirst() {
        list.addFirst(10);
        list.addFirst(20);

        assertEquals(2, list.size());
        assertEquals("MyLinkedList{20 -> 10}", list.toString());
        assertEquals(20, list.getFirst());
    }

    @Test
    void testAddAt() {
        list.add(1);
        list.add(3);
        list.addAt(1, 2); // 1 -> 2 -> 3

        assertEquals("MyLinkedList{1 -> 2 -> 3}", list.toString());
    }

    @Test
    void testGet() {
        list.add(100);
        list.add(200);

        assertEquals(100, list.get(0));
        assertEquals(200, list.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(2));
    }

    @Test
    void testRemoveFirst() {
        list.add(1);
        list.add(2);

        assertEquals(1, list.removeFirst());
        assertEquals(1, list.size());
        assertEquals("MyLinkedList{2}", list.toString());
    }

    @Test
    void testRemoveAtIndex() {
        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals(2, list.remove(1));
        assertEquals("MyLinkedList{1 -> 3}", list.toString());
        assertEquals(2, list.size());
    }

    @Test
    void testContainsAndIndexOf() {
        list.add(10);
        list.add(20);

        assertTrue(list.contains(10));
        assertFalse(list.contains(30));
        assertEquals(1, list.indexOf(20));
        assertEquals(-1, list.indexOf(99));
    }

    @Test
    void testClearAndIsEmpty() {
        list.add(5);
        list.clear();

        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    @Test
    void testReverse() {
        list.add(1);
        list.add(2);
        list.add(3);

        list.reverse();
        assertEquals("MyLinkedList{3 -> 2 -> 1}", list.toString());
    }

    @Test
    void testIterator() {
        list.add(1);
        list.add(2);
        list.add(3);

        Iterator<Integer> it = list.iterator();
        assertTrue(it.hasNext());
        assertEquals(1, it.next());
        assertEquals(2, it.next());
        assertEquals(3, it.next());
        assertFalse(it.hasNext());
        assertThrows(NoSuchElementException.class, it::next);
    }

    @Test
    void testGetFirstAndLast() {
        list.add(5);
        list.add(10);
        list.add(15);

        assertEquals(5, list.getFirst());
        assertEquals(15, list.getLast());
    }
}
