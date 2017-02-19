/*************************************************************************
  * Name: Steven Myers 
  * 
  * Lab 7 and Homework 5:  N-puzzle problem  
  * (no submission for lab 7, just submit to homeowrk 5 box) 
  * 
  * Released:  2/21/16
  * Due:       2/29/16
  * 
  * Add as many private helper functions as you want
  *
  *************************************************************************/

import static java.lang.Math.*;
import java.util.Arrays;
public class Board {
  
  // 2-d array to represent puzzle
  private final int[][] board;
  
  private final int N; // Depth of array
  
  /* I, J are indices in the board where the blank square is located.  
   * Saving this will be helpful for neighbors method 
   * blank square is represented with the value 0
   */
  private int I;
  private int J;
  
  // initialize NxN Puzzle Board 
  public Board(int[][] blocks) {
    N = blocks.length;
    board = new int[N][N];
    for (int i = 0; i <= N - 1; i++) {
      for (int j = 0; j <= N - 1; j++) {
        board[i][j] = blocks[i][j];
        if (blocks[i][j] == 0) {
          I = i;
          J = j;
        }            
      }
    }
  }
  
  // return board dimension
  public int dimension() {
    return N;
  }
  
  // return # of blocks out of position
  public int hamming() {
    int order = 1;
    int hamming = (N * N) - 1; // All the blocks, minus the blank block
    for (int i = 0; i <= N - 1; i++) {
      for (int j = 0; j <= N - 1; j++) {
        if (this.board[i][j] == order) {
          hamming--;
        }
        order++;
      }
    }
    return hamming; 
  }
  
  public int manhattan() { 
    int initValue = 1;
    int[][] goal = new int[N][N];
    for (int i = 0; i <= N - 1; i++) { // Generate the goal board
      for (int j = 0; j <= N - 1; j++) {
        goal[i][j] = initValue;
        initValue++;
      }
    }
    goal[N - 1][N - 1] = 0; // Set the last block as the empty block
    int[] goalIndex = new int[2]; // Store the index of where a block should be on the goal board
    
    int order = 1;
    int manhattan = 0;
    for (int m = 0; m <= N - 1; m++) { // Go through every block in the board
      for (int n = 0; n <= N - 1; n++) {
        if ((this.board[m][n] != order) && (this.board[m][n] != 0)) { 
          for (int row = 0; row <= N - 1; row++) { // Check the block for its proper place in goal board
            for (int col  = 0; col <= N - 1; col++) {
              if (this.board[m][n] == goal[row][col]) { 
                goalIndex[0] = row; // Store the index of the goal block
                goalIndex[1] = col;
              }
            }
          }
          // Now we have the goal index versus the index of the current block and can find the manhattan distance
          manhattan += Math.abs(goalIndex[0] - m) + Math.abs(goalIndex[1] - n);
          // Add 4 because each of the four indices are one less than an X&Y coordinate plane
        }
        order++;
      }
    }
    return manhattan;
  }
  
  public boolean isGoal() {
    int initValue = 1;
    int[][] goal = new int[N][N];
    for (int i = 0; i <= N - 1; i++) { // Generate the goal board
      for (int j = 0; j <= N - 1; j++) {
        goal[i][j] = initValue;
        initValue++;
      }
    }
    goal[N - 1][N - 1] = 0; // Set the last block as the empty block
    
    return Arrays.deepEquals(goal, this.board);
  }
  
  public Board twin() { // Search for two adjacent numbers in a row, that are not the blank blocks,
    int[][] twin = new int[N][N]; // Then swap those two
    int[] lastIndex = {-1, -1}; // Store the last number we've seen. Initialized to -1, -1 for checking
    for (int row = 0; row <= N - 1; row++) { // Copy the board to the twin
      for (int col = 0; col <= N - 1; col++) {
        twin[row][col] = this.board[row][col];
      }
    }
    
    outerloop:
      for (int i = 0; i <= N - 1; i++) {
      for (int j = 0; j <= N - 1; j++) {
        
        if ((this.board[i][j] != 0) && (lastIndex[0] != -1) && (lastIndex[1] != -1)){
          twin[i][j] = this.board[lastIndex[0]][lastIndex[1]];
          twin[lastIndex[0]][lastIndex[1]] = this.board[i][j];
          break outerloop; // If we've successfully swapped, we can end here but we need to break out of both loops
          
        } else {
          lastIndex[0] = i;
          lastIndex[1] = j;
        }
      }
    }
      
      return new Board(twin);
  }
  
