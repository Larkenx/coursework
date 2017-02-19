#include <stdio.h>
#include <stdlib.h>

int maxLength(int num1, int num2);
int numLength(int n);

int main() {
    int n1, n2;
    while(scanf("%d %d", &n1, &n2) == 2) {
        printf("%d %d %d\n", n1, n2, maxLength(n1, n2));
    }
    return 0;
}

int numLength(int n) {
    int result = 1;
    while (n != 1) {
        result++;
        if (n % 2) {
            n *= 3;
            n++;
        } else {
            n = n / 2;
        }
    }
    return result;
}

int maxLength(int n1, int n2) {
    int maximumCycleLength = 0;
    if (n2 < n1) {
        for (int i = n2; i <= n1; i++) {
            int currLength = numLength(i);
            if (currLength > maximumCycleLength) {
                maximumCycleLength = currLength;
            }
        }
    } else {
        for (int i = n1; i <= n2; i++) {
            int currLength = numLength(i);
            if (currLength > maximumCycleLength) {
                maximumCycleLength = currLength;
            }
        }
    }
    return maximumCycleLength;
}
