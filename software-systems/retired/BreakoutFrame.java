import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

public class BreakoutFrame extends JFrame {

    JPanel breakoutPanel;

    public BreakoutFrame() {
        super("Shape Generator"); // Instantiates JFrame class here
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel breakoutPanel = new BreakoutPanel();
        this.add(breakoutPanel);
        this.setContentPane(breakoutPanel);
        this.pack();
        this.setVisible(true);

    }

    public static void main(String[] args) {
        // run main application
      BreakoutFrame test = new BreakoutFrame();

    }
}
