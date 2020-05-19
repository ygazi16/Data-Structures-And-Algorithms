package code;

import given.iPrintable;
import given.iSet;

/*
 * A set class implemented with hashing. Note that there is no "value" here 
 * 
 * You are free to implement this however you want. Two potential ideas:
 * 
 * - Use a hashmap you have implemented with a dummy value class that does not take too much space
 * OR
 * - Re-implement the methods but tailor/optimize them for set operations
 * 
 * You are not allowed to use any existing java data structures
 * 
 */

public class HashSet<Key> implements iSet<Key>, iPrintable<Key>{
	 HashMapDH<Key, Integer> mapping;
  // A default public constructor is mandatory!
	
	
  public HashSet() {
	  mapping = new HashMapDH<Key, Integer>();  
	  /*
    * Add code here 
    */
	  
	 
  }
  
  /*
   * 
   * Add whatever you want!
   * 
   */

  @Override
  public int size() {
    // TODO Auto-generated method stub
    return mapping.size();
  }

  @Override
  public boolean isEmpty() {
	// TODO Auto-generated method stub
	  return size()==0;
  }


  @Override
  public boolean contains(Key k) {
    // TODO Auto-generated method stub
	  return (mapping.get(k) != null); 
 
}

  @Override
  public boolean put(Key k) {
    // TODO Auto-generated method stub
	  return mapping.put(k,(Integer) 1)!=null;
	  }

  @Override
  public boolean remove(Key k) {
	  return mapping.remove(k)!=null;
}


  @Override
  public Iterable<Key> keySet() {
    // TODO Auto-generated method stub
         return mapping.keySet();
	  
  }

  @Override
  public Object get(Key key) {
    // Do not touch
    return null;
  }

}
