import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Comparator;

public class HeapImp<E> implements Heap<E> {
    private ArrayList<E> data;
    private Comparator<E> compare;
    private int heapSize;

     public static class MyComparator implements Comparator<Integer> {

         public int compare(Integer b, Integer a) {
             return b.compareTo(a);
         }
     }

    HeapImp(ArrayList<E> data, Comparator<E> compare) {
        this.data = data;
        this.compare = compare;
        this.heapSize = data.size();
        buildMinHeap();
    }

    /* Swaps i and j in arr */
    public void swap(ArrayList<E> arr, int i, int j) {
        E tmp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, tmp);
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

    /* Takes a given index and propagates the element E at the given index
    *  up until its parent is no longer greater than E at the given index */
    public void increase_key(int i) {
        while (i != 0 && compare.compare(data.get(parent(i)), data.get(i)) > 0) {
            int parentIndex = parent(i);
            swap(data, i, parentIndex);
            i = parentIndex;
        }
    }


    @Override
    public E minimum() {
	    return data.get(0);
    }

    @Override
    public void insert(E e) { // Add one to the heapSize
        data.add(e);
        heapSize++;
        increase_key(heapSize - 1);
    }


    @Override
    public E extractMin() {
	    E min = minimum();
        if (heapSize > 1) data.set(0, data.remove(heapSize - 1));
        else data.remove(heapSize - 1);
        heapSize--;
        minHeapify(0);
        return min;
    }

    @Override
    public void minHeapify(int i) {
        int l = left(i);
        int r = right(i);
        int smallest = (l < heapSize && compare.compare(data.get(i), data.get(l)) > 0) ? l : i;
        if (r < heapSize && compare.compare(data.get(smallest), data.get(r)) > 0)
            smallest = r;
        if (smallest != i) {
            swap(data, i, smallest);
            minHeapify(smallest);
        }
    }


    @Override
    public void buildMinHeap() {
        int lastParent = heapSize / 2 - 1;
        for (int i = lastParent; i != -1; i--) {
            minHeapify(i);
        }
    }

    public String toString() {
	       return data.toString();
    }

    public static void main(String[] args) {
        /* Test Case 1 */
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(5);
        arr.add(1);
        arr.add(2);
        arr.add(7);
        arr.add(8);
        arr.add(3);

        HeapImp<Integer> heap = new HeapImp(arr, new MyComparator());
        int[] heapifiedHeap = {1, 2, 3, 5, 7, 8};
        for (int i = 0; i < heapifiedHeap.length; i++) {
            assert heap.extractMin() == heapifiedHeap[i];
        }

        /* Test Case 2 */
        ArrayList<Integer> arr2 = new ArrayList<>();
        HeapImp<Integer> heap2 = new HeapImp(arr2, new MyComparator());
        heap2.insert(5);
        assert heap2.toString().equals("[5]");
        heap2.insert(1);
        assert heap2.toString().equals("[1, 5]");
        heap2.insert(2);
        assert heap2.toString().equals("[1, 5, 2]");
        heap2.insert(3);
        assert heap2.toString().equals("[1, 3, 2, 5]");
        int[] heapifiedHeap2 = {1, 2, 3, 5};
        for (int i = 0; i < heapifiedHeap2.length; i++) {
            assert heap2.extractMin() == heapifiedHeap2[i];
        }
    }
}
