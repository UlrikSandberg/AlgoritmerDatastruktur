package com.company.ArrayGeneration;

import java.util.Random;

public class ArrayGenerater {

    public static int[] GenerateArray(int size, ArrayType arrayType)
    {
        switch (arrayType)
        {
            case Random:
                return makeRandomArray(size);
            case Sorted:
                return makeSortedArray(size);
            case ReversedSorted:
                return makeReversedSortedArray(size);
            case Shuffle:
                int[] sortedArr = makeSortedArray(size);
                return ShuffleArray(sortedArr);
            case Binary:
                return makeBinaryArray(size);
        }

        return new int[0];
    }

    public static int[] ShuffleArray(int[] input)
    {
        int[] sortedArr = input;
        Random rn = new Random();
        for(int i = 0; i < sortedArr.length; i++)
        {
            int rnIndex = rn.nextInt(sortedArr.length);
            int temp = sortedArr[rnIndex];
            sortedArr[rnIndex] = sortedArr[i];
            sortedArr[i] = temp;
        }
        return sortedArr;
    }

    private static int[] makeRandomArray(int size)
    {
        int[] randomArray = new int[size];
        Random rn = new Random();
        for(int i = 0; i < size; i++)
        {
            randomArray[i] = rn.nextInt(size);
        }

        return randomArray;
    }

    private static int[] makeReversedSortedArray(int size)
    {
        int[] reversedSortedArray = new int[size];

        for(int i = 0; i < size; i++)
        {
            reversedSortedArray[i] = (size - 1) - i;
        }

        return reversedSortedArray;
    }

    private static int[] makeSortedArray(int size)
    {
        int[] sortedArray = new int[size];
        for(int i = 0; i < size; i++)
        {
            sortedArray[i] = i;
        }

        return sortedArray;
    }

    private static int[] makeBinaryArray(int size)
    {
        int[] binaryArr = new int[size];

        for(int i = 0; i < size; i++)
        {
            binaryArr[i] = 0;
        }

        return binaryArr;
    }
}
