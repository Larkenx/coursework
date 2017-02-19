public class Rotate {
    static void rotate(int[] A) {
        int[] tmp = new int[A.length];
        if (A.length > 0) {
            tmp[0] = A[A.length - 1];
            for (int i = 1; i < A.length; i++) {
                tmp[i] = A[i - 1];
            }
            System.arraycopy(tmp, 0, A, 0, A.length);
        }
    }

    static void printArr(int[] A) {
        String str = "[ ";
        for (int i = 0; i < A.length; i++) {
            str = str + A[i] + ' ';
        }
        System.out.println(str + ']');
    }

    public static void main(String[] args) {
        int[] test = {7,4,12,53,32};
        printArr(test);
        rotate(test);
        printArr(test);
        rotate(test);
        printArr(test);
    }
}
