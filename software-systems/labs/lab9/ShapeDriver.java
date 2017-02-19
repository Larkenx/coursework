////////////////////////////////////////////////////////////////////////////////////
//
//  C212 Spring 16
//
//  Homework 6 Template
//  Due: Friday 3/11 11:59 pm
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

  ArrayList<Circle> circles = new ArrayList<Circle>();

  public ShapeDriver() { // Constructor
    super();

    // Action Listener
    timer = new Timer(1000/60, this);
    timer.start();

    // Window
    setPreferredSize( new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
    setBackground(Color.blue);

    // Key Listener
    addKeyListener(this)

    // Buttons
    jbnDecrease = new JButton("- Decrease Speed");
    jbnIncrease = new JButton("+ Increase Speed");
    jbnDecrease.setMnemonic(KeyEvent.VK_MINUS);
    jbnIncrease.setMnemonic(KeyEvent.VK_PLUS);
    add(jbnDecrease);
    add(jbnIncrease);


  }

  public void addNotify() { // Requests the focus to the panel instead of the frame
    super.addNotify();
    requestFocus();
  }

  private void moveCircles(double speed) {
    for (Circle circle : circles) { // Update the circle's locations by moving them
      String d = circle.getDirection();
      if (d == "NW") {
        circle.move(-1, -1, speed);

      } else if (d == "NE") {
        circle.move(1, -1, speed);

      } else if (d == "SW") {
        circle.move(-1, 1, speed);

      } else if (d == "SE") {
        circle.move(1, 1, speed);
      }
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) { // Collision Detector & Direction Changer
    // Change the direction of the circles as they hit the walls/boundaries
    for (Circle circle : circles) {
      double leftX = (circle.getCenter()).x() - circle.getRadius();
      double upY = (circle.getCenter()).y() - circle.getRadius();
      double rightX = (circle.getCenter()).x() + circle.getRadius();
      double downY = (circle.getCenter()).y() + circle.getRadius();
      String direction = circle.getDirection();

      // 2 cases for each boundary, 1 for each corner
      if ((leftX > 0) && (upY <= 0)) { // Hits top wall
        switch (direction) {
          case "NW":
            circle.changeDirection("SW");
            break;
          case "NE":
            circle.changeDirection("SE");
            break;
        }

      } else if ((leftX <= 0) && (upY > 0)) { // Hits left wall
        switch (direction) {
          case "NW":
            circle.changeDirection("NE");
            break;
          case "SW":
            circle.changeDirection("SE");
            break;
        }

      } else if ((leftX == 0) && (upY == 0)) { // Hits the top-left corner
        circle.changeDirection("SW");

      } else if ((rightX == FRAME_WIDTH) && (upY == FRAME_HEIGHT)) { // Hits the top-right corner
        circle.changeDirection("SE");

      } else if ((rightX < FRAME_WIDTH) && (downY >= FRAME_HEIGHT)) { // Hits bottom wall
        switch (direction) {
          case "SW":
            circle.changeDirection("NW");
            break;
          case "SE":
            circle.changeDirection("NE");
            break;
        }
      } else if ((rightX >= FRAME_WIDTH) && (downY < FRAME_HEIGHT)) { // Hits right wall
        switch (direction) {
          case "NE":
            circle.changeDirection("NW");
            break;
          case "SE":
            circle.changeDirection("SW");
            break;
        }
      } else if ((rightX == FRAME_WIDTH) && (downY == FRAME_HEIGHT)) { // Hits bottom-right corner
        circle.changeDirection("NW");
      } else if ((leftX == 0) && (downY == FRAME_HEIGHT)) {
        circle.changeDirection("NE");
      }
    }


    // Now we need to compare every circle against every other circle, itself excluded,
    // If the two circles are within sum of both radii, then we will "bounce" the two off of each other
    // and trade colors!
    for (Circle circleA : circles) { // The circle we're working with
      for (Circle circleB : circles) {
        if (circleA.equals(circleB)) { // Skip the current circle we're evaluating (This is messy)
          continue;
        }
        double xa = (circleA.getCenter()).x();
        double ya = (circleA.getCenter()).y();
        double xb = (circleB.getCenter()).x();
        double yb = (circleB.getCenter()).y();
        double distance = (circleA.getCenter()).distanceTo(circleB.getCenter());

        if (distance < (circleA.getRadius() + circleB.getRadius() + 1)) { // Check if they're within range of one another
          Color colorA = circleA.getFillColor();
          Color colorB = circleB.getFillColor();
          String directionA = circleA.getDirection();
          String directionB = circleB.getDirection();
          circleA.setFillColor(colorB);
          circleA.changeDirection(directionB);
          circleB.changeDirection(directionA);
          circleB.setFillColor(colorA);
          moveCircles(1);
        }
      }
    }
    moveCircles(1);
    this.repaint();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    for (Circle circle : circles) {
      circle.draw(g);
    }
  }

  @Override
  public void keyPressed(KeyEvent e) {
    Color randomColor = new Color((int) (Math.random() * 0x1000000)); // *Found on Stackoverflow by user Boann
    char id = e.getKeyChar();
    if (id == 'c') {
      circles.add(new Circle(randomColor, random.nextInt(550), random.nextInt(550), (random.nextInt(35) + 5)));
    }

    repaint();

  }

  @Override
  public void keyReleased(KeyEvent e) { }


  @Override
  public void keyTyped(KeyEvent e) { }



  public static void main(String[] args) {
    // ShapeDriver test = new ShapeDriver();
    // (test.shapes).add(new Rectangle(2, 2, 2, 2));
  }
}
