package com.company;
import com.company.Algorithms.MergeSortedLists;
import com.company.ArrayGeneration.ArrayGenerater;
import com.company.ArrayGeneration.ArrayType;
import com.company.DataStructures.Heap.Element;
import com.company.DataStructures.Heap.Heap;
import com.company.DataStructures.Heap.HeapType;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here


        Element[] arr1 = ConvertToElementArr(ArrayGenerater.GenerateArray(10, ArrayType.Sorted));

        Element[] arr2 = ConvertToElementArr(ArrayGenerater.GenerateArray(15, ArrayType.Sorted));

        Element[] arr3 = ConvertToElementArr(ArrayGenerater.GenerateArray(20, ArrayType.Sorted));

        MergeSortedLists.Merge(arr1, arr2, arr3);


    }

    private static Element[] ConvertToElementArr(int[] arr)
    {
        Element[] nArr = new Element[arr.length];

        for(int i = 0; i < arr.length; i++)
        {
            nArr[i] = new Element(arr[i], null);
        }

        return nArr;
    }
}
