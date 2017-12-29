// Jason Hetland
// jhetland@ucsc.edu
// pa3 List.java

class List {
  private class node {
    Object data;
    node prev;
    node next;

// first node
node(Object num){
    data = num;
    prev = null;
    next = null;

 }
// inserting a new node
node(Object num, node previous, node after){
    data = num;
    prev = previous;
    next = after;

 }

// to string method
public String toString(){
  return "" + data;

    }
}


private static final node OFF = null;
  private node cursor;
  private node front;
  private node back;
  private int length;

// Creates a new empty list
public List (){
  cursor = OFF;
  front = null;
  back = null;
  length = 0;

 }

// Returns number of elements in list
public int length() {
  return length;

}

// Returns the index of the cursor element or -1 if undefined
public int getIndex(){
  int index = -1;
   if (cursor == OFF) return index;
     for (node tmp = front; tmp != null; tmp = tmp.next){
        index++;
       if (tmp == cursor) break;

  }
        return index;   
 }

// Returns front element if length > 0
public Object front() {
  if (length() > 0) {
    return front.data;

 } 
     return null;
}

// Returns back element if length > 0
public Object back() {
  if (length() > 0){
     return back.data;
  }
   return null;
 }

// Returns cursor element in the list
public Object getElement(){
 if (length() > 0 && getIndex() >= 0) return cursor.data;
   return null;
}

//Returns true if this List and L are same integers
public boolean equals(Object L){
 if (L == null || !(L instanceof List)) {
       System.out.println("List:equals: incorrect parameter type");

	    return false;

 }

	node right = front;
        node left = ((List)L).front;

// checks if corresponding indices are incorrect
   while (right != null && left != null){
       if (!(right.data.equals(left.data))){
          return false;
   }
	 right = right.next;
	 left = left.next;
}

// if either list had more elements than the other
  if (right != null || left != null) return false;
    return true;
}

// Clears the List
public void clear(){
  cursor = OFF;
  front = null;
  back = null;
  length = 0;
}

// moves cursor to index i
public void moveTo(int i){
 if (i >= 0 && i <= length() - 1){
  cursor = front;
   for(; i > 0; i--) cursor = cursor.next;
} else {
  cursor = OFF;
  }

}

// this operation is like moveTo(getIndex() -1)
public void movePrev(){
  int index = getIndex();
   if (index > 0 && index <= length() - 1){
     cursor = cursor.prev;

  } else { 
     cursor = OFF;

	}
}

// similar to  movePrev() 
public void moveNext(){
  int index = getIndex();
    if(index >= 0 && index < length() - 1){
      cursor = cursor.next;
    
    } else {
      cursor = OFF;

    }
}

 // prepends to the list
public void prepend(Object data){
    length++;
    
    node node = new node(data,null, front);
     if (front != null) {
       	front.prev = node;
     } else {
      	back = node;
     }
	front = node;

 }

// appends to the list
public void append(Object data){
    length++;
    
    node temp = new node(data, back, null);
     if (back != null) {
	back.next = temp;

	} else {
	front = temp;
}
	back = temp;

    }

// inserts an element before the cursor
public void insertBefore(Object data){
   if (!(length() >0 && getIndex() >=0)) return;
      	length++;
  
       	node temp = new node(data, cursor.prev, cursor);

   // checks to see if node exists before cursor, if the cursor == front 
   // node else front must be changed 
        if (cursor.prev != null) {
           cursor.prev.next = temp;

	} else {

	    front = temp;

	}
	cursor.prev = temp;

    }

public void insertAfter(Object data){
    if(!(length() >0 && getIndex() >=0)) return;
       length++;

	node temp = new node(data, cursor, cursor.next);

    // checks to see if node exists after cursor. If the cursor == backnode, 
    // back must be changed to the new node
        if (cursor.next != null){
	    cursor.next.prev = temp;

	} else {

	    back = temp;

	}

	cursor.next = temp;

    }

// deletes the front node
public void deleteFront(){
    if (length() > 0) {
 	length--;
// if the cursor == front node then it must be set to OFF
    if(cursor == front) cursor = OFF;
 	front = front.next;
	front.prev = null;

	}

    }

// deletes the backnode
public void deleteBack(){
   if (length() > 0){
   	length--;

// if the cursor == backnode then it must be set to OFF
    if(cursor == back) cursor = OFF;
	back = back.prev;
	back.next = null;

	}

    }

// deletes the cursor node
public void delete(){	
    if (cursor == front){
	deleteFront();

	} else if (cursor == back){
	deleteBack();

   }

     if (length() > 0 && getIndex() > 0){
  	 length--;
	 
	cursor.next.prev = cursor.prev;
	cursor.prev.next = cursor.next;

	cursor = OFF;

	}
}

public String toString() {
    String result = "";
    node temp = front;

      while (temp.next != null){
	result += temp + " ";
	temp = temp.next;

	}
	result += temp;

      return result;

    }
}

