package com.company.DataStructures.BinarySearchTree;

public class Tree<TKey, TData> {

    private Node root;

    public Tree()
    {

    }


    public void Insert(TKey key, TData data)
    {

    }

    public TData Search(TKey key)
    {
        return null;
    }

    public Node Predecessor(Node node)
    {
        //First check if there node has an non-empty left child
        if(node.LeftChild() != null)
        {
            //Since it does not have a non-empty left child find the maximum of the
            //left childs subtree.
            return Maximum(node.LeftChild());
        }
        else
        {
            //Seeing as we have an empty left sub-tree we will have to traverse the parents until we make a right turn.
            //Because when turn right we will have hit the first parent which will have been bigger.
            var leftNode = node;
            var parent = node.Parent();

            //This means, seeing parent is not null and the node are at was the parents leftChild
            //If we traverse all the way to the root of the tree we will return null seeing as we started at the smallest element
            while(parent != null && leftNode == parent.LeftChild())
            {
                //Go one height up the tree and make the old parent the leftChild and make the parent the old parents parent.
                leftNode = parent;
                parent = leftNode.Parent();
            }
            return parent;
        }
    }

    public Node Successor(Node node)
    {
        //First check if the nodes has a non-empty right child
        if(node.RightChild() != null)
        {
            //Since it does not have a non-empty right child find the minimum of the right subtre.
            return Minimum(node.RightChild());
        }
        else
        {
            //Seeing as we have an empty left sub-tree we will have to traverse the parents until we make a left turn.
            //Because when we turn left we will have hit the first parent which will have been less.
            var rightNode = node;
            var parent = node.Parent();

            //This means that, seeing parent is not null and the node we are at was the parents rightchild
            //If we traverse all the way to the root of the tree we will return null seing as we started at the biggest element
            while(parent != null && rightNode == parent.RightChild())
            {
                //Go one height up the tree make the old parent the rightNode and make the parent the old parents parent
                rightNode = parent;
                parent = rightNode.Parent();
            }
            return parent;
        }
    }

    private Node Maximum(Node node)
    {
        return null;
    }

    private Node Minimum(Node node)
    {
        return null;
    }


    /*
     * Key defines the starting node of the algorithm
     * the points from which we will print the range
     *
     * a = range lower bound
     * b = range upper bound
     *
    public void RBEnumerate(Node node, TKey a, TKey b)
    {
        //Check if the key is within the range
        //and print if so
        if(a <= node.Key() && node.Key() <= b)
        {
            System.out.println(node.Key());
        }

        //Seeing as a is below or equal to the node.Key this means
        //that all nodes in the left subtree of the node might be within range
        //so recursively traverse them and print for each
        if(a <= node.Key() && node.LeftChild() != null)
        {
            RBEnumerate(node.LeftChild(), a, b);
        }

        //Seeing as node.key is below upper range b this means
        //that all nodes in the right subtree of the node might be
        //within range so recursibely traverse them and print for
        //each case which fullfill the case.
        if(node.Key() <= b && node.RightChild() != null)
        {
            RBEnumerate(node.RightChild(), a, b);
        }
    }*/
}
