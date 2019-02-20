package com.company.Sorting;

public class MergeSort
{
    public static int[] Sort(int[] input)
    {
        return Sort(input, 0, input.length - 1);
    }

    private static int[] Sort(int[] input, int lowIndex, int highIndex)
    {
        if(lowIndex != highIndex)
        {
            int middleIndex = (highIndex + lowIndex) / 2;

            int[] lowerInput = Sort(input, lowIndex, middleIndex);
            int[] upperInput = Sort(input, middleIndex + 1, highIndex);

            int[] mergedArrays = MergeArrays(lowerInput, upperInput);

            return mergedArrays;
        }

        int[] newArray = new int[1];
        newArray[0] = input[lowIndex];

        return newArray;
    }

    private static int[] MergeArrays(int[] lowerInput, int[] upperInput)
    {
        int[] mergedArray = new int[lowerInput.length + upperInput.length];

        int lowerIndex = 0;
        int upperIndex = 0;

        for(int i = 0; i < mergedArray.length; i++)
        {
            boolean lowerHasBeenIterated = false;
            boolean upperHasBeenIterated = false;

            if(lowerIndex > lowerInput.length - 1)
            {
                lowerHasBeenIterated = true;
            }

            if(upperIndex > upperInput.length -1)
            {
                upperHasBeenIterated = true;
            }

            if(!lowerHasBeenIterated)
            {
                if(!upperHasBeenIterated)
                {
                    if(lowerInput[lowerIndex] <= upperInput[upperIndex])
                    {
                        mergedArray[i] = lowerInput[lowerIndex];
                        lowerIndex++;
                    }
                    else
                    {
                        mergedArray[i] = upperInput[upperIndex];
                        upperIndex++;
                        //Count inversions
                    }
                }
                else
                {
                    //Do lower
                    mergedArray[i] = lowerInput[lowerIndex];
                    lowerIndex++;
                }
            }
            else
            {
                //Do upper;
                mergedArray[i] = upperInput[upperIndex];

                upperIndex++;
            }
        }

        return mergedArray;
    }
}
