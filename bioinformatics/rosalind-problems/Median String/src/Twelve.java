/* Find the most frequent k-mers (with mismatches and reverse complements) in a DNA string.
Given: A DNA string Text as well as integers k and d.
Return: All k-mers Pattern maximizing the sum Countd(Text, Pattern) + Countd(Text, Pattern) over all possible k-mers.
*/

import com.sun.tools.javac.code.Attribute;
import com.sun.tools.javac.util.ArrayUtils;

import javax.print.attribute.standard.MediaName;
import java.io.File;
import java.util.*;

class Twelve {

    static String[] nucleotides = new String[] {"A", "C", "G", "T"};

    static String Suffix(String pattern) { return pattern.substring(1); }

    static String Prefix(String pattern) { return pattern.substring(0, pattern.length() - 1); }

    static int SymbolToNumber(String s) {
        switch (s) {
            case "A":
                return 0;
            case "C":
                return 1;
            case "G":
                return 2;
            case "T":
                return 3;
        }
        return -1; // will never reach here
    }

    static String NumberToSymbol(int n) {
        switch (n) {
            case 0:
                return "A";
            case 1:
                return "C";
            case 2:
                return "G";
            case 3:
                return "T";
        }
        return "ERR"; // will never reach here
    }

    static int PatternToNumber(String pattern) {
        if (pattern.length() == 0) {
            return 0;
        } else {
            String symbol = String.valueOf(pattern.charAt(pattern.length() - 1));
            String prefix = Prefix(pattern);
            return 4 * PatternToNumber(prefix) + SymbolToNumber(symbol);
        }
    }

    static String NumberToPattern(int index, int k) {
        if (k == 1) return NumberToSymbol(index);
        int prefixIndex = index / 4;
        int remainder = index % 4;
        String symbol = NumberToSymbol(remainder);
        String PrefixPattern = NumberToPattern(prefixIndex, k - 1);
        return PrefixPattern + symbol;
    }


    static int HammingDistance(String a, String b) {
        int count = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i))
                count++;
        }
        return count;
    }


    public static int DistanceBetweenPatternAndString(String pattern, ArrayList<String> DNA) {
        int k = pattern.length();
        int distance = 0;
        for (String text : DNA) {
            int hammingdistance = Integer.MAX_VALUE;
            for (int i = 0; i < text.length() - k + 1; i++) {
                String patternPrime = text.substring(i, i+k);
                int actualD = HammingDistance(pattern, patternPrime);
                if (hammingdistance > actualD) {
                    hammingdistance = actualD;
                }
            }
            distance += hammingdistance;

        }
        return distance;
    }

    public static String MedianString(ArrayList<String> DNA, int k) {
        int distance = Integer.MAX_VALUE;
        String median = "";
        for (int i = 0; i < Math.pow(4,k) - 1; i++) {
            String pattern = NumberToPattern(i, k);
            int tmp = DistanceBetweenPatternAndString(pattern, DNA);
            if (distance > tmp) {
                distance = tmp;
                median = pattern;
            }
        }
        return median;
    }


    public static void main(String[] args) {
//        ArrayList<String> testDNA = new ArrayList<String>();
//        testDNA.add("AAATTGACGCAT");
//        testDNA.add("GACGACCACGTT");
//        testDNA.add("CGTCAGCGCCTG");
//        testDNA.add("GCTGAGCACCGG");
//        testDNA.add("AGTACGGGACAG");
//        System.out.println(MedianString(testDNA, 3));

        try {
            File file = new File("/Users/larken/class/bioinformatics/rosalind-problems/Median String/data.txt");
            Scanner in = new Scanner(file);
            int k = in.nextInt();
//            int k = 3;
            ArrayList<String> strings = new ArrayList<>();
//            strings.add("AAG");
//            strings.add("AAT");

            while (in.hasNext()) {
                strings.add(in.nextLine());
            }
            System.out.println(MedianString(strings, k));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