  // returns board as a string
  public String toString() {
    String result = "";
    for (int i = 0; i <= N - 1; i++) {
      for (int j = 0; j <= N - 1; j++) {
        result += this.board[i][j] + " ";
      }
      result += "\n";
    }
    return result;
  }
  
  public boolean equals(Board other) {  
    return Arrays.deepEquals(other.board, this.board);
  }
  
  public int[][] cloneBoard() { // Returns a cloned copy of the current board 2d array - not the Board itself
    int[][] copyBoard = new int[N][N];
    for (int i = 0; i <= N - 1; i++) { // Duplicate the current board into copyBoard 2d array.
      for (int j = 0; j <= N - 1; j++) {
        copyBoard[i][j] = this.board[i][j];
      }
    }
    return copyBoard;
  }
  
  public Stack<Board> neighbors() {
    Stack<Board> stack = new Stack<Board>();
    int[] emptyIndex = new int[2];
    for (int i = 0; i <= N - 1; i++) { // Find and store the empty block
      for (int j = 0; j <= N - 1; j++) {
        if (this.board[i][j] == 0) {
          emptyIndex[0] = i;
          emptyIndex[1] = j;
        }
      }
    }
    
    /* Now we need to try and swap the empty block with its adjacent indices. A catch statement
     * will be necessary for making sure that we don't go out of array bounds and raise an error
     */
    
    int[][] copyBoard;
    
    // Above
    try {
      copyBoard = cloneBoard();
      copyBoard[emptyIndex[0]][emptyIndex[1]] = this.board[emptyIndex[0] - 1][emptyIndex[1]];
      copyBoard[ emptyIndex[0] - 1][ emptyIndex[1] ] = 0;
      stack.push(new Board(copyBoard)); // Push the new neighbor to the stack
    }
    
    catch (Exception err) {} // If it's out of bounds, do nothing
    
    // To the right
    try {
      copyBoard = cloneBoard();
      copyBoard[emptyIndex[0]][emptyIndex[1]] = this.board[emptyIndex[0]][emptyIndex[1] + 1];
      copyBoard[emptyIndex[0]][emptyIndex[1] + 1] = 0;
      stack.push(new Board(copyBoard)); // Push the new neighbor to the stack
    }
    
    catch (Exception err) {} // If it's out of bounds, do nothing
    
    // To the left
    try {
      copyBoard = cloneBoard();
      copyBoard[emptyIndex[0]][emptyIndex[1]] = this.board[ emptyIndex[0] ][ emptyIndex[1] - 1 ];
      copyBoard[ emptyIndex[0] ][ emptyIndex[1] - 1] = 0;
      stack.push(new Board(copyBoard)); // Push the new neighbor to the stack
    }
    
    catch (Exception err) {} // If it's out of bounds, do nothing
    
    // Below
    try {
      copyBoard = cloneBoard();
      copyBoard[emptyIndex[0]][emptyIndex[1]] = this.board[ emptyIndex[0] + 1 ][ emptyIndex[1] ];
      copyBoard[ emptyIndex[0] + 1][ emptyIndex[1] ] = 0;
      stack.push(new Board(copyBoard)); // Push the new neighbor to the stack
    }
    
    catch (Exception err) {} // If it's out of bounds, do nothing  
    
    return stack;
  }
  
  // test client
  public static void main(String[] args) {
   int[][] testArray = { {8, 1, 3}, {4, 0, 2}, {7, 6, 5} };
   int[][] test2Array = { {0, 1, 2}, {3, 4, 5}, {6, 7, 8} };
   // int[][] goalArray = { {1, 2, 3}, {4, 5, 6}, {7, 8, 0} };
   Board test = new Board(testArray);
   Board test2 = new Board(test2Array);
   //System.out.println(test.hamming()); // 5
   //System.out.println(test.manhattan()); // 10
   System.out.println(test2.neighbors());
  }
}
