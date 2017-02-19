import java.util.*;

abstract class Record {
    private HashMap<Object, Object> map;

    public Record (HashMap<Object, Object> map) {
        this.map = map;
    }

    public HashMap<Object, Object> getMap() {
        return this.map;
    }
}
