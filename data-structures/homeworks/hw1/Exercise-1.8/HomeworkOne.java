import java.util.*;
/**
 * Created by larken on 8/25/16.
 */
public class HomeworkOne {
    static Object simpleSearch(Record[] records, Object key) {
        for (int i = 0; i < records.length; i++) {
            HashMap<Object, Object> currMap = records[i].getMap();
            if (currMap.containsKey(key)) {
                Object result = currMap.get(key);
                System.out.println("Record " + i + " contains the key.");
                return result;
            }
        }
        System.out.println("Record doesn't contain the given key!");
        return null;
    }
}
