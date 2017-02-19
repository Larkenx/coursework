////////////////////////////////////////////////////////////////////////////////////
//
//  C212 Spring 16
//  Homework 1
//
//  Released:  Thursday 1/21/16
//  Due:       Friday 1/29/16 11:59 PM  
//
//  Author  Steven Myers | stelmyer
//  Last Edited: 1/22/2016
//
//
//  Directions:  provide code for unimplemented methods
//
//               ** The fractions do not need to be in a simplified form **
//               ** without being in simplified form it makes adding and subtracting easier **
//////////////////////////////////////////////////////////////////////////////////

public class Fraction {
  // Instance Fields declerations
  private int num; 
  private int denom;
  
  // Constructror - method that initializes class 
  // Paramaters
  // num   - numerator of fraction 
  // denom - denomenator of fraction 
  public Fraction(int num, int denom) {
    this.num = num;
    this.denom = denom; 
  }
  
  // @return value of numerator 
  public int num() {
    return num;
  }
  
  // @return value of denomenator 
  public int denom() {
    return denom; 
  }
  
  // add 2 fractions
  public Fraction add(Fraction other) {
    // TO-DO
    if (this.denom() == other.denom()) {
      return new Fraction(this.num() + other.num(), this.denom());
    } else {
      return new Fraction((this.num() * other.denom()) + (other.num() * this.denom()), (this.denom() * other.denom()));     
    }
  }
  
  // subtract two fractions 
  public Fraction minus(Fraction other) {
    // TO-DO
    if (this.denom() == other.denom()) {
      return new Fraction(this.num() - other.num(), this.denom());
    } else {
      return new Fraction((this.num() * other.denom()) - (other.num() * this.denom()), (this.denom() * other.denom()));     
    }
  }
  
// multiply two fractions 
  public Fraction multiply(Fraction other) {
    return new Fraction(this.num() * other.num(), this.denom() * other.denom());
  }
  
// divide two fractions 
  public Fraction divide(Fraction other) {  
    return new Fraction(this.num() * other.denom(), this.denom() * other.num());
  }
  
// returns decimal value of this fraction
  public double decimalVal() {
    // TO-DO
    // cast integer num and denom values as doubles before operating on in this method
    double decimalNum = this.num();
    double decimalDenom = this.denom();
    double val = decimalNum / decimalDenom;
    return val;   
  }
  
  
  public String toString() {
    // TO-DO
    String numerator = Integer.toString(this.num());
    String denominator = Integer.toString(this.denom());
    String fraction = numerator + "/" + denominator;
    return fraction;
  }
  
// Test Client 
  public static void main(String[] args) {
    // creating a Fraction object from Class Fraction 
    // also known as in Instance 
    Fraction f1 = new Fraction(5, 10);
    Fraction f2 = new Fraction(1, 3);
    
    // example call of printing the value of two fractions multiplied 
    // f1.multiply(f2) returns a new Fraction object, so we can call its toString() method
    System.out.println("Multiply: " + f1.multiply(f2).toString() );
    System.out.println("Divide: " + f1.divide(f2).toString() );
    System.out.println("Add: " + f1.add(f2).toString() );
    System.out.println("Subtract: " + f1.minus(f2).toString() );
    System.out.println("Decimal Val: " + f1.decimalVal());
    
  }
}