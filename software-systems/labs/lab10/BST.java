// Lab 10
// March 28, 2016
// Author: Steven Myers
// Username: stelmyer
// *Note: I used the lecture notes for help on a few of the functions

import java.util.Random;

public class BST <Key extends Comparable<Key>, Value> {
  
  private Node root = null;
  
  private int sizeHelper(Node node) { // Size helper
    if (node == null) {
      return 0;
    } else {
      return 1 + sizeHelper(node.left) + sizeHelper(node.right);
    }
  }
  
  // Returns size of the tree (the number of nodes under root node)
  public int size () {
    return sizeHelper(root);
  }
  
  private Value getHelper(Key key, Node node) {
    if (node == null) {
      return null;
      
    } else if (node.key == key) {
      return node.value;
      
    } else if (key.compareTo(node.key) < 0) {
      getHelper(key, node.left);
      
    } else if (key.compareTo(node.key) > 0) { 
      getHelper(key, node.right);
      
    }
    return null;
  }
  
  // Return the value of a specific key
  public Value get(Key key) {
    return getHelper(key, root);
  }
  
  // Insert a key/value pair into the tree
  public void put(Key key, Value val) {
    Node newNode = new Node(key, val);
    
    if (this.root == null) {
      this.root = newNode;
    }
    
    else {
      root.addNode(newNode);
    }
  }
  
  private String print(Node parent) { // Walk helper method
    if (parent == null) {
      return "";
    }
 
    String result = print(parent.left) + (parent.value) + print(parent.right);
    return result;
  }
  
  // Prints the values of the tree
  public void walk() {
    System.out.println(print(root));
  }
  
  // inner Node class
  public class Node {
    
    private Key key;
    private Value value;
    private Node left; // left child
    private Node right; // right child
    // private int N; // number of nodes in this subtree, inclusive
    
    public Node (Key key, Value val) { // Node Constructor - Recursive data type which has a key, value, and a left/right node
      this.key = key;
      this.value = val;
      this.left = null; // Is null or node
      this.right = null; // ""
    }
    
    public void addNode(Node newNode) { // Adds a new node to either the left or right child of this node
      if ((newNode.key).compareTo(key) >= 0) { // Greater than this node
        if (right == null) {
        right = newNode;
        } else {
          (right).addNode(newNode);
        }
        
      } else if ((newNode.key).compareTo(key) < 0) { // Less than this node
        if (left == null) {
          left = newNode;
        } else {
        (left).addNode(newNode);
        }
      }
      // If node already exists, then don't add the new node again.
    }
  
  }
  
  public static void main(String[] args) {
    Random rand = new Random();
    BST<Integer, Character> tree = new BST<Integer, Character>();
    
    for (int i = 0; i < 9; i++) {
      int key = rand.nextInt(26) + 'a';
      char val = (char) key;
      tree.put(key, val);
    }
    
    tree.walk();
    System.out.println(tree.size());
    System.out.println(tree.get(2 + 'a')); // -> Returns either null or the letter c
  }
  
}