////////////////////////////////////////////////////////////////////////////////////
//
//  C212 Spring 16
//  Homework 2
//  Author  Steven Myers | stelmyer
//  Last Edited:  2/5/2016
//
//               
//////////////////////////////////////////////////////////////////////////////////2

import java.util.Scanner;

public class DieSimulation {
  private Die die;
  private Counter one;
  private Counter two;
  private Counter three;
  private Counter four;
  private Counter five;
  private Counter six;
  
  
  public DieSimulation() {
    die = new Die();
    one = new Counter("one");
    two = new Counter("two");
    three = new Counter("three");
    four = new Counter("four");
    five = new Counter("five");
    six = new Counter("six");
  }
  
  public String run() {
    Scanner getRolls = new Scanner(System.in);
    System.out.println("Enter number of rolls: ");
    int numberOfRolls = getRolls.nextInt();
    
    for (int i = 0; i < numberOfRolls; i++) { // For the number of rolls,
      int rolledNumber = die.roll(); // A number from 1-6   
      String counter;
      
      switch (rolledNumber) { // Switch the rand number to increment to the appropriate counter
        case 1: one.increment();
        break;
        case 2: two.increment();
        break;
        case 3: three.increment();
        break;
        case 4: four.increment();
        break;
        case 5: five.increment();
        break;
        case 6: six.increment();
        break;
      }
    }
    System.out.println("Results\n-----------");
    System.out.println(one.toString() + two.toString() + three.toString() + four.toString() + five.toString() + six.toString());
    return one.toString() + two.toString() + three.toString() + four.toString() + five.toString() + six.toString();
      
    }
  
  public static void main(String[] args) {
    DieSimulation simulationOne = new DieSimulation();
    simulationOne.run();
  }
  }