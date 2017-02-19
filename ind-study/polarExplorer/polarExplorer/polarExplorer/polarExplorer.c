#include <stdio.h>
#include <math.h>
#include <string.h>

int main(int argc, const char * argv[]) {
    float pi = 3.14159;
    int radius;
    int fuel;
    int degrees;
    char line[12];
    char result[1500];
    int end = 0;
    while (end != 1) {
        fgets(line, sizeof line, stdin);
        if (strcmp(line, "START\n") == 0) {
            fgets(line, sizeof line, stdin);
            if (sscanf(line, "%d %d %d", &radius, &fuel, &degrees) == 3) {
                float distanceToTravel = (degrees / (float) 360) * 2 * pi * radius;
                int remainingFuel = floor(fuel - distanceToTravel);
                
                if ((remainingFuel > 0) || (distanceToTravel == 0)) {
                    sprintf(result + strlen(result), "YES %d\n", remainingFuel);
                } else {
                    sprintf(result + strlen(result), "NO %d\n", fuel * 5);
                }
                
            }
            fgets(line, sizeof line, stdin); // Skip "END"
        } else if (strcmp(line, "ENDOFINPUT\n") == 0) {
            end = 1;
        }
        /* memset(line, 0, sizeof(line)); */
    }
    /* toPrint[ strlen(result) - 1] = 0; */
    printf("%s", result);
    return 0;
}
