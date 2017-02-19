public class Permutation {

    static void getPermutations(int[] arr, int pos) {
        // Base Cases
        if (pos >= arr.length - 1) {
            String result = "[";
            for(int i = 0; i < arr.length - 1; i++) {
                result+= arr[i] + ", ";
            }
            if (arr.length > 0) {
                result+= arr[arr.length - 1];
            }
            System.out.println(result + "]");
            return;
        }

        for (int i = pos; i < arr.length; i++) {
            int tmp = arr[pos];
            arr[pos] = arr[i];
            arr[i] = tmp;
            // We recurse on the array, incrementing the pos by one
            getPermutations(arr, pos + 1);
            tmp = arr[pos];
            arr[pos] = arr[i];
            arr[i] = tmp;
        }
    }

    public static void main(String[] args) {
        int[] test = {1, 2, 3, 4};
        getPermutations(test, 0);
    }
}
