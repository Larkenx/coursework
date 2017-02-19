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

public class Rectangle extends Shape {
  
  private int width;
  private int length;
  
  
  public Rectangle(Color fillColor, Color borderColor, int x, int y, int width, int length) {
    super(fillColor, borderColor, x, y);
    this.width = width;
    this.length = length;
  }
  
  public Rectangle(Color fillColor, int x, int y, int width, int length) {
    super(fillColor, x, y);
    this.width = width;
    this.length = length;
  }
  
  public Rectangle(int x, int y, int width, int length) {
    super(x, y);
    this.width = width;
    this.length = length;
  }
  
  public double getArea() {
    return this.width * this.length;
  }
      
  public double getPerimeter() {
    return (this.width * 2) + (this.length * 2);
  }
  
  public void draw(Graphics g) {
    //g.fillColor(this.getFillColor());
    g.fillRect(this.getX() - 2, this.getY() - 2, this.width + 4, this.length + 4);
    g.setColor(this.getBorderColor());
    g.setColor(this.getFillColor());
    g.fillRect(this.getX(), this.getY(), this.width, this.length);
  }
  
    public String toString() {
      return "Location: " + "(" + this.getX() + ", " + this.getY() + ")"  +
        "\nWidth: " + this.width +
        "\nLength: " + this.length +
        "\nArea: " + this.getArea() +
        "\nPerimeter: " + this.getPerimeter();
    }
  
  public static void main(String[] args) {
    // Rectangle testRectangle = new Rectangle(10, 20, 5, 10);
    // System.out.println(testRectangle.toString());
  }
  
}