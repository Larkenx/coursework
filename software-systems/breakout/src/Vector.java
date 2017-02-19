public class Vector {
  private int x;
  private int y;

  public Vector(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

  public Vector plus(Vector other) {
    return new Vector(x + other.x, y + other.y);
  }

  public Vector times(int factor) {
    return new Vector(x * factor, y * factor);
  }

  public static void main(String[] args) {}
}
