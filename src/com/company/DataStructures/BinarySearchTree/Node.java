package com.company.DataStructures.BinarySearchTree;

public class Node<TKey, TData> {

    private TKey key;
    private TData data;

    private Node parent;
    private Node leftChild;
    private Node rightChild;


    public TKey Key()
    {
        return key;
    }

    public Node LeftChild()
    {
        return leftChild;
    }

    public Node RightChild()
    {
        return rightChild;
    }

    public Node Parent()
    {
        return parent;
    }
}
