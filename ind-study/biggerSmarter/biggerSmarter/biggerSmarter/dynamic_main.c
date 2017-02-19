/*
 main.c
 biggerSmarter
 
 Created by Steven Myers on 10/2/16.
 Copyright © 2016 Steven Myers. All rights reserved.
 */

#include <stdio.h>
#include <stdlib.h>

int find(int arr[1000][2], int length, int a, int b) {
    for (int i = 0; i < length; i++) {
        if (arr[i][0] == a && arr[i][1] == b){
            return i;
        }
    }
    return -1;
}

/* Sorts a matrix ascending from first column values (weight). However, the problem
 requires that we print the indices of the lines we use for our dataset. So, we need
 to keep track of the original locations vs the new ones.
 */
void bubbleSort(int arr[1000][2], int length) {
    int a, b;
    int tmp[2];
    for (int i = 0; i < length - 1; i++) {
        for (int j = 0; j < length - 1 - i; j++) {
            a = arr[j][0];
            b = arr[j + 1][0];
            if ((a > b) || ((a == b) && arr[j][1] < arr[j + 1][1])) {
                /* temp copy of a */
                tmp[0] = arr[j][0];
                tmp[1] = arr[j][1];
                /* Swap i and i+1 */
                arr[j][0] = b;
                arr[j][1] = arr[j + 1][1];
                arr[j + 1][0] = tmp[0];
                arr[j + 1][1] = tmp[1];
            }
        }
    }
    /* Print Sorted Array
     printf("Sorted:\n");
     for (int i = 0; i < length; i++) {
     printf("(%d, %d)\n", arr[i][0], arr[i][1]);
     }
     */
}

int main(int argc, const char * argv[]) {
    /* Maximum data set is 1000 lines, each line is a pair of two ints */
    int originalInput[1000][2];
    int input[1000][2];
    int count = 0;
    int a, b;
    while (scanf("%d %d", &a, &b) == 2) {
        /* We want to store the original input because we're going to sort the other arr */
        originalInput[count][0] = a;
        originalInput[count][1] = b;
        /* To be sorted */
        input[count][0] = a;
        input[count][1] = b;
        count++;
    }
    /* Print original arr
     printf("Original:\n");
     for (int i = 0; i < count; i++) {
     printf("(%d, %d)\n", input[i][0], input[i][1]);
     }
     */
    
    /* So we have an array containing the pairs (weight, iq).
     We need to go through each element and build up the longest
     sequence of elements where the weight is increasing and the iq is decreasing.
     To do this, we should sort our ascending by the weight */
    
    bubbleSort(input, count);
    
    int n;
    int biggestN = 0;
    int tmpResult[1000][2];
    int bestResult[1000][2];
    for (int i = 0; i < count; i++) { /* Iterate through our dataset */
        int * currentPair = input[i];
        int * lastSeenPair = currentPair;
        n = 1;
        tmpResult[0][0] = currentPair[0];
        tmpResult[0][1] = currentPair[1];
        for (int j = i + 1; j < count; j++) {
            int * nextPair = input[j];
            if (lastSeenPair[0] < nextPair[0] && lastSeenPair[1] > nextPair[1]) {
                /* printf("Got a dataset that meets criteria: (%d, %d) -> (%d, %d)\n", lastSeenPair[0], lastSeenPair[1], nextPair[0], nextPair[1]); */
                tmpResult[n][0] = nextPair[0];
                tmpResult[n][1] = nextPair[1];
                ++n;
                lastSeenPair = nextPair;
            }
        }
        if (n > biggestN) {
            biggestN = n;
            for (int k = 0; k <= biggestN; k++) {
                bestResult[k][0] = tmpResult[k][0];
                bestResult[k][1] = tmpResult[k][1];
            }
        }
    }
    
    /* Print sorted output
     printf("\nResult (Sorted Input):\n");
     printf("%d\n", biggestN);
     for (int i = 0; i < biggestN; i++) {
     printf("(%d, %d)\n", bestResult[i][0], bestResult[i][1]);
     }
     */
    
    /* Print judge output */
    printf("%d\n", biggestN);
    for (int i = 0; i < biggestN; i++) {
        int oriIndex = find(originalInput, count, bestResult[i][0], bestResult[i][1]);
        printf("%d\n", oriIndex + 1);
        /* printf("Original Index: (%d), Pair: (%d, %d)\n", oriIndex, bestResult[i][0], bestResult[i][1]); */
    }
    
    return 0;
}
