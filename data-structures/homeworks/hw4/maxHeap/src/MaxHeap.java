public class MaxHeap {
    int[] data;
    int num_elts;

    MaxHeap(int[] d) {
        this.data = d;
        this.num_elts = d.length;
        build_max_heap();
    }

    /* Swaps i and j in arr */
    public void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /* Returns the index of the element to the left of this element in the heap */
    public int left(int i) {
        return 2*i + 1;
    }

    /* Returns the index of the element to the right of this element in the heap */
    public int right(int i) {
        return 2*(i + 1);
    }

    /* Returns the parent of a given index in a heap*/
    public int parent(int i) {
        return (int) Math.floor((i - 1) / 2);
    }

    public void max_heapify(int i) {
        int l = left(i);
        int r = right(i);
        int largest;
        if (l < num_elts && data[i] < data[l]) { largest = l; }
        else { largest = i; }
        if (r < num_elts && data[largest] < data[r]) { largest = r; }
        if (largest != i) {
            swap(data, i, largest);
            max_heapify(largest);
        }
    }

    public void build_max_heap() {
        int last_parent = num_elts / 2 - 1;
        for (int i = last_parent; i != -1; --i) {
            max_heapify(i);
        }
    }

    public int extract_max() {
        int max = data[0];
        data[0] = data[num_elts-1];
        num_elts -= 1;
        max_heapify(0);
        return max;
    }

    public String toString() {
        String buf = "(";
        for (int i = 0; i < num_elts; i++) {
            buf += data[i] + " ";
        }
        return buf + ")";
    }

    public static void main(String[] args) {
        int[] arr = {7, 5, 6, 4, 2, 1, 3};
        MaxHeap test = new MaxHeap(arr);
        test.extract_max();
        System.out.println(test.toString());
    }
}
