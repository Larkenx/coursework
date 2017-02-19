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
import java.awt.Rectangle;
import java.awt.Point;
import java.util.Random;

abstract class Shape {

  private Random random = new Random();
  private Color fillColor;
  private Color borderColor;
  private Boolean isFilled;
  protected Point location;
  protected Rectangle bounds;
  private String[] possibleDirections = {"NW", "NE", "SW", "SE"};
  // A direction is one of: NW, NE, SW, SE
  private String direction = possibleDirections[random.nextInt(4)]; // Random Direction

  // The three constructors initialize the instance fields
  public Shape (Color fillColor, Color borderColor, int x, int y) {
    this.fillColor = fillColor;
    this.borderColor = borderColor;
    this.location = new Point(x, y);
    this.bounds = new Rectangle(this.location);
  }

  public Shape (Color fillColor, int x, int y) {
    this.fillColor = fillColor;
    this.borderColor = Color.black;
    this.location = new Point(x, y);
    this.bounds = new Rectangle(this.location);
  }

  public Shape (int x, int y) {
    this.fillColor = Color.red;
    this.borderColor = Color.black;
    this.location = new Point(x, y);
    this.bounds = new Rectangle(this.location);
  }

  public void changeDirection(String d) {
    this.direction = d;
  }

  public String getDirection() {
    return this.direction;
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
    return this.location;
  }

  public Rectangle getBounds() {
    return this.bounds;
  }

  public int getX() {
    return (int) this.location.getX();
  }

   public int getY() {
    return (int) this.location.getY();
  }

   public boolean isFilled() {
     return this.fillColor == Color.white;
   }

   protected void moveLoc(int dx, int dy) {
     this.location = new Point(this.getX() + dx, this.getY() + dy);
     this.bounds.translate(dx, dy);
   }

   abstract void draw(Graphics g);

   public static void main(String[] args) {}

}
