package com.company.Sorting.Quicksort;

import com.company.Sorting.InsertionSort;

public class Quicksort {

    //Runs in O(n*log*n) time average case and O(n^2) worst-case.

    /* Pseudo code

        Sort array A = QuickSort(A, 1, A.Length)

        QuickSort(A, p, r)

            if p < r
                q = Partition
                Quicksort(A, p, q - 1)
                Quicksort(A, q + 1, r)

        Partition(A, p, r)

            x = A[r]
            i = p - 1

            for j = p to r - 1
                if A[j] <= x
                    i++;
                    swap A[i] with A[j]
            swap A[i + 1] with A[r]
            return i + 1
     */

    public static int[] Sort(int[] input, int pivot, int determinator)
    {
        if(pivot < determinator)
        {
           int q = Partition(input, pivot, determinator);

           Sort(input, pivot , q - 1);
           Sort(input, q + 1, determinator);
        }

        return input;
    }

    public static int[] SortMedianPartition(int[] input, int pivot, int determinator)
    {
        if(pivot < determinator)
        {
            int q = MedianPartition(input, pivot, determinator);

            Sort(input, pivot , q - 1);
            Sort(input, q + 1, determinator);
        }

        return input;
    }

    public static int MedianPartition(int[] input, int pivot, int determinator)
    {
        int medianIndex = 0;

        int lowest = input[pivot];
        int highest = input[determinator];
        int middle = input[(determinator + pivot) / 2];

        int median = ReturnMedian(lowest, highest, middle);

        medianIndex = median == lowest ? pivot : median == highest ? determinator : middle;

        int tempSwap = input[determinator];
        input[determinator] = input[medianIndex];
        input[medianIndex] = tempSwap;

        int x = input[determinator];

        int j = pivot;

        for(int i = pivot; i < determinator; i++)
        {
            if(input[i] <= x )//&& i % 2 == pivot % 2) <-- If we want the array to be sorted into two equally large partition when the input is filled with the same elements
            {
                //Swap
                int temp = input[i];
                input[i] = input[j];
                input[j] = temp;

                //Increment lower bound
                j++;
            }
        }
        input[determinator] = input[j];
        input[j] = x;

        return j;
    }

    public static int Partition(int[] input, int pivot, int determinator)
    {
        int x = input[determinator];

        int j = pivot;

        for(int i = pivot; i < determinator; i++)
        {
            if(input[i] <= x )//&& i % 2 == pivot % 2) <-- If we want the array to be sorted into two equally large partition when the input is filled with the same elements
            {
                //Swap
                int temp = input[i];
                input[i] = input[j];
                input[j] = temp;

                //Increment lower bound
                j++;
            }
        }
        input[determinator] = input[j];
        input[j] = x;

        return j;
    }

    public static int[] SortWithMark(int[] input, int pivot, int determinator)
    {
        if(pivot < determinator)
        {
            PartitionMark q = PartitionMark(input, pivot, determinator);

            SortWithMark(input, pivot, q.getQ());
            SortWithMark(input, q.getK() + 1, determinator);
        }

        return input;
    }


    public static PartitionMark PartitionMark(int[] input, int pivot, int determinator)
    {
        int x = input[determinator];

        //Swap our right element x with the pivot element to start of the equal section
        int temp = input[determinator];
        input[determinator] = input[pivot];
        input[pivot] = temp;

        int i = pivot - 1;
        int k = pivot;

        for(int j = pivot + 1; j <= determinator; j++)
        {
            if(input[j] < x)
            {
                //Swap with the last element of lower
                int tempEqual = input[i + 1];
                input[i + 1] = input[j];
                input[j] = tempEqual;

                int allocatedEqual = input[j];
                input[j] = input[k + 1];
                input[k + 1] = allocatedEqual;

                i++;
                k++;
            }
            else if(input[j] == x)
            {
                int allocatedEqual = input[j];
                input[j] = input[k + 1];
                input[k + 1] = allocatedEqual;
                k++;
            }
        }

        return new PartitionMark(i, k);
    }

    private static int ReturnMedian(int a1, int a2, int a3)
    {
        if(a1 < a2 && a1 < a3)
        {
            return a2 < a3 ? a2 : a3;
        }
        if(a2 < a1 && a2 < a3)
        {
            return a1 < a3 ? a1 : a3;
        }
        if(a3 < a1 && a3 < a2)
        {
            return a1 < a2 ? a1 : a2;
        }

        return a2;
    }
}
