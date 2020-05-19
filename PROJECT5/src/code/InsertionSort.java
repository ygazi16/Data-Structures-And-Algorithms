package code;

import given.AbstractArraySort;
//STUDENT NAME: YARKIN GAZİ
public class InsertionSort<K extends Comparable<K>> extends AbstractArraySort<K> {

	  public InsertionSort()
	  {
	    name = "Insertionsort";
	  }
	  
	  @Override
	  public void sort(K[] inputArray) 
	  {   
	    //TODO: Implement the insertion sort algorithm
		  int i,j;
		  K value;
		  for (i = 1; i <inputArray.length ; i++)
		  {
		          value = inputArray[i];
		          j = i;
		          while ((j > 0) &&  compare(inputArray[j-1] , value)>0) 
		          {
		        	  swap(inputArray,j,j-1);
		        	  j--;
		                   
		          }
		          inputArray[j] = value;
		         
		  }
	  }

	}
