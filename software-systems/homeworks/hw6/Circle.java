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
import java.awt.Graphics;

public class Circle extends Shape {
  
  private int radius;
  
  
  public Circle(Color fillColor, Color borderColor, int x, int y, int radius) {
    super(fillColor, borderColor, x, y);
    this.radius = radius;
  }
  
  public Circle(Color fillColor, int x, int y, int radius) {
    super(fillColor, x, y);
    this.radius = radius;
  }
  
  public Circle(int x, int y, int radius) {
    super(x, y);
    this.radius = radius;
  }
  
  public double getArea() {
    return Math.pow(this.radius, 2) * Math.PI;
  }
  
  public double getPerimeter() {
    return 2 * Math.PI * this.radius;
  }
  
  public String toString() {
    return "Location: " + "(" + this.getX() + ", " + this.getY() + ")"  +
      "\nRadius: " + this.radius +
      "\nArea: " + this.getArea() +
      "\nPerimeter: " + this.getPerimeter();
  }
  
  public void draw(Graphics g) {
    g.fillOval(this.getX() - 2, this.getY() - 2, (this.radius * 2) + 4, (this.radius * 2) + 4);
    g.setColor(this.getBorderColor());
    g.setColor(this.getFillColor());
    g.fillOval(this.getX(), this.getY(), this.radius * 2, this.radius * 2);
  }
  
  public static void main(String[] args) {
    // Circle testCircle = new Circle(10, 20, 5);
    // System.out.println(testCircle.toString()); 
  }
  
  
}