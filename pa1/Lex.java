//Jason Hetland
//Progamming Assignment 1 
//Lex.java. This is 

import static java.lang.System.*;
import java.io.*;
import java.util.Scanner;

class Lex{

    static Scanner open(String filename){
	Scanner scan = null;
	try{
	   scan = new Scanner(new File(filename));
	} catch (IOException except){
	   System.err.println("Invalid file");
	   exit(1);
	}
	return scan;
   }

     //This counts the number of lines in the file
   static int countLines(Scanner file){
	int index = 0;
	for(; file.hasNextLine(); file.nextLine()) index++;
	return index;
   }

     //Stores the lines in the array
   static String[] read(Scanner file, int lines){
	String[] array = new String[lines];
	for(int i = 0; file.hasNextLine(); i++){
	    array[i] = file.nextLine();
        }
	return array;
   }

    //similar to insertion sort
   static List insertionSort(String[] file){
	List list = new List();
      	//this inserts the first element into the list
	if(file.length > 0)
	list.append(0);
	//sorts
	for(int j = 1; j < file.length; j++){
	   String temp = file[j];
	   int index = j - 1;
	   //moves the cursor to the current position
	   list.moveTo(index);
	   while(index > -1 && temp.compareTo(file[list.getElement()]) < 
		1){
	  	index--;
		list.movePrev();
	   }

	//if the cursor == null then prepend
	   if(list.getIndex() == -1){
	      list.prepend(j);
	   } else{
	      list.insertAfter(j);
	   }
	}
	return list;
    }

    public static void main (String[] args){
	//checks for valid arguments
	if(args.length != 2){
	  System.err.println("Invalid Input");
	  exit(1);
       }

	//stores files in scanner objects
	Scanner input = open(args[0]);
	PrintWriter writer = null;
	try{
	   writer = new PrintWriter(new FileWriter(args[1]));
      } catch (FileNotFoundException excep){
	 System.err.println("Could not create the file");
	 exit(1);
      } catch (IOException excep){
	System.err.println("Could not create the file");
         exit(1);
      }

	// prints out the number of lines
	int lineCount = countLines(input);
  	// resets line Scanner and pointer
	input = open(args[0]);
	String[] array = read (input, lineCount);
	//stores indices in list
	List list = insertionSort(array);
	//writes the lines to the file
	for(list.moveTo(0); list.getIndex() >= 0; list.moveNext()){
	   writer.println(array[list.getElement()]);
	}
	
	writer.close();
   }
}
