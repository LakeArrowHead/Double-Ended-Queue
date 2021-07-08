package deque;

import org.junit.Test;
import static org.junit.Assert.*;


/** Performs some basic linked list tests. */
public class LinkedListDequeTest {

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {
        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();

		assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
		lld1.addFirst("front");

		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

		lld1.addLast("middle");
		assertEquals(2, lld1.size());

		lld1.addLast("back");
		assertEquals(3, lld1.size());

		String firstItem = lld1.get(0);
		assertEquals("Frist item should be: 'front'", "front", firstItem);

		System.out.println("Printing out deque: ");
		lld1.printDeque();
    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
		// should be empty
		assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

		lld1.addFirst(10);
		// should not be empty
		assertFalse("lld1 should contain 1 item", lld1.isEmpty());

		lld1.removeFirst();
		// should be empty
		assertTrue("lld1 should be empty after removal", lld1.isEmpty());

		lld1.addLast(1);
		lld1.removeLast();
		//should be empty
        assertTrue("lld1 should be empty after removal", lld1.isEmpty());
    }

    @Test
    /** Checks if removeFirst and removeLast remove the correct item. */
    public void removeTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>(1);

        lld1.addFirst(2);
        lld1.addFirst(3);
        lld1.addFirst(4);

        lld1.removeLast();
        int lastItem = lld1.get(2);
        assertEquals("Last item should now be: ", 2, lastItem);

        lld1.removeFirst();
        int firstItem = lld1.get(0);
        assertEquals("First item should now be: ", 3, firstItem);
    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }

    @Test
    /* Check if you can create LinkedListDeques with different parameterized types*/
    public void multipleParamTest() {
        LinkedListDeque<String>  lld1 = new LinkedListDeque<String>();
        LinkedListDeque<Double>  lld2 = new LinkedListDeque<Double>();
        LinkedListDeque<Boolean> lld3 = new LinkedListDeque<Boolean>();

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = lld1.removeFirst();
        double d = lld2.removeFirst();
        boolean b = lld3.removeFirst();
    }

    @Test
    /* check if null is return when removing from an empty LinkedListDeque. */
    public void emptyNullReturnTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,",
                null, lld1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,",
                null, lld1.removeLast());
    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }
    }

    @Test
    /* Tests get. Also checks the deque is not altered. */
    public void getTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
        for (int i = 0; i < 5; i++) {
            lld1.addLast(i);
        }
        System.out.println("Printing out deque: ");
        lld1.printDeque();
        int firstItem = lld1.get(0);
        int secondItem = lld1.get(1);
        int lastItem = lld1.get(4);
        assertEquals("First item Should be 0", 0, firstItem);
        assertEquals("Second item should be 1", 1, secondItem);
        assertEquals("Last item should be 4", 4, lastItem);
        assertEquals("Deque should have the same size", 5, lld1.size());
        assertEquals("Should return null when no item exists at the given index",
                null, lld1.get(10));
        System.out.println("Printing out deque after get: ");
        lld1.printDeque();

        //Test get on a 1 item list.
        LinkedListDeque<Integer> lld2 = new LinkedListDeque<Integer>(1);
        int firstItem2 = lld2.get(0);
        assertEquals("First item Should be 1", 1,firstItem2);
    }

    @Test
    /* Tests getRecursive. Also checks the deque is not altered. */
    public void getRecursiveTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
        for (int i = 0; i < 5; i++) {
            lld1.addLast(i);
        }
        System.out.println("Printing out deque: ");
        lld1.printDeque();
        int firstItem = lld1.getRecursive(0);
        int secondItem = lld1.getRecursive(1);
        int lastItem = lld1.getRecursive(4);
        assertEquals("First item Should be 0", 0, firstItem);
        assertEquals("Second item should be 1", 1, secondItem);
        assertEquals("Last item should be 4", 4, lastItem);
        assertEquals("Deque should have the same size", 5, lld1.size());
        assertEquals("Should return null when no item exists at the given index",
                null, lld1.getRecursive(10));
        System.out.println("Printing out deque after get: ");
        lld1.printDeque();

        //Test get on a 1 item list.
        LinkedListDeque<Integer> lld2 = new LinkedListDeque<Integer>(1);
        int firstItem2 = lld2.getRecursive(0);
        assertEquals("First item Should be 1", 1,firstItem2);
    }

}
