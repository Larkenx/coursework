import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.awt.Dimension;

public class PongPanel extends JPanel implements KeyListener, ActionListener {

  public final int FRAME_WIDTH = 500;
  public final int FRAME_HEIGHT = 500;

  private Timer timer;

  private int score;
  private boolean gameOver;
  private PongBall ball;
  private Paddle paddle;
  private boolean[] movement = new boolean[2];

  public PongPanel() { // Constructor
    super();
    // Window
    setPreferredSize( new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
    setBackground(Color.black);

    // Score
    score = 0;

    // Action Listener
    timer = new Timer(1000/60, this);
    timer.start();

    // Key Listener
    addKeyListener(this);

    // Initialize Pong Ball and Paddle
    this.ball = new PongBall(Color.white, FRAME_WIDTH / 2, FRAME_HEIGHT / 2, 10);
    this.paddle = new Paddle(Color.gray, FRAME_WIDTH / 2, FRAME_HEIGHT - 50, 50, 30);
    this.movement[0] = false;
    this.movement[1] = false;

  }

  public void addNotify() { // Requests the focus to the panel instead of the frame
    super.addNotify();
    requestFocus();
  }

  private void moveBall() { // Update the pong ball's location
    String d = ball.getDirection();
    int speed = 2;
    if (d == "NW") {
      ball.move(-1 * speed, -1 * speed);

    } else if (d == "NE") {
      ball.move(1 * speed, -1 * speed);

    } else if (d == "SW") {
      ball.move(-1 * speed, 1 * speed);

    } else if (d == "SE") {
      ball.move(1 * speed, 1 * speed);
    }
  }

  private void drawScore(Graphics g) {
    g.setColor(Color.white);
    g.drawString("Score: " + score, 20, 20);
  }

  private void drawGameOver(Graphics g) {
    g.setColor(Color.red);
    g.drawString("GAME OVER!", FRAME_WIDTH / 2 - 10, FRAME_HEIGHT / 2);
  }

  @Override
  public void actionPerformed(ActionEvent e) { // Collision Detector & Direction Changer
    // Change the direction of the balls as they hit the walls OR the paddle
    double leftX = (ball.getCenter()).x() - ball.getRadius();
    double upY = (ball.getCenter()).y() - ball.getRadius();
    double rightX = (ball.getCenter()).x() + ball.getRadius();
    double downY = (ball.getCenter()).y() + ball.getRadius();
    String direction = ball.getDirection();

    double paddleLeft = paddle.getX();
    double paddleRight = paddleLeft + paddle.getWidth();

    // 2 cases for each boundary, 1 for each corner

    if ((leftX == 0) && (upY == 0)) { // Hits the top-left corner
      ball.changeDirection("SW");

    } else if ((rightX == FRAME_WIDTH) && (upY == FRAME_HEIGHT)) { // Hits the top-right corner
      ball.changeDirection("SE");

    } else if ((leftX > 0) && (upY <= 0)) { // Hits top wall
      switch (direction) {
        case "NW":
        ball.changeDirection("SW");
        break;
        case "NE":
        ball.changeDirection("SE");
        break;
      }

    } else if ((leftX <= 0) && (upY > 0)) { // Hits left wall
      switch (direction) {
        case "NW":
        ball.changeDirection("NE");
        break;
        case "SW":
        ball.changeDirection("SE");
        break;
      }

    } else if ((rightX >= FRAME_WIDTH) && (downY < FRAME_HEIGHT)) { // Hits right wall
      switch (direction) {
        case "NE":
        ball.changeDirection("NW");
        break;
        case "SE":
        ball.changeDirection("SW");
        break;
      }
    } else if ( (leftX >= paddleLeft) && (leftX <= paddleRight) && (downY >= (paddle.getY() + 2))) {
      score++;
      switch (direction) {
        case "SW":
        ball.changeDirection("NW");

        break;
        case "SE":
        ball.changeDirection("NE");
        break;
      }
    } else if ( (downY > FRAME_HEIGHT + 10) ) {
      this.gameOver = true;
      timer.stop();
    }

    moveBall();

    if ((movement[0] == true) && (movement[1] == true)) { // No movement
    } else if (movement[0] == true) { // Move left
      paddle.move(-3, 0);
    } else if (movement[1] == true) { // Move right
      paddle.move(3, 0);
    }

    this.repaint();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    ball.draw(g);
    paddle.draw(g);
    drawScore(g);
    if (gameOver) {
      drawGameOver(g);
    }
  }

  @Override
  public void keyPressed(KeyEvent e) {
    char id = e.getKeyChar();
    if (id == 'a') {
      movement[0] = true;
    } else if (id == 'd') {
      movement[1] = true;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    char id = e.getKeyChar();
    if (id == 'a') {
      movement[0] = false;
    } else if (id == 'd') {
      movement[1] = false;
    }
  }


  @Override
  public void keyTyped(KeyEvent e) {}

  public static void main(String[] args) {
    PongPanel test = new PongPanel();
  }
}
