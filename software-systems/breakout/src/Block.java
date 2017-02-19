import java.awt.Graphics;
import java.awt.Color;

public class Block extends Paddle { // Blocks technically should never move, but it's ok if we give them the ability to

  public Block(int x, int y, int width, int height, Color color) {
    super(x, y, width, height, color);
  }

  public static void main(String[] args) {}
}
