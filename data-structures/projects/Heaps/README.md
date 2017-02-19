# DNA Compression Project

For this project, I completed the ```HeapImp``` class which implements all of the methods given by the ```Heap``` interface.

#### Helper Functions

I defined a number of helper functions, listed and described below:

```java
/* Swaps i and j in arr */
public void swap(ArrayList<E> arr, int i, int j);

/* Returns the index of the element to the left of this element in the heap */
public int left(int i);

/* Returns the index of the element to the right of this element in the heap */
public int right(int i);

/* Returns the parent of a given index in a heap */
public int parent(int i);

/* Takes a given index and propagates the element E at the given index
*  up until its parent is no longer greater than E at the given index */
public void increase_key(int i).
```

#### Implementing the Heap interface

Using the above helper functions, I implemented all of the functions for the ```heap``` interface. Here are each of the functions with a brief description of what I did:

```java
/* Returns the first element in the heap arraylist, but does not alter
 * the contents of the heap (peeks at the min val) */
public E minimum();

/* I simply added the new element to the end of the heap data arraylist
 * then called my increase_key helper on the last value
 * (the one I just inserted) */
public void insert(E e);

/* Unlike minimum, this does mutate the heap arraylist. We use
 * our defined minimum value to store away the val before we
 * remove it. After we've stored it away, we call the arraylist
 * remove function and remove the first value from the arraylist.
 * Then, we replace the first value of the arraylist with the
 * last value, and call minHeapify on the 0th index of the
 * arrlist. Finally, we return the temp min we stored away.  */
public E extractMin();

/* This function moves down the left and right subtrees of the
 * given index i. It selects the smallest of the two trees, and
 * swaps with that value. We call minHeapify recursively on the
 * newly swapped tree. We stop when the given index i is smaller
 * or equal to its left and right children.*/
public void minHeapify(int i);

/* This function iterates through all of the parent nodes in the
 * heap and calls minHeapify on each parent node/index */
public void buildMinHeap();
```

#### Hours
I spent roughly ```~3.5 hours``` on this project.
