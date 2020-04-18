package code;

import given.Entry;
import given.iAdaptablePriorityQueue;
import given.iBinarySearchTree;

import java.util.ArrayList;
import java.util.Comparator;
//Student Name : Yarkın GAZİ
/*
 * Implement an array based heap
 * Note that you can just use Entry here!
 *
 */
//  ﬁrst implement the array based binary tree functionality
public class ArrayBasedHeap<Key, Value> implements iAdaptablePriorityQueue<Key, Value> {

    protected ArrayList<Entry<Key, Value>> nodes = new ArrayList<>();
    private Comparator<Key> Mycomparator;

    public ArrayBasedHeap() {
    	
    	
    }

    @Override
	public Comparator<Key> getComparator() {
		// TODO Auto-generated method stub
		return Mycomparator;
	}
    
    
    
    private int parent(int j) { 
    	
    	return (j - 1) / 2; }

    private int rightChild(int j) { 
    	
    	return 2 * j + 2; }

    private int leftChild(int j) { 
    	
    	return 2 * j + 1; }

    private boolean hasLeft(int j) { 
    	
    	return leftChild(j) < nodes.size(); }

    private boolean hasRight(int j) { 
    	
    	return rightChild(j) < nodes.size(); }
    
    
    public boolean IsOutOfBounds(int index){
    	if(index<0 || index>=size()){
    		return true;
    	}else {
    		return false;
    	}
    }

    private void swap(int a, int b) {
        Entry<Key, Value> temp = nodes.get(a);
        nodes.set(a, nodes.get(b));
        nodes.set(b, temp);
    }

    private void upHeap(int a) {
        while (a > 0) {
            int parentNode = parent(a);
            if (Mycomparator.compare(nodes.get(a).getKey(), nodes.get(parentNode).getKey()) >= 0) {
            	break;}
            else {
            swap(parentNode, a);
            a = parentNode;
        }
            }
    }

    private void downHeap(int a) {
        while (hasLeft(a)) {
            int LC = leftChild(a);
            
            int SC = LC; //Assuming the left child is the small child.
            int RC = rightChild(a);
            if (hasRight(a)) {
                
                if (Mycomparator.compare(nodes.get(LC).getKey(), nodes.get(RC).getKey()) > 0) {
                    SC = RC;}   ///finding out which one is the small child, left or right?
            }
            if (Mycomparator.compare(nodes.get(a).getKey(), nodes.get(SC).getKey()) <= 0) break;
            swap(a, SC);
            a = SC;
        }
    }
    
    
   
    
    public Entry<Key,Value> remove(int i){
        if(IsOutOfBounds(i)){
        	return null;
        	}
        Entry<Key,Value> removingNode = nodes.get(i);
        swap(i, nodes.size() - 1);
        nodes.remove(nodes.size() - 1);
        if (i < nodes.size()) {
            upHeap(i);
        }
        
        	
       downHeap(i);

        return removingNode;
    }


    private int heapSearch(int a,Key k){
        if (isEmpty()) {
            return -1;
            }
        
        
        
        if(Mycomparator.compare(nodes.get(a).getKey(), k)==0) {
        	return a;
        }
        if(Mycomparator.compare(nodes.get(a).getKey(), k)>0){
        	return -1;
        	}else{
        		
            if(hasLeft(a)) {
                
                if (heapSearch(leftChild(a), k) > 0) {
                    return heapSearch(leftChild(a), k);
                }
            }
            if(hasRight(a)){
                
                if(heapSearch(rightChild(a), k)>0) {
                    return heapSearch(rightChild(a), k);
                }
            }
        }

        return -1;
    }
    private int indexOfKey(Key k) {
        return heapSearch(0,k);
    }

    //find index of given value
    private int indexOfValue(Value v) {
        if (isEmpty())
            return -1;
        for (int i = 0; i < size(); i++) {
            if (nodes.get(i).getValue().equals(v)) return i;
        }
        return -1;
    }

    @Override
    public int size() {
        return nodes.size();
    }

    @Override
    public boolean isEmpty() {
        return nodes.size() == 0;
    }

    @Override
    public void setComparator(Comparator<Key> C) {
    	Mycomparator = C;
    }

    @Override
    public void insert(Key k, Value v) {
        //check key maybe
        Entry<Key, Value> newItem = new Entry<>(k, v);
        nodes.add(newItem);
        upHeap(nodes.size() - 1);
    }

    @Override
    public Entry<Key, Value> pop() {
        if (nodes.isEmpty()) return null;
        Entry<Key, Value> result = nodes.get(0);
        remove(0);
        return result;
    }

    @Override
    public Entry<Key, Value> top() {
        if (nodes.isEmpty()) return null;
        return nodes.get(0);
    }


    @Override
    public Value remove(Key k) {

        int RemovingInd = indexOfKey(k);
        if (RemovingInd == -1) {
        	return null;
        }
        Value removal = remove(RemovingInd).getValue();
        return removal;
    }

   
    @Override
    public Key replaceKey(Entry<Key, Value> entry, Key k) {
        int ind = indexOfKey(entry.getKey());
        if (ind == -1 || !nodes.get(ind).equals(entry) ) return null;
        Key removingKey = entry.getKey();
        remove(ind);
        insert(k, entry.getValue());
        return removingKey;
    }

    
    @Override
    public Key replaceKey(Value v, Key k) {
        int ind = indexOfValue(v);
        if (ind == -1) {
        	return null;
        }

        Key removingKey = nodes.get(ind).getKey();
        remove(ind);
        insert(k, v);
        return removingKey;
    }

    @Override
    public Value replaceValue(Entry<Key, Value> entry, Value v) {
        int ind = indexOfKey(entry.getKey());
        if (ind == -1 || Mycomparator.compare(nodes.get(ind).getKey(), entry.getKey()) != 0) {
        	return null;
        }

        if (!nodes.get(ind).getValue().equals(entry.getValue())) {
            return null;
            }

        Value removingValue = entry.getValue();
        nodes.get(ind).setValue(v);
        return removingValue;
    }

	
}