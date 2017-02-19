public class Main {
    // create extra helper functions as needed

    static <E extends Comparable<? super E>>
    Iterator<E> partition_helper(Iterator<E> begin, Iterator<E> end) {
        Iterator<E> a = begin.clone();
        Iterator<E> b = a.clone();
        b.advance();
        while (! b.equals(end)) {
            a.advance();
            b.advance();
        }
        return a;
    }

    static <E extends Comparable<? super E>>
    Iterator<E> partition(Iterator<E> begin, Iterator<E> end) {
        Iterator<E> pivot = partition_helper(begin, end); // Collection[End - 1]
        Iterator<E> i = begin.clone();
        for (Iterator<E> j = begin.clone(); ! j.equals(pivot); j.advance()) {
            if (j.get().compareTo(pivot.get()) < 0) {
                // Now swap
                E tmp = i.get();
                i.set(j.get());
                j.set(tmp);
                i.advance();
            }
        }
        // Swap first and last
        E tmp = i.get();
        i.set(pivot.get());
        pivot.set(tmp); // Change end
        return i;
    }

    static <E extends Comparable<? super E>>
    void quicksort(Iterator<E> begin, Iterator<E> end) {
        if (! begin.equals(end)) {
            Iterator<E> pivot = partition(begin, end);
            quicksort(begin, pivot);
            quicksort(pivot.advance(), end);
        }
    }

    public static void main(String[] args) {
        /* Lists */
       List<Integer> testList = new List<Integer>();
       testList.addFirst(2);
       testList.addFirst(1);
       testList.addFirst(5);
       testList.addFirst(7);
       testList.addFirst(9);
       Iterator<Integer> listIter = testList.begin();
       String result = "";
       while (! listIter.equals(testList.end())) {
           result += listIter.get() + " ";
           listIter = listIter.advance();
       }
       System.out.println(result);
       quicksort(testList.begin(), testList.end());

       result = "";
       listIter = testList.begin();
       while (! listIter.equals(testList.end())) {
           result += listIter.get() + " ";
           listIter = listIter.advance();
       }
       System.out.println(result);

       /* Arrays */
       Array<Integer> testArr = new Array<Integer>();
       testArr.add(10);
    //    testArr.add(9);
    //    testArr.add(8);
    //    testArr.add(7);
    //    testArr.add(7);
       Iterator<Integer> arrIter = testArr.begin();
       result = "";
       while (! arrIter.equals(testArr.end())) {
           result += arrIter.get() + " ";
           arrIter = arrIter.advance();
       }
       System.out.println(result);
       quicksort(testArr.begin(), testArr.end());

       result = "";
       arrIter = testArr.begin();
       while (! arrIter.equals(testArr.end())) {
           result += arrIter.get() + " ";
           arrIter = arrIter.advance();
       }
       System.out.println(result);


    }

}
