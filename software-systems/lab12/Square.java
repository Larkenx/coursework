////////////////////////////////////////////////////////////////////////////////////
//
//  C212 Spring 16
//
//  Lab 12
//  @Author  Steven Myers
//
///////////////////////////////////////////////////////////////////////////////////

import java.awt.Color;

public class Square extends Rect {

  private int side;

  public Square(Color fillColor, Color borderColor, int x, int y, int side) {
    super(fillColor, borderColor, x, y, side, side);
    this.side = side;
  }

  public Square(Color fillColor, int x, int y, int side) {
    super(fillColor, x, y, side, side);
    this.side = side;
  }

  public Square(int x, int y, int side) {
    super(x, y, side, side);
    this.side = side;
  }

  public static void main(String[] args) {}

}
