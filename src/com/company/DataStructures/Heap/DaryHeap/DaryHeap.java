package com.company.DataStructures.Heap.DaryHeap;

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


    private IndexRange ChildrenIndexRange(int i)
    {
        return null;
    }

    private int Parent(int i)
    {
        return 0;
    }

    private int Left(int i)
    {
        return 0;
    }

    private int Right(int i)
    {
        return 0;
    }
}
