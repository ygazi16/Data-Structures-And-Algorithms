package code;

import given.Entry;
import given.iAdaptablePriorityQueue;

/*
 * Implement a binary search tree based priority queue
 * Do not try to create heap behavior (e.g. no need for a last node)
 * Just use default binary search tree properties
 */
//Student Name : Yarkın GAZİ
public class BSTBasedPQ<Key, Value> extends BinarySearchTree<Key, Value> implements iAdaptablePriorityQueue<Key, Value> {

    public BSTBasedPQ() {
        super();
    }
    @Override
    public void insert(Key k, Value v) {
        put(k,v);
    }

    @Override
    public Entry<Key, Value> pop() {
        if(isEmpty()) {
        	return null;
        }
        BinaryTreeNode<Key, Value> temp;
        BinaryTreeNode<Key, Value> node = (BinaryTreeNode<Key, Value>) top();
        temp=node;
        remove(node.getKey());
        return temp;
    }

    @Override
    public Entry<Key, Value> top() {
        if(isEmpty()) {
        	return null;
        }
        BinaryTreeNode<Key, Value> node = super.getRoot();
        while(super.getLeftChild(node)!=null ){
            node = super.getLeftChild(node);
        }
        return node;
    }

    @Override
    public Key replaceKey(Entry<Key, Value> entry, Key k) {
        BinaryTreeNode<Key, Value> node =  getNode(entry.getKey());
        Value replacingValue = node.getValue();
        Key oldK = node.getKey();
        remove(node.getKey());
        insert(k,replacingValue);
        return oldK;
    }

    @Override
    public Key replaceKey(Value v, Key k) {
        BinaryTreeNode<Key, Value> node = null;
        for(BinaryTreeNode<Key,Value> n: getNodesInOrder()){
            if(n.getValue().equals(v)) {
                node = n;
                break;
            }
        }
        if(node!=null) {
            Key oldK = node.getKey();
            remove(node.getKey());
            insert(k, v);
            return oldK;
        }
        return null;
    }

    @Override
    public Value replaceValue(Entry<Key, Value> entry, Value v) {
        BinaryTreeNode<Key, Value> node =  getNode(entry.getKey());
        Value finaled = node.getValue();
        node.setValue(v);
        return finaled;
    }

}