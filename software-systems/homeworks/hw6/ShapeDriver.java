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

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Dimension;

/*
 * Driver program for randam shape generator app
 */
public class ShapeDriver extends JPanel implements KeyListener {

    // Panel constants
    public final int FRAME_WIDTH = 600;
    public final int FRAME_HEIGHT = 600;
    private Random random = new Random();
    ArrayList<Shape> shapes = new ArrayList<Shape>();
    public ShapeDriver() {
        super();
        setPreferredSize( new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setBackground(Color.blue);
        shapes.add(new Rectangle(random.nextInt(600), random.nextInt(600), random.nextInt(50), random.nextInt(50)));
        shapes.add(new Rectangle(random.nextInt(600), random.nextInt(600), random.nextInt(50), random.nextInt(50)));
        shapes.add(new Rectangle(random.nextInt(600), random.nextInt(600), random.nextInt(50), random.nextInt(50)));
 
        addKeyListener(this);
        
        

        /* T0-DO: 
         *  - set up JPanel
         *  - initialize any other fiels you want to declare and use
         *  - add the KeyListiner 
         */
    }
    
    public void addNotify() {
      super.addNotify();
      requestFocus();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        // call super class paintComponent method
        // background will not be colored otherwise
        super.paintComponent(g);
      for (Shape shape : shapes) {
        shape.draw(g);
      }

        // TO-DO use the different Shapes draw methods here
        // The draw methods in the differnet shapes should take 
        // The Graphics object should be passed to the Shapes Draw method
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
      /* To-DO: 
       *  - if c, r, or s is pressed draw a cirlce, rectangle or square 
       *  - repaint the JPanel
       */
      Color randomColor = new Color((int) (Math.random() * 0x1000000)); // *Found on Stackoverflow by user Boann
      char id = e.getKeyChar();
      if (id == 'r') {
        shapes.add(new Rectangle(randomColor, random.nextInt(600), random.nextInt(600), random.nextInt(50), random.nextInt(50)));
      }
      
      if (id == 'c') {
        shapes.add(new Circle(randomColor, random.nextInt(600), random.nextInt(600), random.nextInt(50)));
      }
      
      if (id == 's') {
        shapes.add(new Square(randomColor, random.nextInt(600), random.nextInt(600), random.nextInt(50)));
        
      }
      repaint();
      
    }
    // do not neet to do anything with this method from KeyListener
    // but must have since this class implements KeyListiner 
    @Override
    public void keyReleased(KeyEvent e) { }
    
    // do not neet to do anything with this method from KeyListener
    // but must have since this class implements KeyListiner 
    @Override
    public void keyTyped(KeyEvent e) { }
    
    
    // test client
    public static void main(String[] args) {
      // ShapeDriver test = new ShapeDriver();
      // (test.shapes).add(new Rectangle(2, 2, 2, 2));
    }
}