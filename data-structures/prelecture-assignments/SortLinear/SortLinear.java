public class SortLinear {

    static int[] linearSort(int[] arr, int k) { // Assuming k is given...
        int[] result = new int[arr.length + 1 ];
        int[] count = new int[k];
        for (int i = 0; i < arr.length; i++) {
            count[arr[i]]++;
        }

        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            result[count[arr[i]]] = arr[i];
            count[arr[i]] = arr[i];
        }

        return result; // Totals to 2i + k runtime?
    }

    public static void main(String[] args) {
        int[] test = {3,5,2,2,8,3};
        int[] res = linearSort(test, 9);
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i] + " ");
        }
    }
}
