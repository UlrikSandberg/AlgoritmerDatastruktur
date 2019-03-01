package com.company.DataStructures.Heap.DaryHeap;

public class IndexRange {

    private int Left;
    private int Right;

    private int[] IndexRange;

    public int getLeft() {
        return Left;
    }

    public int getRight() {
        return Right;
    }

    public int[] getIndexRange() {
        return IndexRange;
    }

    public IndexRange(int left, int right) {
        Left = left;
        Right = right;

        IndexRange = new int[right + 1 - left];

        for(int i = left; i < right +1; i++)
        {
            IndexRange[i - left] = i;
        }
    }
}
