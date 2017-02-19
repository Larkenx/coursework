/* Lab 3 Exercises
 * Name: Steven Myers
 * Username: stelmyer
 * Date Last Modified: 1/31/2016
 */


import java.util.Scanner;
import java.util.ArrayList;


class Lab3Exercises {
  public static int fib() {
    String number;
    Scanner scan = new Scanner(System.in);

    System.out.println("Enter the nth number of the Fib Sequence > ");
    number = scan.nextLine();

    int n = Integer.valueOf(number);
    int fibNumber = 0;
    int fib1 = 0;
    int fib2 = 0;
    while (n > 0) {
      if (fibNumber == 0) { // 1 1 2 3 5 8 13 21
        fib2 += 1;
        fibNumber += 1;
      } else {
        fibNumber = fib1 + fib2;
        fib1 = fib2;
        fib2 = fibNumber;
      }

      n -= 1;
    }
    return fibNumber;
  }

  public static String numbers() {
    Scanner scan = new Scanner(System.in);
    int isInt = 10;
    int i = 0;
    ArrayList<Integer> numbers = new ArrayList<Integer>();

    while (isInt == 10) {
      try {
        System.out.println("Enter an integer > ");
        String input = scan.nextLine();
        int numberInput = 0;
        numberInput  = Integer.valueOf(input);
        numbers.add(numberInput);
      }

      catch (Exception err) {
        isInt = 9;
      }

      i++;
    }

    int sum = 0;
    for (int nums : numbers) {
      sum += nums;
    }

    try {
      int min = numbers.get(0);

      for (int nums : numbers) {
        if (min > nums) {
          min = nums;
        }
      }

      int max = numbers.get(0);
      for (int nums : numbers) {
        if (max < nums) {
          max = nums;
        }
      }

      return "Sum: " + sum + " MIN: " + min + " MAX: " + max;
    }
    catch (Exception err) {
      return "No integers entered!";
    }
  }

  public static String grade() {
    Scanner getGrade = new Scanner(System.in);
    System.out.println("Enter a grade percentage > ");
    String input = getGrade.nextLine();
    int percentage = Integer.valueOf(input);

    if (percentage >= 90) {
      return "A";
    } else if (percentage >= 80) {
      return "B";
    } else if (percentage >= 70) {
      return "C";
    } else if (percentage >= 60) {
      return "D";
    } else  {
      return "F";
    }
  }

  public static String intToBinary(int n) {
    String binaryResult = "";

    while (n != 1) {
      int modulo = n % 2;
      n /= 2;
      if (modulo == 1) {
        binaryResult = "1" + binaryResult;
      } else {
        binaryResult = "0" + binaryResult;
      }
    }

    return "1" + binaryResult;
  }





  public static void main(String[] args) {
    System.out.println(fib());
    System.out.println(numbers());
    System.out.println(grade());
    System.out.println(intToBinary(4)); // => 100
    System.out.println(intToBinary(156)); // => 10011100

    // Part E Questions (ii)
    double t = 9.0;
    while (Math.abs(t - 9.0/t) > .001) {
      t = (9.0/t + t) / 2.0;
    }
    System.out.println(t); // => 3.00009155413138

    int sum1 = 0;
    for (int i = 1; i < 1000; i++) {
      for (int j = 0; j < 1000; j++) {
        sum1++;
      }
    }
    System.out.println(sum1);  // => 999000

    int sum2 = 0;
    for (int i = 1; i < 1000; i *= 2) {
      for (int j = 0; j < 1000; j++) {
        sum2++;
      }
    }
    System.out.println(sum2); // => 10000


  }
}


/* Part E Questions
 *
 * (i):
 *
 * 1) (1 + 2.236)/2 => This returns a double: 1.618
 * 2) 1 + 2 + 3 + 4.0 => This returns a double: 10.0
 * 3) 4.1 >= 40 => This returns a boolean: false
 * 4) 1 + 2 + "3"  => This returns a string: "33"
 *
 */
