package code;

import given.AbstractArraySort;

/*
 * Implement the merge-sort algorithm here. You can look at the slides for the pseudo-codes.
 * Make sure to use the swap and compare functions given in the AbstractArraySort!
 * 
 * You may need to create an Array of K (Hint: the auxiliary array). 
 * Look at the previous assignments on how we did this!
 * 
 */
//STUDENT NAME: YARKIN GAZİ
public class MergeSort<K extends Comparable<K>> extends AbstractArraySort<K> {
  K[] externalArray;
	  // Add any fields here

	
	
		
	  public MergeSort() {
	    name = "Mergesort";

	    // Initialize anything else here
	  }

	  
	  
	  @SuppressWarnings("unchecked")
	@Override
	  public void sort(K[] inputArray) {
	    // TODO: Implement the merge-sort algorithm
		  externalArray = (K[]) new Comparable[inputArray.length];
		  mergesort(inputArray, 0, inputArray.length-1);  
	  }
	  
	  public void mergesort(K[] inputArray, int lo, int hi) {
		  if(lo<hi) {
			  int mid = ( lo + (hi-lo)/2 ); //As Baris Hoca mentioned in Discussions.
			  mergesort(inputArray,lo,mid);
			  mergesort(inputArray,mid+1,hi);
			  merge(inputArray,lo,mid,hi);
		  }
	  }

	  // Public since we are going to check its output!
	  public void merge(K[] inputArray, int lo, int mid, int hi) {
	    // TODO: Implement the merging algorithm
		  System.arraycopy(inputArray, lo, externalArray, lo, hi - lo + 1);
		  int i=lo;
		  int k=lo;
		  int j=mid+1;
		  
		  while(k <= hi) {
			  if((j > hi) || (i<= mid && compare(externalArray[i],externalArray[j]) <= 0)) {
				  inputArray[k] = externalArray[i];
				  k++;i++;
			  } else {
				  inputArray[k] = externalArray[j];
				  k++;j++;
			  }
		  }
	  }
	  
	
	}