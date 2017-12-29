// Jason Hetland
// jhetland
// pa4 List.h

#ifndef __GRAPH_H__
#define __GRAPH_H__

#include <stdio.h>
#include "List.h"
#define INF -255
#define NIL -254

typedef struct GraphObj* Graph;

/*** Constructors-Destructors ***/
// returns a Graph 
Graph newGraph(int n);
// frees memory
void freeGraph(Graph* pG);

/*** Access functions ***/
// returns Order
int getOrder(Graph G);
// returns size
int getSize(Graph G);
// returns source vertex most recently used
int getSource(Graph G);
// return the parent of vertex u
// Pre : 1 <= U <= getOrder(G)
int getParent(Graph G, int u);
//returns the distance from most recent source
// Pre : 1 <= U <= getOrder(G)
int getDist(Graph G, int u);
// appends to List L the vertices of shortest path in G 
// Pre : getSource(G) != NIL, 1 <= U <= getOrder()
void getPath(List L, Graph G, int u);

/*** Manipulation procedures ***/
// clears G
void makeNull(Graph G);
// inserts a new edge
// Pre : 1<= u | v < getOrder(G)
void addEdge(Graph G, int u, int v);
// adds a directed edge 
// Pre : 1<= u | v < getOrder(G)
void addArc(Graph G, int u, int v);
//implementation of BFS(G,S)
void BFS(Graph G, int s);

/*** Other operations ***/
// prints the adjacenct list representation of G
void printGraph(FILE* out, Graph G);

#endif

