package com.company.Compression;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ByteCounter {

    public void readThatFileAsBytes(String filePath)
    {
        int[] numberOfDistinctBytes = new int[256];

        try
        {
            FileInputStream fin = new FileInputStream(filePath);

            int b = fin.read();
            while(b != -1)
            {
                numberOfDistinctBytes[b]++;
                b = fin.read();
            }
        }
        catch (Exception ex)
        {
            ex.getMessage();
        }

        System.out.println("Number of different bytes are: ");
        for(int i = 0; i < numberOfDistinctBytes.length; i++)
        {
            System.out.println("Byte " + i + ": " + numberOfDistinctBytes[i]);
        }
    }

    public void readThatFileAsBits(String filePath)
    {
        try
        {
            FileInputStream fin = new FileInputStream(filePath);

            BitInputStream bis = new BitInputStream(fin);

            int b = bis.readBit();
            while(b != -1)
            {
                System.out.println(b);
                b = bis.readBit();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readThatFileAs4Bytes(String filePath)
    {
        try
        {
            FileInputStream fin = new FileInputStream(filePath);

            BitInputStream bis = new BitInputStream(fin);

            int b = bis.readInt();
            while(b != -1)
            {
                System.out.println(b);
                b = bis.readInt();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
