/*
  main.c
  dynasty-xcode

  Created by Steven Myers on 11/6/16.
  Copyright © 2016 Steven Myers. All rights reserved.
*/

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

const int MAX_LENGTH = 100;

/* This function, str_replace was taken from
 http://stackoverflow.com/questions/779875/what-is-the-function-to-replace-string-in-c and authored by user: jmucchiello */

char *str_replace(char *orig, char *rep, char *with) {
    char *result; // the return string
    char *ins;    // the next insert point
    char *tmp;    // varies
    int len_rep;  // length of rep (the string to remove)
    int len_with; // length of with (the string to replace rep with)
    int len_front; // distance between rep and end of last rep
    int count;    // number of replacements
    
    // sanity checks and initialization
    if (!orig && !rep)
        return NULL;
    len_rep = strlen(rep);
    if (len_rep == 0)
        return NULL; // empty rep causes infinite loop during count
    if (!with)
        with = "";
    len_with = strlen(with);
    
    // count the number of replacements needed
    ins = orig;
    for (count = 0; (tmp = strstr(ins, rep)); ++count) {
        ins = tmp + len_rep;
    }
    
    tmp = result = malloc(strlen(orig) + (len_with - len_rep) * count + 1);
    
    if (!result)
        return NULL;
    
    while (count--) {
        ins = strstr(orig, rep);
        len_front = ins - orig;
        tmp = strncpy(tmp, orig, len_front) + len_front;
        tmp = strcpy(tmp, with) + len_with;
        orig += len_front + len_rep; // move to next "end of rep"
    }
    strcpy(tmp, orig);
    return result;
}

int main(int argc, const char * argv[]) {
    char dirs[MAX_LENGTH];
    scanf("%s", dirs);
    int bestLen = strlen(dirs);
    
    for (int i = 0; i < strlen(dirs) - 1; i++) {
        for (int j = i + 1; j < strlen(dirs); j++) {
            char sub[j + 1 - i];
            memcpy(sub, &dirs[i], j+1);
            sub[j+1-i] = '\0';
            
            char * encodedString = str_replace(dirs, sub, "M");
            int currLength = strlen(encodedString) + strlen(sub);
            if (currLength < bestLen) {
                bestLen = currLength;
            }
        }
    }
    printf("%d\n", bestLen);
    return (0);
}
