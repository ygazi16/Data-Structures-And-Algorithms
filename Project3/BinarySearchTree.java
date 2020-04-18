package code;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import given.iMap;
import given.Entry;
import given.iBinarySearchTree;
//Student Name : Yarkın GAZİ
/*
 * Implement a vanilla binary search tree using a linked tree representation
 * Use the BinaryTreeNode as your node class
 */

public class BinarySearchTree<Key, Value> implements iBinarySearchTree<Key, Value>, iMap<Key, Value> {
    
	
	
	private BinaryTreeNode<Key,Value> root;
    private int size = 0;
    private Comparator<Key> Mycomparator;

    public BinarySearchTree(Comparator<Key> c){
        root = new BinaryTreeNode<Key,Value>();
        Mycomparator = c;
    }

    public BinarySearchTree(){
       
        root = new BinaryTreeNode<Key,Value>(null,null,null,null,null);
    }

    public BinaryTreeNode<Key,Value> treeSearch(BinaryTreeNode<Key,Value> node, Key k ){
        if(isExternal(node)){ 
        	return node;
        	}
        if(!isExternal(node) && !isInternal(node)){ 
        	return null;
        	}
        
        if(Mycomparator.compare(k,node.getKey()) == 0){
            return node;
        }else if(Mycomparator.compare(k,node.getKey()) < 0){
            if(node.getLeftNode()!=null)
                return treeSearch(node.getLeftNode(),k);
            else
                return node;
        }else{
            if(node.getRightNode()!=null)
                return treeSearch(node.getRightNode(),k);
            else
                return node;
        }
        
       
    }
    @Override
    public Value get(Key k) {
        if(Mycomparator.compare(k,treeSearch(root,k).getKey()) == 0) {
            return treeSearch(root,k).getValue();
            }else {
        return null;
        }
    }
    
    @Override
    public Value put(Key k, Value v) {
        if(isEmpty()){
            root.setKey(k);
            root.setValue(v);
            size++;
            return null;
        }
        BinaryTreeNode<Key,Value> theNode = treeSearch(root,k);
       

        if(Mycomparator.compare(k,theNode.getKey())==0){
            Value temp = theNode.getValue();
            theNode.setValue(v);
            return temp;
        }else{
            if(Mycomparator.compare(k,theNode.getKey())<0){
            	theNode.setLeftNode(new BinaryTreeNode<Key,Value>(k,v,null,null,theNode));
                size++;
                return null;
            }else{
            	theNode.setRightNode(new BinaryTreeNode<Key,Value>(k,v,null,null,theNode));
                size++;
                return null;
            }
        }

    }
    
    
    @Override
    public Value remove(Key k) {

        boolean done = false ;
        BinaryTreeNode<Key, Value> node = treeSearch(root,k);
        if(Mycomparator.compare(node.getKey(),k)==0){
            Value val = node.getValue();
            BinaryTreeNode<Key, Value> parent = node.getParentNode();
            BinaryTreeNode<Key, Value> LNode = node.getLeftNode();
            BinaryTreeNode<Key, Value> RNode = node.getRightNode();
            if(LNode!=null && RNode==null){
                if(parent!=null) {
                    if (isLeftChild(node))
                        parent.setLeftNode(LNode);
                    else
                        parent.setRightNode(LNode);
                    LNode.setParentNode(parent);
                }else{
                    setRoot(LNode);
                }
            }else if(LNode==null && RNode!=null){
                if(parent!=null) {
                    if (isLeftChild(node))
                        parent.setLeftNode(RNode);
                    else if(isRightChild(node))
                        parent.setRightNode(RNode);
                    RNode.setParentNode(parent);
                }else{
                    setRoot(RNode);
                }
            }else if(LNode!=null && RNode!=null){

                BinaryTreeNode<Key, Value> Right = LNode.getRightNode();


                if(Right!=null){
                    while(Right.getRightNode()!=null){
                    	Right = Right.getRightNode();
                    }
                    Key oldKey = Right.getKey();
                    Value oldValue = remove(Right.getKey());
                    done = true;

                    node.setKey(oldKey);
                    node.setValue(oldValue);

                }else{
                    if(parent!=null) {
                        if (isLeftChild(node))
                            parent.setLeftNode(LNode);
                        else
                            parent.setRightNode(LNode);
                        LNode.setParentNode(parent);
                    }else
                        setRoot(LNode);
                    RNode.setParentNode(LNode);
                    LNode.setRightNode(RNode);
                }

            }else{
                if(parent!=null) {
                    if (isLeftChild(node))
                        node.getParentNode().setLeftNode(null);
                    else
                        node.getParentNode().setRightNode(null);
                }
            }


            if(!done) {
                size--;
                node.setParentNode(null);
                node.setLeftNode(null);
                node.setRightNode(null);
                node.setValue(null);
                node.setKey(null);
            }
            return val;
        }
        return null;
    }
    

    @Override
    public Iterable<Key> keySet() {
        ArrayList<Key> iterable = new ArrayList<>(size());
        for(BinaryTreeNode<Key, Value> nodes : getNodesInOrder())
        	iterable.add(nodes.getKey());
        return iterable;
    }
    
    
    @Override
    public int size() { 
    	
    	return size; 
    	}

    @Override
    public boolean isEmpty() { 
    	return size==0; 
    	}

