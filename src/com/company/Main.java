package com.company;
import com.company.ArrayGeneration.ArrayGenerater;
import com.company.ArrayGeneration.ArrayType;
import com.company.Compression.ByteCounter;
import com.company.Compression.HoffmanEncoding;
import com.company.DataStructures.Heap.Element;
import com.company.DataStructures.Heap.Heap;
import com.company.DataStructures.Heap.HeapType;
import com.company.DictBinTree.DictBinTree;
import com.company.Helpers.Printer;
import com.company.Sorting.CountingSort;
import com.company.Sorting.Quicksort.Quicksort;

public class Main {

    public static void main(String[] args) {


        Element[] heap = new Element[9];


        heap[0] = new Element(2, null);
        heap[1] = new Element(1, null);
        heap[2] = new Element(5, null);
        heap[3] = new Element(4, null);
        heap[4] = new Element(8, null);
        heap[5] = new Element(6, null);
        heap[6] = new Element(7, null);
        heap[7] = new Element(9, null);
        heap[8] = new Element(3, null);

        Heap realHeap = new Heap(HeapType.Max, heap);

        Element[] buildHeap = realHeap.GetHeapSnapshot();

        for(int i = 0; i < buildHeap.length; i++)
        {
            System.out.println(buildHeap[i].getKey());
        }



        //HoffmanEncoding encoding = new HoffmanEncoding();

        //encoding.Encode("/Users/ulriksandberg/Desktop/download.jpg", "/Users/ulriksandberg/Desktop/output.txt");
        //encoding.Decode("/Users/ulriksandberg/Desktop/output.txt", "/Users/ulriksandberg/Desktop/decoded.jpg");
    }
}
