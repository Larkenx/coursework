////////////////////////////////////////////////////////////////////////////////////
//
//  C212 Spring 16
//
//  Homework 6 Template
//  Due: Friday 3/11 11:59 pm
//  @Author  Steven Myers
//
///////////////////////////////////////////////////////////////////////////////////

import java.awt.Color;

public class Square extends Rectangle {
  
  private int side;
  
  public Square(Color fillColor, Color borderColor, int x, int y, int side) {
    super(fillColor, borderColor, x, y, side, side);
    this.side = side;
  }
  
  public Square(Color fillColor, int x, int y, int side) {
    super(fillColor, x, y, side, side);
    this.side = side;
  }
  
  public Square(int x, int y, int side) {
    super(x, y, side, side);
    this.side = side;
  }
  
  public String toString() {
    return "Location: " + "(" + this.getX() + ", " + this.getY() + ")"  +
      "\nSide Length: " + this.side +
      "\nArea: " + this.getArea() +
      "\nPerimeter: " + this.getPerimeter();
  } 
  
  public static void main(String[] args) {
    // Square testSquare = new Square(20, 20, 5);
    // System.out.println(testSquare.toString());
  }
  
}