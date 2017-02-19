////////////////////////////////////////////////////////////////////////////////////
//
//  C212 Spring 16
//  Homework 2
//  Author  Steven Myers | stelmyer
//  Last Edited:  2/5/2016
//
//               
//////////////////////////////////////////////////////////////////////////////////

import java.lang.Math;

public class Homework2 {
  public static boolean isPalindrome(String s) {
    String reversedString = "";
    for (int i = 0; i < s.length(); ++i) {
      char c = s.charAt(i);
      reversedString = c + reversedString;
    }
    return reversedString.equals(s);
  }
  
  public static int match(String[] anArray, String character) { // Helper function to help find the index of a character in an array
    int result;
    int i;
    for (i = 0; i < anArray.length; ++i) {
      if (anArray[i].equals(character)) {
        break;
      }
    }
    return i;
  }
  
  public static boolean isSorted(String s) {
    String[] alphabet = // An array to store the alphabet
    {"", "a", "b", "c", "d", "e", "f", "g", "h", "i",
      "j", "k", "l", "m", "n", "o", "p", "q", "r",
      "s", "t", "u", "v", "w", "x", "y", "z"};
    
    boolean result = true; // We're going to presume that the list is in alphabetical order.
    String lastLetter = "";
    int lastIndex = 0;
    
    for (int i = 0; i < s.length(); ++i) { // Iterate through the string
      char c = s.charAt(i);
      String currentLetter = Character.toString(c);
      
      int currentIndex = match(alphabet, currentLetter);
      lastIndex = match(alphabet, lastLetter);
      
      if (currentIndex >= lastIndex) { // If the index of the letter affirms that it's not out of order
        lastLetter = Character.toString(c); // Set the last letter to the current letter, and loop again.
        
      } else if (currentIndex < lastIndex) { // If it's out of order, make the result false, and break.
        result = false;
        break;
      }
    }
    return result;
  }
  
  public static void border(int n) {
    double area = Math.pow(n, 2);
    String border = "";
    
    while (area != 0) {
      if (Math.sqrt(area) == n || (area / n) == 1) {
        for (int i = 0; i != n; i++) {
          border = border.concat("*");
        }
        border = border.concat("\n");
        area -= n;
        
      } else {
        border = border.concat("*");
        for (int i = 0; i != (n - 2); i++) {
          border = border.concat(" ");
        }
        border = border.concat("*");
        border = border.concat("\n");
        area -= n;
      }
    }
    System.out.println(border);
  }
  
  public static void t(int n) { // The vertical line of the 'T' shall be of n length.
    String t;
    if (n == 0) {
      t = "";
    } else if (n == 1) {  
      t = "***\n * ";
    } else {
      t = "*";
      
      for (int i = 1; i <= n; i++) { // Rows
        for (int j = 1; j <= n; j++) { // Columns
          if (i == 1) { // the first row
            t = t.concat("**");
            
          } else { // the other rows
            
            if ((n % 2) == 0) { // if n is even:
              if ((j == ((n / 2)) + 1) || (j == n / 2)) { // if it's a middle column
                t = t.concat(" *");
              } else { // it's not a middle column
                t = t.concat("  ");
              }
              
            } else { // if n is odd:
              if (j == (Math.ceil(n / 2.0))) { // if it's the middle column
                t = t.concat("*");
              } else { // it's not the middle column
                t = t.concat("  ");
              }
            }
          }
        }
        t = t.concat("\n");
      }
    }
    System.out.println(t);
  }
  
  
  
  
  public static void main(String[] args) {
    System.out.println(isPalindrome("racecar")); // => true
    System.out.println(isPalindrome("john")); // => false
    System.out.println(isSorted("abcefghijk")); // => true
    System.out.println(isSorted("abbeea")); // => false
    border(5);
    t(5);
    t(6);
  }
} 