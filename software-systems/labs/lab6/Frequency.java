public class Frequency {
  
  private String word;
  private int count;
  
  public Frequency(String w) {
    this.word = w;
    this.count = 1; 
  }
  
  public int getCount() {
    return this.count;
  }
  
  public String getWord() {
   return this.word; 
  }
  
  public void increaseCount() {
   ++this.count;
  }
  
  public boolean isDistinct() {
    if (this.getCount() == 1) {
      return true;
    } else {
      return false;
    }
  }
  
  public static void main(String[] args) {}
}