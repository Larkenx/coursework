
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

/*
 * Main application for random shape generator app
 */
public class Pong extends JFrame {

    JPanel pong;

    public Pong() {
        super("Pong"); // Instantiates JFrame class here
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        pong = new PongPanel();        
        this.add(pong);
        this.setContentPane(pong);
        this.pack();
        this.setVisible(true);
      
    }

    public static void main(String[] args) {
        // run main application 
      Pong newGame = new Pong();
      
    }
}