package com.company;

import com.company.Algorithms.SumOfXInArray;
import com.company.ArrayGeneration.ArrayGenerater;
import com.company.ArrayGeneration.ArrayType;
import com.company.CalculateCycles.CountInversions;
import com.company.CalculateCycles.Cycles;
import com.company.CalculateCycles.InversionCount;
import com.company.Helpers.ArrayPrinter;
import com.company.Searching.BinarySearch;
import com.company.Sorting.InsertionSort;
import com.company.Sorting.MergeSort;
import com.sun.scenario.effect.Merge;

import java.io.Console;

public class Main {

    public static void main(String[] args) {
	// write your code here

        CountInversions countInversions = new CountInversions();
        InversionCount iC = countInversions.Count(ArrayGenerater.GenerateArray(10, ArrayType.ReversedSorted));

        System.out.println(iC.getInversionCount());
    }
}
