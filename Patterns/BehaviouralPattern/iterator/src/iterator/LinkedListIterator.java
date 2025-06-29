package iterator;
import datastructures.LinkedList;

public class LinkedListIterator implements Iterator<Integer> {
    private LinkedList current;

    public LinkedListIterator(LinkedList head) {
        current = head;
    }

    public boolean hasNext() {
        return current != null;
    }

    public Integer next() {
        int val = current.data;
        current = current.next;
        return val;
    }
}