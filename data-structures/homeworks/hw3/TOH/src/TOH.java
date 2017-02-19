import java.util.Stack;

public class TOH {

    enum Pos {
        LEFT, MIDDLE, RIGHT
    }

    static class Pole extends Stack<Integer> {
        Pos pos;

        Pole(Pos pos) {
            super();
            this.pos = pos;
        }

        String getPos() {
            if (pos == Pos.LEFT) {
                return "LEFT";
            } else if (pos == Pos.MIDDLE) {
                return "MIDDLE";
            } else {
                return "RIGHT";
            }
        }
    }

    static void move(Pole start, Pole goal) {
        System.out.format("Moving disk %d from %s pole to %s pole.\n", goal.push((int)start.pop()), start.getPos() ,goal.getPos());
    }

    static void TOH_helper(int n, Pole start, Pole goal, Pole temp) {
        if (n == 0) {
            return;
        } else {
            TOH_helper(n-1, start, temp, goal); // Recursive call: n-1 rings
            move(start, goal); // Move bottom disk to goal
            TOH_helper(n-1, temp, goal, start); // Recursive call: n-1 rings
        }
    }

    static void TOH(int n) {
        Pole left = new Pole(Pos.LEFT);
        Pole right = new Pole(Pos.RIGHT);
        Pole middle = new Pole(Pos.MIDDLE);

        for (int i = 1; i < n + 1; i++)
            left.push(i);
        TOH_helper(n, left, right, middle);
    }

    public static void main(String[] args) {
        TOH.TOH(3);
    }
}
