import java.util.*;

class FrequentWords {
    static int count(String text, String pattern ) {
        int plength = pattern.length();
        int occurences = 0;
        for (int i = 0; i < text.length() - plength + 1; i++) {
            if (text.substring(i, i + plength).equals(pattern)) {
                if (pattern.equals("AAC")) System.out.println("Found AAC at " + i);
                occurences++;
            }
        }
        System.out.println("Found " + "\'" + pattern + "\' " + occurences + " times.");
        return occurences;
    }

    @SuppressWarnings("rawtypes")
    static String getFreqWords(String text, int k) {
        // Store it in a hashmap of <pattern, count>
        HashMap<String, Integer> hmap = new HashMap<String, Integer>();
        int possiblePatterns = text.length() - k; // |Text| - k
        for (int i = 0; i < possiblePatterns; i++) {
            String currString = text.substring(i, i + k);
            hmap.put(currString, count(text, currString));
        }
        // Now iterate through HashMap
        Iterator it = hmap.entrySet().iterator();
        ArrayList<Map.Entry> mostFrequent = new ArrayList<Map.Entry>();
        int maxCount = 0;
        while (it.hasNext()) { // Go through all patterns
            Map.Entry pair = (Map.Entry) it.next();
            int value = (int) pair.getValue();
            if (value > maxCount) { // If it exceeds current maxCount
                mostFrequent.clear();
                mostFrequent.add(pair);
                maxCount = value;
            } else if (value == maxCount) {
                mostFrequent.add(pair);
            }
        }

        // Now print what's left
        String result = "";
        for (int i = 0; i < mostFrequent.size() - 1; i++) {
            result+= (String) mostFrequent.get(i).getKey() + " ";
        }
        result += mostFrequent.get(mostFrequent.size() - 1).getKey();
        return result;
    }

    public static void main(String[] args) {
        String input = "GAGTTAACGAACGCTTAAC";
        String output = "";
        System.out.println(getFreqWords(input, 3));
    }

}
