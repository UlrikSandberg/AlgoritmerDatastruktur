package com.company.Compression;

public class Node
{
    private Node Left;
    private Node Right;

    private boolean isLeafNode;
    private int code;
    private int frequiency;

    public int getFrequiency() {
        return frequiency;
    }

    public void setFrequiency(int frequiency) {
        this.frequiency = frequiency;
    }

    public Node getLeft() {
        return Left;
    }

    public void setLeft(Node left) {
        Left = left;
    }

    public Node getRight() {
        return Right;
    }

    public void setRight(Node right) {
        Right = right;
    }

    public boolean isLeafNode() {
        return isLeafNode;
    }

    public void setIsLeafNode(boolean leafNode) {
        isLeafNode = leafNode;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    private int frequency;
}
