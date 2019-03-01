package com.company.Sorting;

import com.company.ArrayGeneration.ArrayGenerater;
import com.company.ArrayGeneration.ArrayType;

public class CountingSort {

    public static int[] Sort(int[] input, int k)
    {
        //First start by creating a counting array.
        int[] countingArray = ArrayGenerater.GenerateArray(k + 1, ArrayType.Binary);

        //Count number of occurences of numbers in the input array up till k.
        for(int i = 0; i < input.length; i++)
        {
            countingArray[input[i]]++;
        }

        for(int i = 1; i < countingArray.length; i++)
        {
            countingArray[i] += countingArray[i -1];
        }

        //Construct an output array
        int[] output = new int[input.length];

        //Iterate the input array
        for(int i = 0; i < input.length; i++)
        {
            //For each number in the input array, read at which index the number should be in the output array from the countingArray
            output[countingArray[input[i]] - 1] = input[i];
            countingArray[input[i]] --;
        }
        return output;
    }
}
