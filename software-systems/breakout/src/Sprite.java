import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;

abstract class Sprite {
    private Vector location;
    private Vector speed;
    private Vector size;
    private Rectangle bounds;
    private Color color;

    public Sprite(int x, int y, int width, int height, Color color) { // Its location and size
      this.location = new Vector(x, y);
      this.speed = new Vector(0, 0);
      this.size = new Vector(width, height);
      this.bounds = new Rectangle(x, y, width, height);
      this.color = color;
    }

    public Vector getSpeed() {
      return speed;
    }

    public Vector getSize() {
      return size;
    }

    public Vector getLocation() {
      return location;
    }

    public Rectangle getBounds() {
      return bounds;
    }

    public Color getColor() {
      return color;
    }

    public void setSpeed(Vector other) {
      speed = other;
    }

    public void travel() { // Adds its speed to its location, moving it in its direction (For basic movement)
      location = location.plus(speed);
      bounds.translate(speed.getX(), speed.getY());
    }

    abstract void draw(Graphics g);

  public static void main(String[] args) {}
}
