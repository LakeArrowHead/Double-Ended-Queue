package deque;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/** Performs some basic array based list tests. */
public class ArrayDequeTest {
    
    @Test
    public void addIsEmptySizeTest() {
        ArrayDeque<String> ad1 = new ArrayDeque<>();

        assertTrue("A newly initialized ArrayDeque should be empty", ad1.isEmpty());
        ad1.addFirst("front");

        assertEquals(1, ad1.size());
        assertFalse("ad1 should now contain 1 item", ad1.isEmpty());

        ad1.addLast("middle");
        assertEquals(2, ad1.size());

        ad1.addLast("back");
        assertEquals(3, ad1.size());

        String firstItem = ad1.get(0);
        assertEquals("First item should be: 'front'", "front", firstItem);

        String secondItem = ad1.get(1);
        assertEquals("Second item should be: 'middle'", "middle", secondItem);

        System.out.println("Printing out deque: ");
        ad1.printDeque();
    }

    @Test
    /** Adds an item, then removes an item, and ensures that the array deque is empty afterwards. */
    public void addRemoveTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        // should be empty
        assertTrue("ad1 should be empty upon initialization", ad1.isEmpty());

        ad1.addFirst(10);
        // should not be empty
        assertFalse("ad1 should contain 1 item", ad1.isEmpty());

        ad1.removeFirst();
        // should be empty
        assertTrue("ad1 should be empty after removal", ad1.isEmpty());

        ad1.addLast(1);
        ad1.removeLast();
        //should be empty
        assertTrue("ad1 should be empty after removal", ad1.isEmpty());
    }

    @Test
    /** Checks if removeFirst and removeLast remove the correct item. */
    public void removeTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();

        ad1.addFirst(2);
        ad1.addFirst(3);
        ad1.addFirst(4);

        ad1.removeLast();
        int lastItem = ad1.get(1);
        assertEquals("Last item should now be: ", 3, lastItem);

        ad1.removeFirst();
        int firstItem = ad1.get(0);
        assertEquals("First item should now be: ", 3, firstItem);
    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        ad1.addFirst(3);

        ad1.removeLast();
        ad1.removeFirst();
        ad1.removeLast();
        ad1.removeFirst();

        int size = ad1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }

    @Test
    /* Check if you can create LinkedListDeques with different parameterized types*/
    public void multipleParamTest() {
        LinkedListDeque<String>  ad1= new LinkedListDeque<String>();
        LinkedListDeque<Double>  ad2 = new LinkedListDeque<Double>();
        LinkedListDeque<Boolean> ad3 = new LinkedListDeque<Boolean>();

        ad1.addFirst("string");
        ad2.addFirst(3.14159);
        ad3.addFirst(true);

        String s = ad1.removeFirst();
        double d = ad2.removeFirst();
        boolean b = ad3.removeFirst();
    }

    @Test
    /* check if null is return when removing from an empty LinkedListDeque. */
    public void emptyNullReturnTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,",
                null, ad1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,",
                null, ad1.removeLast());
    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigArrayDequeTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        for (int i = 0; i < 1000000; i++) {
            ad1.addFirst(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) ad1.removeLast(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) ad1.removeFirst(), 0.0);
        }
    }

    @Test
    /* Tests get. Also checks the deque is not altered. */
    public void getTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        for (int i = 0; i < 5; i++) {
            ad1.addLast(i);
        }
        System.out.println("Printing out deque: ");
        ad1.printDeque();
        int firstItem = ad1.get(0);
        int secondItem = ad1.get(1);
        int lastItem = ad1.get(4);
        assertEquals("First item Should be 0", 0, firstItem);
        assertEquals("Second item should be 1", 1, secondItem);
        assertEquals("Last item should be 4", 4, lastItem);
        assertEquals("Deque should have the same size", 5, ad1.size());
        assertEquals("Should return null when no item exists at the given index",
                null, ad1.get(10));
        System.out.println("Printing out deque after get: ");
        ad1.printDeque();

        //Test get on a 1 item list.
        LinkedListDeque<Integer> ad2 = new LinkedListDeque<Integer>(1);
        int firstItem2 = ad2.get(0);
        assertEquals("First item Should be 1", 1,firstItem2);
    }
}
