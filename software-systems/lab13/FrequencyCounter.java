import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;


public class FrequencyCounter {

  private HashMap<String, Integer> map = new HashMap<String, Integer>();

  private String fileName;
  private int totalWords;
  private int lines;

  public FrequencyCounter(File file) {
    try {
      this.fileName = file.getName();
      Scanner scan = new Scanner(file); // Open file for reading

      while (scan.hasNextLine()) { // Read every line in the file
        this.lines++;
        String line = scan.nextLine(); // The next line stored as a string
        String[] words = line.split(" "); // Parse the line for words, getting rid of periods

        for (String word : words) { // For all the words in the string...
          this.totalWords++;
          boolean wordExists = map.containsKey(word);
          if (wordExists) {
            int count = map.get(word);
            map.remove(word);
            map.put(word, count + 1);
          }

          if (wordExists == false) {
            map.put(word, 1);
          }
        }
      }
    }

    catch (FileNotFoundException e) {
      e.printStackTrace();
    }

  }

  public String getFileName() {
    return this.fileName;
  }

  public String getTotalWords() {
    return this.fileName + " has a total of " + this.totalWords + " words.";
  }

  public String getTotalLines() {
    return this.fileName + " has a total of " + this.lines + " lines.";
  }

  public String toString() {
    return map.toString();
  }

  public static void main(String[] args) {}
}
