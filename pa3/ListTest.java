// Jason Hetland 
// jhetland@ucsc.edu
// pa3 ListTest.java

public class ListTest{
  static class Entry{
 	int column;
	double value;
     Entry (int col, double val){
         column = col;
	 value = val;
}

public static void main(String[] args){
   List A = new List();
   List B = new List();
   List C = new List();

  for(int i = 1; i<=20; i++){
    A.append(new Entry(i, (double)i));
    B.prepend(new Entry(i, (double)i));
    C.append(new Entry(i, (double)i));

}
   System.out.println(A);
   System.out.print(B);

  for(A.moveTo(0); A.getIndex()>=0; A.moveNext()){
     System.out.print(A.getElement()+" ");
}

  System.out.println();
}

//  System.out.println(A.equals(B));
//  A.moveTo(2);
 // A.insertAfter(-4);
//  A.moveTo(7);
//  A.insertBefore(-5);
//  A.delete();
//  System.out.println(A);
 // System.out.println(B.equals(C));
 // A.clear();
 }
}
