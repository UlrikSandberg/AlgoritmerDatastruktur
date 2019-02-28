package com.company.Algorithms;

import com.company.DataStructures.Heap.Element;
import com.company.DataStructures.Heap.Heap;
import com.company.DataStructures.Heap.HeapType;

public class MergeSortedLists {


    //Not done

    /* Pseudo code / idea

    If we constrcut a min-heap from the heads of each k sorted list, we can construct a min heap in linear time

    Hereafter we will extract min-element i O(lg*k) time followed by an insertion into the array which the last min
    element original came from also lg*k. Thus we will make 2*lg(k), n times, in asymtotic notation this it
    the same a O(n*log(k))

     */

    public static Element[] Merge(Element[]... lists)
    {
        int totalSize = 0;
        //Construct min heap from --> O(lists.length)
        Element[] heads = new Element[lists.length];

        for(int i = 0; i < lists.length; i++)
        {
            heads[i] = lists[i][0];
            totalSize += lists[i].length;
        }

        Heap minHeap = new Heap(HeapType.Min, heads);

        //Make new Element array
        Element[] finalArr = new Element[totalSize];


        for(int i = 0; i < totalSize; i++)
        {

        }

        return new Element[0];
    }
}
