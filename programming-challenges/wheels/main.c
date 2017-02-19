/*
  main.c
  wheels

  Created by Steven Myers on 12/15/16.
  Copyright © 2016 Steven Myers. All rights reserved.
*/

#include <stdio.h>
#include <string.h>

/* Maximum number of moves from any combination to another */
#define MAXMOVES 10001

/* Array to store all of our visited combinations */
int visited[MAXMOVES * 2][4];
/* Index of last entered combination */
int position = 0;

/* Copies content of src to target */
void copy(int src[4], int target[4]) {
    memcpy(target, src, sizeof(int)*4);
}

/* Checks two int arrays for equality */
int equals(int src[4], int target[4]) {
    return memcmp(target, src, sizeof(int)*4) == 0;
}

/* Checks if a number has been visited */
int tried(int combination[4]) {
    int i;
    for (i = 0; i < position; i++)
        if (equals(combination, visited[i])) return 1;
    return 0;
}

/* Checks if a number is forbidden */
int isForbidden(int combination[4], int forbiddenNum, int forbidden[forbiddenNum][4]) {
    int i;
    for (i = 0; i < forbiddenNum; i++)
        if (equals(combination, forbidden[i])) return 1;
    return 0;
}

/* Takes a direction (0 or 1) and either subtracts one or adds one to the given number.
 * if the number is nine or zero, it will roll over accordingly. */
int dial(int dir, int num) {
    if (dir == 0) { /* dial up */
        if (num == 9) return 0;
        else return num + 1;
    } else { /* dial down */
        if (num == 0) return 9;
        else return num - 1;
    }
}

/* Originally I tried to solve this function using the call stack, but quickly found out that
 * using recursion/the callstack does not respect the "moves." It will evaluate a combination down
 * to max moves before it "pops back up" the recursion, instead of evaluating like a wave, one move at a time. 
 * After recognizing this, I decided to make a queue-like structure, but instead of actually implementing it,
 * I made two index variables "back" and "front" to indicate where the queue began and ended, and just added 1
 * to each for pops/pushes. */
void solve(int init[4], int target[4], int forbiddenNum, int forbidden[forbiddenNum][4]) {
    int q[MAXMOVES*10][4], tmp[4], i, j, moves = 0, front = 0, back = 0;
    copy(init, q[back++]); /* Copy in the initial starting combo */
    
    while (front != back) { /* Until no new combinations are generated (max of 10k) */
        int added = back; /* delay updating the back variable until the loop back */
        for (i = front; i != back; i++) {
            front++; /* pop, q[i] is our current combination */
            if (equals(q[i], target)) { /* found our target */
                printf("%d\n", moves);
                return;
            }
            
            if ((isForbidden(q[i], forbiddenNum, forbidden) || tried(q[i])) && i != 0) continue;
            copy(q[i], visited[position++]);
            
            for (j = 0; j < 4; j++) { /* dial up */
                copy(q[i], tmp);
                tmp[j] = dial(0, tmp[j]);
                copy(tmp, q[added++]); /* push */
            }
            
            for (j = 0; j < 4; j++) { /* dial down */
                copy(q[i], tmp);
                tmp[j] = dial(1, tmp[j]);
                copy(tmp, q[added++]); /* push */
            }
        }
        back = added;
        moves++;
    }
    printf("-1\n");
}

int main() {
    int cases, i, j;
    scanf("%d", &cases);
    for (i = 0; i < cases; i++) {
        int init[4], target[4], forbiddenNum;
        scanf("%d %d %d %d", &init[0], &init[1],&init[2],&init[3]); /* Initial Combination */
        scanf("%d %d %d %d", &target[0], &target[1],&target[2],&target[3]); /* Target Combination */
        scanf("%d", &forbiddenNum);
        int forbidden[forbiddenNum][4];
        for (j = 0; j < forbiddenNum; j++) /* Get Forbidden Combinations */
            scanf("%d %d %d %d", &forbidden[j][0],&forbidden[j][1],&forbidden[j][2],&forbidden[j][3]);
        
        solve(init, target, forbiddenNum, forbidden);
        position = 0;
    }
}
