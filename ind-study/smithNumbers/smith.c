/* Worked with Xin and Sophie */

#include <stdio.h>
#include <stdlib.h>

#define MAX_SIZE 32000

long int primeArr[MAX_SIZE];
long int primeArrSize = 0;

void findPrimeList();
int isSmithNumber(long int);
long int getDigitSum(long int);
long int getDigitSumFactorization(long int, long int *);

int main()
{
    long int numTest, num, nextNum;

    findPrimeList();

    scanf("%ld", &numTest);
    while(numTest > 0)
    {
        scanf("%ld", &num);

        nextNum = num + 1;
        while(1)
        {
            if(isSmithNumber(nextNum))
            {
                printf("%ld\n", nextNum);
                break;
            }
            nextNum++;
        }

        numTest--;
    }
    return 0;
}

void findPrimeList()
{
    long int primeSieve[MAX_SIZE] = {0}, num, multiple;

    primeSieve[0] = primeSieve[1] = 1;

    for(num = 2; num < MAX_SIZE; num++)
        if(primeSieve[num] == 0)
        {
            primeArr[primeArrSize++] = num;

            for(multiple = num; multiple < MAX_SIZE; multiple += num)
                primeSieve[multiple] = 1;
        }
}

int isSmithNumber(long int N)
{
    long int isPrime, digitSum, digitSumFactor;

    digitSumFactor = getDigitSumFactorization(N, &isPrime);
    if(isPrime)
        return 0;

    digitSum = getDigitSum(N);
    return digitSum == digitSumFactor;
}

long int getDigitSumFactorization(long int N, long int *isPrime)
{
    long int index = 0, sumFactor = 0, copyN = N;
    *isPrime = 0;

    if(N <= 1)
        return 0;

    for(index = 0; index < primeArrSize && primeArr[index] * primeArr[index] <= N; index++)
    {
        while(copyN % primeArr[index] == 0)
        {
            sumFactor += getDigitSum(primeArr[index]);
            copyN /= primeArr[index];
        }
    }

    if(copyN == N)
        /* N is a prime! */
        *isPrime = 1;
    else
        if(copyN > 1)
            sumFactor += getDigitSum(copyN);

    return sumFactor;
}

long int getDigitSum(long int N)
{
    long int digitSum = 0;

    while(N)
    {
        digitSum += N % 10;
        N /= 10;
    }

    return digitSum;
}
