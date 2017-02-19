/*
  main.c
  bicolors

  Created by Steven Myers on 12/5/16.
  Copyright © 2016 Steven Myers. All rights reserved.
*/

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdbool.h>


/* We need to check if there are any cycles that exist in our
   graph containing an odd number of nodes in the cycle. A bicolored
   graph must contain no cycles of odd length. */

/* We will explore the entire graph using BFS, and color all of the nodes in the graph. If we find
 any node such that it is the same color of an adjacent node, then we return false. To do this,
 we need a queue to hold the nodes. I borrowed my queue implementation from the following src
 "https://www.tutorialspoint.com/data_structures_algorithms/queue_program_in_c.htm" */

/* Maxmimum is equivalent to n*(n-1)/2 where n is the max number of nodes. In our case, this is 200 */
#define MAX 200*(199) / 2

int queue[MAX];
int front = 0;
int rear = -1;
int itemCount = 0;

int peek() {
    return queue[front];
}

bool isEmpty() {
    return itemCount == 0;
}

bool isFull() {
    return itemCount == MAX;
}

int size() {
    return itemCount;
}

void insert(int data) {
    
    if(!isFull()) {
        
        if(rear == MAX-1) {
            rear = -1;
        }
        
        queue[++rear] = data;
        itemCount++;
    }
}

int pop() {
    int data = queue[front++];
    
    if (front == MAX) {
        front = 0;
    }
    
    itemCount--;
    return data;  
}

void bicolor(int n, int graph[n][n], int colorMatrix[n]) {
    int v;
    /* Now we are ready to traverse the graph and visit every node and color it */
    insert(0); /* insert the first node */
    colorMatrix[0] = 1; /* color the first node (1 and 0 denotes two colors) */
    while (! isEmpty()) {
        int u = pop();
        for (v = 0; v < n; v++) { /* go through all of its possible adjacent neighbors */
            if (graph[u][v] == 1 && colorMatrix[v] == -1) {
                colorMatrix[v] = 1 - colorMatrix[u]; /* flip the color to be different than u since it's a neighbor */
                insert(v);
            } else if (graph[u][v] == 1 && colorMatrix[v] == colorMatrix[u]) { /* if a neighbor is the same color */
                /* printf("(%d, %d) violates the bicolorable theorem.\n", u,v); */
                printf("NOT BICOLORABLE.\n");
                return;
            }
        }
    }
    printf("BICOLORABLE.\n");
}

int main(int argc, const char * argv[]) {
    int nodes, edges, i, j;
    while (scanf("%d", &nodes) == 1) {
        if (nodes == 0) break;
        int graph[nodes][nodes]; /* n * n adjacency matrix */
        /* We will initialise all of the graph elements to be 0 */
        for (i = 0; i < nodes; i++)
            for (j = 0; j < nodes; j++)
                graph[i][j] = 0;
        
        int colorMatrix[nodes]; /* n*n grid to mark what the colors are for each node */
        scanf("%d", &edges); /* number of edges */
        for (i = 0; i < edges; i++) {
            int u, v;
            scanf("%d %d", &u, &v);
            /* Mark that the edge exists in the adj matrix */
            graph[u][v] = 1;
            graph[v][u] = 1;
        }
        /* We will denote uncolored (unvisited nodes) as -1 */
        for (i = 0; i < nodes; i++) colorMatrix[i] = -1;
        bicolor(nodes, graph, colorMatrix);
        /* for each test case, we are going to clear out our queue. */
        while (! isEmpty()) pop();
    }
    return 0;
}
