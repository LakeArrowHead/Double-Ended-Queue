package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T> {

    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;

    /** Defaulting first and last to -1 to distinguish empty lists. When list is not empty,
    first and last should never be negative. */
    private int first = -1;
    private int last = -1;

    /** Creates an empty Array Deque. */
    public ArrayDeque() {
        items = (T []) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    /** Creates an empty Array Deque of length x. */
    public ArrayDeque(int x) {
        items = (T []) new Object[x];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    /** Resizes the deque to capacity x. */
    public void resize(int x) {
        // Copying old to new
        T[] newItems = (T []) new Object[x];
        System.arraycopy(items, 0, newItems, size, first);
        System.arraycopy(items, first, newItems, first, size - first);
        items = newItems;
        // Reset last, nextFirst, nextLast pointers
        nextFirst = first - 1;
        last = first + size - 1;
        nextLast = last + 1;
    }

    @Override
    /** Adds an item of type T to the front of the deque.
     * You can assume that item is never null. */
    public void addFirst(T item) {
        // Resize if necessary
        if (size == items.length) {
            resize(size * 2);
        }

        first = nextFirst;
        // last == -1 meas we are adding the first item to the list
        // therefore we set the last pointer equal to the first pointer
        if (last == -1) {
            last = first;
        }
        items[first] = item;

        // Reset the nextFirst pointer
        if (nextFirst - 1 < 0) {
            nextFirst = items.length - 1;
        } else {
            nextFirst -= 1;
        }
        size += 1;
    }

    @Override
    /** Adds an item of type T to the back of the deque.
     * You can assume that item is never null. */
    public void addLast(T item) {
        // Resize if necessary
        if (size == items.length) {
            resize(size * 2);
        }

        last = nextLast;
        // first == -1 meas we are adding the first item to the list
        // therefore we set the first pointer equal to the last pointer
        if (first == -1) {
            first = last;
        }
        items[last] = item;

        //Reset the nextLast pointer
        if (nextLast + 1 >= items.length) {
            nextLast = 0;
        } else {
            nextLast += 1;
        }

        size += 1;
    }

    @Override
    /** Returns the number of items in the deque. */
    public int size() {
        return size;
    }

    @Override
    /** Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line. */
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }

    @Override
    /** Removes and returns the item at the front of the deque.
     * If no such item exists, returns null. */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        // Reset the nextFirst pointer
        nextFirst = first;

        T firstItem = items[first];
        // Delete the first item
        items[first] = null;

        // Reset the first pointer
        if (first + 1 >= items.length) {
            first = 0;
        } else {
            first += 1;
        }

        size -= 1;
        return firstItem;
    }

    @Override
    /** Removes and returns the item at the back of the deque.
     * If no such item exists, returns null. */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        // Reset the nextLast pointer
        nextLast = last;

        T lastItem = items[last];
        // Delete the last item
        items[last] = null;

        // Reset the last pointer
        if (last - 1 < 0) {
            last = size - 1;
        } else {
            last -= 1;
        }

        size -= 1;
        return lastItem;
    }

    @Override
    /** Gets the item at the given index,
     * where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque! */
    public T get(int index) {
        if (index > size) {
            return null;
        }
        if (first + index >= items.length) {
            return items[first + index - items.length];
        }
        return items[first + index];
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int currentPos;

        public ArrayDequeIterator() {
            currentPos = 0;
        }

        public boolean hasNext() {
            return currentPos < size;
        }

        public T next() {
            T returnItem = get(currentPos);
            currentPos += 1;
            return returnItem;
        }
    }
}
