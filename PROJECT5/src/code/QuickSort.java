package code;

import java.util.Arrays;
import java.util.Random;

import given.AbstractArraySort;

/*
 * Implement the quick-sort algorithm here. You can look at the slides for the pseudo-codes.
 * Make sure to use the swap and compare functions given in the AbstractArraySort!
 * 
 */
//STUDENT NAME: YARKIN GAZİ
public class QuickSort<K extends Comparable<K>> extends AbstractArraySort<K> {  
	  //Add any fields here
	  
	  public QuickSort()
	  {
	    name = "Quicksort";
	    
	  //Initialize anything else here
	  }
	  
	  //useful if we want to return a pair of indices from the partition function.
	  //You do not need to use this if you are just returning and integer from the partition
	  public class indexPair {
	    public int p1, p2;
	    
	    indexPair(int pos1, int pos2)
	    {
	      p1 = pos1;
	      p2 = pos2;
	    }
	    
	    public String toString()
	    {
	      return "(" + Integer.toString(p1) + ", " + Integer.toString(p2) + ")";
	    }
	  }
	  
	  
	  @Override
	  public void sort(K[] inputArray)
	  {
	    //TODO:: Implement the quicksort algorithm here
		quickSort(inputArray,0,inputArray.length-1);
	  }

	  // Public since we are going to check its output!
	  public indexPair partition(K[] inputArray, int lo, int hi, int p)
	  {
		  int u,e,g;
	  //TODO:: Implement a partitioning function here
		  u = lo;
          e = lo;
		  g = hi+1;
		  K pivotElement = inputArray[p];
		  while(u < g) {
			  if(compare(inputArray[u],pivotElement) < 0) {
				  swap(inputArray, u, e);
				  e++;
				  u++;
			  } else if(compare(inputArray[u],pivotElement) == 0) {
				  u++;
			  } else {
				  g--;
				  swap(inputArray,u,g);
			  }
		  }
		  indexPair IndR = new indexPair(e,g);
		  return IndR;
	  }
	  
	  /* Alternative, if you are just returning an integer
	  public int partition(K[] inputArray, int lo, int hi, int p)
	  {
	    //TODO:: Implement a partitioning function here
	    return null;
	  }*/
	  
	  public void quickSort(K[] inputArray, int lo, int hi) {
		    if(lo<hi) {
			  int piv = pickPivot(inputArray, lo, hi);
			  indexPair p = partition(inputArray, lo, hi, piv);
			  quickSort(inputArray, lo, p.p1);
			  quickSort(inputArray, p.p2, hi);}
		    
	  }
	  
	  //The below methods are given given as suggestion. You do not need to use them.
	  // Feel free to add more methods  
	  protected int pickPivot(K[] inputArray, int lo, int hi)
	  {
	    //TODO: Pick a pivot selection method and implement it
		  //A good pivot should partition the array into equal sizes.
		  //OPTIONS:
		  //1)left end or right end. This could give O(n^2) performance depending on the input
		  //so this is a bad choice but easiest to implement.
		  //2)Secondly Best of 3. Left, middle and right. This gives substantially better performance
		  //than simply choosing an end in the worst case.
		  //3)Random. Choosing a random pivot is fairly easy to implement and guarantees
		  //nlogn performance so it is a very good choice.
		  //4)Yarkin's method: choose a random integer btw 0-3 and decide your strategy based on this.
		  int answer=0;
		  Random ran = new Random();
		  int randomInt = ran.nextInt(4);
		  if(randomInt == 0) { //Choose Left
			  answer=lo;
		  }
          if(randomInt == 1) { //Choose Right
        	  answer=hi;
		  }
		  if(randomInt == 2) { //Choose best among left,right and mid.(best means which one is in the between among other two.)
			  int mid = (hi+lo)/2; 
			  int MyArray[] = new int[3];
			  MyArray[0]=lo;
			  MyArray[1]=mid;
			  MyArray[2]=hi;
			  Arrays.sort(MyArray);
			  answer=  MyArray[1];
			  
		  }
          
		  if(randomInt == 3) {
			  int randomIndex = (int)(inputArray.length * Math.random());
			  answer=randomIndex; 
		  }
		  return answer;
	  }

	}