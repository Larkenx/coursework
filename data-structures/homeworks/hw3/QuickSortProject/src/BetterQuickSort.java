/*
Starting with the Java code for Quicksort given in this chapter, write a series
of Quicksort implementations to test the following optimizations on a wide
range of input data sizes. Try these optimizations in various combinations to
try and develop the fastest possible Quicksort implementation that you can.
    a) Look at more values when selecting a pivot.
    b) Do not make a recursive call to qsort when the list size falls below a
    given threshold, and use Insertion Sort to complete the sorting process.
    Test various values for the threshold size.
    c) Eliminate recursion by using a stack and inline functions.

*/
class BetterQuickSort {
    static int steps = 0;
    static int swaps = 0;
    /* Functions swap, findpivot, partition and qsort */

    static void swap(int[] A, int i, int j) {
        int tmp = A[j];
        A[j] = A[i];
        A[i] = tmp;
        swaps++;
    }

    static void inssort(int[] A) {
        for (int i=1; i<A.length; i++) // Insert i’th record
        for (int j=i; (j>0) && (A[j] < (A[j-1])); j--)
        swap(A, j, j-1);
    }

    static int findpivot(int[] A, int i, int j) {
        int middle = (i+j) / 2;
        int pivot = middle;
        steps += 2;
        if (i+j >= 4) {
            steps += 4;
            int first = middle - 1;
            int second = middle;
            int third = middle + 1;
            if (A[first] < A[second]) {
                steps += 2;
                if (A[second] < A[third])
                    pivot = second;
                else
                    pivot = third;
            } else {
                pivot = first;
            }
        }
        return pivot;
    }

    static int partition(int[] A, int left, int right, int pivot) {
        while (left < right) { // Move bounds inward until they meet
            steps++; // +1 above comparison
            while (A[++left] < pivot) { steps++; }
                while ((right != 0) && (A[--right] > pivot)) {steps += 2;}
                    swap(A, left, right); // Swap out-of-place values
        } // Stop when they cross
        swap(A, left, right); // Reverse last, wasted swap
        return left; // Return first position in right partition
    }

    static void qsort(int[] A, int i, int j) { // Quicksort
        steps += 3; // the if statements == 2 O(1) operations
        int pivotindex = findpivot(A, i, j); // Pick a pivot
        swap(A, pivotindex, j); // Stick pivot at end
        // k will be the first position in the right subarray
        int k = partition(A, i-1, j, A[j]);
        swap(A, k, j); // Put pivot in place
        if ((k-i) > 1) inssort(A); // Sort left partition
        if ((j-k) > 1) inssort(A); // Sort right partition
    }

    public static void printResult(int[] A) {
        System.out.print("Sorted Array: ( ");
        for (int i = 0; i < A.length; i++) System.out.format("%d ", A[i]);
        System.out.print(")\n");
        System.out.format("Total of %d swaps and %d total steps.\n", swaps, steps);
        System.out.println("Standard calculated runtimes for qsort on this array length are:");
        System.out.println("Worst\t\tAverage\t\tBest\t\tActual");
        double len = (double) A.length;
        double nlogn = len*Math.log(len);
        System.out.format("%f\t%f\t%f\t%d", Math.pow(len, 2), nlogn, nlogn, swaps+steps); // n^2, nlogn, nlogn
    }

    public static void main(String[] args) {
        int[] A = {3, 2, 1, 5, 6, 8, 9, 3, 10, 27, 30, 10, 50, 5, 1, 3, 5, 6, 3, 2, 1, 5, 6, 8, 9, 3, 10, 27, 30, 10, 50, 5, 1, 3, 5, 6};
        qsort(A, 0, A.length - 1);
        printResult(A);
    }
}
