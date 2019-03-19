package com.company.DataStructures.Heap;

public class Heap {

    protected Element[] heap;
    protected int _heapSize = 0;
    protected HeapType heapType;
    private boolean isMinHeap;

    public Heap(int heapSize, HeapType heapType)
    {
        this.heapType = heapType;
        heap = new Element[heapSize];

        isMinHeap = heapType.equals(HeapType.Min) ? true : false;
    }

    public Heap(HeapType heapType, Element[] inputArray)
    {
        this.heapType = heapType;
        isMinHeap = heapType.equals(HeapType.Min) ? true : false;
        Build(inputArray);
    }

    /**
     * Build a min or max heap accordingly to the specified HeapType.
     *
     * Running time --> O(n)
     *
     * @param input
     */
    public void Build(Element[] input)
    {
        //Set heap equal to the new heap to be build
        heap = input;
        _heapSize = heap.length;

        for(int i = (heap.length - 1) / 2; i >= 0; i--)
        {
            if(isMinHeap) MinHeapify(i); else MaxHeapify(i);
        }
    }

    public Element[] GetHeapSnapshot()
    {
        return heap;
    }


    /**
     * Insert the element e into the heap, and run the min-heapify procedure
     *
     * Running-Time --> O(log*n)
     *
     * @param e
     */
    public void Insert(Element e) {

        //Insert e into the last index of the heap.
        int i = _heapSize;

        //Insert e into the last index
        heap[i] = e;

        var parentI = Parent(i);

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

    /**
     * Return the first element in the heap
     *
     * if(HeapType == HeapType.Min) return minimum value
     *
     * if(HeapType == HeapType.Max) return maximum value
     *
     * Running-Time --> O(log*n)
     *
     * @return Element
     */
    public Element ExtractFirst() {

        //Check if heapSize is below 1, if so throw a HeapUnderflowException();
        if(_heapSize < 1)
        {
            //throw new HeapUnderflowException();
        }

        //Extract min element
        Element first = heap[0];

        //Swap the element in the last index to the front of the array or to the front of the priority que
        heap[0] = heap[_heapSize - 1];

        //Decrement heapSize after extraction
        _heapSize--;

        Heapify(0);

        return first;
    }

    private void Heapify(int i)
    {
        if(heapType.equals(HeapType.Min)) MinHeapify(i); else MaxHeapify(i);
    }

    /**
     * Performs a max heapify procedure to repair the heap structure from the respective index.
     * @param i
     */
    private void MaxHeapify(int i)
    {
        //Get index of left and right child respective to i
        int lIndex = Left(i);
        int rIndex = Right(i);

        int largestIndex = i;
        //As long as lIndex is not greater than the size of the heap and
        //lChild is greater than it's parent make parent largest else
        //make leftChild largest
        if(lIndex <= _heapSize && heap[lIndex].getKey() > heap[i].getKey())
        {
            largestIndex = lIndex;
        }
        //Do the same challenge on the rightIndex
        if(rIndex <= _heapSize && heap[rIndex].getKey() > heap[largestIndex].getKey())
        {
            largestIndex = rIndex;
        }

        //If largest is not the parent swap parent and the largest child
        if(i != largestIndex)
        {
            Element temp = heap[i];
            heap[i] = heap[largestIndex];
            heap[largestIndex] = temp;

            //Since we made a swap we might have messed up the heap structure again.
            //Thus we call Max-Heapify yet again to ensure that the new parent is also in order
            MaxHeapify(largestIndex);
        }
    }

    /**
     * Performs a min heapify on the heap datastructure to repair the heap structure from the respective index
     * @param i
     */
    private void MinHeapify(int i)
    {
        //Get index of left and right child respective to i
        int lIndex = Left(i);
        int rIndex = Right(i);

        int smallestIndex = i;
        //As long as lIndex is greater than the size of the heap and
        //lChild is less than it's parent make lChild smallestIndesx else make parent
        //smallestIndex.
        if(lIndex <= _heapSize - 1 && heap[lIndex].getKey() < heap[i].getKey())
        {
            smallestIndex = lIndex;
        }
        //Do the same challenge on the rightIndex
        if(rIndex <= _heapSize - 1 && heap[rIndex].getKey() < heap[smallestIndex].getKey())
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
        return (index - 1) / 2;
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
