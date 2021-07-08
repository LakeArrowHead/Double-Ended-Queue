package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque {

    private Comparator<T> ourComparator;

    /** Creates a MaxArrayDeque with the given Comparator. */
    public MaxArrayDeque(Comparator<T> c) {
        super();
        ourComparator = c;
    }

    /** returns the maximum element in the deque as governed by the previously given Comparator.
    If the MaxArrayDeque is empty, simply return null.
    */
    public T max() {
        int size = super.size();
        if (size == 0) {
            return null;
        }
        T maxItem = (T) this.get(0);
        for (int i = 0; i < size; i++) {
            if (ourComparator.compare((T) this.get(i), maxItem) > 0) {
                maxItem = (T) this.get(i);
            }
        }
        return maxItem;
    }

    /**  returns the maximum element in the deque as governed by the parameter Comparator c.
    If the MaxArrayDeque is empty, simply return null.
    */
    public T max(Comparator<T> c) {
        int size = super.size();
        if (size == 0) {
            return null;
        }
        T maxItem = (T) this.get(0);
        for (int i = 0; i < size; i++) {
            if (c.compare((T) this.get(i), maxItem) > 0) {
                maxItem = (T) this.get(i);
            }
        }
        return maxItem;
    }

    public class DequeComparator implements Comparator<T> {

        @Override
        public int compare(T t1, T t2) {
            if (t1.equals(t2)) {
                return 0;
            }
            return 1;
        }
    }
}
