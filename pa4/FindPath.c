// Jason Hetland
// jhetland@ucsc.edu
// pa4 FindPath.c

#include <stdlib.h>
#include <string.h>
#include <stdio.h>

#include "Graph.h"
#define MAX_LEN 155

int main (int argc, char* argv[]){
    char line[MAX_LEN];
    // checks for correct command line inputs
    if(argc != 3) {
        printf("Invalid number of inputs");
        exit(1);
    }
    
    // opens the file
    FILE* input = fopen(argv[1], "r");
    FILE* output = fopen(argv[2], "w");
    
    // checks if files have been open and or created
    if(input == NULL){
        printf("Unable to open file %s for reading\n", argv[1]);
        return 1;
    } else if (output == NULL){
        printf("Unable to open file %s for reading\n", argv[2]);
        return 1;
    }
    
    // read each line of input file, then count and print
    fgets(line, MAX_LEN, input);
    int numVertex = 0;
    sscanf(line, "%d", &numVertex);
    
    // Creates Graph G
    Graph G = newGraph(numVertex);
    while( fgets(line, MAX_LEN, input) != NULL) {
        int ver1 = 0;
        int ver2 = 0;
        sscanf(line, "%d %d", &ver1, &ver2);
        if(ver1 == 0 && ver2 == 0) break;
        addEdge(G, ver1, ver2);
    }
    printGraph(output, G);
    // print path and get distances
    List L;
    int start = 0;
    while(fgets(line, MAX_LEN, input) != NULL) {
        int ver1 = 0;
        int ver2 = 0;
        start ++;
        sscanf(line, "%d %d", &ver1, &ver2);
        
        if(ver1 == 0 && ver2 == 0) break;
        if(start != 1) fprintf(output, "\n");
        fprintf(output, "\n");
        BFS(G, ver1);
        int dist = getDist(G, ver2);
        fprintf(output, "The distance from %d to %d is ", ver1, ver2);
        if(dist == -255) {
            fprintf(output, "infinity\n");
        } else {
            fprintf(output, "%d\n", dist);
        }
        L = newList();
        getPath(L, G, ver2);
        if(front(L) == -254){
            fprintf(output, "No %d-%d path exists", ver1, ver2);
        } else {
            fprintf(output, "A shortest %d-%d path is: ", ver1, ver2);
            printList(output, L);
        }
        
        
        freeList(&L);
    }
    // frees all the necessary items
    fprintf(output, "\n");
    freeGraph(&G);
    fclose(input);
    fclose(output);
    return(0);
}
