

package code;

/* 
 * ASSIGNMENT 2
 * AUTHOR:  <YARKIN GAZİ>
 * Class : LLDeque
 *
 * You are not allowed to use Java containers!
 * You must implement the linked list yourself
 * Note that it should be a doubly linked list
 *
 * MODIFY 
 * 
 * */

import given.iDeque;
import java.util.Iterator;

//If you have been following the class, it should be obvious by now how to implement a Deque wth a doubly linked list
public class LLDeque<E> implements iDeque<E> {
	  
	  //Use sentinel nodes. See slides if needed
	  private Node<E> header;
	  private Node<E> trailer;
	  

	  /*
	   * ADD FIELDS IF NEEDED
	   */
      private int size=0;
      
  
	  // The nested node class, provided for your convenience. Feel free to modify
	  private class Node<T> {
	    private T element;
	    private Node<T> next;
	    private Node<T> prev;
	    /*
	     * ADD FIELDS IF NEEDED
	     */
	    
	    Node(T d, Node<T> n, Node<T> p) {
	      element = d;
	      next = n;
	      prev = p;
	    }
	   
	    // Getter And Setter Methods:
	    public Node<T> getNext() {
	        return next;
	      }
	    
	    public Node<T> getPrev() {
	        return prev;
	      }
	    
	    public void setNext(Node<T> next) {
	        this.next = next;
	      }
	    
	    public void setPrev(Node<T> prev) {
	        this.prev = prev;
	      }
	    
	    public void setElement(T element) {
	        this.element = element;
	      }
	 
	    
	    public T getElement()  {
	        
	        
	              return element;
	        }
	      }
	    
	    
	    
	    /*
	     * ADD METHODS IF NEEDED
	     */
	  
	  
	  public LLDeque() {
	    //Remember how we initialized the sentinel nodes
	    header  = new Node<E>(null, null, header);
	    trailer = new Node<E>(null, trailer, header);
	    header.next = trailer;
	    
	    /*
	     * ADD CODE IF NEEDED
	     */
	  }
	  
	  public String toString() {
	    if(isEmpty())
	      return "";
	    StringBuilder sb = new StringBuilder(1000);
	    sb.append("[");
	    Node<E> tmp = header.next;
	    while(tmp.next != trailer) {
	      sb.append(tmp.element.toString());
	      sb.append(", ");
	      tmp = tmp.next;
	    }
	    sb.append(tmp.element.toString());
	    sb.append("]");
	    return sb.toString();
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
	   if(size()==0) return true;
	   return false;
	  
	  }

	  @Override
	  public void addFront(E o) {
		  
	    // TODO Auto-generated method stub
		  Node<E> temp = new Node<E>(null,header.getNext(),header);
		  temp.setElement(o);
		  header.getNext().setPrev(temp);
		  header.setNext(temp);
		    
		    
		    size++;
		  
	  }

	  @Override
	  public E removeFront() {
	    // TODO Auto-generated method stub
      if(isEmpty()) {
    	  return null;
    	  
      }else {
    	  if(isEmpty())
    	      return null;
    	    Node<E> removingnode = header.getNext();
    	    E temp = removingnode.getElement();
    	    removingnode.setElement(null);
    	    
    	    header.setNext(removingnode.getNext());
    	    removingnode.getNext().setPrev(header);
         //since nothing is pointing to the removing node; garbage collector will collect it.
    	    
    	    
    	    size--;
    	    return temp;
      }

	 
	    
	  }

	  @Override
	  public E front() {
	    // TODO Auto-generated method stub
	    if(isEmpty()) {
	    	return null;
	    }else {
	    	E frontie = header.getNext().getElement();
	    	return frontie;
	    }
	
	  }

	  @Override
	  public void addBehind(E o) {
	    // TODO Auto-generated method stub
		  Node<E> addingTempNode = new Node<E>(null,trailer,trailer.getPrev());
		  addingTempNode.setElement(o);
		  trailer.getPrev().setNext(addingTempNode);
		  trailer.setPrev(addingTempNode);
		  size++;
		  
	  }

	  @Override
	  public E removeBehind() {
		    if(isEmpty()) {
		        return null;
		        }else {
		     
		    
		    Node<E> removingnode = trailer.getPrev();
		      E removingElement = removingnode.getElement();
		      trailer.setPrev(removingnode.getPrev());
		      removingnode.getPrev().setNext(trailer);
		      removingnode.setElement(null);
		      size--;
		      return removingElement;}
	  }

	  @Override
	  public E behind() {
	    // TODO Auto-generated method stub
	  if(isEmpty()) {
		  return null;
		  
	  }else {
		  E behindE = trailer.getPrev().getElement();
		  
		  return behindE;
		  
	  }
	
	  }

	  @Override
	  public void clear() {
		 
		
		Iterator<E> Myiterator = iterator();
		    while(Myiterator.hasNext()) { 
		    	this.removeBehind();
		    }
		    	
		    	
		    
	
	  }
	  
	  @Override
	  public Iterator<E> iterator() {
	    
		  Iterator<E> MyIterator = new LLDequeIterator();
		  
		  return MyIterator;
	    
	  }
	 
	 
	  
	  private final class LLDequeIterator implements Iterator<E> {
	    Node<E> currentNode = header;
	    
	    /*
	     * 
	     * ADD A CONSTRUCTOR IF NEEDED
	     * Note that you can freely access everything about the outer class!
	     * 
	     */

	    @Override
	    public boolean hasNext() {
	   if(currentNode.getNext()!=trailer) {
		   return true;
	   }else {
		      return false;
	   }
	   
	    }

	    @Override
	    public E next(){
	        if (hasNext() == false) {
	        	return null;
	        }
	        currentNode = currentNode.getNext();
	      return currentNode.getElement();
	     }
	    }    
	    
	    
	  }
	  
	

