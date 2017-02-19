import java.util.ArrayList;

class Main {

    static class Result {
        int cost;
        Coord coord;
        Result next;
        Result(int c, Coord coord, Result next) {
            this.cost = c;
            this.coord = coord;
            this.next = next;
        }
    }

    // Pixel[][] A is the image. int[][] D is the cost map. The arraylist is the
    // list of coords that result in the lowest sum of disruptions
    static int carve_seam(Pixel[][] A, int[][] D, ArrayList<Coord> seam) {
        Result[][] table = new Result[A.length][A[0].length];
        Result res = carve_seam_helper(D, table);
        int resCost = res.cost;
        while (res != null) {
            seam.add(res.coord);
            res = res.next;
        }
        return resCost;
    }

    static Result carve_seam_helper(int[][] D, Result[][] table) {
        Result best_yet = new Result((int) Double.POSITIVE_INFINITY, new Coord(-1, -1), null);
        for (int j = D.length - 1; j != -1; j--) {
            for (int i = 0; i != D[1].length; i++) {
                if (j == D.length - 1) {
                    table[j][i] = new Result(D[j][i], new Coord(j, i), null);
                } else {
                    // Go through the options and choose minimal cost one
                    // 3 options - bottom-left, bottom, bottom-right
                    Result minRes = new Result((int) Double.POSITIVE_INFINITY, new Coord(-1, -1), null);;
                    for (int k = -1; k != 2; k++) {
                        if (i + k >= 0 && i + k <= D.length - 1) { // If it's within the 2d arr
                            Result possibleRes = table[j + 1][i + k];
                            if (possibleRes.cost < minRes.cost) {
                                minRes = possibleRes;
                            }
                        }
                    }
                    int cost = D[j][i] + minRes.cost;
                    table[j][i] = new Result(cost, new Coord(j, i), minRes);
                }
                if ((j == 0) && (table[j][i].cost < best_yet.cost)) {
                    best_yet = table[j][i];
                }
            }
        }
        return best_yet;
    }

    public static void main(String[] args) {
        Pixel r = new Pixel(255, 0, 0);
        Pixel g = new Pixel(0, 255, 0);
        Pixel b = new Pixel (0, 0, 255);
        Pixel[][] img = { {g, r, b, r, r, r},
                          {r, g, r, b, r, r},
                          {b, r, g, r, b, r},
                          {r, b, r, g, r, b},
                          {r, r, b, r, g, r},
                          {r, r, r, b, r, g}}; // 6x6 pixel image

        // You can quickly tell that the optimal path will be starting at
        // (row 6, col 0) and going diagonally until the top row, then choosing the 2.
        // This will result in an optimal cost of 7.
        int[][] testD = { {10, 3, 10, 4, 2, 3},
                          {1, 10, 1, 10, 1, 1},
                          {10, 1, 10, 1, 10, 1},
                          {1, 10, 1, 10, 1, 10},
                          {1, 1, 10, 1, 10, 1},
                          {1, 1, 1, 10, 1, 10}}; // 6x6
        ArrayList<Coord> seam = new ArrayList<Coord>();
        System.out.format("The minimal cost of the seam for the given cost map is %d\n", carve_seam(img, testD, seam));
        System.out.println("The seam path is as follows:");
        for (Coord e : seam) {
            System.out.format("(%d, %d)\n", e.x, e.y);
        }
    }
}
