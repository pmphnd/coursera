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
        
        Map<List<Point>, String> results = new HashMap<List<Point>, String>();

        for (int i = 0; i < count; i++) {
            Arrays.sort(points);
            Arrays.sort(points, points[i].SLOPE_ORDER);
            int currentIndex = 0;
            int j = 1;
            double current = 0.0;

            while (j < count) {
                int linelen = 1;

                current = points[0].slopeTo(points[j]);
                currentIndex = j;
                boolean skip = false;
                while (((j + 1) < count)
                        && current == points[0].slopeTo(points[j + 1])) {
                    ++linelen;
                    j++;
                    skip = true;
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
                if (!skip)
                    j++;
            }
        }

        Iterator<List<Point>> it = results.keySet().iterator();
        while (it.hasNext()) {
            List<Point> r = it.next();
            for (int k = 0; k < r.size(); ++k) {

                System.out.print(r.get(k));
                if (k < (r.size() - 1))
                    System.out.print(" -> ");
                else
                    System.out.println("");
            }
            r.get(0).drawTo(r.get(r.size() - 1));
        }
        StdDraw.show(0);

    }

}
