////////////////////////////////////////////////////////////////////////////////////
//
//  C212 Spring 16
//
//  Lab 12
//  @Author  Steven Myers
//
///////////////////////////////////////////////////////////////////////////////////

// These are the imports I used
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Point;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.awt.Dimension;

public class ShapeDriver extends JPanel implements KeyListener, ActionListener {

  protected JButton jbnDecrease, jbnIncrease;
  public final int FRAME_WIDTH = 1000;
  public final int FRAME_HEIGHT = 600;
  private Random random = new Random();
  private Timer timer;

  ArrayList<Shape> shapes = new ArrayList<Shape>();

  public ShapeDriver() { // Constructor
    super();

    // Action Listener
    timer = new Timer(1000/60, this);
    timer.start();

    // Window
    setPreferredSize( new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
    setBackground(Color.blue);

    // Key Listener
    addKeyListener(this);

    // Buttons
    // jbnDecrease = new JButton("- Decrease Speed");
    // jbnIncrease = new JButton("+ Increase Speed");
    // jbnDecrease.setMnemonic(KeyEvent.VK_MINUS);
    // jbnIncrease.setMnemonic(KeyEvent.VK_PLUS);
    // add(jbnDecrease);
    // add(jbnIncrease);


  }

  public void addNotify() { // Requests the focus to the panel instead of the frame
    super.addNotify();
    requestFocus();
  }

  private void moveShapes() {
    for (Shape shape : shapes) { // Update the circle's locations by moving them
      String d = shape.getDirection();
      if (d == "NW") {
        shape.moveLoc(-1, -1);

      } else if (d == "NE") {
        shape.moveLoc(1, -1);

      } else if (d == "SW") {
        shape.moveLoc(-1, 1);

      } else if (d == "SE") {
        shape.moveLoc(1, 1);
      }
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) { // Collision Detector & Direction Changer
    // Change the direction of the shapes as they hit the walls/boundaries
    for (Shape shape : shapes) {
      String direction = shape.getDirection();
      Rectangle bounds = shape.bounds;
      int leftX = shape.getX();
      int upY = shape.getY();
      int rightX = shape.getX() + (int) bounds.getWidth();
      int downY =  shape.getY() + (int) bounds.getHeight();

      // 2 cases for each boundary, 1 for each corner
      if ( (upY < 0) && (leftX > 0) && (rightX < FRAME_WIDTH) ) { // Hits top wall
        switch (direction) {
          case "NW":
            shape.changeDirection("SW");
            break;
          case "NE":
            shape.changeDirection("SE");
            break;
        }

      } else if ((leftX <= 0) && (upY > 0)) { // Hits left wall
        switch (direction) {
          case "NW":
            shape.changeDirection("NE");
            break;
          case "SW":
            shape.changeDirection("SE");
            break;
        }

      } else if ((leftX == 0) && (upY == 0)) { // Hits the top-left corner
        shape.changeDirection("SW");

      } else if ((rightX == FRAME_WIDTH) && (upY == FRAME_HEIGHT)) { // Hits the top-right corner
        shape.changeDirection("SE");

      } else if ((rightX < FRAME_WIDTH) && (downY >= FRAME_HEIGHT)) { // Hits bottom wall
        switch (direction) {
          case "SW":
            shape.changeDirection("NW");
            break;
          case "SE":
            shape.changeDirection("NE");
            break;
        }
      } else if ((rightX >= FRAME_WIDTH) && (downY < FRAME_HEIGHT)) { // Hits right wall
        switch (direction) {
          case "NE":
            shape.changeDirection("NW");
            break;
          case "SE":
            shape.changeDirection("SW");
            break;
        }
      } else if ((rightX == FRAME_WIDTH) && (downY == FRAME_HEIGHT)) { // Hits bottom-right corner
        shape.changeDirection("NW");
      } else if ((leftX == 0) && (downY == FRAME_HEIGHT)) {
        shape.changeDirection("NE");
      }
    }


    // Now we need to compare every circle against every other circle, itself excluded,
    // If the two circles are within sum of both radii, then we will "bounce" the two off of each other
    // and trade colors!
    for (Shape shapeA : shapes) { // The circle we're working with
      for (Shape shapeB : shapes) {
        if (shapeA.equals(shapeB)) { // Skip the current circle we're evaluating (This is messy)
          continue;
        }

        Rectangle boundsA = shapeA.getBounds();
        Rectangle boundsB = shapeB.getBounds();

        if (boundsA.intersects(boundsB)) {
          Color colorA = shapeA.getFillColor();
          Color colorB = shapeB.getFillColor();
          String directionA = shapeA.getDirection();
          String directionB = shapeB.getDirection();
          shapeA.setFillColor(colorB);
          shapeB.setFillColor(colorA);
          shapeA.changeDirection(directionB);
          shapeB.changeDirection(directionA);
          moveShapes();
        }
      }
    }
    moveShapes();
    this.repaint();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    for (Shape shape : shapes) {
      shape.draw(g);
    }
  }

  @Override
  public void keyPressed(KeyEvent e) {
    Color randomColor = new Color((int) (Math.random() * 0x1000000)); // *Found on Stackoverflow by user Boann
    char id = e.getKeyChar();
    if (id == 'c') {
      shapes.add(new Circle(randomColor, random.nextInt(FRAME_WIDTH), random.nextInt(FRAME_HEIGHT), (random.nextInt(35) + 5)));
    }
    if (id == 'r') {
      shapes.add(new Rect(randomColor, random.nextInt(FRAME_WIDTH), random.nextInt(FRAME_HEIGHT), (random.nextInt(35) + 5), (random.nextInt(35) + 5)));
    }
    if (id == 's') {
      shapes.add(new Square(randomColor, random.nextInt(FRAME_WIDTH), random.nextInt(FRAME_HEIGHT), (random.nextInt(35) + 5)));
    }

    repaint();

  }

  @Override
  public void keyReleased(KeyEvent e) { }


  @Override
  public void keyTyped(KeyEvent e) { }



  public static void main(String[] args) {}
}
