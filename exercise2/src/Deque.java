import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int size;
    private Node first;
    private Node last;

    // helper linked list class
    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }

    public Deque() // construct an empty deque
    {
        size = 0;
        first = null;
        last = null;
    }

    public boolean isEmpty() // is the deque empty?
    {
        return (size == 0);
    }

    public int size() // return the number of items on the deque
    {
        return size;
    }

    public void addFirst(Item item) // insert the item at the front
    {
        if (item == null)
            throw new java.lang.NullPointerException();

        Node temp = new Node();
        temp.item = item;
        temp.prev = null;

        temp.next = first;

        if (first == null)
            last = temp;
        else
            first.prev = temp;

        first = temp;

        ++size;
    }

    public void addLast(Item item) // insert the item at the end
    {
        if (item == null)
            throw new java.lang.NullPointerException();

        Node temp = new Node();
        temp.item = item;
        temp.next = null;

        temp.prev = last;

        if (last == null)
            first = temp;
        else
            last.next = temp;

        last = temp;

        ++size;

    }

    public Item removeFirst() // delete and return the item at the front
    {
        if (size == 0)
            throw new java.util.NoSuchElementException();

        Node temp = first;
        Item retVal = null;

        if (first.next != null)
            first.next.prev = null;

        retVal = temp.item;
        first = first.next;
        if (first == null)
            last = null;

        temp = null;
        --size;
        return retVal;
    }

    public Item removeLast() // delete and return the item at the end
    {
        if (size == 0)
            throw new java.util.NoSuchElementException();

        Node temp = last;
        Item retVal = null;

        if (last.prev != null)
            last.prev.next = null;

        retVal = temp.item;
        last = last.prev;
        if (last == null)
            first = null;

        temp = null;
        --size;
        return retVal;
    }

    public Iterator<Item> iterator() // return an iterator over items in order
                                     // from front to end
    {
        return new ListIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    private void print() {
        Node node = first;
        while (node != null) {
            System.out.print(node.item + " ");
            node = node.next;

        }
        System.out.println("");
    }

    public static void main(String[] args) {
        Deque<Integer> d = new Deque<Integer>();
        for (int i = 0; i < 10; ++i) {

            // d.addFirst(i);
            d.addLast(i);
            d.print();
        }
        // for (int i = 0; i < 11; ++i) {
        //
        // // d.removeLast();
        // d.removeFirst();
        // d.print();
        // }
        Iterator<Integer> it = d.iterator();

        while (it.hasNext())
            System.out.print(it.next() + " ");
    }
}