package com.company.Sorting;

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

    //Not done
    public static int PartitionMark(int[] input, int pivot, int determinator)
    {
        int x = input[determinator];

        int j = 0;
        int h = 1;

        for(int i = pivot; i < determinator; i++)
        {
            if(input[i] == x)
            {
                int temp = input[j + 1];
                input[j + 1] = input[i];
                input[i] = temp;
                j++;
                h++;
            }
            else
            {
                if(input[i] <= x)
                {
                    //Swap
                    int temp = input[i];
                    input[i] = input[j];
                    input[j] = temp;

                    //Increment lower bound
                    j++;
                    h++;
                }
            }
        }

        input[determinator] = input[h];
        input[h] = x;

        return j;
    }
}
