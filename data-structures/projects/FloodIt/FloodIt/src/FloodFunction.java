import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Iterator;


public class FloodFunction {
    public Driver driver;
    public List<Coord> flooded_list = new LinkedList<>(); // Store flooded tiles here

    public FloodFunction(final Driver _driver) {
        this.driver = _driver;
        this.flooded_list.add(new Coord(0, 0));
    }

    public boolean inbound(final Coord coord) {
        return coord.x > -1 && coord.x < this.driver.size && coord.y > -1 && coord.y < this.driver.size;
    }

    public Coord up(final Coord coord) {
        return new Coord(coord.x, coord.y-1);
    }

    public Coord down(final Coord coord) {
        return new Coord(coord.x, coord.y+1);
    }

    public Coord left(final Coord coord) {
        return new Coord(coord.x-1, coord.y);
    }

    public Coord right(final Coord coord) {
        return new Coord(coord.x+1, coord.y);
    }

    public void flood(final Map color_of_tiles, final Integer color) {
    	// students: put your code here!
        Iterator it = color_of_tiles.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry e = (Map.Entry) it.next();
            Coord posn = (Coord) e.getKey();
            Integer currColor = (int) e.getValue();
            if (currColor.equals(color) && this.inbound(posn)) {
                if (flooded_list.contains(left(posn)) ||
                    flooded_list.contains(right(posn)) ||
                    flooded_list.contains(down(posn)) ||
                    flooded_list.contains(up(posn))) {
                    this.flooded_list.add(posn);
                }
            }
        }
    }
}
