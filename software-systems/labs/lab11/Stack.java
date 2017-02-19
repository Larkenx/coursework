import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class Stack<Item> implements Iterable<Item> {

  private Item[] stack; // Array of items
  private int N; // Index of array we're pushed up to

  public Stack() {
    stack = (Item[]) new Object[2];
  }

  public boolean isEmpty() {
    return stack[0] == null;
  }

  public int size() {
    return N;
  }

  /**
  * Add the item to the stack.
  */
  public void push(Item item) {
    stack[N] = item;
    N++;
    if (this.size() == stack.length) {
      resize(stack.length * 2);
    }
  }

  public Item pop() {
    if (this.isEmpty()) { throw new RuntimeException("Stack underflow"); }
    if ((this.size() < stack.length / 4.0)) {
      resize(stack.length / 2);
    }

    Item item = stack[N];       // save item to return
    stack[N] = null;        // delete first node
    N--;
    return item;                   // return the saved item
  }

  private void resize(int n) { //increases or decreases the size of the array by 2
    Item[] temp = (Item[]) new Object[n];
    for (int i = 0; i < N; i++) {
      temp[i] = stack[i];
    }
    stack = temp;
  }

  public String toString() { // prints the contents of the stack array
    String result;

    if (this.size() == 0) {
      result = "Stack: [";
    } else {
      result = "Stack: [" + stack[0];
      for (int i = 1; i < N; i++) {
        result = result + ", " + stack[i];
      }
    }
    return result + "]";
}

  public Iterator<Item> iterator() {
    return new StackIterator();
  }

  private class StackIterator implements Iterator<Item> {

    public StackIterator() {}

      public boolean hasNext() {
        return stack.length < N;
      }

      public Item next() {
        if (this.hasNext()) {
          return stack[N];
        }
        throw new NoSuchElementException();
      }

      public void remove() {
        throw new UnsupportedOperationException();
      }
    }

    public static void main(String[] args) {
      Stack stack = new Stack();
      Random rand = new Random();

      for (int i = 0; i < 32; i++) {
          stack.push( (Integer) rand.nextInt(50) );
          // System.out.println(stack.stack.length); // used to check array was resizing
      }

      System.out.println(stack.toString());

    // get the iterator from the stack
      Iterator stackIterator = stack.iterator();

    // print out all the items in the stack with the iterator
      while (stackIterator.hasNext()) {
        System.out.println(stackIterator.next());
      }

    // now that Stack implements Iterable we can use the enhance for loop also
      for (Object i : stack) {
        System.out.println(i);
      }
    }
  }
