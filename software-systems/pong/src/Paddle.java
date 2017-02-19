import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Paddle extends Shape {

  private int width;
  private int length;


  public Paddle(Color fillColor, Color borderColor, int x, int y, int width, int length) {
    super(fillColor, borderColor, x, y);
    this.width = width;
    this.length = length;
  }

  public Paddle(Color fillColor, int x, int y, int width, int length) {
    super(fillColor, x, y);
    this.width = width;
    this.length = length;
  }

  public Paddle(int x, int y, int width, int length) {
    super(x, y);
    this.width = width;
    this.length = length;
  }

  public int getWidth() {
    return this.width;
  }

  public int getLength() {
    return this.length;
  }

  public double getArea() {
    return this.width * this.length;
  }

  public double getPerimeter() {
    return (this.width * 2) + (this.length * 2);
  }

  public String toString() {
    return "Location: " + "(" + this.getX() + ", " + this.getY() + ")"  +
      "\nWidth: " + this.width +
      "\nLength: " + this.length +
      "\nArea: " + this.getArea() +
      "\nPerimeter: " + this.getPerimeter();
  }

  protected void move(int dx, int dy) {
    super.moveLoc(dx, dy);
  }

  protected void draw(Graphics g) {
    g.setColor(this.getFillColor());
    g.fillRect((int) Math.round(this.getX()),(int) Math.round(this.getY()), this.width, this.length);
  }

  public static void main(String[] args) {
    Paddle testPaddle = new Paddle(10, 20, 5, 10);
    System.out.println(testPaddle.toString());
  }

}
