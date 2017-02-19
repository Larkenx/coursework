#import <stdio.h>
#import <math.h>

/* My group partner was Joey */
static double cases =0;

static double total = 0;
static double member[1000000];

static double postot;
static double negtot;
static double rem;
int calc(){
    double k = cases;
    double avg = total/k;
    double change = 0;
    int i;

    for (i = 0; i < k; i++) {
        rem = (double) (long) (member[i] - avg);
        if (rem < 0) {
            negtot += rem;
        } else {
            postot += rem;
        }
    }
    change = (-negtot > postot) ? -negtot : postot;
    printf("$%.2f\n", (change/100));
    negtot = 0;
    postot = 0;
    total =0;
    return 0;
}


int main(){
    cases = 1;
    if(cases != 0){
        while(cases != 0){
            scanf("%lf", &cases);
            int j ;
            for(j= 0; j< cases;j++){
                double n;
                scanf("%lf\n", &n);
                member[j] = (n * 100);
                total = total + member[j];
            }
            if(cases>0){
                calc();
                j=0;
            }
        }

        if(cases == 0){
            return 0;
        }
    }
}
