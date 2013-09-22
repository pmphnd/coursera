import java.util.Iterator;

public class Subset {

    private RandomizedQueue<String> rq = new RandomizedQueue<String>();

    public static void main(String[] args) {

        Subset subset = new Subset();

        int k = Integer.parseInt(args[0]);

        while (!StdIn.isEmpty())
            subset.rq.enqueue(StdIn.readString());

        Iterator<String> it = subset.rq.iterator();

        for (int i = 0; i < k; i++)
            System.out.println(it.next());

    }
}