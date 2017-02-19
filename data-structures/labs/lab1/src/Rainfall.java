import java.util.*;


/**
 * Created by larken on 8/25/16.
 */

public class Rainfall {

    public static int average(ArrayList<Integer> arr) {
        int posCount = 0;
        int sum = 0;
        for (int number : arr) {
            posCount++;
            sum += number;
        }
        return sum / posCount;
    }

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<Integer>();
        Scanner scan = new Scanner(System.in);
        while (true) {
            int curr = scan.nextInt();
            if (curr == -999) {
                break;
            } else if (curr > -1) {
                arr.add(curr);
            }
        }
        System.out.println(average(arr));
    }
}
