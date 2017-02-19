#include <stdio.h>
#include <math.h>

typedef struct {
    int factors[32];
    int n;
} FACTORIZATION;

FACTORIZATION factor(int n) {
    FACTORIZATION f;
    int i;
    f.n = 0; // index of last factor
    for (i = 2; i <= ceil(sqrt((float) n)); i++) {
        while (n % i == 0) {
            f.factors[f.n++] = i;
            n /= i;
        }

        if (n == 1) break;
    }

    if (n > 1) {
        f.factors[f.n++] = n;
    }
    return f;
}

int divide(long int n, long int m) {
    FACTORIZATION f = factor(m);
    for (long int i = 2; i < n; i++) {
        long int tmp = i;
        while (tmp > 1) {
            for (int j = 0; j < f.n; j++) {
                if (tmp % f.factors[j] == 0) break;
                if (j < f.n) {
                    tmp /= f.factors[j];
                    f.factors[j] = f.factors[--f.n];
                }
                if (f.n == 0) return (1);
            }
        }
    }
    return 0;
}

void print_factorization(FACTORIZATION f) {
    for (int i = 0; i < f.n; i++) printf("%d ", f.factors[i]);
    printf("\n");
}

int main() {
    long int n, m;
    while (!feof(stdin)) {
        scanf("%ld %ld", &n, &m);
        if (divide(n, m)) printf("%ld divides %ld!\n", m, n);
        else printf("%ld divides %ld!\n", m, n);
    }
}
