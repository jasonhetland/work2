// Jason Hetland
// jhetland
// List.h Pa4


#ifndef __LIST_H__
#define __LIST_H__

#include <stdio.h>

typedef struct ListObj* List;

//Constructors-Destructors 
List newList(void);
void freeList(List* pL);

// Access functions 
// Returns number of elements in the list
int length(List L);
// returns the index of the cursor element or -1 if undefined
int getIndex(List L);
// if length > 0 return the front element
int front(List L);
// if length > 0 return back element
int back(List L);
// returns cursor element in the list
//pre : length() > 0, getIndex() >=0
int getElement(List L);
int equals(List A, List B);

//Manipulation procedures
void clear (List L);
// moves cursor to element
void moveTo(List L, int i);
// moves cursor to previous element
void movePrev(List L);
// moves cursor to next element
void moveNext(List L);
// prepends
void prepend(List L, int data);
// appends
void append(List L, int data);
// Pre: length() > 0, getIndex() >= 0
void insertBefore(List L, int data);
// Pre: length() > =, getIndex() >= 0
void insertAfter(List L, int data);
// Pre: length() > 0
void deleteFront(List L);
// Pre: length () > 0
void deleteBack(List L);
// Pre: length () > 0, getIndex() >= 0
void delete(List L);

//Other operations 
void printList (FILE* out, List L);
List copyList(List L);

#endif
