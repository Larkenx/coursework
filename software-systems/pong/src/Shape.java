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

abstract class Shape {
  
  private Color fillColor;
  private Color borderColor;
  private Boolean isFilled;
  private Point Location;
  
  // The three constructors initialize the instance fields
  public Shape (Color fillColor, Color borderColor, int x, int y) {
    this.fillColor = fillColor;
    this.borderColor = borderColor;
    this.Location = new Point(x, y); 
  }
  
  public Shape (Color fillColor, int x, int y) {
    this.fillColor = fillColor;
    this.borderColor = Color.black;
    this.Location = new Point(x, y); 
  }
  
  public Shape (int x, int y) {
    this.fillColor = Color.red;
    this.borderColor = Color.black;
    this.Location = new Point(x, y); 
  }
  
  public void setFillColor(Color c) {
    this.fillColor = c;
  }
  
  public Color getFillColor() {
    return this.fillColor;
  }
  
  public void setBorderColor(Color c) {
    this.borderColor = c;
  }
  
  public Color getBorderColor() {
    return this.borderColor;
  }
  
  public Point getLocation() {
    return this.Location;
  }
  
  public double getX() {
    return this.Location.x();
  }
  
   public double getY() {
    return this.Location.y();
  }
   
   public boolean isFilled() {
     return this.fillColor == Color.white;
   }
   
   protected void moveLoc(double dx, double dy) {
     this.Location = new Point(this.getX() + dx, this.getY() + dy);
   }
   
   abstract double getArea();
   
   abstract double getPerimeter();
   
   abstract void draw(Graphics g);
   
   public static void main(String[] args) {}
  
}