package deque;

public class LinkedListDeque<T> implements Deque<T> {
    /* The first item, if it exists, is at sentinel.next . */
    private StuffNode sentinel;
    private int size;

    private class StuffNode {
        public T item;
        public StuffNode prev;
        public StuffNode next;

        public StuffNode(T i, StuffNode p, StuffNode n) {
            item = i;
            prev = p;
            next = n;
        }

        /** Helper method for getRecursive.
         * Returns the item at index of a StuffNode. */
        public T getRecursiveHelper(int index) {
            if (index == 0) {
                return item;
            }
            return next.getRecursiveHelper(index - 1);
        }
    }

    public LinkedListDeque(T t) {
        sentinel = new StuffNode((T)"null", null, null);
        sentinel.next = new StuffNode(t, sentinel, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }



    /** Creates an empty LinkedListDeque. */
    public LinkedListDeque() {
        sentinel = new StuffNode((T) "null", null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    @Override
    /** Adds an item of type T to the front of the deque.
     * You can assume that item is never null. */
    public void addFirst(T item) {
        sentinel.next = new StuffNode(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    @Override
    /** Adds an item of type T to the back of the deque.
     * You can assume that item is never null. */
    public void addLast(T item) {
        sentinel.prev = new StuffNode(item, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
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
        StuffNode s = sentinel.next;
        while (s != sentinel) {
            System.out.print(s.item + " ");
            s = s.next;
        }
        System.out.println();
    }

    @Override
    /** Removes and returns the item at the front of the deque.
     * If no such item exists, returns null. */
    public T removeFirst() {
        if (sentinel.next == sentinel) {
            return null;
        }
        T firstItem = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        /* Update the prev pointer. */
        sentinel.next.prev = sentinel;
        size -= 1;
        return firstItem;
    }

    @Override
    /** Removes and returns the item at the back of the deque.
     * If no such item exists, returns null. */
    public T removeLast() {
        if (sentinel.prev == sentinel) {
            return null;
        }
        T lastItem = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        /* Update the next pointer. */
        sentinel.prev.next = sentinel;
        size -= 1;
        return lastItem;
    }

    @Override
    /** Gets the item at the given index,
     * where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque! */
    public T get (int index) {
        if (index > size) {
            return null;
        }
        StuffNode s = sentinel.next;
        T result = s.item;
        for (int i = 0; i <= index; i++) {
            result = s.item;
            s = s.next;
        }
        return result;
    }

    /** Same us get, but uses recursion. */
    public T getRecursive(int index) {
        if (index > size || index < 0) {
            return null;
        }
        return sentinel.next.getRecursiveHelper(index);
    }
}
