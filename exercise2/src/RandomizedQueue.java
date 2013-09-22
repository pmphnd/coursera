import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] items; // array of items
    private int nextFreePos;
    private int used; // number of used elements in queue

    public RandomizedQueue() // construct an empty randomized queue
    {
        items = (Item[]) new Object[4];
        nextFreePos = 0;
        used = 0;

    }

    public boolean isEmpty() // is the queue empty?
    {
        return used == 0;
    }

    public int size() // return the number of items on the queue
    {
        return used;
    }

    private void resize(int capacity) {

        Item[] temp = (Item[]) new Object[capacity];
        int j = 0;
        for (int i = 0; i < nextFreePos; i++) {
            if (items[i] != null)
                temp[j++] = items[i];
        }
        items = temp;
        nextFreePos = j;
    }

    public void enqueue(Item item) // add the item
    {
        if (item == null)
            throw new java.lang.NullPointerException();

        if (nextFreePos == items.length)
            resize(2 * items.length);

        items[nextFreePos++] = item;
        used++;
    }

    public Item dequeue() // delete and return a random item
    {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        int pick = StdRandom.uniform(nextFreePos);
        while (items[pick] == null)
            pick = StdRandom.uniform(nextFreePos);

        Item item = items[pick];
        items[pick] = null;
        used--;
        if (used > 0 && used == items.length / 4)
            resize(items.length / 2);
        return item;
    }

    public Item sample() // return (but do not delete) a random item
    {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        int pick;
        pick = StdRandom.uniform(nextFreePos);
        while (items[pick] == null)
            pick = StdRandom.uniform(nextFreePos);

        return items[pick];
    }

    public Iterator<Item> iterator() {
        return new RandomIterator();

    } // return an independent
      // iterator over items in random order

    // an iterator, doesn't implement remove() since it's optional
    private class RandomIterator implements Iterator<Item> {

        private int[] visitor = new int[nextFreePos];

        RandomIterator() {
            int j = 0;
            for (int i = 0; i < nextFreePos; i++)
                visitor[j++] = 0;

        }

        public boolean hasNext() {
            for (int i = 0; i < nextFreePos; i++)
                if (visitor[i] == 0 && items[i] != null)
                    return true;
            return false;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();

            int pick = StdRandom.uniform(nextFreePos);
            while (visitor[pick] == 1 || items[pick] == null)
                pick = StdRandom.uniform(nextFreePos);
            visitor[pick] = 1;
            return items[pick];
        }
    }

    private void print() {
        for (int i = 0; i < nextFreePos; i++)
            System.out.print(items[i] + " ");
        System.out.println("Lenght" + items.length + " N=" + nextFreePos + " Used="
                + used);

    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        for (int i = 0; i < 20; ++i) {

            // d.addFirst(i);
            rq.enqueue(i);
            // rq.print();
        }

        Iterator<Integer> it = rq.iterator();
        while (it.hasNext())
            System.out.print(it.next() + " ");
        // for (int i = 0; i < 15; ++i) {
        //
        // // d.addFirst(i);
        // rq.dequeue();
        // rq.print();
        // }

    }
}
