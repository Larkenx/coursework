/* Lab5 - Matrix
 * Steven Myers
 * Username: stelmyer
 * Date: 2/12/2016
 * 
 */

import java.util.Scanner;

public class Matrix {
  private double[][] matrix;
  private final int ROW;
  private final int COL;
  
  public Matrix(double[][] m) { // Constructor
    ROW = m.length;
    COL = m[0].length;
    matrix = m;
  }
  
  
  public String toString() {
    String result = "[";
    for (int row = 0; row <= (ROW - 1); row++) {
      for (int col = 0; col <= (COL - 1); col++) {
        if (matrix[row][col] == matrix[ROW - 1][COL - 1]) {
          result = result + matrix[row][col];
        } else {
          result = result + matrix[row][col] + ", ";
        }
      }
    }
    return result + "]";
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
  
  public static void main(String[] args) {
    double[][] test = new double[3][3];
    test[0][0] = 1;
    test[0][1] = 2;
    test[0][2] = 3;
    test[1][0] = 4;
    test[1][1] = 5;
    test[1][2] = 6;
    test[2][0] = 7;
    test[2][1] = 8;
    test[2][2] = 9;
    Matrix testMatrix = new Matrix(test);
    System.out.println(testMatrix.toString());
    System.out.println("Matrix: ");
    Matrix otherMatrix = testMatrix.transpose();
    System.out.println("Transposed Matrix: ");
    System.out.println(otherMatrix.toString());
    System.out.println("UpperDiagonal Matrix: ");
    System.out.println((testMatrix.upperDiagonal()).toString());
    System.out.println("LowerDiagonal Matrix: ");
    System.out.println((testMatrix.lowerDiagonal()).toString());
    System.out.println("Diagonal Matrix: ");
    System.out.println(testMatrix.diagonalMatrix());
    
  } 
}