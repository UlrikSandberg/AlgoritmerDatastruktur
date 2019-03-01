package com.company.DataStructures.Heap.DaryHeap;

import com.company.DataStructures.Heap.Element;
import com.company.DataStructures.Heap.HeapType;

public class DaryHeap {

    private int[] heap;
    private int _heapSize;
    private HeapType heapType;
    private int d;

    public DaryHeap(int dimensions, int heapSize, HeapType heapType)
    {
        d = dimensions;
        _heapSize = 0;
        heap = new int[heapSize];
        this.heapType = heapType;
    }

    /**
     * Running-time is log_d(n)
     * @param i
     */
    public void Insert(int i)
    {
        int j = _heapSize;

        heap[j] = i;

        int parent = Parent(j);

        while(j > 0 && heap[Parent(j)] < heap[j])
        {
            //Swap procedure
            int temp = heap[Parent(j)];
            heap[Parent(j)] = heap[j];
            heap[j] = temp;

            //Set the index of interest to the index of the parent we just swapped.
            j = Parent(j);
        }

        _heapSize++;

    }

    public int ExtractMax()
    {
        if(_heapSize < 1)
        {
            //Error
        }

        int max = heap[0];

        //Swap the element in the last index to the front of the array
        heap[0] = heap[_heapSize - 1];

        _heapSize--;

        MaxHeapify(0);

        return max;
    }

    private void MaxHeapify(int i)
    {
        //Get index range
        var ir = ChildrenIndexRange(i).getIndexRange();

        int largestIndex = i;

        //Iterate each children and
        for(int j = 0; j < ir.length; j++)
        {
            if(ir[j] <= _heapSize && heap[ir[j]] > heap[largestIndex])
            {
                largestIndex = ir[j];
            }
        }

        //If largest is not the parent swap parent and the largest child
        if(i != largestIndex)
        {
            int temp = heap[i];
            heap[i] = heap[largestIndex];
            heap[largestIndex] = temp;

            //Since we made a swap we might have messed up the heap structure again.
            //Thus we call Max-Heapify yet again to ensure that the new parent is also in order
            MaxHeapify(largestIndex);
        }
    }

    public void IncreaseKey(int index, int key)
    {
        if(heap[index] < key)
        {
            //Throw error
        }

        heap[index] = key;

        //Since the key was increased we know that the heap structure below the this index is fine, thus. We will only have to repair above.
        while(index > 0 && heap[Parent(index)] < heap[index])
        {
            //Swap procedure
            int temp = heap[Parent(index)];
            heap[Parent(index)] = heap[index];
            heap[index] = temp;

            //Set the index of interest to the index of the parent we just swapped.
            index = Parent(index);
        }
    }


    private IndexRange ChildrenIndexRange(int i)
    {
        return new IndexRange(Left(i), Right(i));
    }

    private int Parent(double i)
    {
        return i == 0 ? 0 : (int)((1.0/(double)d) * i - (1.0/(double)d));
    }

    private int Left(int i)
    {
        return (i * d + 1);
    }

    private int Right(int i)
    {
        return (i * d) + d;
    }

    public int[] GetSnapshot()
    {
        return heap;
    }
}
