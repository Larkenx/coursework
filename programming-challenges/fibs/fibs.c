#include <stdio.h>
int fibs(long low, long high) {
    long last=0, secLast=1, current=1, count = 0;
    while (current <= high) {
        if (low <= current)
            count++;
        secLast = last;
        last = current;
        current = last + secLast;
    }
    return count;
}
int main() {
    long low, high;
    while(scanf("%ld %ld", &low, &high)) {
        if (low == 0 && high == 0) break;
        printf("%d\n", fibs(low, high));
    }

    return 0;
}
