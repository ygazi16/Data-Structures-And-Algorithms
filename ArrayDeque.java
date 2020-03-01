
package code;

/* 
 * ASSIGNMENT 2
 * AUTHOR:  <YARKIN GAZİ>
 * Class : ArrayDeque
 *
 * You are not allowed to use Java containers!
 * You must implement the Array Deque yourself
 *
 * MODIFY 
 * 
 * */

import given.iDeque;
import java.util.Iterator;
import java.util.NoSuchElementException;


/*
 * You must have a circular implementation. 
 * 
 * WARNING: Modulo operator (%) might create issues with negative numbers.
 * Use Math.floorMod instead if you are having issues
 */
public class ArrayDeque<E> implements iDeque<E> {

  private E[] A; //Do not change this name!

  
  private int size=0;
  private int front = 0;

 

  
  
  
 
  
  /*
   * ADD FIELDS IF NEEDED
   */
	
	public ArrayDeque() {
		this(1000);
    /*
     * ADD CODE IF NEEDED
     */
	}
	
	public ArrayDeque(int initialCapacity) {
	   if(initialCapacity < 1)
	      throw new IllegalArgumentException();
		A = createNewArrayWithSize(initialCapacity);
		
		
		/*
		 * ADD CODE IF NEEDED
		 */
	
		
		
		
		
	}
	
	// This is given to you for your convenience since creating arrays of generics is not straightforward in Java
	@SuppressWarnings({"unchecked" })
  private E[] createNewArrayWithSize(int size) {
	  return (E[]) new Object[size];
	}
	
	//Modify this such that the dequeue prints from front to back!
	//Hint, after you implement the iterator, use that!
  public String toString() {
    
    /*
     * MODIFY THE BELOW CODE
     */
    if(isEmpty()) {
    	return "";
    }else {
    StringBuilder sb = new StringBuilder(1000);
    sb.append("[");
    Iterator<E> Myiterator = iterator();
    while(Myiterator.hasNext()) {
      E e = Myiterator.next();
      if(e == null) {
        continue;
        }else {
          sb.append(e);}
      if(!Myiterator.hasNext())
        sb.append("]");
      else
        sb.append(", ");
    }
    return sb.toString(); }
  }
	
  /*
   * ADD METHODS IF NEEDED
   */

  /*
   * Below are the interface methods to be overriden
   */
	
  @Override
  public int size() {
    // TODO Auto-generated method stub
   
    return size;
  }

  @Override
  public boolean isEmpty() {
    // TODO Auto-generated method stub
 return (size==0);
   
  
  }

  @Override
  public void addFront(E o) {
    // TODO Auto-generated method stub
		if(isFull()) {
			E tempArray[] = createNewArrayWithSize(2*A.length);
			
			
			
		for(int i=0; i<size-1;i++) {
				tempArray[i] = A[i]; }
			 A = tempArray;
		    front = 0;
			
		}  
		front = Math.floorMod((front - 1 + A.length),  A.length); // new front
		A[front] = o;
		size++;
  
  }
   
 
  

  

 
	   


private boolean isFull() {
	 return (size==A.length);
}

@Override
  public E removeFront() {
    // TODO Auto-generated method stub
  if(isEmpty()) {
	  return null;
	  
  }else {
	  
	  E frontie = A[front];
	  A[front] = null;
	  front = Math.floorMod(front + 1 + A.length, A.length);  /////
	  size--;
	  return frontie;
	  
  }
  
  
  }

  @Override
  public E front() {
 if(isEmpty()) {
	 return null;
 }else {
	 return A[front];
	 
 }
  }

  @Override
  public void addBehind(E o) {
    // TODO Auto-generated method stub
	  if(isFull()) {
  		E[] temp = createNewArrayWithSize(2*A.length);
  		
  		
  		for(int i=0; i<size-1; i++) {
  			temp[i] = A[i];}
  		
  		A = temp;
  		front = 0;
  		
  }
  A[(front + size + A.length) % A.length] = o;
  size++;
  
}
    
    
 
    
  

  @Override
  public E removeBehind() {
    // TODO Auto-generated method stub
	  if(isEmpty()) {
  		return null;
  } else {
  		E ans = A[(front  + size + A.length - 1) % A.length];
  		A[(front + size - 1) % A.length] = null;
  		size--;
  		return ans;
  }
   
  

   
    
  }

  @Override
  public E behind() {
    // TODO Auto-generated method stub
   if(isEmpty()) {
	   return null;
   }else {
	   
	   
	   return A[Math.floorMod(front + size - 1 + A.length, A.length)];
   }
     }

  @Override
  public void clear() {
          for(int a = 0; a< A.length; a++) {
        	  A[a] = null;
        	  
        	  
        	  
          }
          
          front = 0;
      
          size = 0;
          
  }
  
  //Must print from front to back
  @Override
  public Iterator<E> iterator() {
	  return new ArrayDequeIterator();
  }
  
  private final class ArrayDequeIterator implements Iterator<E> {
      
	  
	   private int it = 0;
	   private int frontie = front;
	   
	  
    /*
     * 
     * ADD A CONSTRUCTOR IF NEEDED
     * Note that you can freely access everything about the outer class!
     * 
     */

	  
    @Override
    public boolean hasNext() {
        return it<size();
         
    }

    @Override
    public E next() throws NoSuchElementException{
    	if (it==size()) {
    		throw new NoSuchElementException("There is not any element left at the array right now.");
    	}else {
    		
        it++;
        
        return A[(frontie++) % A.length]; 
    	}
    	
        
    
        
    

    	
    	
    }

    }        
  }
