import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int listsize;

    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }

    // construct an empty deque
    public Deque() {
        this.first = null;
        this.last = null;
        this.listsize = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return (first == null);
    }

    // return the number of items on the deque
    public int size() {
        if (isEmpty())
            return 0;
        return listsize;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        else {
            Node temp = new Node();
            temp.item = item;
            if (isEmpty())
                last = temp;
            else {
                temp.next = first;
                first.prev = temp;
            }
            first = temp;
            listsize++;
        }
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        else {
            Node temp = new Node();
            temp.item = item;
            if (isEmpty())
                first = temp;
            else {
                last.next = temp;
                temp.prev = last;
            }
            last = temp;
            listsize++;
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException();
        else {
            Item temp = first.item;
            if (first.next == null)
                last = null;
            first = first.next;
            if (first != null)
                first.prev = null;
            listsize--;
            return temp;
        }
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException();
        else {
            Item temp = last.item;
            listsize--;
            if (first.equals(last)) {
                first = null;
                last = null;
                return temp;
            }
            Node tempnode = last.prev;
            tempnode.next = null;
            last = tempnode;
            return temp;
        }
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (hasNext()) {
                Item item = current.item;
                current = current.next;
                return item;
            }
            else {
                throw new NoSuchElementException();
            }
        }

    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> d = new Deque<>();
        d.addFirst(1);
        d.addFirst(2);
        d.addLast(3);
        StdOut.print(d.size());
        StdOut.print(d.isEmpty());
        StdOut.print(d.removeFirst());
        StdOut.print(d.removeLast());
        StdOut.print(d.removeFirst());
        StdOut.print(d.isEmpty());
        d.addLast(3);
        StdOut.print(d.removeLast());
        StdOut.print(d.isEmpty());
        d.addFirst(2);
        StdOut.print(d.isEmpty());
        StdOut.print(d.iterator().hasNext());
        StdOut.print(d.size());
        StdOut.print(d.removeLast());
        StdOut.print(d.iterator().hasNext());
    }
}
