package com.company;
import com.company.ArrayGeneration.ArrayGenerater;
import com.company.ArrayGeneration.ArrayType;
import com.company.Sorting.Quicksort.Quicksort;

public class Main {

    public static void main(String[] args) {

        int[] array = ArrayGenerater.GenerateArray(0, ArrayType.Custom, 3,4,1,5,7,4,4,5,6,5);//ArrayGenerater.GenerateArray(20, ArrayType.Shuffle);

        Quicksort.SortMedianPartition(array, 0, array.length -1);



    }
}
