public class AlignmentFunction {

    // Returns the result with the highest score
    static Result maxCount(Result a, Result b, Result c) {
        if (a.score >= b.score && a.score >= c.score ) {
            return a;
        } else if (b.score < c.score) {
            return c;
        } else {
            return b;
        }
    }

    static void align_helper(final String s1, final String s2, Result[][] table) {
        // Base cases
        table[0][0] = new Result(0, null, null);
        for (int i = 1; i < table[0].length; i++) {
            // Fill in the top row
            Result prev = table[0][i - 1];
            table[0][i] = new Result(Constants.SPACE_PENALTY + prev.score, Mutation.D, prev);
        }

        for (int j = 1; j < table.length; j++) {
            // Fill in left column
            Result prev = table[j - 1][0];
            table[j][0] =  new Result(Constants.SPACE_PENALTY + prev.score, Mutation.I, prev);
        }

        // Recursive Cases
        for (int j = 1; j < table.length; j++) {
            for (int i = 1; i < table[0].length; i++) {
                // Find the best score of the previous nodes
                Result above = table[j - 1][i];
                Result diag = table[j-1][i-1];
                Result left = table[j][i - 1];
                // Find the max score
                Result best = maxCount(left, diag, above);
                // Find the mutation
                Mutation dir;
                int currentScore = 0;
                if (best == left) { // Delete
                    dir = Mutation.D;
                    currentScore = Constants.SPACE_PENALTY;
                } else if (best == above) { // Insert
                    dir = Mutation.I;
                    currentScore = Constants.SPACE_PENALTY;
                } else { // Match OR MISMATCH
                    // Determine if it was a match or not
                    Boolean match = s2.charAt(j - 1) == s1.charAt(i - 1);
                    dir = Mutation.M;
                    currentScore = match ? Constants.MATCH : Constants.MISMATCH;
                }
                // Set this result in the table
                table[j][i] = new Result(best.score + currentScore, dir, best);
            }
        }
    }

    static Result[][] align(final String s1, final String s2, StringBuilder s1_aligned, StringBuilder s2_aligned) {
        // Construct result table of size [s1.length()][s2.length()]
        Result[][] table = new Result[s2.length()+1][s1.length()+1];
        align_helper(s1, s2, table);
        // Now our result table is full. All we need to do is back track from the sink
        Result sink = table[table.length - 1][table[0].length - 1];
        // System.out.format("Ending sink score %d!!!\n", sink.score);
        Result currentRes = sink;
        System.out.println(currentRes.score);
        int indexOfS1 = table[0].length - 2;
        int indexOfS2 = table.length - 2;
        while (currentRes != null) {
            if (currentRes.dir == Mutation.I) { // Insert Space in 2nd string
                // s1_aligned.append(s1.charAt(indexOfS1));
                // s2_aligned.append(Constants.SPACE_CHAR);
                 --indexOfS1;
            } else if (currentRes.dir == Mutation.D) { // Delete from 1st string
                // s1_aligned.append(Constants.SPACE_CHAR);
                // s2_aligned.append(s2.charAt(indexOfS2)); // Breaks here
                --indexOfS2;
            }  else if (currentRes.dir == Mutation.M) { // Match
                if (s1.charAt(indexOfS1) == s2.charAt(indexOfS2)) {
                    s1_aligned.append(s1.charAt(indexOfS1));
                    s2_aligned.append(s2.charAt(indexOfS2));
                }
                --indexOfS1;
                --indexOfS2;
            }
            currentRes = currentRes.prev;
            // System.out.println("\nS1: " + s1_aligned.toString());
            // System.out.println("S2: " + s2_aligned.toString());
        }
        return table;
    }

    public static void main(String[] args) {
        final String s2 = "AACCTTGG";
        final String s1 = "ACACTGTGA";
        StringBuilder s1_aligned = new StringBuilder();
        StringBuilder s2_aligned = new StringBuilder();
        align(s1, s2, s1_aligned, s2_aligned);
        System.out.println("\nS1: " + s1_aligned.reverse().toString());
        System.out.println("S2: " + s2_aligned.reverse().toString());
    }
}
