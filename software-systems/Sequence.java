import java.util.ArrayList;

public class Sequence {
  
  private int[] values;
  
  public Sequence(int[] array) {
    values = new int[array.length];
    values = array;  
  }
  
  public int size() {
    return this.values.length; 
  }
  
  public int get(int i) { // Grabs value at index i
    return this.values[i];
  }
  
  public boolean isPermutationof(Sequence other) {
    ArrayList<Integer> thisCopy = new ArrayList<Integer>();
    ArrayList<Integer> otherCopy = new ArrayList<Integer>();
    for (int num : this.values) { // Make a copy of this.values
      thisCopy.add(num);
    }
    
    for (int otherNum: other.values) {
      otherCopy.add(otherNum);
    }
    
    if ((this.size() == 0) && (other.size() == 0)) { // When the array is empty return true
      return true;
    } else { // Otherwise proceed for recursion
      
      if (this.size() == other.size()) {
        
        for (int i = 0; i < this.size(); i++) {
          for (int j = 0; j < other.size(); j++) {
            
            if (this.values[i] == other.values[j]) {
              thisCopy.remove( (Object) this.values[i]);
              otherCopy.remove( (Object) other.values[j]);
              // we have removed the common number between the arrays, now let's construct new sequences
              int[] newThis = new int[this.size() - 1];
              int[] newOther = new int[other.size() - 1];
              for (int n1 = 0; n1 < this.size() - 1; n1++) {
                newThis[n1] = thisCopy.get(n1);
              }
              for (int n2 = 0; n2 < this.size() - 1; n2++) {
                newOther[n2] = otherCopy.get(n2);
              }
              Sequence recurseThis = new Sequence(newThis);
              Sequence recurseOther = new Sequence(newOther);
              return recurseThis.isPermutationof(recurseOther); 
            }
          }
        }
      }
    }
    return false;
  }
  
  public static void main(String[] args) {
    int[] testArray = {1, 2, 3, 4};
    Sequence test = new Sequence(testArray);
    int[] testArray2 = {2, 3, 4, 1};
    Sequence test2 = new Sequence(testArray2);
    System.out.println(test.isPermutationof(test2)); // => true
    int[] testArray3 = {6, 9, 9, 6};
    Sequence test3 = new Sequence(testArray3);
    System.out.println(test.isPermutationof(test3)); // => false
  }
}
