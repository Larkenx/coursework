////////////////////////////////////////////////////////////////////////////////////
//
//  C212 Spring 16
//  Homework 2
//  Author  Steven Myers | stelmyer
//  Last Edited:  2/5/2016
//
//               
//////////////////////////////////////////////////////////////////////////////////

import java.util.Random;

public class Die {
  
  private Random rand; // instance field
  
  public Die() {
    rand = new Random();
  }
    
  public int roll() { // returns a random number between [1,6]
  // Look at the nextInt(int bound) method of Random 
    int result = rand.nextInt(6) + 1;
    return result;
  }
  
  public static void main(String[] args) {
  }
}