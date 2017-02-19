#include <stdio.h>
#include <math.h>

int numOfDigits(int n) {
    int count = 0;
    while (n != 0) {
            n /= 10;
            ++count;
        }
    return count;
}

int main() {
    int n;
    while (scanf("%d", &n) == 1) {
        int match = -1;
        int lengthN = numOfDigits(n);
        for (int i = 7; i < 5766432; i++) {
            long long candidate = pow((double) 2, (double) i);
            int candidateLength = numOfDigits((int) candidate);
            int moreThanHalf = lengthN;
            if (candidateLength % 2 == 0) /* Even */
                moreThanHalf++;
            if (candidateLength / 2 >= moreThanHalf) {
                if ((candidate / (int) pow(10, candidateLength - lengthN)) == n) {
                    match = i;
                    break;
                }
            }
        }

        if (match == -1) {
            printf("no power of 2\n");
        } else {
            printf("%d\n", match);
        }

    }
}
