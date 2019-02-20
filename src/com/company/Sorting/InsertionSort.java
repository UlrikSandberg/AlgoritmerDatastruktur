package com.company.Sorting;

public class InsertionSort {

    public static int[] Sort(int[] input)
    {
        for(int i = 1; i < input.length; i++)
        {
            int key = input[i];
            int j = i - 1;

            while(j >= 0 && key < input[j])
            {
                input[j + 1] = input[j];
                j--;
            }
            input[j + 1] = key;
        }
        return input;
    }
}
