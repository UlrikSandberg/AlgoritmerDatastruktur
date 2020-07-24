package com.company.DictBinTree;

import com.company.Helpers.Printer;

import java.util.ArrayList;

public class DictBinTree implements Dict
{
    //The root
    private Node Root;
    private int size = 0;

    public DictBinTree()
    {
    }

    @Override
    public void insert(int k) {

        //The new key to be inserted into the tree
        Node nodeToInsert = new Node(k, null, null);

        //Variables for holding the respective node of interest during traversal
        Node y = null;
        Node x = Root;

        //Traverse the tree.
        while(x != null)
        {
            y = x;

            if(k < x.getKey())
            {
                x = x.getLeftChild();
            }
            else
            {
                x = x.getRightChild();
            }
        }

        if(y == null)
        {
            Root = nodeToInsert;
        }
        else if (k < y.getKey())
        {
            y.setLeftChild(nodeToInsert);
        }
        else
        {
            y.setRightChild(nodeToInsert);
        }
        size++;
    }

    @Override
    public int[] orderedTraversal() {

        int[] orderedTrav = new int[size];

        inorder_Tree_Walk(0, orderedTrav, Root, new ArrayList<String>());

        return orderedTrav;
    }

    private int inorder_Tree_Walk(int index, int[] orderedArr, Node x, ArrayList<String> path)
    {
        if(x != null)
        {
            if(x.getLeftChild() != null)
            {
                path.add("L");
            }
            index = inorder_Tree_Walk(index, orderedArr, x.getLeftChild(), path);

            StringBuilder p = new StringBuilder();
            //Print out the path to this node
            for(int i = 0; i < path.size(); i++)
            {
                p.append(path.get(i));
            }
            System.out.println("Key " + x.getKey() + ": " + p.toString());

            orderedArr[index] = x.getKey();

            if(x.getRightChild() != null)
            {
                path.add("R");
            }

            index = inorder_Tree_Walk(index+1, orderedArr, x.getRightChild(), path);
            if(path.size() > 0)
            {
                path.remove(path.size() -1);
            }
        }

        return index;
    }

    /**
     * Recursive tree-search helper method
     * @param k
     * @return
     */
    @Override
    public boolean search(int k) {

        return tree_Search(Root, k);
    }

    /**
     * Recursive treeSearch procedure
     * @param x
     * @param key
     * @return
     */
    private boolean tree_Search(Node x, int key)
    {
        //We have called a search on either an empty tree or recursively we have encountered a null pointer before reaching our goal return false
        if(x == null)
        {
            return false;
        }

        if(x.getKey() == key)
        {
            return true;
        }

        if(key < x.getKey())
        {
            return tree_Search(x.getLeftChild(), key);
        }
        else
        {
            return tree_Search(x.getRightChild(), key);
        }
    }
}
