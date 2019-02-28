package com.company.DataStructures.PriorityQue;

import com.company.DataStructures.Heap.Element;

public interface PQ {
    public Element extractMin();
    public void insert(Element e);
}
