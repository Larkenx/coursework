/* Lab 4
 * 
 * Username: stelmyer
 * Name: Steven Myers
 * Date: 2/5/2016
 */
import java.util.Scanner;

public class PointAccumulator {
  Point[] points; // An array of points
  
  public PointAccumulator() { 
    
// Constructor
  }
  
  public void run () { // Populates points' array from user input
    Scanner getStorage = new Scanner(System.in);
    System.out.println("Enter the number of points you'd like to store: ");
    
    if (getStorage.hasNextInt()) {
      final int test = getStorage.nextInt();
      points = new Point[test];
      System.out.println(test + " points ready for storage!");
      
      
      for (int i = 0; i != test; i++) {
        Scanner getPoints = new Scanner(System.in);
        System.out.println("Enter the x & y value separated by one space: ");
        String pointResult = getPoints.nextLine();
        String[] coordinates = pointResult.split(" ");
        points[i] = new Point(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]));      
      }
      
    } else {
      System.out.println("Invalid Input!");
      
    }
    System.out.println("Points stored!");
  }
  
  // now we want to find the point furthest from the origin
  private double distanceFromOrigin(Point posn) { // Takes a Point object and finds its distance from the origin
    return Math.sqrt(Math.pow(posn.x(), 2) + Math.pow(posn.y(), 2));
  }
  
  public void farthestFromOrigin() {
    Point result = new Point(0, 0); // Beginning with a point at (0, 0)
    
    for (Point posn : points) {
      if (distanceFromOrigin(posn) > distanceFromOrigin(result))
        result = posn;
    }
    System.out.println("The farthest point from the origin is " + result.toString() + ".");
  }
  
  private double distanceBetweenPoints(Point p1, Point p2) { // Finds the distance between two given points
    return Math.sqrt(Math.pow(p1.x() - p2.x(), 2) + Math.pow(p1.y() - p2.y(), 2));
  }
  
  public void farthestBetweenPoints () {
    Point[] twoPoints = new Point[2]; // Reserve memory for two points
    double maxDistance = 0;
    
    for (Point p1: points) {
      for (Point p2: points) {
        double currentDistance = distanceBetweenPoints(p1, p2);
        
        if (currentDistance > maxDistance) {
          twoPoints[0] = p1;
          twoPoints[1] = p2;
          currentDistance = maxDistance;
        }
      }
    }
    
    System.out.println("The points farthest from each other are " + twoPoints[0].toString() + " and " + twoPoints[1].toString() + ".");
  }
  
  public static void main(String[] args) {
    PointAccumulator test = new PointAccumulator();
    test.run(); 
    test.farthestFromOrigin();
    test.farthestBetweenPoints();
  } 
}