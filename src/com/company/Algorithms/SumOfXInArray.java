package com.company.Algorithms;

import com.company.Searching.BinarySearch;

public class SumOfXInArray {

    public static boolean DetermineSumOfXInArray(int[] input, int x)
    {
        for(int i = 0; i < input.length; i++)
        {
            int counterInt = x - input[i];

            if(BinarySearch.Search(input, counterInt) != -1)
            {
                return true;
            }
        }

        return true;
    }

    public static int DetermineNumberOfSumsOfXInArray(int[] input, int x)
    {
        int numberOfSums = 0;

        for(int i = 0; i < input.length; i++)
        {
            int counterInt = x - input[i];

            if(BinarySearch.Search(input, counterInt) != -1)
            {
                numberOfSums++;
            }
        }

        return numberOfSums;
    }
}
