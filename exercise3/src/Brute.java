
public class Brute {

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

        for (int i = 0; i < count; ++i)
            for (int j = i + 1; j < count; ++j) {
                double s1 = points[i].slopeTo(points[j]);
                for (int k = j + 1; k < count; ++k) {
                    double s2 = points[i].slopeTo(points[k]);

                    if (s1 != s2)
                        continue;
                    for (int l = k + 1; l < count; ++l) {
                        double s3 = points[i].slopeTo(points[l]);
                        if (s1 == s3) {
                            points[i].drawTo(points[j]);
                            points[i].drawTo(points[k]);
                            points[i].drawTo(points[l]);
                            System.out.println(points[i] + " -> " + points[j]
                                    + " -> " + points[k] + " -> " + points[l]);
                        }
                    }
                }
            }

        StdDraw.show(0);
    }
}
