// Jason Hetland
// jhetland@ucsc.edu
// FindComponents.c Pa5

#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include "Graph.h"

#define MAX_LEN 155

int main (int argc, char* argv[]){
    char line[MAX_LEN];
    // checks for correct command line inputs
    if(argc != 3) {
        printf("Invalid inputs");
        exit(1);
    }
    
    // opens the file
    FILE* input = fopen(argv[1], "r");
    FILE* output = fopen(argv[2], "w");
    
    // checks if the files have been open
    if(input == NULL){
        printf("Unable to open file %s for reading\n", argv[1]);
        return 1;
    } else if (output == NULL){
        printf("Unable to open file %s for reading\n", argv[2]);
        return 1;
    }
    
    // read each line of input file, then count and prints
    fgets(line, MAX_LEN, input);
    int numVertex = 0;
    sscanf(line, "%d", &numVertex);
    List S = newList();
    for (int i = 1; i <= numVertex; i++) append(S, i);
    // creates a Graph
    Graph G = newGraph(numVertex);
    while( fgets(line, MAX_LEN, input) != NULL) {
        int vert1 = 0;
        int vert2 = 0;
        sscanf(line, "%d %d", &vert1, &vert2);
        if(vert1 == 0 && vert2 == 0) break;
        addArc(G, vert1, vert2);
    }
    
    DFS(G, S);
    fprintf(output, "Adjacency list representation of G:\n");
    printGraph(output, G);
    
    Graph T = transpose(G);
    DFS(T, S);
    
    //counts strongly connected components
    int numScc = 0;
    for(moveTo(S, 0); getIndex(S) >= 0; moveNext(S)){
        if(getParent(T, getElement(S)) == NIL) numScc ++ ;
    }
    // puts the components into a list
    List Scc[numScc];
    int i = numScc;
    for(moveTo(S, 0); getIndex(S) >= 0; moveNext(S)){
        if(getParent(T, getElement(S)) == NIL){
            i--;
            Scc[i] = newList();
        }
        if(i == numScc) break;
        append(Scc[i], getElement(S));
    }
    
    // prints out strongly connected components
    fprintf(output, "\nG contains %d strongly connected components:", numScc);
    for(int j = 0; j < numScc; j++){
        fprintf(output, "\n");
        fprintf(output, "Component %d: ", j + 1);
        printList(output, Scc[j]);
        freeList(&(Scc[j]));
    }
    // frees
    fprintf(output, "\n");
    freeGraph(&G);
    freeGraph(&T);
    freeList(&S);
    fclose(input);
    fclose(output);
    return(0);
}

