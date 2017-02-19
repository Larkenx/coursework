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

public class Rect extends Shape {

  private Random random = new Random();
  private int width;
  private int height;

  public Rect(Color fillColor, Color borderColor, int x, int y, int width, int height) {
    super(fillColor, borderColor, x, y);
    this.width = width;
    this.height = height;
    this.bounds.add(x + width, y + height);
  }

  public Rect(Color fillColor, int x, int y, int width, int height) {
    super(fillColor, x, y);
    this.width = width;
    this.height = height;
    this.bounds.add(x + width, y + height);
  }

  public Rect(int x, int y, int width, int height) {
    super(x, y);
    this.width = width;
    this.height = height;
    this.bounds.add(x + width, y + height);
  }

  public void draw(Graphics g) {
    g.setColor(this.getFillColor());
    g.fillRect(this.getX(), this.getY(), this.width, this.height);
  }

  public static void main(String[] args) {}

}
