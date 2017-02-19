/*
  ones.c
  final

  Created by Steven Myers on 12/10/16.
  Copyright © 2016 Steven Myers. All rights reserved.
*/

#include <stdio.h>

int main(int argc, const char * argv[]) {
    int a;
    while (scanf("%d", &a) == 1) {
        /* our objective is find the number of digits that exist
        in a string of ones that is a multiple of 'a' */

        /* Our base case is that a is 0 or 1 (0 a <= 10000). */
        int digits = 1;
        int remainder = 1 % a;
        /* we can't set it to zero because that means our while loop will always
         * terminate immediately so we pre-compute the remainder. if a is zero or one,
         * then the remainder will autoamtically pass the while loop since
         * the correct number of digits for the base case is indeed 1 where b==1
          */

        /* we want to keep counting and adding digits until
         n divides the string of ones.
         in the problem, we have p = a*b.
         we are essentially doing p mod a over and over,
         until we get a remainder equal to zero, meaning that there is a number (b)
         multipled against a that is greater than zero. */

        while (remainder != 0) { /* it doesn't divide into our number evenly */
            /* we can keep the previous remainder and build on its mod each time
               since this is equivalent to doing over each iteration of the summation.
               our remainder is essentially the "p" value from the formula in the prompt
               but it has the modulo of a worked into every iteration of the summation */
            remainder = (1 + 10 * remainder) % a;
            digits++;
        }
        printf("%d\n", digits);
    }
    return 0;
}
