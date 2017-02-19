/* Worked with Xin and Sophie */

#import <stdio.h>
unsigned long factorial(unsigned long f)
{
    if ( f == 0 )
        return 1;

    return(f * factorial(f - 1));
}

/* (A*B)%m = (A%m * B%m) %m */
int divides2(int m, int n) {
    int remainder = 1;
    while (n != 0) {
        remainder *= n % m;
        n--;
    }
    return (remainder % m == 0);
}

int divides(int m, int n) {

    if (factorial(n) % m == 0) {
        return 1;
    } else {
        return 0;
    }
}

int main() {
    int m, n;
    while (1) {
        scanf("%d %d", &n, &m);
        if (feof(stdin)) break;
        if (divides2(m, n)) {
            printf("\n%d divides %d!", m, n);
        } else {
            printf("\n%d does not divide %d!", m, n);
        }
    }
}
