package com.company.Searching;

public class BinarySearch {

    public static int Search(int[] searchArray, int key)
    {
        return Search(searchArray, key, 0, searchArray.length - 1);
    }

    private static int Search(int[] searchArray, int key, int low, int high)
    {
        int middle = (high + low) / 2;
        int middleValue = searchArray[middle];
        int scopeOfInterest = high-low;

        if(middleValue == key)
        {
            //We found the correct key
            return searchArray[middle];
        }

        if(scopeOfInterest < 2)
        {
            if(searchArray[high] == key)
            {
                return searchArray[high];
            }

            if(searchArray[low] == key)
            {
                return searchArray[low];
            }
            return -1;
        }

        //Since the key wasn't the middle most item, check if the value is higher or lower
        if(middleValue < key)
        {
            return Search(searchArray, key, middle, high);
        }
        else
        {
            return Search(searchArray, key, low, middle);
        }
    }
}
