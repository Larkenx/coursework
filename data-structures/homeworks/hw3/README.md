#Homework 3
###Chapter 13

#### 13.5)
Completed on paper, image shown below:
![Error - file not found...please look for 13_5.jpeg elsewhere in the repo]
(13_5.jpeg)

###Chapter 7

#### 7.1)
Unfinished

#### 7.6)
**Insertion Sort** -
Insertion Sort is stable because we will push every duplicate value past every other duplicate value. If you are familiar with the concept of a "last man sprint" in Cross Country, it's sort of like that. It is where runners run in single file, and the last runner runs to the front of the line. Here is an example of this works out in insertion sort:

    [2 (first), 2 (second), 1]
    // move 2 (first) until its predecessor is less than 2 OR end
    [2 (second), 1, 2 (first)]
    // same as above
    [1, 2 (first), 2(second)]

**Bubble Sort** -
BubbleSort is stable because it only moves an element if its next element in the index is greater than the current element. Even when pushing numbers through duplicates, like so:

    [5, 2, 2]
    [2, 5, 2]
    [2, 2, 5]

The order of the two 2's is preserved. This is a lot like how insertion sort moves its numbers around.

**Selection Sort** -
SelectionSort is not stable, because in the case that you have the following:

    [4 2 3 4 1]
    // Swap 4 & 1
    [1 2 3 4 4]
    // The first 4 is now ahead of the last 4 in the original order, and we are unstable.

To make SelectionSort stable, instead of swapping the current and least value, we can remove the min value that we find and push all the other numbers forward. The iteration would look like this instead:

    [4 2 3 4 1]
    [=> 4 2 3 4]
    [1 4 2 3 4]
    [1 => 4 3 4]
    [1 2 4 3 4]
    [1 2 => 4 4]
    [1 2 3 4 4]

