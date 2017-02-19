/* Find the most frequent k-mers (with mismatches and reverse complements) in a DNA string.
Given: A DNA string Text as well as integers k and d.
Return: All k-mers Pattern maximizing the sum Countd(Text, Pattern) + Countd(Text, Pattern) over all possible k-mers.
*/

import com.sun.tools.javac.util.ArrayUtils;

import java.io.File;
import java.util.*;

class Eleven {

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

    static String ReverseComplement(String text) {
        String rText = new StringBuilder(text).reverse().toString();
        String result = "";
        for (int i = 0; i < rText.length(); i++) {
            String curr = String.valueOf(rText.charAt(i));
            switch (curr) {
                case "A":
                    result += "T";
                    break;
                case "C":
                    result += "G";
                    break;
                case "G":
                    result += "C";
                    break;
                case "T":
                    result += "A";
                    break;
            }
        }
        return result;
    }

    static int HammingDistance(String a, String b) {
        int count = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i))
                count++;
        }
        return count;
    }

    static ArrayList<String> Neighbors(String pattern, int d) {
        ArrayList<String> result = new ArrayList<String>();
        ArrayList<String> Neighborhood = new ArrayList<String>();
        ArrayList<String> SuffixNeighbors = new ArrayList<String>();
        if (d == 0) {
            ArrayList<String> tmp = new ArrayList<String>();
            tmp.add(pattern);
            return tmp;
        } else if (pattern.length() == 1) {
            ArrayList<String> tmp = new ArrayList<String>();
            tmp.add("A");
            tmp.add("C");
            tmp.add("G");
            tmp.add("T");
            return tmp;
        }
        SuffixNeighbors.addAll(Neighbors(Suffix(pattern), d));
        for (String text : SuffixNeighbors) {
            if (HammingDistance(Suffix(pattern), text) < d) {
                for (int i = 0; i < nucleotides.length; i++) {
                    Neighborhood.add(nucleotides[i] + text);
                }
            } else {
                Neighborhood.add(String.valueOf(pattern.charAt(0)) + text);
            }
        }
        return Neighborhood;

    }

    static int maxInCount(int[] arr) {
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > result){
                result = arr[i];
            }
        }
        return result;
    }

    static ArrayList<String> Eleven(String text, int k, int d) {
        ArrayList<String> FrequentPatterns = new ArrayList<String>();
        ArrayList<String> Neighborhoods = new ArrayList<String>();
        for (int i = 0; i < text.length() - k + 1; i++) {
            Neighborhoods.addAll(Neighbors(text.substring(i, i + k), d));
            Neighborhoods.addAll(Neighbors(ReverseComplement(text).substring(i, i + k), d));
        }

        String[] NeighborhoodArray = Neighborhoods.toArray(new String[Neighborhoods.size()]);
        int[] Index = new int[NeighborhoodArray.length];
        int[] Count = new int[NeighborhoodArray.length];
        for (int i = 0; i < NeighborhoodArray.length; i++) {
            String Pattern = NeighborhoodArray[i];
            Index[i] = PatternToNumber(Pattern);
            Count[i] = 1;
        }

        Arrays.sort(Index); // Sort our index

        for (int i = 0; i < NeighborhoodArray.length - 1; i++) {
            if (Index[i] == Index[i + 1]) {
                Count[i + 1] = Count[i] + 1;
            }
        }
        int maxCount = maxInCount(Count);
        for (int i = 0; i < NeighborhoodArray.length - 1; i++) {
            if (Count[i] == maxCount) {
                FrequentPatterns.add(NumberToPattern(Index[i], k));
            }
        }
        return FrequentPatterns;

    }

    public static void main(String[] args) {
        ArrayList<String> test = new ArrayList<String>();
        try {
            File file = new File("/Users/larken/class/bioinformatics/rosalind-problems/FreqWordsMismatches/data.txt");
            Scanner in = new Scanner(file);
            String stringInput = in.nextLine();
            int a = in.nextInt();
            int b = in.nextInt();
            for (String e : Eleven(stringInput, a, b))
                System.out.println(e);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        for (String e : test)
            System.out.println(e);
//
    }
}
