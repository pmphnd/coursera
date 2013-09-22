import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Fast {

    public static void main(String[] args) {

        // rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.setPenRadius(0.01);

        In in = new In(args[0]);
        int count = in.readInt();

        Point[] points = new Point[count];

        for (int i = 0; i < count; ++i) {
            points[i] = new Point(in.readInt(), in.readInt());
            points[i].draw();
        }

        List<Point> a = new ArrayList<Point>();
        a.add(points[0]);

        List<Point> b = new ArrayList<Point>();
        b.add(points[1]);

        Map<List<Point>, String> results = new HashMap<List<Point>, String>();

        for (int i = 0; i < count; ++i) {

            Arrays.sort(points, points[i].SLOPE_ORDER);

            int linelen = 1;
            int currentIndex = 0;
            int j = 1;
            double current = 0.0;

            while (j + 1 < count) {

                current = points[0].slopeTo(points[j]);
                currentIndex = j;

                while (((j + 1) < count)
                        && current == points[0].slopeTo(points[++j])) {
                    ++linelen;
                }
                if (linelen >= 3) {

                    List<Point> r = new ArrayList<Point>();
                    r.add(points[0]);

                    for (int k = currentIndex; k < currentIndex + linelen; ++k) {

                        r.add(points[k]);

                    }
                    Collections.sort(r);
                    results.put(r, "");

                }
                linelen = 1;

            }

        }

        Iterator<List<Point>> it = results.keySet().iterator();
        while (it.hasNext()) {
            List<Point> r = it.next();
            for (int k = 0; k < r.size(); ++k) {
                
                r.get(0).drawTo(r.get(k));
                
                System.out.print(r.get(k));
                if (k < (r.size() - 1))
                    System.out.print(" -> ");
                else
                    System.out.println("");
            }

        }
        StdDraw.show(0);

    }

}
