package com.company.DictBinTree;

public class Node {

    private int key;
    private Node LeftChild;
    private Node RightChild;

    public Node(int key, Node leftChild, Node rightChild)
    {
        this.key = key;
        LeftChild = leftChild;
        RightChild = rightChild;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public Node getLeftChild() {
        return LeftChild;
    }

    public void setLeftChild(Node leftChild) {
        LeftChild = leftChild;
    }

    public Node getRightChild() {
        return RightChild;
    }

    public void setRightChild(Node rightChild) {
        RightChild = rightChild;
    }

    public int getKey()
    {
        return key;
    }
}
