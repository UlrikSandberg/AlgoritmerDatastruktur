package com.company;
import com.company.ArrayGeneration.ArrayGenerater;
import com.company.ArrayGeneration.ArrayType;
import com.company.DataStructures.Heap.Element;
import com.company.DataStructures.Heap.Heap;
import com.company.DataStructures.Heap.HeapType;
import com.company.Helpers.Printer;
import com.company.Sorting.CountingSort;
import com.company.Sorting.Quicksort.Quicksort;

public class Main {

    public static void main(String[] args) {

        var array = ArrayGenerater.GenerateArray(10, ArrayType.Random);

        Printer.PrintArray(array);

        System.out.println("=======================");

        var heap = new Heap(array.length, HeapType.Min);

        for(int i = 0; i < array.length; i++)
        {
            heap.Insert(new Element(i, null));
        }

        for(int i = 0; i < array.length; i++)
        {
            System.out.println(heap.ExtractFirst().getKey());
        }
    }
}
