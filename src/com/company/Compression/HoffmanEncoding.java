package com.company.Compression;

import com.company.DataStructures.Heap.Element;
import com.company.DataStructures.PriorityQue.PQ;
import com.company.DataStructures.PriorityQue.PQHeap;

import java.io.*;
import java.util.ArrayList;

public class HoffmanEncoding {

    //************* ENCODE SECTION START ****************

    /**
     *
     * Encode a file into huffman code
     *
     * @param filePath respective file to read from
     * @param outputFilePath respective file to write to
     */
    public void Encode(String filePath, String outputFilePath)
    {
        //Count all the occurences of each byte in the file
        int[] byteOccurences = readFileBytes(filePath);

        //Insert each byteOccurence into the priorityQue.
        PQ priorityQue = initializePriorityQue(byteOccurences);

        //Build a huffman code tree from the priorityQue and get the root of the tree for traversing
        Node root = BuildHuffmanTree(priorityQue);

        //Traverse the huffman tree and generate a huffmanCode table.
        String[] hoffmanCodes = GenerateHoffmanCodeTable(root);

        //Used said huffmanCode table to read the file one extra time and write the
        //huffmancodes as well as the huffman code table to the output file
        writeToOutputFile(byteOccurences, hoffmanCodes, filePath, outputFilePath);
    }

    /**
     * Recursive helper-method for generating a huffman code table, the recursion
     * @param root - root of the huffmanCodeTree
     * @return huffman code table in the form of a string array
     */
    private String[] GenerateHoffmanCodeTable(Node root)
    {
        String[] hoffmanCodes = new String[256];

        //Call the core recursive method - pass the hoffmane code table array,
        //and initialize an arrayList to keep the current path at all times
        //through the recursive calls
        inorder_Tree_Walk(hoffmanCodes, root, new ArrayList<>());

        return hoffmanCodes;
    }

    /**
     *
     * Traverse the huffman code tree recursively and store the code in the huffman code array
     *
     * @param huffmanCodes
     * @param x
     * @param path
     */
    private void inorder_Tree_Walk(String[] huffmanCodes, Node x, ArrayList<String> path)
    {
        //If x is null we have reached the end and should go back in the recursive-call stack.
        if(x != null)
        {
            //If x is not null it means we can go down that path and thus we are not at a leaf node
            //since we will walk left if we can, we thus add '0' to our path.
            if(x.getLeft() != null)
            {
                path.add("0");
            }

            //Recurse to the left
            inorder_Tree_Walk(huffmanCodes, x.getLeft(), path);

            //If we have reached a leaf node write the huffmanCode
            if(x.isLeafNode())
            {
                //The node contain index information for the original byte
                //If the code is 10 then this path, codes for the respective byte at index 10
                huffmanCodes[x.getCode()] = GenerateCode(path);
            }

            //If x is not null it means we can go down that path and thus we are not at a leaf node
            //since we will walk right if we can, we thus add '1' to our path.
            if(x.getRight() != null)
            {
                path.add("1");
            }

            //Recursive right
            inorder_Tree_Walk(huffmanCodes, x.getRight(), path);

            //We couldn't go right nor left we should backtrack and thus pop the last path from the stack
            if(path.size() > 0)
            {
                path.remove(path.size() - 1);
            }
        }
    }

    private String GenerateCode(ArrayList<String> path)
    {
        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < path.size(); i++)
        {
            builder.append(path.get(i));
        }

