public class Result {
    Result(int s, Mutation d, Result p) { score = s; dir = d; prev = p; }
    int score;
    Result prev;
    Mutation dir;
}
