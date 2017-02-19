//
//  main.c
//  bishop
//
//  Created by Steven Myers on 12/5/16.
//  Copyright © 2016 Steven Myers. All rights reserved.
//




#include <stdio.h>
#include <stdlib.h>

int board[8][8];

int b(int n, int k, int p, int lasti, int lastj) {
    int i,j,ii,jj,total=0;
    
    if (k == 0) return(1);
    for (i=0; i < n; i++) for (j=0; j < n; j++) {
        if (((i*n + j) % 2)  != p) continue;
        if (i*n +j <=  lasti*n+lastj) continue;
        if (board[i][j] != 0) continue;
        for (ii=0; ii < n; ii++) for (jj=0; jj < n; jj++) if (abs(i-ii) == abs(j - jj)) board[ii][jj] += 1;
        total += b(n,k-1,p,i,j);
        for (ii=0; ii < n; ii++) for (jj=0; jj < n; jj++) if (abs(i-ii) == abs(j - jj)) board[ii][jj] -= 1;
    }
    return(total);
    
}


int bish(int n, int k, int p) {
    int i,j;
    
    for (i=0; i < 8; i++) for (j=0; j < 8; j++) board[i][j] = 0;
    return(b(n,k,p,-1,-1));
}

int
main() {
    int n = 3, k = 2, p = 1;
    
    printf("bish(%d,%d,%d) = %d\n",n,k,p,bish(n,k,p));
}