**Shell Sort** -
ShellSort is unstable because it performs insertion sorts on sublists. If we have 3 duplicate values in our array to be sorted, then it is possible that our duplicate values on the left and right of the middle duplicate will be sorted first, thus displacing the original middle duplicate. I found this example online at this [site] (http://www.cs.wcupa.edu/rkline/ds/shell-comparison.html). In the example below, our '2' value will be displaced:

    0  1  2  3  4  5  6  7  8  9  10
    -  -  -  -  -  -  -  -  -  -  --
    A=[6, 7, 8, 6, 9, 7, 2, 2, 2, 9, 8]
       ^                    ^          
    [2, 7, 8, 6, 9, 7, 2, 6, 2, 9, 8]
        ^                    ^       
    [2, 2, 8, 6, 9, 7, 2, 6, 7, 9, 8]
           ^                    ^    
    [2, 2, 8, 6, 9, 7, 2, 6, 7, 9, 8]
              ^                    ^

    [2, 2, 8, 6, 9, 7, 2, 6, 7, 9, 8]  7's now sorted
     ^        ^        ^        ^      ---- 4 elements 3 apart
    [2, 2, 8, 2, 9, 7, 6, 6, 7, 9, 8]
        ^        ^        ^        ^   ---- 4 elements 3 apart
    [2, 2, 8, 2, 6, 7, 6, 8, 7, 9, 9]
           ^        ^        ^         ---- 3 elements 3 apart

    [2, 2, 7, 2, 6, 7, 6, 8, 8, 9, 9]  3's now sorted
    [2, 2, 2, 6, 6, 7, 7, 8, 8, 9, 9]  regular insertion sort

I am unaware of a way on how to improve ShellSort to make it into a stable sorting algorithm without absolutely ruining its runtime, or changing the integral method by which ShellSort uses gaps to grab sublists. The only solution might be to not use ShellSort and use insertion sort instead.

**Merge Sort** -
Merge Sort is stable because after the 'divde' portion of the algorithm, the 'conquer' steps simply join the two linked lists, and runs insertion sort on them. I am using the implementation from the book as reference (code below):

```java
static <E extends Comparable<? super E>>
void mergesort(E[] A, E[] temp, int l, int r) {
    int i, j, k, mid = (l+r)/2; // Select the midpoint
    if (l == r) return; // List has one element
    if ((mid-l) >= THRESHOLD) mergesort(A, temp, l, mid);
    else inssort(A, l, mid-l+1);
    if ((r-mid) > THRESHOLD) mergesort(A, temp, mid+1, r);
    else inssort(A, mid+1, r-mid);
    // Do the merge operation. First, copy 2 halves to temp.
    for (i=l; i<=mid; i++) temp[i] = A[i];
    for (j=1; j<=r-mid; j++) temp[r-j+1] = A[j+mid];
    // Merge sublists back to array
    for (i=l,j=r,k=l; k<=r; k++)
    if (temp[i].compareTo(temp[j])<0) A[k] = temp[i++];
    else A[k] = temp[j--];
}
```


**Quick Sort** -
Quicksort is unstable whenever the pivot happens to be a duplicate value. If we happen to pull the duplicate value whose original index falls before another duplicate, it will now be sorted in the back of those other duplicates, as the pivot is put at the "end" of the left partition and its other duplicates will be sorted on the right since those duplicates are not less than the duplicate pivot. To fix this, we could clean up the algorithm by storing the pivot in memory, then after the algorithm is finished running, we can check to see if the pivot (or any pivots) occurred more than once. Something to the extent of the following pseudo-python-code might work:

```python
# Store all the pivots in memory
global pivots <- 0 # List of pivots
for each quicksort: # Every quicksort evaluation
    pivots <-pivot # chosen pivot
for pivot in pivots:
    for pivot in sortedArr:
        if (sortedArr[pivot + 1] == sortedArr[pivot])
            put [pivot] at [pivot + 2] # Instead of pivot+2, this would actually be until pivot + n != pivot, then insert before
```


**Heap Sort** -
This is a stable sort.

**Bin Sort** -
This is an unstable sort.

**Radix Sort** -
This is a stable sort.

#### 7.11)
*Modify Quicksort to find the smallest k values in an array of records. Your
output should be the array modified so that the k smallest values are sorted
in the first k positions of the array. Your algorithm should do the minimum
amount of work necessary, that is, no more of the array than necessary should
be sorted.*

#### 7.16)
***By the exact wording of the problems, Sleep Sort is technically the "fewest comparisons as possible" for all numbers since it does not make any comparisons or swaps. I included sleep sort [here] (https://github.iu.edu/C343-Fall2016/C343stelmyer/blob/master/homeworks/hw3/Sort_Numbers/src/SleepSort.java). However, I have implemented "real" solutions [here] (https://github.iu.edu/C343-Fall2016/C343stelmyer/blob/master/homeworks/hw3/Sort_Numbers/src/SortingNumbers.java).***

#### a) **SortThree Function**

Cases       | Worst | Average | Best
---         |:-----:|:-------:| :----:
Comparisons | 3     |  3      | 2        
Swaps       | 4     |  2      | 1


#### b) **SortFive Function**

Cases       | Worst | Average | Best
---         |:-----:|:-------:| :----:
Comparisons |      |       |        
Swaps       |     |        |



#### c) **SortEight Function**
Cases       | Worst | Average | Best
---         |:-----:|:-------:| :----:
Comparisons |      |        |         
Swaps       |      |        | 



#### Project 7.5)
I completed this project [here] (https://github.iu.edu/C343-Fall2016/C343stelmyer/blob/master/homeworks/hw3/QuickSortProject/src). For larger and larger values of n, I found that my 'BetterQuickSort.java' took less steps than the original by using inssort and considering 3 values for the pivot (-1, 0, 1 around the middle of the array for arrays larger than 5).


#### Chapter 16

#### 16.1)
I completed this in TOH/src directory. This is a dynamic programming solution because it divides each problem into two subproblems.
The Towers of Hanoi problem cannot be solved in a faster way since there will always be
an optimal number of moves equal to 2^n - 1.
