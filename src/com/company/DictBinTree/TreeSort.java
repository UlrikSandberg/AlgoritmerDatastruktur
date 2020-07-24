package com.company.DictBinTree;

import java.util.Scanner;

public class TreeSort {

    public static void main(String[] args) {

        DictBinTree tree = new DictBinTree();

        int n = 0;
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            tree.insert(sc.nextInt());
        }

        int[] sortedOutput = tree.orderedTraversal();

        for(int i = 0; i < sortedOutput.length; i++)
        {
            System.out.println(sortedOutput[i]);
        }
    }

    public static class DictBinTree implements Dict
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

            inorder_Tree_Walk(0, orderedTrav, Root);

            return orderedTrav;
        }

        private int inorder_Tree_Walk(int index, int[] orderedArr, Node x)
        {
            if(x != null)
            {
                index = inorder_Tree_Walk(index, orderedArr, x.getLeftChild());
                orderedArr[index] = x.getKey();
                index = inorder_Tree_Walk(index+1, orderedArr, x.getRightChild());
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

    public static class Node {

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

    interface Dict {
        void insert(int k);
        int[] orderedTraversal();
        boolean search(int k);
    }
}
