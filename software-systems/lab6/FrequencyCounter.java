import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;


public class FrequencyCounter {

  private ArrayList<Frequency> frequencies;

  private String fileName;
  private int totalWords;
  private int distinctWords;
  private int lines;


  public FrequencyCounter(File file) {
    try {
      this.fileName = file;
      this.frequencies = new ArrayList<Frequency>();

      Scanner scan = new Scanner(file); // Open file for reading
      while (scan.hasNextLine()) { // Read every line in the file
        this.lines++;
        String line = scan.nextLine(); // The next line stored as a string
        String[] words = line.split(" "); // Parse the line for words, getting rid of periods

        for (String word : words) { // For all the words in the string...
          this.totalWords++;
          boolean wordExists = false;
          for (Frequency wordFreq : frequencies) {
            if (wordFreq.getWord().equals(word)) {
              wordFreq.increaseCount();
              wordExists = true;
            }
          }

          if (wordExists == false) {
            this.frequencies.add(new Frequency(word));
          }

          wordExists = false;
        }
      }



      // Now we can determine the number of distinct words
      this.distinctWords = 0;
      String result = "";
      for (Frequency wordFreq : frequencies) {
       // System.out.println(wordFreq.getWord());
       // System.out.println(wordFreq.getCount());
        if (wordFreq.isDistinct()) {
         this.distinctWords++;
         result = result + wordFreq.getWord() + ": " + wordFreq.getCount() + "\n";
        }
      }


      System.out.println(this.fileName + " has a total of " + this.totalWords + " words.");
      System.out.println(this.fileName + " has a total of " + this.distinctWords + " distinct words.");
      System.out.println(this.fileName + " has a total of " + this.lines + " lines.");
      System.out.println("The distinct words in the file are as follows: ");

      System.out.println(result);
    }

    catch (FileNotFoundException e) {
      e.printStackTrace();
    }

  }

  public static void main(String[] args) {
  }
}
