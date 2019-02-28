package com.company.DataStructures.PriorityQue;

import com.company.DataStructures.Heap.Element;

public class PQHeap implements PQ {

    private Element[] heap;
    private int _heapSize = 0;

    public PQHeap(int heapSize) {
        heap = new Element[heapSize];
    }

    /**
     * Return the element with the lowest value.
     *
     * Running-Time --> O(log*n)
     *
     * @return Element
     */
    @Override
    public Element extractMin() {

        //Check if heapSize is below 1, if so throw a HeapUnderflowException();
        if(_heapSize < 1)
        {
            //Error
            return null;
        }

        //Extract min element
        Element min = heap[0];

        //Swap the element in the last index to the front of the array or to the front of the priority que
        heap[0] = heap[_heapSize - 1];

        //Decrement heapSize after extraction
        _heapSize--;

        //Min-Heapify
        MinHeapify(0);

        return min;
    }

    /**
     * Insert the element e into the heap, and run the min-heapify procedure
     *
     * Running-Time --> O(log*n)
     *
     * @param e
     */
    @Override
    public void insert(Element e) {

        //Insert e into the last index of the heap.
        int i = _heapSize;

        //Insert e into the last index
        heap[i] = e;

        //As long as the index is above the root, namely 0, and the parent's key at our respective index is lower than our key, then child with parent
        while (i > 0 && (heap[Parent(i)].getKey() > heap[i].getKey()))
        {
            //Swap procedure
            Element temp = heap[Parent(i)];
            heap[Parent(i)] = heap[i];
            heap[i] = temp;

            //Set the index of interest to the index of the parent we swapped
            i = Parent(i);
        }

        //Increment heapSize
        _heapSize++;
    }

    private void MinHeapify(int i)
    {
        //Get index of left and right child respective to i
        int lIndex = Left(i);
        int rIndex = Right(i);

        int smallestIndex = i;
        //As long as lIndex is greater than the size of the heap and
        //lChild is less than it's parent make lChild smallestIndesx else make parent
        //smallestIndex.
        if(lIndex <= _heapSize && heap[lIndex].getKey() < heap[i].getKey())
        {
            smallestIndex = lIndex;
        }
        //Do the same challenge on the rightIndex
        if(rIndex <= _heapSize && heap[rIndex].getKey() < heap[smallestIndex].getKey())
        {
            smallestIndex = rIndex;
        }

        //If the smallest is not the parent swap parent and the smallest child
        if(i != smallestIndex)
        {
            Element temp = heap[i];
            heap[i] = heap[smallestIndex];
            heap[smallestIndex] = temp;

            //Since we made a swap we might have messed up the heap structure again.
            //Thus we call Min-Heapify yet again to ensure that the new parent is also in order
            MinHeapify(smallestIndex);
        }
    }

    /**
     * Returns the respective parent index of i
     * @param index
     * @return parentIndex
     */
    private int Parent(int index)
    {
        return index / 2;
    }

    /**
     * Return the index of the parentIndex's respective left child
     * @param parentIndex
     * @return
     */
    private int Left(int parentIndex)
    {
        return parentIndex == 0 ? 1 : parentIndex * 2;
    }

    /**
     * Return the index of the parentIndex's respective right child
     * @param parentIndex
     * @return
     */
    private int Right(int parentIndex)
    {
        return parentIndex == 0 ? 2 : parentIndex * 2 + 1;
    }
}
