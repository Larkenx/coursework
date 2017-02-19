import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.Dimension;
import java.awt.Color;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JEditorPane;
import javax.swing.text.*;


public class WordCounter extends JPanel {

  private Color backgroundColor = new Color((int) (Math.random() * 0x1000000));

  public WordCounter() {
    super();
    setPreferredSize(new Dimension(500, 500));
    JFileChooser chooser = new JFileChooser();
    JButton selectButton = new JButton("Select a file");
    JEditorPane textDisplay = new JEditorPane(".txt", "Please select a file.");
    JButton colorButton = new JButton("Change background color");
    textDisplay.setEditable(false);
    textDisplay.setSize(400, 400);
    //Add action listener to button
    colorButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        Color randomColor = new Color((int) (Math.random() * 0x1000000));
        backgroundColor = randomColor;
        WordCounter.this.setBackground(randomColor);
      }
    });
    selectButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            int ret = chooser.showOpenDialog(WordCounter.this);
            if (ret == JFileChooser.APPROVE_OPTION) {
              File selectedFile = chooser.getSelectedFile();
              FrequencyCounter frequencyCount = new FrequencyCounter(selectedFile);
              String fileName = frequencyCount.getFileName();
              String wordCount = frequencyCount.getTotalWords();
              String lineCount = frequencyCount.getTotalLines();
              String hashMapPrint = frequencyCount.toString();
              String result = wordCount + "\n" + lineCount + "\nHash Map:\n" + hashMapPrint;

              Document doc = textDisplay.getDocument();
              try {
                doc.remove(0, doc.getLength());
                doc.insertString(0, result, null);
              }
              catch(BadLocationException d) {}
            }
        }
    });
    add(selectButton);
    add(textDisplay);
    add(colorButton);
    setBackground(backgroundColor);
  }




  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    WordCounter panel = new WordCounter();
    frame.add(panel);
    frame.setContentPane(panel);
    frame.pack();
    frame.setVisible(true);
  }

}
