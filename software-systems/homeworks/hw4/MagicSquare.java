/* Homework 4 - Matrix
 * Steven Myers
 * Username: stelmyer
 * Date: 2/18/2016
 * 
 */

public class MagicSquare {
  private MagicSquare() {}
  
  public static int[][] makeMagicSquare(int N) {
    int[][] magicSquare = new int[N][N];
    int col = N / 2; // initial x
    int row = 0; // initial y
    for (int i = 1; i <= Math.pow(N, 2); i++) {
      magicSquare[row][col] = i;
      if (i % N == 0) {
        row++;
      } else {
        if (row == 0) {
          row = N - 1;
        } else {
          row--;
        } if (col == (N - 1)) {
          col = 0;
        } else {
          col++;
        }
      }
    }
    return magicSquare;
  }
  
  public static boolean noDuplicates(int[][] magic) {
    boolean result = true;
    int occurences = 0;
    for (int n = 1; n < Math.pow(magic.length, 2); n++) {
      for (int row = 0; row < magic.length; row++) {
        for (int col = 0; col < magic.length; col++) {
          if (n == magic[row][col]) {
            occurences++;
          }
        }
      }
      
      if (occurences > 1) {
        result = false;
      }
      occurences = 0;
    } 
    return result;
  }
  
  public static boolean isMagic(int[][] magic) {
    boolean result = true;
    int n = magic.length;
    int correctSum = (n * ((n * n) + 1)) / 2;
    int sum = 0;
    
    // Sum rows
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        sum = sum + magic[i][j];
      }
      if (sum != correctSum) {
        result = false;
      }
      sum = 0;
    }
    
    // Sum columns
    for (int j = 0; j < n; j++) {
      for (int i = 0; i < n; i++) {
        sum = sum + magic[i][j];        
      }
      
      if (sum != correctSum) {
        result = false;
      }
      sum = 0;
    }
    
    // Diagonal - Top left to bottom right
    for (int i = 0; i < n; i++) {
      sum = sum + magic[i][i];
    }
    
    if (sum != correctSum) {
      result = false; 
    }
    
    
    sum = 0;
    
    // Diagonal - Top right to bottom left
    for (int i = n - 1; i >= 0; i--) {
      sum = sum + magic[i][i];
    }
    
    if (sum != correctSum) {
      result = false; 
    }
    
    sum = 0;
    
    return result && noDuplicates(magic);
  }
  
  
  public static String toString(int[][] magicSquare) {
    String result = ""; 
    for (int i = 0; i < magicSquare.length; i++) {
      for (int j = 0; j < magicSquare.length; j++) {
        result = result + magicSquare[i][j] + "  ";  
      }
      result = result + "\n";
    }
    return result;
  }
  
  public static void main(String[] args) {
    System.out.println(toString(makeMagicSquare(3)));
    System.out.println(isMagic(makeMagicSquare(3)));
    int[][] test = { {1, 2, 2}, {2, 3, 1} };
    System.out.println(noDuplicates(test));
  }
}

