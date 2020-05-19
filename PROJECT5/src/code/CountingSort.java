package code;

import given.AbstractArraySort;

/*
 * Implement the c algorithm here. You can look at the slides for the pseudo-codes.
 * You do not have to use swap or compare from the AbstractArraySort here
 * 
 * You may need to cast any K to Integer
 * 
 */
//STUDENT NAME: YARKIN GAZİ
public class CountingSort<K extends Comparable<K>> extends AbstractArraySort<K> {

	  //Add any fields here
	  
	  public CountingSort()
	  {
	    name = "Countingsort";
	  }
	  
	  @SuppressWarnings("unchecked")
	@Override
	  public void sort(K[] inputArray) {
	    
	    if(inputArray == null)
	    {
	      System.out.println("Null array");
	      return;
	    }
	    if(inputArray.length < 1)
	    {
	      System.out.println("Empty array");
	      return;
	    }   
	    
	    if(!(inputArray[0] instanceof Integer)) {
	      System.out.println("Can only sort integers!");
	      return;
	    }
	    
	    //TODO:: Implement the counting-sort algorithm here
	    
	    Integer min = 0;
	    Integer max = 0;
	    
	    
	    for(int i=0; i<inputArray.length; i++) {
	    	
	    		if(compare(inputArray[i], (K) min) < 0) {
	    			min = (Integer) inputArray[i];
	    		} else if(compare(inputArray[i], (K) max) > 0) {
	    			max = (Integer) inputArray[i];
	    		}
	    		
	    }
	    
	    int minKey = min.intValue();
	    int maxKey = max.intValue();
	    
	    int k = maxKey - minKey + 1;
	    
	    int[] counts = new int[k];
	    
	    for(int i=0; i<inputArray.length; i++) {
	    		counts[((Integer) inputArray[i]).intValue() - minKey]++;
	    }
	    
	    int c=0;
	    
	    for(int i=0; i<k; i++) {
	    		if(counts[i] > 0) {
		    		for(int j=0; j<counts[i]; j++) {
		    			inputArray[c] =(K) new Integer(i + minKey);
		    			c++;
		    		}
	    		}
	    }
	    
	  }

	}