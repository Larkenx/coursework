/////////////////////////////////////////////////////////////////////////////////////
//
//  C212 Spring 16
//
//  Homework 6 Template
//  Due: Friday 3/11 11:59 pm
//  @Author  Steven Myers
//
///////////////////////////////////////////////////////////////////////////////////


import java.lang.Math;

public class Point {
  
  private double x;
  private double y;
  
  // Constructor method 
  public Point(double x, double y) {
    // this.x is the x as an instance field 
    // x is just the x local as a paramter to this method 
    this.x = x; 
    this.y = y;
  }
  
  // return this points x value 
  public double x() {
    return this.x;   
  }
  
  // return this points y value 
  public double y() {
    return this.y;   
  }
  
  // return distance from this point to other point  
  public double distanceTo(Point other) {
    // TO-DO - find distance from one point to other point 
    // with distance formula
    double result = Math.sqrt(Math.pow(this.x() - other.x(), 2) + Math.pow(this.y() - other.y(), 2));
    return result;
  }
  
  // returns the point as a String 
  public String toString() {
    // TO-DO
    String result = "(" + this.x() + ", " + this.y() + ")";
    return result;
  }
  
  // test client 
  public static void main(String[] args) {
    // Instantiating 3 Objects of type Point 
    // Type is a Point - Variable Name - Creating new Point with new keywork 
    Point center = new Point(0,0);
    Point p1 = new Point(1, 1);
    Point p2 = new Point(3, 7);
    
    // creating a variable d1 that is didstance from Point Center to Point p1
    // call objects or class methods with . operator
    // distanceTO takes a parameter of type Point, so we passed in p1
    Double d1 = center.distanceTo(p1);
    
    System.out.println(d1);
    // Printing the points as strings
    System.out.println(p1.toString());
    System.out.println(p2.toString());
  }
}