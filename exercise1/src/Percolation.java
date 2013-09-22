public class Percolation {
    private int[][] array;
    private WeightedQuickUnionUF uf;
    private int N;
    private int vTop;
    private int vBottom;

    public Percolation(int N) // create N-by-N grid, with all sites blocked
    {

        vTop = 0;
        vBottom = 0;
        this.N = N;

        array = new int[N + 1][N + 1];
        for (int i = 0; i < N + 1; i++)
            for (int j = 0; j < N + 1; j++)
                array[i][j] = 0;

        uf = new WeightedQuickUnionUF(N * N + 2);

        for (int i = 0; i < N; ++i)
            uf.union(N * N, i);

        for (int i = N * N - 1; i > N * N - 1 - N; --i)
            uf.union(N * N + 1, i);

    }

    public void open(int i, int j)// open site (row i, column j) if it is not already
    {

        indicesAreValidException(i, j);
        array[i][j] = 1;

        if (i == 1)
            vTop = 1;

        if (i == N)
            vBottom = 1;

        if (indicesAreValid(i - 1, j) && array[i - 1][j] == 1)
            uf.union(xyTo1D(i - 1, j), xyTo1D(i, j));

        if (indicesAreValid(i + 1, j) && array[i + 1][j] == 1)
            uf.union(xyTo1D(i + 1, j), xyTo1D(i, j));

        if (indicesAreValid(i, j - 1) && array[i][j - 1] == 1)
            uf.union(xyTo1D(i, j - 1), xyTo1D(i, j));

        if (indicesAreValid(i, j + 1) && array[i][j + 1] == 1)
            uf.union(xyTo1D(i, j + 1), xyTo1D(i, j));

    }

    public boolean isOpen(int i, int j) // is site (row i, column j) open?
    {

        indicesAreValidException(i, j);
        return array[i][j] == 1;
    }

    public boolean isFull(int i, int j) // is site (row i, column j) full?
    {
        return isOpen(i, j) && (vTop == 1) && uf.connected(xyTo1D(i, j), N * N);

    }

    public boolean percolates() // does the system percolate?
    {
        return (vTop == 1) && (vBottom == 1) && uf.connected(N * N + 1, N * N);

    }

    private int xyTo1D(int i, int y) {
        return (i - 1) * N + y - 1;

    }

    private boolean indicesAreValid(int i, int j) {

        if (!((i >= 1 && i <= N) && (j >= 1 && j <= N)))
            return false;
        return true;

    }

    private void indicesAreValidException(int i, int j) {

        if (!indicesAreValid(i, j))
            throw new IndexOutOfBoundsException("row index i or j out of bounds");
    }
}
