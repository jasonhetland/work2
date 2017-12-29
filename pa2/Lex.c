// Jason Hetland
// jhetland
// pa2 Lex.c

#include <stdlib.h>
#include <string.h>
#include <stdio.h>

#include "List.h"

#define MAX_LEN 160

// Similar to insertion sort
List insertsort(char** array, int length){
  // makes a new list object
  List list = newList();

  // appends first element
  if (length > 0) 
     append(list, 0);
  for (int j = 1; j < length; j++){
    char *temp = array[j];
    int index = j - 1;
    //move cursor to current position
    moveTo(list, index);
    //sort
    while (index > -1 && strcmp(temp, array[getElement(list)]) < 1){
      index--;
      movePrev(list);
    }
    
    // if cursor is null, then prepend otherwise insert after 
    if(getIndex(list) == -1){
      prepend(list, j);
    } else {
      insertAfter(list, j);
    }
  }

  return list;
}

// implementation of strdup
char *strdup (const char *s) {
  char *d = malloc(strlen(s) + 1);
  if (d == NULL) return NULL;
  strcpy(d,s);
  return d;
}

//reads the file into an array of strings
char** read(FILE* input, int lines){
  char** array = malloc(sizeof(char**) * lines);
  char line[MAX_LEN];
  for(int i = 0; i < lines; i++){
    fgets(line, MAX_LEN, input);
    array[i] = strdup(line);
  }
  return array;
}

//frees all the malloced heap space used by the program
void freeall(char** lines, int lineCount, List list){
  for (int i = 0; i < lineCount; i++){
    free(lines[i]);
  }
  free(lines);
  freeList(&list);
}

int main(int argc, char* argv[]){
  int count = 0;
  char line[MAX_LEN];

  if(argc != 3) {
    printf("Invalid number of inputs");
    exit(1);
  }
  
  FILE* input = fopen(argv[1], "r");
  FILE* output = fopen(argv[2], "w");

  if(input == NULL){ 
    printf("Unable to open file %s for reading\n", argv[1]);
    return 1;
  } else if (output == NULL){
    printf("Unable to open file %s for reading\n", argv[2]);
    return 1;
  }

  // reads each line of the input file, then count and prints
  while( fgets(line, MAX_LEN, input) != NULL) {
    count++;
  }

  // closes and reopens file to reset fgets
  fclose(input);
  input = fopen(argv[1], "r");
  char** lines = read(input, count);

  // carries out insertion sort
  List list = insertsort(lines, count);

  // prints out the list by searching for indices in array
  for(moveTo(list, 0); getIndex(list) >= 0; moveNext(list)){
    fprintf(output, "%s", lines[getElement(list)]);
  } 

  //closes files
  fclose(input);
  fclose(output);
  freeall(lines, count, list);
  return(0);
}
