import java.util.*;
import java.io.*;

class Skew {
    static void skew(String dna) {
        ArrayList<Integer> mins = new ArrayList<Integer>();
        int currentDifference = 0;
        int globalMin = 0;
        for (int i = 0; i < dna.length(); i++) {
            if (dna.charAt(i) == 'C') {
                --currentDifference;
            } else if (dna.charAt(i) == 'G') {
                ++currentDifference;
            }

            if (currentDifference < globalMin) {
                mins.clear();
                globalMin = currentDifference;
                mins.add(i + 1);
            } else if (currentDifference == globalMin) {
                mins.add(i + 1);
            }
        }
        String result = "";
        for (int min : mins) {
            result+= min + " ";
        }
        System.out.println(result);
    }

    public static void main(String[] args) {
        try {
            File file = new File("/Users/larken/class/bioinformatics/rosalind-problems/nine/out/rosalind_ba1f.txt");
            Scanner in = new Scanner(file);
            String input = in.nextLine();
            String output = "89969 89970 89971 90345 90346";
            skew(input);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
