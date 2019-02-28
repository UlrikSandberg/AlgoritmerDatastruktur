package com.company.CalculateCycles;

import com.company.ArrayGeneration.ArrayGenerater;
import com.company.ArrayGeneration.ArrayType;

public class Cycles {

    public static int CountCycles(int[] input)
    {
        int numberOfCycles = 0;
        int[] binaryArr = ArrayGenerater.GenerateArray(input.length, ArrayType.Binary);

        for(int index = 0; index < input.length; index++)
        {
            //Is on the correct place
            if(index == input[index])
            {
                binaryArr[index] = 1;
                numberOfCycles ++;
                continue;
            }

            if(binaryArr[index] == 1)
            {
                //we have looked at this index, skip;
                continue;
            }

            //The index is not ordered, count cycles
            boolean countingCycles = true;
            int startIndex = index;

            while(countingCycles)
            {
                int valueOfInterest = input[startIndex];

                if(binaryArr[valueOfInterest] == 1)
                {
                    //We have encountered a end of this cycle;
                    numberOfCycles++;
                    countingCycles = false;
                }

                binaryArr[startIndex] = 1;

                startIndex = valueOfInterest;
            }
        }
        return numberOfCycles;
    }
}
