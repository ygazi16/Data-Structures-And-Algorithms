package code;

import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.List;

import given.AbstractHashMap;
import given.HashEntry;

/*
 * The file should contain the implementation of a hashmap with:
 * - Separate Chaining for collision handling
 * - Multiply-Add-Divide (MAD) for compression: (a*k+b) mod p
 * - Java's own linked lists for the secondary containers
 * - Resizing (to double its size) and rehashing when the load factor gets above a threshold
 *   Note that for this type of hashmap, load factor can be higher than 1
 * 
 * Some helper functions are provided to you. We suggest that you go over them.
 * 
 * You are not allowed to use any existing java data structures other than for the buckets (which have been 
 * created for you) and the keyset method
 */

public class HashMapSC<Key, Value> extends AbstractHashMap<Key, Value> {

  // The underlying array to hold hash entry Lists
  private LinkedList<HashEntry<Key, Value>>[] buckets;

  // Note that the Linkedlists are still not initialized!
  @SuppressWarnings("unchecked")
  protected void resizeBuckets(int newSize) {
    // Update the capacity
    N = nextPrime(newSize);
    buckets = (LinkedList<HashEntry<Key, Value>>[]) Array.newInstance(LinkedList.class, N);
  }

  /*
   * ADD MORE FIELDS IF NEEDED
   * 
   */

  // The threshold of the load factor for resizing
  protected float criticalLoadFactor;

  /*
   * ADD A NESTED CLASS IF NEEDED
   * 
   */

  public int hashValue(Key key, int iter) {
    return hashValue(key);
  }

  public int hashValue(Key key) {
    // TODO: Implement the hashvalue computation with the MAD method. Will be almost
    // the same as the primaryHash method of HashMapDH
	    int theK = Math.abs(key.hashCode());
	    int hash = Math.abs((theK * a + b) % P) % N;
	    return hash;
	  }

  // Default constructor
  public HashMapSC() {
    this(101);
  }

  public HashMapSC(int initSize) {
    // High criticalAlpha for representing average items in a secondary container
    this(initSize, 10f);
  }

  public HashMapSC(int initSize, float criticalAlpha) {
    N = initSize;
    criticalLoadFactor = criticalAlpha;
    resizeBuckets(N);
    
    // Set up the MAD compression and secondary hash parameters
    updateHashParams();
    n =0 ;
    /*
     * ADD MORE CODE IF NEEDED
     * 
     */
  }

  /*
   * ADD MORE METHODS IF NEEDED
   * 
   */

  @Override
  public Value get(Key k) {
    // TODO Auto-generated method stub
	  if(k == null) {
		  return null;
	  }
	  
	    if(buckets[hashValue(k)] != null) {
	    	int hashSize = buckets[hashValue(k)].size();
		   if(hashSize != 0) {
	    	for(int i=0; i<hashSize;  i++) {
				if(k.equals(buckets[hashValue(k)].get(i).getKey())) {
					return buckets[hashValue(k)].get(i).getValue();
				}
		    }
	    }}
	    return null;
	  }

  @Override
  public Value put(Key k, Value v) {
	    checkAndResize();  
	    if(k == null) {
	    	return null;
	    }
	    HashEntry<Key, Value> addingEntry = new HashEntry<Key, Value>(k, v);
	    if(buckets[hashValue(k)] == null) {  //If the place with our key(hash value) is roomy.
	    		LinkedList<HashEntry<Key, Value>> addingList = new LinkedList<HashEntry<Key, Value>>();
	    		addingList.add(addingEntry);
	    		buckets[hashValue(k)] = addingList;
	    		n++;
	    		return null;
	    } else {  
    		for(int i=0; i<buckets[hashValue(k)].size(); i++) {  //check out whether there are some other value in the place where our key is associated. We remove it and replace it with our new value.
    			if(k.equals(buckets[hashValue(k)].get(i).getKey())) {
    				Value ValueWeRemove = buckets[hashValue(k)].get(i).getValue();
    				buckets[hashValue(k)].get(i).setValue(v);  //setting the new value
    				
    				return ValueWeRemove;
    			}
    		}
    		buckets[hashValue(k)].add(addingEntry); // If there are no encounter in the place where our key is associated. We just add.
    		n++;
    		return null;
    }
	  }


  @Override
  public Value remove(Key k) {
    // TODO Auto-generated method stub
	  if(k == null) {
		  return null;
	  }
	    if(buckets[hashValue(k)] != null) {
	    		for(int i=0; i<buckets[hashValue(k)].size(); i++) {
	    			if((k.equals(buckets[hashValue(k)].get(i).getKey()))) {
	    				Value removingValue = buckets[hashValue(k)].get(i).getValue();
	    				buckets[hashValue(k)].remove(i);    				
	    				n--;
	    				return removingValue;
	    			}
	    		}
	    }
	    return null;
	  }

  @Override
  public Iterable<Key> keySet() {
	  List<Key> myIterList = new LinkedList<Key>();
	  HashEntry<Key, Value> theEntry;
	  for(int i=0; i<buckets.length; i++) {
		  while(buckets[i] != null) {
			  theEntry = buckets[i].get(i);
			  myIterList.add(theEntry.getKey());
  
			  }
		  }
	  
	  return myIterList;
  }

  /**
   * checkAndResize checks whether the current load factor is greater than the
   * specified critical load factor. If it is, the table size should be increased
   * to 2*N and recreate the hash table for the keys (rehashing). Do not forget to
   * re-calculate the hash parameters and do not forget to re-populate the new
   * array!
   */
  protected void checkAndResize() {
	  if (loadFactor() > criticalLoadFactor) {
	      // TODO: Fill this yourself
		    	@SuppressWarnings("unchecked")
				HashEntry<Key, Value>[] prevBucket = (HashEntry<Key, Value>[]) Array.newInstance(HashEntry.class, N);

	    		resizeBuckets(2*N);
	    		updateHashParams();
	    		HashEntry<Key, Value> entry;
	    		for(int i=0; i<prevBucket.length; i++) {
	    			
	    			entry=prevBucket[i];
	    			if(entry != null) {
	    			
	    				put(entry.getKey(), entry.getValue());
	    			}
	    		}

    }
  }
}
