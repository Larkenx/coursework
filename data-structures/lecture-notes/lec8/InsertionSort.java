class InsertionSort {
    void insertion_sort(int[] A) {
        for (int j = 1; j != A.length; j++) {
            int key = A[j];
            int i = j - 1;
            while ((i >= 0) && (A[i] > key)) {
                A[i+1] = A[i];
                i--;
            }
        
        }
    }

}
