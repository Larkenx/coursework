////////////////////////////////////////////////////////////////////////////////////
//
//  C212 Spring 16
//
//  Homework 6 Template
//  Due: Friday 3/11 11:59 pm
//  @Author  Steven Myers
//
///////////////////////////////////////////////////////////////////////////////////

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

/*
 * Main application for random shape generator app
 */
public class ShapeWindow extends JFrame {

    JPanel shapeDriver;

    public ShapeWindow() {
        super("Shape Collisions"); // Instantiates JFrame class here
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel shapeDriver = new ShapeDriver();
        this.add(shapeDriver);
        this.setContentPane(shapeDriver);
        this.pack();
        this.setVisible(true);

    }

    public static void main(String[] args) {
        // run main application
      ShapeWindow test = new ShapeWindow();

    }
}
