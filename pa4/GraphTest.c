// Jason Hetland
// jhetland
// GraphTest.c Pa4

#include<stdio.h>
#include<stdlib.h>
#include"Graph.h"

int main(int argc, char* argv[]){
   int i, s, max, min, d, n=35;
   List  C = newList(); // central vertices 
   List  P = newList(); // peripheral vertices 
   List  E = newList(); // eccentricities 
   Graph G = NULL;

   // Build graph G 
   G = newGraph(n);
   for(i=1; i<n; i++){
      if( i%7!=0 ) addEdge(G, i, i+1);
      if( i<=28  ) addEdge(G, i, i+7);
   }
   addEdge(G, 9, 31);
   addEdge(G, 17, 13);
   addEdge(G, 14, 33);

   // Print adjacency list representation of G
   printGraph(stdout, G);

   // Calculate the eccentricity of each vertex 
   for(s=1; s<=n; s++){
      BFS(G, s);
      max = getDist(G, 1);
      for(i=2; i<=n; i++){
         d = getDist(G, i);
         max = ( max<d ? d : max );
      }
      append(E, max);
   }

   // Determine the Radius and Diameter of G, as well as the Central and 
   // Peripheral vertices.
   append(C, 1);
   append(P, 1);
   min = max = front(E);
   moveTo(E,0);
   moveNext(E);
   for(i=2; i<=n; i++){
      d = getElement(E);
      if( d==min ){
         append(C, i);
      }else if( d<min ){
         min = d;
         clear(C);
         append(C, i);
      }
      if( d==max ){
         append(P, i);
      }else if( d>max ){
         max = d;
         clear(P);
         append(P, i);
      }
      moveNext(E);
   }

   // Print results 
   printf("\n");
   printf("Radius = %d\n", min);
   printf("Central vert%s: ", length(C)==1?"ex":"ices");
   printList(stdout, C);
   printf("\n");
   printf("Diameter = %d\n", max);
   printf("Peripheral vert%s: ", length(P)==1?"ex":"ices");
   printList(stdout, P);
   printf("\n");

// My testing

   BFS(G,2);
   clear(P);
   clear(C)
   getPath(P,G,4);
   getPath(P,G,30);
   
// Prints out results
   printf("The path from");
   printList(stdout,C);
   printf("\n the path from the source vertex is: ");
   printList(stdout, P);
   printf("\n is the diatnce from the source vertex:  ");
   printf("%d\n", getDist(G,4));
   printf("\n");
  

   clear(P);
   clear(C);

   freeGraph(&G);

   n = 100;
   G =  newGraph(n);
// addArc(G, 10, 40);
// addEdge(G, 1, 20);

// BFS(G,41);

getPath(C, G, 40);

printGraph(stdout, G);

printf("The path from source verex 40 is ");
printList(stdout, C);
printf("\n is the distance from the source vertex to vertex 40");

getPath(P, G, 60);
printf("The path from source vertex 60 is ");
printList(stdout, P);
printf("\n is the distance from the source vertex to vertex 60"); 
   
getPath(C, G, 2);
printf("The path from source vertex 2 is ");
printList(stdout, C);
printf("\n is the distance from the source vertex to vertex 2");
  // Free objects 
   freeList(&C);
   freeList(&P);
   freeList(&E);
   freeGraph(&G);

   return(0);
}
