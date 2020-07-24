package com.company.Helpers;

public class Printer {

    public static void PrintArray(int[] arr)
    {
        for(int i = 0; i < arr.length; i++)
        {
            System.out.println(arr[i]);
        }
    }

    //http://sites.math.rutgers.edu/~ajl213/CLRS/CLRS.html

    public static void PrintHelperUrl()
    {
        System.out.println("http://sites.math.rutgers.edu/~ajl213/CLRS/CLRS.html");
        System.out.println("https://www.cs.usfca.edu/~galles/visualization/Algorithms.html");
    }

    public static int[] SwapIndex(int[] input, int i, int j)
    {
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;

        return input;
    }
}


