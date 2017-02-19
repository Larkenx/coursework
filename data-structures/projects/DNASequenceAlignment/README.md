# Sequence Alignment Project

For my ```AlignmentFunction``` class, I altered the ```Result``` class to include a pointer
to the previous ```Result``` with the optimal score.

```java
public class Result {
    Result(int s, Mutation d, Result p) { score = s; dir = d; prev = p; }
    int score;
    Result prev;
    Mutation dir;
}
```
## Forward Calculation
Before backtracking, I compute the entire ```Result[][] table.``` My code does this iteratively. I start at the first row and first column since these are the base cases. Straight down or straight right will always be contiguous -1 scores. We also set the first node, ```Result[0][0]``` to ```new Result(0, null, null);```
#### Filling in the first row
```java
for (int i = 1; i < table[0].length; i++) {
    // Fill in the top row
    Result prev = table[0][i - 1];
    table[0][i] = new Result(Constants.SPACE_PENALTY + prev.score, Mutation.D, prev);
}
```

#### Filling in the first column
```java
for (int j = 1; j < table.length; j++) {
    // Fill in left column
    Result prev = table[j - 1][0];
    table[j][0] =  new Result(Constants.SPACE_PENALTY + prev.score, Mutation.I, prev);
}
```

Once I finish doing the base cases, I work from (1,1) to the last node. Each result is computed based on the "best" score of its left, diagonal, and vertical neighbors. The score of a node is its current score (based on the mutation chosen) plus the previous node's score.

## Backtracking
Once the table of ```Results[][]``` was filled out, I used the previous pointer to backtrack from the sink of the table to the source, and rebuilt the aligned strings. Here is the code for how I did that:

```java
Result sink = table[table.length - 1][table[0].length - 1];
// System.out.format("Ending sink score %d!!!\n", sink.score);
Result currentRes = sink;
System.out.println(currentRes.score);
int indexOfS1 = table[0].length - 2;
int indexOfS2 = table.length - 2;
while (currentRes != null) {
    if (currentRes.dir == Mutation.I) { // Insert Space in 2nd string
        s1_aligned.append(s1.charAt(indexOfS1));
        s2_aligned.append(Constants.SPACE_CHAR);
        --indexOfS1;
    } else if (currentRes.dir == Mutation.D) { // Delete from 1st string
        s1_aligned.append(Constants.SPACE_CHAR);
        s2_aligned.append(s2.charAt(indexOfS2)); // Breaks here
        --indexOfS2;
    }  else if (currentRes.dir == Mutation.M) { // Match
        s1_aligned.append(s1.charAt(indexOfS1));
        s2_aligned.append(s2.charAt(indexOfS2));
        --indexOfS1;
        --indexOfS2;
    }
    currentRes = currentRes.prev;
    // System.out.println("\nS1: " + s1_aligned.toString());
    // System.out.println("S2: " + s2_aligned.toString());
}
```
