//
//  main.c
//  ftm
//
//  Created by Joseph Phillips on 9/26/16.
//  Copyright © 2016 Joseph Phillips. All rights reserved.
//

//joesph phillips and stephen myers

#include <stdio.h>


int brain(long long int a){
    long long int input = a;
    long long int count;
    long long int m = 0;
    long long int m2 = 1;
    long long int onOff = 0;


    for(count = 0; count < 200; count++){
        if(m%input == 0 && m!=0 && m%10 == 0){printf("%lld \n", m);break;}
    else{
        if(m2%input == 0 && m2!=0 && m2%10 == 0){printf("%lld \n", m2);break;}

        if(onOff == 0){ m=m+1;m2=m2*10; onOff = 1;}
        else if(onOff == 1){ m=m*10;m2=m2*10; onOff = 0;}

    }
       // printf("%lld \n", count);
    }
    return 0;
};


int main(int argc, const char * argv[]) {
    // insert code here...
    long long int a;


    while(2==2){

    scanf("%lld",&a);
  //  printf("%lld \n", a);

        if(a==0){break;};
          brain(a);

        };
       return 0;
}
