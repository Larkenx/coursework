import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class PongBall extends Shape {
  private Random random = new Random();
  private int radius;
  private Point center;
  private String[] possibleDirections = {"NW", "NE", "SW", "SE"};
  // A direction is one of: NW, NE, SW, SE
  private String direction = "SW"; // Starts going SW or SE

  public PongBall(Color fillColor, Color borderColor, int x, int y, int radius) {
    super(fillColor, borderColor, x, y);
    this.radius = radius;
    this.center = new Point(getX() + radius, getY() + radius);
  }

  public PongBall(Color fillColor, int x, int y, int radius) {
    super(fillColor, x, y);
    this.radius = radius;
    this.center = new Point(getX() + radius, getY() + radius);
  }

  public PongBall(int x, int y, int radius) {
    super(x, y);
    this.radius = radius;
    this.center = new Point(getX() + radius, getY() + radius);
  }

  public double getArea() {
    return Math.pow(this.radius, 2) * Math.PI;
  }

  public double getPerimeter() {
    return 2 * Math.PI * this.radius;
  }

  public Point getCenter() {
    return this.center;
  }

  public int getRadius() {
    return this.radius;
  }

  public String getDirection() {
    return this.direction;
  }

  public String toString() {
    return "Location: " + "(" + this.getX() + ", " + this.getY() + ")"  +
      "\nRadius: " + this.radius +
      "\nArea: " + this.getArea() +
      "\nPerimeter: " + this.getPerimeter();
  }

  public void draw(Graphics g) {
    // -- Set borders --
    //g.setColor(Color.black);
    //g.fillOval((int) Math.round(this.getX() - 2), (int) Math.round(this.getY() - 2), (this.radius * 2) + (int) Math.round((this.radius * .1)), (this.radius * 2) + (int) Math.round((this.radius * .1)));
    g.setColor(this.getFillColor());
    g.fillOval((int) Math.round(this.getX()), (int) Math.round(this.getY()), this.radius * 2, this.radius * 2);
  }

  protected void move(int dx, int dy) {
    super.moveLoc(dx, dy);
    center = new Point((this.center).x() + dx, (this.center).y() + dy);
  }

  public void changeDirection(String d) {
    this.direction = d;
  }

  public void bounce() {
    String d = this.getDirection();
    if (d == "NW") {
      this.direction = "SE";

    } else if (d == "NE") {
      this.direction = "SW";

    } else if (d == "SW") {
      this.direction = "NE";

    } else if (d == "SE") {
      this.direction = "NW";
    }
  }

  public static void main(String[] args) {
    // PongBall testBall = new PongBall(10, 20, 5);
    // System.out.println(testBall.toString());
  }


}
