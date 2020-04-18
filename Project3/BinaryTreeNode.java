package code;

import given.Entry;
//Student Name : Yarkın GAZİ
/*
 * The binary node class which extends the entry class.
 * This will be used in linked tree implementations
 * 
 */
public class BinaryTreeNode<Key, Value> extends Entry<Key, Value> {

    private BinaryTreeNode<Key, Value> leftNode;
    private BinaryTreeNode<Key, Value> rightNode;
    private BinaryTreeNode<Key, Value> parentNode;

    
    public BinaryTreeNode(){
       
       
    }
    public BinaryTreeNode(Key k,Value v, BinaryTreeNode<Key, Value> left, BinaryTreeNode<Key, Value> right, BinaryTreeNode<Key, Value> parent){
        super(k,v);
        leftNode = left;
        rightNode = right;
        parentNode = parent;
    }

    public BinaryTreeNode<Key, Value> getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(BinaryTreeNode<Key, Value> leftNode) {
        this.leftNode = leftNode;
    }

    public BinaryTreeNode<Key, Value> getRightNode() {
        return rightNode;
    }

    public void setRightNode(BinaryTreeNode<Key, Value> rightNode) {
        this.rightNode = rightNode;
    }

    public BinaryTreeNode<Key, Value> getParentNode() {
        return parentNode;
    }

    public void setParentNode(BinaryTreeNode<Key, Value> parentNode) {
        this.parentNode = parentNode;
    }
}
