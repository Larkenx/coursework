class SortingNumbers {

    static void swap(int[] A, int i, int j) {
        int tmp = A[j];
        A[j] = A[i];
        A[i] = tmp;
    }

    static void sortThree(int[] A) {
        if (A[0] > A[1]) // 0 -> 1
            swap(A, 0, 1);
        if (A[1] > A[2]) { // 1 -> 2
            if (A[2] < A[0]) {
                swap(A, 1, 2);
                swap(A, 0, 1);
            } else {
                swap(A, 1, 2);
            }
        }
    }

    public static void printArr(int[] A) {
        System.out.print("( ");
        for (int i = 0; i < A.length; i++) System.out.format("%d ", A[i]);
        System.out.print(")");
    }

    public static void main(String[] args) {
        /* Sorting three numbers */
        // int[] triple = {1, 2, 3};
        // sortThree(triple);
        // printArr(triple);
    }
}
