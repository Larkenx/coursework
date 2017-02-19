import java.util.*;


public class Routing {

    // Mark the coordinate as visited
    static private void visit(Coord pos, boolean[][] visited) { visited[pos.y][pos.x] = true; }

    // Return whether or not the point is visited
    static private boolean visited(Coord pos, boolean[][] visited) { return visited[pos.y][pos.x];}

    static void bfs(Board board, Coord start, boolean[][] visited, Map<Coord,Coord> parents) {
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[0].length; j++) {
                visited[i][j] = false; // Technically boolean[][] is already initialized to false
                Coord linkToSelf = new Coord(j,i);
                parents.put(linkToSelf, linkToSelf);
            }
        }
        LinkedList<Coord> Q = new LinkedList<>();
        Q.add(start);
        visit(start, visited);
        while (! Q.isEmpty()) {
            Coord u = Q.remove();
            for (Coord v : board.adj(u)) { /* We also need to check if it's an obstacle */
                if (! visited(v, visited) &&
                    (board.getValue(v) == board.getValue(start) || board.getValue(v) == 0)) {
                    parents.put(v, u);
                    Q.add(v);
                    visit(v, visited);
                }
            }
        }
    }

    static void printPaths(ArrayList<Coord[]> points) {
        System.out.println("[");
        int count = 0;
        for (Coord[] pair : points) {
            System.out.format("%d : %s -> %s\n", count, pair[0].toString(), pair[1].toString());
        }
        System.out.println("]");

    }

    static ArrayList<Coord> backtrack(Coord start, Coord end, Map<Coord, Coord> parents) {
        ArrayList<Coord> wire = new ArrayList<>();
        if (start.equals(end)) {
            wire.add(start);
            return wire;
        }
        Coord current = end;
        while (current != start) {
            wire.add(0, current);
            Coord next = parents.get(current);
            /* As we lay more wires, more spots become unavailable on the chip
             * and it's possible that one of the potential paths from src to sink
             * for some wire placement isn't possible after all the other wires have been
             * optimally placed. If this is the case, then we will end up with some node
             * pointing back to itself (the initial state in bfs), where that node is NOT
             * the starting coord. If we reach such a node, we need to return null to signal
             * that there is no such wire that can be laid. */
            if (next == current && current != start) {return null;}
            current = next;
        }
        wire.add(0, start);
        return wire;
    }

    /* This is the proper solution for all the test cases except for wire5.in and wire8.in */
    public static ArrayList<ArrayList<Coord>> findPaths2(Board board, ArrayList<Coord[]> points) {
        ArrayList<ArrayList<Coord>> res = new ArrayList<>();
         /*As we lay each wire, we need to mark off the possible
         coordinates we can lay future wires. To do this, we will just take the
         optimal path of the last completed wire placement, and place its coordinate
         path as obstacles on the board.*/

        for (Coord[] pair : points) {
            Coord src = pair[0];
            Coord sink = pair[1];
            HashMap<Coord, Coord> parents =  new HashMap<>();
            bfs(board, src, new boolean[board.height][board.width], parents);
            ArrayList<Coord> wire = backtrack(src, sink, parents);
            /* If we successfully laid the wire, make its path obstacles in the board
               Instead of actually placing a '-1' for each coord in a path, I am
               going to use the number value of the path itself (e.g if we are connecting
               13 to 13, the path from src to sink is 13's. This is easy to see with
               the pretty grid print function in the driver main. */
            if (wire != null) {
                for (int i = 0; i < wire.size(); i++) {
                    if (i != 0 && i != wire.size() - 1) {
                        board.putValue(wire.get(i), board.getValue(wire.get(0)));
                    }
                }
            }
            res.add(wire);
        }
        return res;
    }

    static public class CustomComparator implements Comparator<Coord[]> {

        private int distanceBetween(Coord[] pair) {
            Coord a = pair[0];
            Coord b = pair[1];
            return (int) Math.sqrt(Math.pow((a.x - b.x), 2) + Math.pow((a.y - b.y), 2));
        }

        @Override
        public int compare(Coord[] o1, Coord[] o2) {
            return Integer.compare(distanceBetween(o1), distanceBetween(o2));
        }
    }

    /* This is the solution that gets all of the test cases by sorting the points ArrayList */
    public static ArrayList<ArrayList<Coord>> findPaths(Board board, ArrayList<Coord[]> points) {
        ArrayList<ArrayList<Coord>> res = new ArrayList<>();
        // Same as findPaths2 above, but we are going to sort all of our initial points
        // so that we do points that are closer together first

//        System.out.println("Before sorting:");
//        printPaths(points);
        Collections.sort(points, new CustomComparator());
//        System.out.println("After sorting:");
//        printPaths(points);
//

        for (Coord[] pair : points) {
            Coord src = pair[0];
            Coord sink = pair[1];
            HashMap<Coord, Coord> parents =  new HashMap<>();
            bfs(board, src, new boolean[board.height][board.width], parents);
            ArrayList<Coord> wire = backtrack(src, sink, parents);
            if (wire != null) {
                for (int i = 0; i < wire.size(); i++) {
                    if (i != 0 && i != wire.size() - 1) {
                        board.putValue(wire.get(i), board.getValue(wire.get(0)));
                    }
                }
            }
            res.add(wire);
        }
        return res;
    }

    public static void main(String[] args) {
        Driver driver = new Driver();
        driver.test("Inputs/wire5.in");
        driver.test("Inputs/wire8.in");
    }



}
