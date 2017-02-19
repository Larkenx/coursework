#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/* Global candidates array */
typedef struct {
    char name[80];
    int votes[20]; /* Each element from 0 to n represent preference of votes 0 = first */
} candidate;

candidate candidates[20];

void printCands(int n) {
    for (int i = 0; i < n; i++) printf("[%s]", candidates[i].name);
    printf("\n");
}

void winners(int n, int voters) {
    int place = 0;
    while (place <= n) {
        int max = -1;
        int min = 1001;
        int ties = 0;
        int totalVotes = 0;

        for (int i = 0; i < n; i++) {
            int currVotes = candidates[i].votes[place];
            totalVotes += currVotes;
            if (currVotes > max) max = currVotes;
            if (currVotes < min) min = currVotes;
        }

        for (int i = 0; i < n; i++) {
            candidate curr = candidates[i];
            if (((float) curr.votes[place] / (float) totalVotes) > (float) .5) { // Has to be more than fifty percent of the vote
                printf("%s\n",curr.name);
                return;
            } else if (curr.votes[place] == min) { ties++; }
        }

        candidate tmp[n - ties];
        int tmpIndex = 0;
        for (int i = 0; i < n; i++) { /* Eliminate the lowest votes */
            if (candidates[i].votes[place] > min) {
                tmp[tmpIndex] = candidates[i];
                tmpIndex++;
            }
        }
        printCands(n);
        memset(candidates, 0, sizeof candidates);
        memcpy(candidates, tmp, sizeof tmp);
        printCands(n);
        place++;
        n -= ties;
    }
    for (int i = 0; i < n; i++) printf("%s\n", candidates[i].name);
    return;
}

int main(int argc, char const *argv[]) {
    int cases;
    char line[80];
    fgets(line, sizeof line, stdin);
    sscanf(line, "%d", &cases);
    fgets(line, sizeof line, stdin); /* Empty Line */
    for (int i = 0; i < cases; i++) {
        int n; /* Number of candidates */
        fgets(line, sizeof line, stdin);
        sscanf(line, "%d", &n);
        char first[40];
        char last[40];
        for (int j = 0 ; j < n; j++) {
            candidate curr;
            fgets(line, sizeof line, stdin);
            sscanf(line, "%s %s", first, last);
            strcpy(curr.name, first);
            strcat(curr.name, " ");
            strcat(curr.name, last);
            candidates[j] = curr;
        }

        /* Now we have an array of the candidates */
        int vote;
        int totalVoters = 0;
        while (fgets(line, sizeof line, stdin) != NULL && (line[0] != '\n')) { /* Until we hit an empty line */
            totalVoters++;
            int counter = 0;
            vote = 0;
            char* token = strtok(line, " ");
            while (token != NULL) {
                if (token) {
                    vote =  atoi(token);
                    candidate* votedFor = &candidates[vote - 1]; //candidate votedFor = candidates[vote - 1]
                    votedFor->votes[counter] += 1; /* increment votes for candidate at the received preference */
                    counter++;
                }
                token = strtok(NULL, " ");
            }
            memset(line, 0, sizeof line);
        }

        winners(n, totalVoters);
    }
    return 0;
}
