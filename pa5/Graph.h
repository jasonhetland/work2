// Jason Hetland
// jhetland@ucsc.edu
// Graph.h pa5

#ifndef __GRAPH_H__
#define __GRAPH_H__

#include <stdio.h>
#include "List.h"
#define UNDEF -165
#define NIL 0

typedef struct GraphObj* Graph;

/*** Constructors-Destructors ***/
// returns a Graph pointing to GraphObj
Graph newGraph(int n);
// frees dynamic memory
void freeGraph(Graph* pG);
/*** Access functions ***/
// returns the number of vertices
int getOrder(Graph G);
// returns size 
int getSize(Graph G);
// return the parent 
// Pre : 1 <= U <= getOrder(G)
int getParent(Graph G, int u);
// Pre : 1 <= U <= getOrder(G)
int getDiscover(Graph G, int u);
// Pre : 1 <= U <= getOrder()
int getFinish(Graph G, int u);

/*** Manipulation procedures ***/
// inserts a edge 
// 1<= u | v < getOrder(G)
void addEdge(Graph G, int u, int v);
// inserts a directed edge
// Pre:  1<= u | v < getOrder(G)
void addArc(Graph G, int u, int v);
// implements DFS
// Pre: getLength(s) == n 
// n = getOrder(G)
void DFS(Graph G, List s);

/*** Other operations ***/
// prints the adjacenct list representation of G
void printGraph(FILE* out, Graph G);
// returns Graph G transposed
Graph transpose(Graph G);
// returns the copy of Graph G
Graph copyGraph(Graph G);

#endif
