public class ArrayStack implements Stack {
    private int[] data;
    private int N;

    public ArrayStack() {
        data = new int[2];
        N = 0;
    }

    public void push(int d) {
        data[N] = d;
        N++;
        if (N == data.length) {
            resize(data.length * 2);
        }
    }

    public int peek() {
        return this.data[N - 1];
    }

    public int pop() {
        if ((N < (data.length / 4.0))) { // If our stack is less than a quarter of the size, resize it
            resize(data.length / 2);
        }

        if (! this.empty()) {
            int tmp = data[N - 1];
            data[data.length - 1] = 0; // Set it to 0? Null requires objects not ints
            --N;
            return tmp;
        } else {
            return 0; // Raise error?
        }
    }

    public boolean empty() {
        return N == 0;
    }

    // Increases or decreases the size of the array by 2
    private void resize(int n) {
        int[] temp = new int[n];
        for (int i = 0; i < N; i++) {
            temp[i] = data[i];
        }
        data = temp;
    }

    public String toString() {
        String result = "Stack: [";
        result += data[0];
        for (int i = 1; i < N; i++) {
            result += ", " + data[i];
        }
        return result + "]";
    }

    public static void main(String[] args) {
        ArrayStack testStack = new ArrayStack();
        // Testing
        System.out.println(testStack.toString());
        System.out.println(testStack.empty());
        testStack.push(3);
        testStack.push(1);
        testStack.push(4);
        testStack.push(2);
        System.out.println(testStack.toString());
        System.out.println(testStack.pop());
        System.out.println(testStack.pop());
        System.out.println(testStack.pop());
        System.out.println(testStack.peek());
        System.out.println(testStack.toString());
    }
}
