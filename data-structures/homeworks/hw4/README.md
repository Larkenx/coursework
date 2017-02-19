# Homework 4

## Chapter 5
### 5.23
*What are the minimum and maximum number of elements in a heap of
height h?*

If ``` h = 0 ``` is the height the root node, then:

```
Maximum case is where every parent has two children.
Maximum Number of nodes == 2 * h + 1

Minimum case is where we each parent has exactly one child.
Minimum Number of nodes == h + 1
```

### 5.25

*Show the max-heap that results from running buildHeap on the following
values stored in an array:*
```
Input array: 10 5 12 3 2 1 8 7 9 4

Output MaxHeap: 12 9 10 7 4 1 8 5 3 2
```



### 5.26

*(a) Show the heap that results from deleting the maximum value from the
max-heap of Figure 5.20b.*

The heap that results from extracting the max value ```7``` is:

```
data = [6 5 3 4 2 1]
     _ 6 _
   /      \
  5       3
 / \     /
4   2   1
```

*(b) Show the heap that results from deleting the element with value 5 from
the max-heap of Figure 5.20b.*

If we remove the 5 from the given heap, we will get the following maxHeap:

```
data = [7, 4, 6, 2, 1, 3]

      7
    /   \
   4     6
  /     / \
 2     1   3

```

### 5.28

*Build the Huffman coding tree and determine the codes for the following set
of letters and weights:*

|    Letter | A | B | C | D | E | F | G  | H | I | J |  K | L                 |
|----------:|:----------------------------------------------------------------|
| Frequency | 2 | 3 |  5  | 7  | 11  | 13 |  17 |  19 |  23 |  31 |  37 |  41 |

*What is the expected length in bits of a message containing n characters for
this frequency distribution?*

# NOT DONE

## Chapter 9
### 9.13

*Assume that you are hashing key K to a hash table of n slots (indexed from
0 to n − 1). For each of the following functions h(K), is the function acceptable
as a hash function (i.e., would the hash program work correctly for
both insertions and searches), and if so, is it a good hash function? Function
Random(n) returns a random integer between 0 and n − 1, inclusive.*

(a) ```h(k) = k/n where k and n are integers.```

This hash function works fine for input keys whose value is greater than the total number of slots. It does not work well for numbers smaller than the total number of slots ```n```,

```java
// Num of slots
n = 10;
h(0) = 0;
h(1) = 0;
...
h(9) = 0;

// All numbers from k to n-1 will be placed in the same slot!
```
So, this hash function works well on data sets where the input key happens to be multiples of n, but not well on series of semi-natural numbers.


(b) ```h(k) = 1.```

This is not an appropriate hash function because all keys K will attempt to be placed in the same slot, and the hash table will only be able to store entries in a single slot.

When we try to insert a unique key K into the hash table after the single slot is filled, we will always put at position 1, and we essentially will always have a linear insert and search, but the goal of a hash map is to have nearly constant time insertion and search.

(c) ```h(k) = (k + Random(n)) mod n.```

This is a good hashing function. If you have input keys ranging from ```0 to n-1``` then almost every slot will contain just one entry, and you'll have the constant search and insert access. It begins to get a little more squirly as k becomes more varied input, but even on a test size of 100 keys you still maintain no more than 3 entries in any given slot.

(d) ```h(k) = k mod n where n is a prime number.```

This is the most ideal hashing function since a prime number has no divisors other than itself (in this case, ```n```).

For keys ranging from ```0 to n-1```, we get a completely even distribution of keys to each slot. Every range of numbers that exist in some constant ```c``` multiplied by our prime number ```n``` will exist in an already filled slot, so for example:

```java
// We choose n = 27 for our prime number
h(27) = 0;
h(54) = 0;
h(81) = 0;
// Every k such that k is a multiple of 27 will fall in the same slot.
```

### 9.16

*Assume that you have a ten-slot closed hash table (the slots are numbered
0 through 9). Show the final hash table that would result if you used the
hash function h(k) = k mod 10 and pseudo-random probing on this list of
numbers: 3, 12, 9, 2, 79, 44. The permutation of offsets to be used by the
pseudo-random probing will be: 5, 9, 2, 1, 4, 8, 6, 3, 7. After inserting the
record with key value 44, list for each empty slot the probability that it will
be the next one filled.*



```java
probes[] = [5, 9, 2, 1, 4, 8, 6, 3, 7]
h(k) = k % 10;

/** Insert record r with key k into HT */
void hashInsert(Key k, E r) {
    int home; // Home position for r
    int pos = home = h(k); // Initial position
    for (int i=1; HT[pos] != null; i++) {
        pos = (home + p(k, i)) % M; // Next pobe slot
        assert HT[pos].key().compareTo(k) != 0 : "Duplicates not allowed";
    }
    HT[pos] = new KVpair<Key,E>(k, r); // Insert R
}

// Inserting 3, 12, 9, 2, 79, 44




```





### 9.19 (in Java)

*Write an algorithm for a deletion function for hash tables that replaces the
record with a special value indicating a tombstone. Modify the functions
hashInsert and hashSearch to work correctly with tombstones*

# NOT DONE


## Chapter 13

### 13.1
*Show the binary trie (as illustrated by Figure 13.1) for the following collection
of values: 42, 12, 100, 10, 50, 31, 7, 11, 99.*

```
/ = 0
\ = 1
---- is just used to specify children
          ______________________
     ____/______                 \
    /           __\__           __\____
   _\_        _/_     \       _/_       \
  /    \     /   \     \     /   \       \
 /     12    \   / 10   7   /     11      \
 \            \  \          \             31
  \           50   42        \
  /                           99
 100
```
