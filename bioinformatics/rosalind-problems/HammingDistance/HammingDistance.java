import java.util.*;
import java.io.*;

class HammingDistance {

    static void HammingDistance(String a, String b) {
        int count = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i))
                count++;
        }
        System.out.println(count);
    }

    public static void main(String[] args) {
        try {
            File file = new File("/Users/larken/class/bioinformatics/rosalind-problems/HammingDistance/data.txt");
            Scanner in = new Scanner(file);
            String a = in.nextLine();
            String b = in.nextLine();
            HammingDistance(a,b);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
