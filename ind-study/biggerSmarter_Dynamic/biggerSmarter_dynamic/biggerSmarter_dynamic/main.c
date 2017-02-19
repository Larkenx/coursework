//
//  main.c
//  biggerSmarter_dynamic
//
//  Created by Steven Myers on 10/9/16.
//  Copyright © 2016 Steven Myers. All rights reserved.
//

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/* Sorts in descending order by weight, not ascending order */
void bubbleSort(int arr[1000][2], int length) {
    int a, b;
    int tmp[2];
    for (int i = 0; i < length - 1; i++) {
        for (int j = 0; j < length - 1 - i; j++) {
            a = arr[j][0];
            b = arr[j + 1][0];
            if ((a > b) || ((a == b))) {
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
    
    /* Print Sorted Array */
     printf("Sorted:\n");
     for (int i = 0; i < length; i++) {
     printf("(%d, %d)\n", arr[i][0], arr[i][1]);
     }
}

int find(int arr[1000][2], int length, int a, int b) {
    for (int i = 0; i < length; i++) {
        if (arr[i][0] == a && arr[i][1] == b){
            return i;
        }
    }
    return -1;
}

int bestCount(int arr[1000][2], int start, int length, int result[1000]) {
    int best_yet, curr_result;
    int best_result[length];
    if (start - length == 1) {
        result[start] = 2;
        return 1;
    } else {
        best_yet = 0;
        curr_result = 0;
        for (int i = start; i < length - 2; i++) {
            result[start] = 2;
            for (int j = i + 1; j < length - 1; j++) { // has to be greater than the current since it's in order (sorted)
                if (arr[i][0] < arr[j][0] && arr[i][1] > arr[j][1]) { // Found candidate
                    curr_result += 1 + bestCount(arr, j, length, result);
                    if (best_yet < curr_result) {
                        best_yet = curr_result;
                        memcpy(best_result, result, length);
                        memset(result, 0, length);
                    }
                }
                curr_result = 0;
            }
        }
    }
    memset(result, 0, length);
    memcpy(result, best_result, length);
    return best_yet;
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

        input[count][0] = a;
        input[count][1] = b;
        count++;
    }
    
    /* Sort our input descending by weight */
    bubbleSort(input, count);
    
    int best_chain[1000];
    printf("%d\n", bestCount(input, 0, count, best_chain));
    for (int i = 0; i < count; i++) {
        if (best_chain[i] == 2) {
            printf("Sorted Index: %d\n", i);
//            printf("Original Index: %d\n", find(input, count, input[i][0], input[i][1]) + 1);
        }
    }
    
}
