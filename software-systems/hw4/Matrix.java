/* Homework 4 - Matrix
 * Steven Myers
 * Username: stelmyer
 * Date: 2/17/2016
 * 
 */

public class Matrix {
  public final double[][] matrix;
  public final int ROW;
  public final int COL;
  
  public Matrix(double[][] m) { // Constructor
    ROW = m.length;
    COL = m[0].length;
    matrix = m;
  }
  
  
  public String toString() {
    String result = ROW + "x" + COL + " Array:\n[";
    for (int row = 0; row <= (ROW - 1); row++) {
      for (int col = 0; col <= (COL - 1); col++) {
        if (col == COL - 1) {
          result = result + matrix[row][col];
        } else {
          result = result + matrix[row][col] + ", ";
        }
      }
      if (row == ROW - 1) {
        result = result + "]";
      } else {
        result = result + "]\n[";
      }
    }
    return result;
  }
  
  public Matrix transpose() {
    double [][] transposedArray = new double[COL][ROW];
    for (int row = 0; row <= (ROW - 1); row++) {
      for (int col = 0; col <= (COL - 1); col++) {
        transposedArray[col][row] = matrix[row][col]; 
      }
    }
    Matrix transposedMatrix = new Matrix(transposedArray);
    return transposedMatrix;
  }
  
  
  public Matrix upperDiagonal() { // Only needs to work on NxN Matrices
    double [][] result = new double[ROW][COL];
    for (int row = 0; row <= (ROW - 1); row++) {
      for (int col = 0; col <= (COL - 1); col++) {
        if (col > row ) {
          result[row][col] = 0;
        } else {
          result[row][col] = matrix[row][col];
        }
      }
    }
    return new Matrix(result);
  }
  
  public Matrix lowerDiagonal() { // Only needs to work on NxN Matrices
    double [][] result = new double[ROW][COL];
    for (int row = 0; row <= (ROW - 1); row++) {
      for (int col = 0; col <= (COL - 1); col++) {
        if (col < row ) {
          result[row][col] = 0;
        } else {
          result[row][col] = matrix[row][col];
        }
      }
    }
    return new Matrix(result);
  }
  
  public Matrix diagonalMatrix() { // Only needs to work on NxN Matrices
    return (this.lowerDiagonal()).upperDiagonal();
  }
  
  public Matrix multiply (Matrix other) {
    if (matrix[0].length == other.matrix.length) {
      double[][] result = new double[matrix.length][other.matrix[0].length];
      double sum = 0;
      for (int aRows = 0; aRows <= (matrix.length - 1); aRows++) { // The rows in matrix      
        for (int bCols = 0; bCols <= (matrix.length - 1); bCols++) { // The columns in other (equal to rows in matrix)       
          for (int aCols = 0; aCols <= (matrix[0].length - 1); aCols++) { // The columns in matrix 
            sum = sum + (matrix[aRows][aCols] * other.matrix[aCols][bCols]);
          }
          result[aRows][bCols] = sum;
          sum = 0;
        }
      }
      return new Matrix(result);
    } else {
      return null; // This raises an error!
    }
  }
  
  public static void main(String[] args) {
    double[][] testA = { {1, 2, 3}, {4, 5, 6} };
    Matrix matrixA = new Matrix(testA);
    
    double[][] testB = { {1, 2}, {3, 4}, {5, 6} };   
    Matrix matrixB = new Matrix(testB);
    
    double[][] testC = { {1, 2, 3, 4, 5, 6, 7,}, {2, 3, 4, 5, 6, 7,} };
    Matrix matrixC = new Matrix(testC);
    
    System.out.println(matrixA.toString());
    System.out.println((matrixA.multiply(matrixB)).toString());
    // System.out.println((matrixA.multiply(matrixC)).toString()); // This raises an error
  } 
}