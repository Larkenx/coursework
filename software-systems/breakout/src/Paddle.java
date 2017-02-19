import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;

public class Paddle extends Sprite {

  public Paddle(int x, int y, int width, int height, Color color) { // Its location and size
    super(x, y, width, height, color);
  }

  public void draw(Graphics g) {
    Vector location = getLocation();
    Vector size = getSize();
    int x = location.getX();
    int y = location.getY();
    int width = size.getX();
    int height = size.getY();
    g.setColor(getColor());
    g.fillRect(x, y, width, height);
  }

  public static void main(String[] args) {}
}

/** If the ball has a positive horizontal velocity and touches the left quarter of the paddle,
  * its horizontal and vertical velocities will be negated.
  * If the ball has a negative horizontal velocity and touches the right quarter of the paddle,
  * its horizontal and vertical velocities will be negated.
  * Every time the player successfully touches the ball with the paddle 7 times,
  * the vertical and horizontal velocity of the ball increase by a factor of 2. */