    @Override
    public BinaryTreeNode<Key, Value> getRoot() {
        return root;
    }
    
    
    @Override
    public BinaryTreeNode<Key, Value> getParent(BinaryTreeNode<Key, Value> node) {
        return node.getParentNode();
    }
    
    
    @Override
    public boolean isInternal(BinaryTreeNode<Key, Value> node) {
        if(node == null){ 
        	return false; 
        	}
        if(node.getKey()!=null){
            return true;
        }
        return false;
    }
    
    

    @Override
    public boolean isExternal(BinaryTreeNode<Key, Value> node) {
        if(node == null){ 
        	return true; 
        	}
        if(node.getKey()==null)
            return true;
        return false;
    }
    
    

    @Override
    public boolean isRoot(BinaryTreeNode<Key, Value> node) {
        return node==root;
    
    }
    
    
    
    
    
    
    
    private int maxDepth(BinaryTreeNode<Key, Value> node){
        if(node == null)
            return 0;
        else{
            int leftSize = maxDepth(node.getLeftNode());;
            int rightSize = maxDepth(node.getRightNode());
            int depth = 1 + Math.max(leftSize,rightSize);
            return depth;
        }

    }
   
    public int height() {
        return maxDepth(getRoot());
    }

    public void setRoot(BinaryTreeNode<Key, Value> newNode){
        if(newNode != null) {
            newNode.setParentNode(null);
            root = newNode;
        }else{
            root.setValue(null);
            root.setKey(null);
            root.setRightNode(null);
            root.setLeftNode(null);
            root.setRightNode(null);
        }
    }
   

   

   


    @Override
    public BinaryTreeNode<Key, Value> getNode(Key k) {
        BinaryTreeNode<Key, Value> tempNode = treeSearch(root,k);
        if(Mycomparator.compare(k,tempNode.getKey()) == 0) {
            return tempNode;
            }
        return null;
    }

    @Override
    public Value getValue(Key k) {
        if(getNode(k) != null)
            return getNode(k).getValue();
        return null;
    }

    @Override
    public BinaryTreeNode<Key, Value> getLeftChild(BinaryTreeNode<Key, Value> node) {
        return node.getLeftNode();
    }

    @Override
    public BinaryTreeNode<Key, Value> getRightChild(BinaryTreeNode<Key, Value> node) {
        return node.getRightNode();
    }
    @Override
    public BinaryTreeNode<Key, Value> sibling(BinaryTreeNode<Key, Value> node) {
        if(isRoot(node)){
            return null;
        }else if(isLeftChild(node)){
            BinaryTreeNode<Key, Value> rightS = node.getParentNode().getRightNode();
            return rightS;
        }else if(isRightChild(node)){
            BinaryTreeNode<Key, Value> leftS = node.getParentNode().getLeftNode();
            return leftS;
        }
        return null;
    }

    @Override
    public boolean isLeftChild(BinaryTreeNode<Key, Value> node) {
        BinaryTreeNode<Key, Value> par = node.getParentNode();
        if(par==null) return false;
        return node.equals(par.getLeftNode());
    }
    @Override
    public boolean isRightChild(BinaryTreeNode<Key, Value> node) {
        BinaryTreeNode<Key, Value> par = node.getParentNode();
        if(par==null) return false;
        return node.equals(par.getRightNode());
    }


    private void inOrderTraversal(BinaryTreeNode<Key, Value> parent,List<BinaryTreeNode<Key, Value>> nodeList ){
        if(parent.getLeftNode()!=null){
        	inOrderTraversal(parent.getLeftNode(),nodeList);
        }
        nodeList.add(parent);
        if(parent.getRightNode() != null){
        	inOrderTraversal(parent.getRightNode(),nodeList);
        }
    }
    @Override
    public List<BinaryTreeNode<Key, Value>> getNodesInOrder() {
        List<BinaryTreeNode<Key, Value>> BSTList = new ArrayList<>();
        if(!isEmpty()){
        	inOrderTraversal(getRoot(),BSTList);
        }
        return BSTList;
    }

    @Override
    public void setComparator(Comparator<Key> C) {
    	Mycomparator = C;
    }

    @Override
    public Comparator<Key> getComparator() {
        return Mycomparator;
    }

    @Override
    public BinaryTreeNode<Key, Value> ceiling(Key k) {
      
        BinaryTreeNode<Key, Value> p = treeSearch(getRoot(),k);
        if(p != null) {
            int compare = Mycomparator.compare(k, p.getKey());
            if (compare == 0) return p;
            while (!isRoot(p)) {
                BinaryTreeNode<Key, Value> parent = p.getParentNode();
                if (compare < 0) {
                    return p;
                } else {
                    p = parent;
                    compare = Mycomparator.compare(k, p.getKey());
                }
            }
        }
        return  null;
    }

    @Override
    public BinaryTreeNode<Key, Value> floor(Key k) {
      
        BinaryTreeNode<Key, Value> p = treeSearch(getRoot(),k);
        if(p!=null) {
        
        if(Mycomparator.compare(k,p.getKey())==0) {
        	return p;
        }
        
        while(!isRoot(p)) {
                BinaryTreeNode<Key, Value> parent = p.getParentNode();
                if(Mycomparator.compare(k,p.getKey())>0){
                    return p;
                } else {
                    p = parent;
                    
                }
        }
        return getRoot();
    }else {
     return null;
     }
        }
}
    


