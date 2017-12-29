// Jason Hetland
// jhetland@ucsc.edu
// pa3 Matrix.java

class Matrix {
private class Entry{
  int column;
  double value;
 
 Entry(int col, double val){
  column = col;
  value = val;

 }

public boolean equals(Object object){
  if(object == null || !(object instanceof Entry)){
     System.out.println("Entry:Equals: incorrect parameter type");
     return false;
}
   return ((Entry)object).value == value && ((Entry)object).column == column;

	}

public String toString(){
  return "(" + column + ", " +  value + ")";

	}

    }

	private List[] matrix;
	private int NNZ;

 // Creates a new n by n zero Matrix. pre : n >=1
public Matrix(int n){
    if (n < 1){
	System.out.println("Please enter a  matrix size");
	return;

    }

	matrix = new List[n];
	for (int i = 0; i < n; i++) matrix[i] = new List();
	NNZ = 0;

    }

// Returns the number of rows and columns of this matrix
public int getSize(){
    return matrix.length;

    }

// Returns the number of non-zero entries in this Matrix
public int getNNZ(){
    return NNZ; 

    }

// overrides Object's equals() method
public boolean equals(Object x){

 // checks for correct parameters
  if (x == null || !(x instanceof Matrix)) {
     System.out.println("Matrix:equals: incorrect parameters");
     return false;

	}
	Matrix temp = (Matrix)x;

	// checks for equal lengths
	if(temp.getSize() != matrix.length){
	   return false;

	}

	// compares list entries
	for(int i = 0; i < matrix.length; i++){
	    if(!(matrix[i].equals(temp.matrix[i]))){
	       return false;

	    }

	}

	return true;

    }

// clears this Matrix to the all zeros
public void makeZero(){
// clears all the lists
  for(int i = 0; i < matrix.length; i++){
     matrix[i].clear();
  }
    NNZ = 0;

    }

// returns a new Matrix having the same entries as this Matrix if it is equivalent to scalarMult(1);
public Matrix copy(){
// if scalar is 0 then everything 0iterates over the rows
    Matrix copy = new Matrix(matrix.length);
    for(int row = 0; row < matrix.length; row++){
	List old = matrix[row];
	List fres = copy.matrix[row];
	for(old.moveTo(0); old.getIndex() >= 0; old.moveNext()){
	    Object object = old.getElement();

	// checks Entries
	   if (object == null || !(object instanceof Entry)) {
		System.out.println("Matrix:copy: Not correct type");
		return null;

		}

		Entry ij = (Entry)object;

	   // copies the column and value
	 	Entry ijcopy = new Entry(ij.column, ij.value);
		fres.append(ijcopy);

	    }

	}

	// number of non-zero entries will be the same
	copy.NNZ = NNZ;
	return copy;

    }



// changes ith row, jth column of this Matrix to x
public void changeEntry(int i, int j, double x){
  if( (i + j) < 2 || (i + j) > (2 * matrix.length)){
     	System.out.println("Matrix: changeEntry: Indexes are out of bounds");
	 return;

	} 
	List row = matrix[i - 1];
	if(row == null) System.out.println("Matrix:ChangeEntry: row is Null");
	Entry ij = null;

	// iterates over columns of row 
	for(row.moveTo(0); row.getIndex() >= 0; row.moveNext()){
	    Object object = row.getElement();

	    
	   if (object == null || !(object instanceof Entry)) {
		System.out.println("Matrix:changeEntry: Not correct types");
		return;

	    }

	    ij = (Entry)object;
	    if(ij.column < j) continue;
	    break;

	}

      // if the index doesn't exist and x isn't 0
	if(row.getIndex() == -1 && x != 0){
	  row.append(new Entry(j, x));
	  NNZ++;

	}else if (ij != null && ij.column == j){

	 // if new value == 0  remove the node 
	   if(x == 0){
	     row.delete();
	     NNZ--;

	    } else {
	       ij.value = x;

	    }

	} else {
	    if(x != 0){
	       row.insertBefore(new Entry(j, x));
	       NNZ++;
	 }
       }
    }

// returns the scalar product of this matrix and x
public Matrix scalarMult(double x){
// if scalar is 0 then everything is 0
   if(x == 0) this.makeZero();

//iterates over the rows
   Matrix copy = new Matrix(matrix.length);
   for(int row = 0; row < matrix.length; row++){
       	List old = matrix[row];
	List fres = copy.matrix[row];
	for(old.moveTo(0); old.getIndex() >= 0; old.moveNext()){
	   Object object = old.getElement();

	// checks Entry
	    if (object == null || !(object instanceof Entry)) {
		System.out.println("Matrix:copy: Not correct type");
		return null;
       }

		Entry ij = (Entry)object;

	// copies the column and value * scalar
		Entry ijcopy = new Entry(ij.column, (ij.value) * x);
		fres.append(ijcopy);

	    }

	}

	// number of non-zero entries will be the same
	copy.NNZ = NNZ;
	return copy;

    }

// Performs addition. Returns a new Matrix that is the sum of this Matrix with M It iterates
// over each row, n times, and accessing each element in both lists
public Matrix add(Matrix M){
    if(matrix.length != M.matrix.length){
	System.out.println("Matrix:add: different matrix sizes");
	return null;
    }
	
   // if it is the exact same matrix then mutiply it by 2
    if(M == this) return this.scalarMult(2);
    Matrix copy = new Matrix(matrix.length);

   // iterates over rows
    for(int row = 0; row < matrix.length; row++){
	List one = matrix[row];
	List two = M.matrix[row];
	List sum = copy.matrix[row];

	one.moveTo(0);
	two.moveTo(0);
   
   // iterates through each list
    	while(one.getIndex() >= 0 && two.getIndex() >= 0){
	     Entry r = (Entry)(one.getElement());
             Entry l = (Entry)(two.getElement());
             copy.NNZ++;

	     if(r.column == l.column){
		if(r.value + l.value != 0){
		  sum.append(new Entry(r.column, r.value + l.value));

		  } else {
		     NNZ--; 

		    }

		    one.moveNext();
		    two.moveNext();

		} else if (r.column < l.column){
		    sum.append(new Entry(r.column, r.value));
                    one.moveNext();

		} else {
                    sum.append(new Entry(l.column, l.value));
		    two.moveNext();

		}

	    }

	// if both cursors are OFF then exit the loop
	    if (one.getIndex() == two.getIndex()) continue;

        // if either cursor is still ON then append 
	    List other = (one.getIndex() >= 0)? one : two;
	     while(other.getIndex() >= 0){
	       Entry fin = (Entry)(other.getElement());
	       copy.NNZ++;
               sum.append(new Entry(fin.column, fin.value));
               other.moveNext();

	    }

         }

	  return copy;

    }

// This performs subtraction of a matrix. It returns a new Matrix that is 
// the difference of this Matrix with M. It iterates over each row n times, and accessing each element in both lists
public Matrix sub(Matrix M){
   if(matrix.length != M.matrix.length){
     System.out.println("Matrix:sub: different matrix sizes");
     return null;
	
}

   // if it is the same matrix then return the 0 matrix
   if(M == this) return new Matrix(matrix.length);
	Matrix copy = new Matrix(matrix.length);

	
        for(int row = 0; row < matrix.length; row++){
	   List one = matrix[row]; 
           List two = M.matrix[row];
           List sum = copy.matrix[row];

	    

       // subtraction 
       //the values in two will be subtracted the values in one will be added
	    one.moveTo(0);
            two.moveTo(0);

	    while(one.getIndex() >= 0 && two.getIndex() >= 0){
		Entry r = (Entry)(one.getElement());
		Entry l = (Entry)(two.getElement());
		copy.NNZ++;

		if(r.column == l.column){
		  if(r.value - l.value != 0){
		    sum.append(new Entry(r.column, r.value - l.value));

		    } else {

			NNZ--; // decrement if 0 result

		    }

		    one.moveNext();
		    two.moveNext();

		} else if (r.column < l.column){
                    sum.append(new Entry(r.column, r.value));
		    one.moveNext();

		} else {
		    sum.append(new Entry(l.column, 0 - l.value));
	            two.moveNext();

		}

	    }

	// if both cursors are OFF then exit the loop
	    if (one.getIndex() == two.getIndex()) continue;

	// if either cursor is still ON then append the other
	    List other = (one.getIndex() >= 0)? one : two;
	    int sign = (one.getIndex() >= 0)? 1 : -1;

	     while(other.getIndex() >= 0){
	          Entry fin = (Entry)(other.getElement());
	          copy.NNZ++;
		  sum.append(new Entry(fin.column, sign * fin.value));
                  other.moveNext();
           }
	}

	return copy;

    }

// returns the transpose of the matrix
public Matrix transpose(){
  Matrix copy = new Matrix(matrix.length);

// iterates over current matrix rows
  for(int i = 0; i < matrix.length; i++){
      List row = matrix[i];
      for(row.moveTo(0); row.getIndex() >= 0; row.moveNext()){
          Entry entry = (Entry)(row.getElement());

       // converts column indexes into row indexes
          int line = entry.column - 1;
	  copy.matrix[line].append(new Entry(i + 1, entry.value));

	    }

	}

	copy.NNZ = NNZ;
        return copy;

    }

// returns a new Matrix that is the product of this matrix with M
public Matrix mult(Matrix prod){
    if(matrix.length != prod.matrix.length){
        System.out.println("Matrix:mult: invalid size parameter");
        return null;

	}

      Matrix copy = new Matrix(matrix.length);

      // if either matrices are the 0 matrix just return a 0 matrix
      if(NNZ == 0 || prod.NNZ == 0) return copy;

      // transposes a matrix to carry row to row vector multiplication
      Matrix trans = prod.transpose();
      for(int i = 0; i < matrix.length; i++){
           // if the list length is 0 skip
            if(matrix[i].length() == 0) continue;
            for(int j = 0; j < matrix.length; j++){
               if(trans.matrix[j].length() == 0) continue;
               double result = dot(matrix[i] , trans.matrix[j]);
               if(result != 0){
                  copy.matrix[i].append(new Entry(j + 1, result));
                  copy.NNZ++;

		}

	    }

	}

	return copy;
 }

// executes a vector dot product on two lists
private static double dot(List P, List Q){
   double result = 0;
   P.moveTo(0);
   Q.moveTo(0);

   while(P.getIndex() >= 0 && Q.getIndex() >= 0){
      Entry p = (Entry)(P.getElement());
      Entry q = (Entry)(Q.getElement());

      if(p.column == q.column){
        result += p.value * q.value;
        P.moveNext();
        Q.moveNext();

       } else if (p.column < q.column){
           P.moveNext();

	} else {
           Q.moveNext();
 }

	}

	return result;

    }

// to string method for Matrix
public String toString(){
  String ret = "";
  for(int i = 0; i < matrix.length; i++){
     if(matrix[i].length() > 0){
       ret += (i+1) + ": " +  matrix[i] + "\n";
    }
}
	return ret;

    }
}
