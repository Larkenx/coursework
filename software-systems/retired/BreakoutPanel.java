import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.awt.Dimension;

public class BreakoutFrame extends JFrame {

  public BreakoutFrame() {
    super("Breakout");
    BreakoutPanel breakoutPanel = new BreakoutPanel();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(breakoutPanel);
    frame.setContentPane(breakoutPanel);
    frame.pack();
    frame.setVisible(true);
}

  public class BreakoutPanel extends JPanel implements KeyListener, ActionListener {
    // Jpanel Variables
    private Timer timer;
    private int FRAME_WIDTH = 500;
    private int FRAME_HEIGHT = 500;
    // Sprites
    private Paddle paddle;
    private int paddleWidth = 50;
    private int paddleHeight = 10;
    private int hitCounter = 0; // If the paddle is hit 7 times in a row, increase the speed of the ball
    private Boolean[] paddleMovement = {false, false}; // Index 0 = Left Movement, 1 = Right Movement.

    private Ball ball;

    // Blocks
    // Block Colors
    private Color magenta = new Color(105, 19, 181);
    private Color indigo = new Color(111, 63, 255);
    private Color navyBlue = new Color(57, 47, 236);
    private Color oceanBlue = new Color(74, 107, 204);
    private Color skyBlue = new Color(95, 159, 235);
    private Color[] blockColors = {magenta, indigo, navyBlue, oceanBlue, skyBlue}; // Cool Scheme
    private ArrayList<ArrayList<Block>> blocks = new ArrayList<ArrayList<Block>>();
    private int blockWidth = FRAME_WIDTH / 10;
    private int blockHeight = FRAME_HEIGHT / 25;

    public BreakoutPanel() {
      super();

      // Window
      setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
      setBackground(new Color(37, 54, 56));

      // Action Listener
      timer = new Timer(1000/60, this);
      timer.start();

      // Key Listener
      addKeyListener(this);

      // Initialize sprites
      paddle = new Paddle(FRAME_WIDTH / 2, FRAME_HEIGHT - 30, paddleWidth, paddleHeight, Color.white);
      ball = new Ball(FRAME_WIDTH / 2, (int) Math.round(FRAME_HEIGHT * .3), 7, Color.red);
      ball.setSpeed(new Vector(2, 2));

      for (int i = 0; i < 5; i++) { // 5 rows
        Color color = blockColors[i];
        ArrayList<Block> temp = new ArrayList<Block>();
        for (int j = 0; j < 10; j++) { // 10 columns
          temp.add(new Block(((j * FRAME_WIDTH) / 10),
                                    (((i * FRAME_HEIGHT)) / 25) + 40,
                                    blockWidth, blockHeight, color));
        }
        blocks.add(temp);
      }
    }

    public void addNotify() { // Requests the focus to the panel instead of the frame
      super.addNotify();
      requestFocus();
    }

