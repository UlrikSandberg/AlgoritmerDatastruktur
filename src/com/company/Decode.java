package com.company;

import com.company.Compression.HoffmanEncoding;

public class Decode {

    public static void main(String[] args)
    {
        if(args.length > 1)
        {
            HoffmanEncoding hoffmanEncoding = new HoffmanEncoding();
            hoffmanEncoding.Decode(args[0], args[1]);
        }
        else
        {
            System.out.println("Requires exactly two arguments --> " + args.length + " is given");
        }
    }
}
