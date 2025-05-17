import angrymiaucino.MyArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyArrayListTest {

    private MyArrayList<String> list;

    @BeforeEach
    void setUp() {
        list = new MyArrayList<>();
    }

    @Test
    void testAddAndGet() {
        list.add("apple");
        list.add("banana");

        assertEquals(2, list.size());
        assertEquals("apple", list.get(0));
        assertEquals("banana", list.get(1));
    }

    @Test
    void testRemoveMiddleElement() {
        list.add("one");
        list.add("two");
        list.add("three");

        list.remove(1); // remove "two"

        assertEquals(2, list.size());
        assertEquals("one", list.get(0));
        assertEquals("three", list.get(1));
    }

    @Test
    void testRemoveFirstElement() {
        list.add("first");
        list.add("second");

        list.remove(0);

        assertEquals(1, list.size());
        assertEquals("second", list.get(0));
    }

    @Test
    void testRemoveLastElement() {
        list.add("x");
        list.add("y");
        list.remove(1);

        assertEquals(1, list.size());
        assertEquals("x", list.get(0));
    }

    @Test
    void testSizeAfterAddAndRemove() {
        assertEquals(0, list.size());

        list.add("item1");
        list.add("item2");
        list.add("item3");

        assertEquals(3, list.size());

        list.remove(1);

        assertEquals(2, list.size());
    }

    @Test
    void testGetInvalidIndexThrows() {
        list.add("a");

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
    }

    @Test
    void testRemoveInvalidIndexThrows() {
        list.add("a");

        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(1));
    }

    @Test
    void testAutoResizeBeyondInitialCapacity() {
        for (int i = 0; i < 15; i++) {
            list.add("item" + i);
        }

        assertEquals(15, list.size());
        assertEquals("item0", list.get(0));
        assertEquals("item14", list.get(14));
    }

    @Test
    void testToStringReflectsContents() {
        list.add("a");
        list.add("b");

        String output = list.toString();
        assertTrue(output.contains("a"));
        assertTrue(output.contains("b"));
    }
}
