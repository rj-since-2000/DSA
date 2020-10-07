import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int[][][] grid;
    private final int nn;
    private int count;
    private final int top;
    private final int bot;
    private final WeightedQuickUnionUF qu;

    public Percolation(int n) {
        nn = n;
        top = 0;
        bot = (nn * nn + 1);
        int size = nn * nn + 2;
        qu = new WeightedQuickUnionUF(size);
        if (n <= 0)
            throw new IllegalArgumentException("n should be greater than  0");
        else {
            grid = new int[n][n][2];
            map();
        }
        count = 0;
    }


    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        int r = (row - 1);
        int c = (col - 1);
        if (r < 0 || c < 0 || r > nn - 1 || c > nn - 1)
            throw new IllegalArgumentException("row and col should be in range");
        else {
            if (grid[r][c][1] != 1) {
                grid[r][c][1] = 1;
                count++;
                if (((r - 1) >= 0) && isOpen((row - 1), col))
                    qu.union(grid[r - 1][c][0], grid[r][c][0]);
                if (((r + 1) <= (nn - 1)) && isOpen((row + 1), col))
                    qu.union(grid[r + 1][c][0], grid[r][c][0]);
                if ((c - 1 >= 0) && isOpen(row, (col - 1)))
                    qu.union(grid[r][c - 1][0], grid[r][c][0]);
                if ((c + 1 <= nn - 1) && isOpen(row, (col + 1)))
                    qu.union(grid[r][c + 1][0], grid[r][c][0]);
                if (r == 0)
                    qu.union(top, grid[r][c][0]);
                if (r == nn - 1)
                    qu.union(bot, grid[r][c][0]);

            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 1 || col < 1 || row > nn || col > nn)
            throw new IllegalArgumentException("row and col should be in range");
        return grid[row - 1][col - 1][1] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 1 || col < 1 || row > nn || col > nn)
            throw new IllegalArgumentException("row and col should be in range");
        return qu.connected(grid[row - 1][col - 1][0], top);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return count;
    }

    // does the system percolate?
    public boolean percolates() {
        return qu.connected(top, bot);
    }

    private void map() {
        int k = 1;
        for (int i = 0; i < nn; i++) {
            for (int j = 0; j < nn; j++) {
                grid[i][j][0] = k++;
                grid[i][j][1] = 0;
            }
        }
    }

    // test client (optional)
    public static void main(String[] args) {
        int i = 5;
        Percolation p = new Percolation(i);
        while (!p.percolates()) {
            int row = StdIn.readInt();
            int col = StdIn.readInt();
            p.open(row, col);
        }
        System.out.println("percolated");
    }
}




