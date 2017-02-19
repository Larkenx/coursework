import java.awt.*;

public class Ball extends Sprite {

  public Ball(int x, int y, int r, Color color) { // x & y coordinates, radius, color
    super(x, y, r * 2, r * 2, color);
  }

  public void draw(Graphics g) {
    Vector location = getLocation();
    Vector size = getSize();
    int x = location.getX();
    int y = location.getY();
    int side = size.getX();
    g.setColor(getColor());
    g.fillRect(x, y, side, side);
  }

  public static void main(String[] args) {}
}