        return builder.toString();
    }

    /**
     * Read a specified file and returns the number of distinct bytes.
     * @param filePath of the file to read from
     * @return int[], with index'es representing bytes and the value on each index representing that respective bytes count
     */
    private int[] readFileBytes(String filePath)
    {
        //Initialize a array to hold the each byte and its occurences
        int[] numberOfDistinctBytes = new int[256];

        try
        {
            //FileInput stream so that we may read each byte from the file
            FileInputStream fin = new FileInputStream(filePath);

            //fin.read() gives us the next byte
            int b = fin.read();
            //If b is equal to -1 we have reached the end of the file and the loop should terminate
            while(b != -1)
            {
                //Count the respective byte b up by one.
                numberOfDistinctBytes[b]++;
                //Try read the next byte, to keep the while loop running
                b = fin.read();
            }
        }
        catch (Exception ex)
        {
            ex.getMessage();
        }

        return numberOfDistinctBytes;
    }

    /**
     *
     * With the a generated hoffmanCode table read the file one last time, but this time write the corrosponding
     * hoffman codes to the output file instead of original file bytes.
     *
     * @param byteOccurences from the original file
     * @param hoffmanCodes hoffman codes generate from the byteoccurences
     * @param readFile file to read from
     * @param writeFile file to write to
     */
    private void writeToOutputFile(int[] byteOccurences, String[] hoffmanCodes, String readFile, String writeFile)
    {
        try
        {
            //FileInputStream to read the file one byte at a time
            FileInputStream fin = new FileInputStream(readFile);
            //BitOutputStream to write bits one at a time to the output file
            BitOutputStream output = new BitOutputStream(new FileOutputStream(writeFile));

            //Since the decoding algorithm have to backtrack, it will need information about the original files byteOccurences
            //in the beginning of the new file, write the a int for each occurences of that index's respective byte
            for(int i = 0; i < byteOccurences.length; i++)
            {
                output.writeInt(byteOccurences[i]);
            }

            //Read first byte of the file we want to encode
            int b = fin.read();

            //If the byte is -1 we have reached the end and the loop should terminate
            while(b != -1)
            {
                //Fetch respective hoffman code on this bytes location
                char[] hoffmanCode = hoffmanCodes[b].toCharArray();


                //Write hoffmanCode to output file by traversing the char[] and writing 0 or 1 respectively
                for(int i = 0; i < hoffmanCode.length; i++)
                {
                    output.writeBit(hoffmanCode[i] == '0' ? 0 : 1);
                }

                //Read the next byte to keep the loop running
                b = fin.read();
            }

            //Close resources
            output.close();
            fin.close();
        }
        catch (Exception ex)
        {
            ex.getMessage();
        }
    }

    //************** ENCODE SECTION END **************
    //************************************************
    //************** DECODE SECTION START ************

    /**
     *
     * Decode a file, encoded with HoffmanEncoding.Encode(String filePath, String outputFilePath)
     *
     * @param encodedFile encodedFile to read from
     * @param decodedFile output file to write original file content into
     */
    public void Decode(String encodedFile, String decodedFile)
    {
        //Read byteoccurence represented in the original file
        int[] byteOccurences = decodeByteOccurencesTable(encodedFile);

        //Initialize a priorityQue and insert the values into the priorityQue
        PQ priorityQue = initializePriorityQue(byteOccurences);

        //Build a huffman code tree from the priorityQue and get the root of the tree for traversing
        Node root = BuildHuffmanTree(priorityQue);

        //Use the huffmanCode tree to make sense of the bits in the encoded file and
        //convert them to original byte to the outPutfile
        writeDecodedFile(root, encodedFile, decodedFile, totalNumberOfOccurences(byteOccurences));
    }

    /**
     * Read the first 256 bytes and regenerate the byteOccurences table from the original file
     * @param filePath encoded file to read from
     * @return
     */
    private int[] decodeByteOccurencesTable(String filePath)
    {
        int[] numberOfDistinctBytes = new int[256];

        try
        {
            //BitInputStream to read the first 256 ints
            BitInputStream inputStream = new BitInputStream(new FileInputStream(filePath));

            for(int i = 0; i < numberOfDistinctBytes.length; i++)
            {
                int b = inputStream.readInt();

                //The read int is the count from the original file
                numberOfDistinctBytes[i] = b;
            }
        }
        catch (Exception ex)
        {
            ex.getMessage();
        }

        //Return an exact copy of the byteOccurences table generate from the original file
        return numberOfDistinctBytes;
    }

    /**
     *
     * @param root
     * @param encodedFile
     * @param decodedFile
     * @param sumOfOccurences
     */
    public void writeDecodedFile(Node root, String encodedFile, String decodedFile, int sumOfOccurences)
    {
        //Used in connection with sumOfOccurences to make
        //sure that we account for the encoded file being
        //padded with bits to have a total number of bytes.
        int numberOfBytesWritten = 0;

        try
        {
            //BitInputStream to read the the encoded file bit by bit
            BitInputStream input = new BitInputStream(new FileInputStream(encodedFile));
            //FileOutputStream to write to the final decoded file
            FileOutputStream output = new FileOutputStream(decodedFile);

            //Since we have read byte occurences table, we thus have to traverse those first 256 ints of 32 bytes
            for(int i = 0; i < 256*32; i++)
            {
                input.readBit();
            }

            String path = "";

            //We start by reading the next bit after the byteOccurences table
            //if it is -1 we have read to the end of the file and if the numberOfBytesWritten
            //succeeds sumOfOccurences we are writing more than what was in the original file.
            int b = input.readBit();
            while(b != -1 && (numberOfBytesWritten < sumOfOccurences))
            {
                //We add the bit to the path which will be used to traverse the huffman tree given in the parameters
                path += b;

                //Private method to get the original code if the current path leads to one if not -1 is returned
                var code = getCode(path, root);
                //If the code is greater than -1 we have a found a code
                if(code > -1)
                {
                    //We have encountered a leaf on this path, and we should thus write the respective leaf code to the decoded file
                    output.write(code);
                    //Increment the number of bytes written to constantly keep track of the number of bytes we have written to the output file
                    numberOfBytesWritten++;

                    //After reaching a leaf begin a new path from the root
                    path = "";
                }

                //Read the next bit to keep the loop going
                b = input.readBit();
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Returns the total sum of all the bytes read from the original file
     * @param numberOfOccurences
     * @return
     */
    private int totalNumberOfOccurences(int[] numberOfOccurences)
    {
        int number = 0;

        for(int i = 0; i < numberOfOccurences.length; i++)
        {
            number += numberOfOccurences[i];
        }
        return number;
    }

    /**
     * Check if a path leads to a valid leaf from the respective root. If leaf is reached return the original byte
     * if not return -1
     * @param path
     * @param root
     * @return
     */
    private int getCode(String path, Node root)
    {
        //Convert string path to char array for iteration
        char[] charPath = path.toCharArray();

        //The root of the tree we want to traverse for code
        Node exploration = root;

        //For iteration to the length of the char array
        for(int i = 0; i < charPath.length; i++)
        {
            //Determine the nature of the char
            int bit = charPath[i] == '0' ? 0 : 1;

            //If the bit is a 0 set the node to keep exploring as the left child of the node.
            if(bit == 0)//Go right
            {
                if(exploration.getLeft() != null)
                {
                    exploration = exploration.getLeft();
                }
            }
            else //If the bit is 1 set the node to keep exploring as the right child of the node.
            {
                if(exploration.getRight() != null)
                {
                    exploration = exploration.getRight();
                }
            }
        }

        //We are done traversing the path, determine if the path led to a leaf-node
        if(exploration.isLeafNode())
        {
            return exploration.getCode();
        }

        //If no leaf was reached return -1 to indicate that this respective path is not done
        return -1;
    }

    //**************** DECODE SECTION END *************************************
    //*************************************************************************
    //**************** UTILITY METHODS USED BY BOTH ENCODE AND DECODE HERE ****

    /**
     * Initializes a priorityQue and inserts a node into the que foreach byte in the occurences array
     * @param byteOccurences
     * @return PQHeap with all the bytes as a element containing a node(Possibly an entire tree) as object
     */
    private PQ initializePriorityQue(int[] byteOccurences)
    {
        //Initialize an empty priority-que
        PQ priorityQue = new PQHeap(256);

        //foreach byte create a node
        for(int i = 0; i < byteOccurences.length; i++)
        {
            Node node = new Node();
            //Set the node is leaf to true
            node.setIsLeafNode(true);
            //Set the byte code this leaf is representing
            node.setCode(i);
            //Insert the node into the priority que with count of occurences as its priority in the que and the node(pssobile an entire tree as the value)
            priorityQue.insert(new Element(byteOccurences[i], node));
        }

        return priorityQue;
    }

    /**
     * From a priorityQue, build a huffman code tree bottom up.
     * @param priorityQue priority que initialized with nodes from a byteOccurences array
     * @return a single node representing the root of the final huffmanCode tree
     */
    private Node BuildHuffmanTree(PQ priorityQue)
    {
        int size = priorityQue.getHeapSize();

        //Iterate the priorityQue and extract two and insert one for each iteration. So we iterate n - 1 times
        for(int i = 0; i < size - 1; i++)
        {
            //Create a new node, this new node isLeaf property is now false as default
            Node n = new Node();

            //Extract the two lowest priorityQue elements
            Element x = priorityQue.extractMin();
            Element y = priorityQue.extractMin();

            //Set the two lowest priority elements as the children of our new node
            n.setLeft((Node)x.getData());
            n.setRight((Node)y.getData());

            //Sum the total frequencies from the extracted nodes and set that as the new nodes frequency
            n.setFrequency(x.getKey() + y.getKey());

            //Insert the new node which now contain the two extracted trees as its left and right child
            priorityQue.insert(new Element(n.getFrequency(), n));
        }

        //In the end extract the last remaining node which at this point is the root of the entire huffmanCode tree
        return (Node)priorityQue.extractMin().getData();
    }
}
