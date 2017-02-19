////////////////////////////////////////////////////////////////////////////////////
//
//  C212 Spring 16
//
//  Lab 12
//  @Author  Steven Myers
//
///////////////////////////////////////////////////////////////////////////////////

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.awt.Point;

public class Circle extends Shape {
  private Random random = new Random();
  private int radius;

  public Circle(Color fillColor, Color borderColor, int x, int y, int radius) {
    super(fillColor, borderColor, x, y);
    this.radius = radius;
    this.bounds.add(x + (radius * 2), y + (radius * 2)); // Add a new point to extend the bounds by the dimensions of the circle
  }

  public Circle(Color fillColor, int x, int y, int radius) {
    super(fillColor, x, y);
    this.radius = radius;
    this.bounds.add(x + (radius * 2), y + (radius * 2)); // Add a new point to extend the bounds by the dimensions of the circle
  }

  public Circle(int x, int y, int radius) {
    super(x, y);
    this.radius = radius;
    this.bounds.add(x + (radius * 2), y + (radius * 2)); // Add a new point to extend the bounds by the dimensions of the circle
  }

  public int getRadius() {
    return this.radius;
  }


  public void draw(Graphics g) {
    g.setColor(this.getFillColor());
    g.fillOval(this.getX(),this.getY(), this.radius * 2, this.radius * 2);
  }

  protected void moveLoc(int dx, int dy) {
    super.moveLoc(dx, dy);
  }

  public static void main(String[] args) {}


}
