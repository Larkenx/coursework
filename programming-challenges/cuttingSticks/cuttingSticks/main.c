/*
  main.c
  cuttingSticks

  Created by Steven Myers on 10/10/16.
  Copyright © 2016 Steven Myers. All rights reserved.
*/

#include <stdio.h>
#include <string.h>

int cutSticks_rec(int * cuts, int stickLength, int start, int end, int originalLength, int table[originalLength][originalLength]) {
//    if (table[start][end] != -1) {
//         return table[start][end];
//   } else
        if (end - start == 1) {
        /* We are at the last cut */
//        table[start][end] = stickLength;
        return stickLength;
    } else {
        int best_yet, currentCost, cut;
        best_yet = -1;
        for (cut = start; cut != end; ++cut) {
            currentCost = stickLength;
            /* Go through each of the possible first cuts and find the lowest cost, recursively */
            currentCost +=  cutSticks_rec(cuts, cuts[cut], 0, cut, originalLength, table); /* Calculate cost to the left of the cut */
            currentCost +=  cutSticks_rec(cuts, stickLength - cuts[cut], cut+1, end, originalLength,table); /* Calculate cost of the right of the cut */
//            table[start][end] = currentCost;
            if (currentCost < best_yet || best_yet == -1) {
                best_yet = currentCost;
            }
        }
        return best_yet;
    }
}

int cutSticks(int * cuts, int stickLength, int start, int end) {
    int table[end][end], i, j;
    for (i = 0; i < end; i++) {
        for (j = 0; j < end; j++) {
            table[i][j] = -1;
        }
    }
    return cutSticks_rec(cuts, stickLength, start, end, end, table);
}

int main(int argc, const char * argv[]) {
    int length, i;
    int numOfCuts;

    char result[1000];
    while (scanf("%d", &length) != EOF) {
        if (length == 0) break; /* Stop at 0 */
        scanf("%d", &numOfCuts);
        int cuts[numOfCuts];
        for (i = 0; i != numOfCuts; i++) scanf("%d", &cuts[i]);
        int currNum = cutSticks(cuts, length, 0, numOfCuts);
        char currResult[50];
        sprintf(currResult, "The minimum cutting is %d.\n", currNum);
        strcat(result, currResult);
    }
    printf("%s", result);
    return 0;
}
