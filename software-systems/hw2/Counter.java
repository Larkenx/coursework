////////////////////////////////////////////////////////////////////////////////////
//
//  C212 Spring 16
//  Homework 2
//  Author  Steven Myers | stelmyer
//  Last Edited:  2/5/2016
//
//               
//////////////////////////////////////////////////////////////////////////////////

public class Counter {
  private final String name; // instance field
  private int count  = 0; // instance field
  
  public Counter(String name) { // Constructor
    this.name = name; 
  }
  
  public void increment() { // Increments count
    count++;
  }
  
  public int tally() { // Returns count
    return count;
  }
  
  public String toString() { // prints name and count
    return name + " was rolled " + count + " times.\n";
  }
  
  public static void main(String[] args) {}
  
}