    private void collisions() {
      Rectangle paddleBounds = paddle.getBounds();
      Rectangle ballBounds = ball.getBounds();
      Rectangle gameBounds = new Rectangle(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
      Vector ballSpeed = ball.getSpeed();
      int ballX = (int) ballBounds.getX();
      int ballY = (int) ballBounds.getY();

      // Paddle Collisions - *If ball hits left or right quarter, negate x and y velocity
      if (paddleBounds.intersects(ballBounds)) {
        Rectangle leftQuarter = new Rectangle((int) paddleBounds.getX(), (int) paddleBounds.getY(), (blockWidth / 4), 2);
        Rectangle rightQuarter = new Rectangle( (int) (paddleBounds.getX() + Math.round((3 * blockWidth / 4))),
                                                (int) paddleBounds.getY(), (blockWidth / 4), 2);
        if ((rightQuarter.intersects(ballBounds) && ballSpeed.getX() < 0) ||
            (leftQuarter.intersects(ballBounds) && ballSpeed.getX() > 0)) {
          ball.setSpeed(ballSpeed.times(-1)); // Reverse x & y direction
        } else {
          ball.setSpeed(new Vector(ballSpeed.getX(), ballSpeed.getY() * -1)); // Reverse y direction
        }
      }

        // Wall Collisions
        if (! gameBounds.contains(ballBounds)) {
          if (ballX > 0 && ballX < FRAME_WIDTH && ballY < 0) { // Hits top wall
            ball.setSpeed(new Vector(ballSpeed.getX(),ballSpeed.getY() * -1));
          } else if (ballX < 0 && ballY > 0 && ballY < FRAME_HEIGHT) { // Hits left Wall
            ball.setSpeed(new Vector(ballSpeed.getX() * -1, ballSpeed.getY()));
          } else if (ballX + ballBounds.getWidth() > FRAME_WIDTH && ballY > 0 && ballY < FRAME_HEIGHT) { // Hits Right Wall
            ball.setSpeed(new Vector(ballSpeed.getX() * -1, ballSpeed.getY()));
          } else if (ballX > 0 && ballX < FRAME_WIDTH && ballY + ballBounds.getHeight() > FRAME_HEIGHT) { // Goes past bottom wall
            // Game Over!
          } else { // Hits one of the corners
            ball.setSpeed(ballSpeed.times(-1));
          }
        }

        // Block Collisions
        // *Must use removalQueue or CurrentModification Exception is raised
        ArrayList<Block> removalQueue = new ArrayList<Block>();
        for (ArrayList<Block> currentRow : blocks) {
          for (Block block : currentRow) { // 10 columns
            Rectangle blockBounds = block.getBounds();
            if (blockBounds.intersects(ballBounds)) {
              removalQueue.add(block);
              int blockX = (int) blockBounds.getX();
              int blockY = (int) blockBounds.getY();
              // I was having an issue where corners of blocks double count as either top/right, top/left or bottom/right, bottom/left
              // collisions, so I made the left and right block detection a little harsher by reducing the edges by 1 pixel
              Rectangle leftBlock = new Rectangle(blockX, blockY + 1, 1, blockHeight - 2);
              Rectangle rightBlock = new Rectangle(blockX + (blockWidth - 1), blockY + 1, 1, blockHeight - 2);
              Rectangle topBlock = new Rectangle(blockX, blockY, blockWidth, 1);
              Rectangle bottomBlock = new Rectangle(blockX, blockY + (blockHeight - 1), blockWidth, 1);
              if (ballBounds.intersects(leftBlock) || ballBounds.intersects(rightBlock)) { // Left or Right
                ball.setSpeed(new Vector(ballSpeed.getX() * -1, ballSpeed.getY())); // Reverse horizontal velocity
              } else if (ballBounds.intersects(topBlock) || ballBounds.intersects(bottomBlock)) {
                ball.setSpeed(new Vector(ballSpeed.getX(), ballSpeed.getY() * -1)); // Reverse verticle velocity
              }
            }
          }
        }

        for (ArrayList<Block> currentRow : blocks) {
        currentRow.removeAll(removalQueue);
      }
    }


    private void moveSprites() {
      if (paddleMovement[0] && paddleMovement[1]) {
        paddle.setSpeed(new Vector(0, 0));
      } else if (paddleMovement[0]) {
        paddle.setSpeed(new Vector(-2, 0));
      } else if (paddleMovement[1]) {
        paddle.setSpeed(new Vector(2, 0));
      } else {
        paddle.setSpeed(new Vector(0, 0));
      }

      Rectangle paddleBounds = paddle.getBounds();
      Rectangle gameBounds = new Rectangle(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
      if (! gameBounds.contains(paddleBounds)) { // 'Bounce' off the game walls if too close.
        Vector paddleSpeed = paddle.getSpeed();
        if (paddleBounds.getX() + paddleBounds.getWidth() > FRAME_WIDTH) {
          paddle.setSpeed(new Vector(-2, 0));
        } else if (paddleBounds.getX() < FRAME_WIDTH) {
          paddle.setSpeed(new Vector(2, 0));
        }
        // If someone tries hard enough, they can press "a" as soon as they go into this zone, causing it to go more to the right (negated)
      }

      paddle.travel();
      ball.travel();
    }

    public void actionPerformed(ActionEvent e) {
      collisions();
      moveSprites();
      repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      for (ArrayList<Block> currentRow : blocks) {
        for (Block currentBlock : currentRow) { // 10 columns
          currentBlock.draw(g);
        }
      }
      ball.draw(g);
      paddle.draw(g);
    }


    @Override
    public void keyPressed(KeyEvent e) {
      char id = e.getKeyChar();
      if (id == 'a') {
        paddleMovement[0] = true;
      } else if (id == 'd') {
        paddleMovement[1] = true;
      }
    }

    @Override
    public void keyReleased(KeyEvent e) {
      char id = e.getKeyChar();
      if (id == 'a') {
        paddleMovement[0] = false;
      } else if (id == 'd') {
        paddleMovement[1] = false;
      }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {}
  }

  public static void main(String[] args) {}
}
