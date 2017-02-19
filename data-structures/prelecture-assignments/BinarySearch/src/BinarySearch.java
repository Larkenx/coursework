public class BinarySearch {

    static int binary_search(int x, int[] A, int begin, int end) {
        while (end >= begin) {
            int pos = (begin + end) / 2;
            if (A[pos] == x) return pos;
            if (A[pos] < x) begin = pos + 1;
            if (A[pos] > x) end = pos - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] A = {1,3,4,7,9,14,21,22,26};
        int pos = binary_search(14, A, 0, A.length);
        System.out.println(pos);
    }
}
