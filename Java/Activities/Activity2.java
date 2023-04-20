package Activities;

import org.w3c.dom.ls.LSOutput;

import java.util.*;
public class Activity2 {
    public static void main(String[] args) {
        int searchNum = 10;
        int fixedSum = 30;
        int arr[] = {10, 77, 10, 54, -11, 10};
        System.out.println("original array is:   "+Arrays.toString(arr));
        System.out.println("Result:  "+sumis30(arr,searchNum,fixedSum));

    }

    public static boolean sumis30(int[] numbers, int searchNum, int fixedSum) {
        int temp_sum = 0;
        for (int num : numbers) {
            //if value is 10
            if (num == searchNum) {
                temp_sum += searchNum;
            }
//when sum met 30
            if (temp_sum > fixedSum) {
                break;
            }


        }
        //return true if sum is 30
        return temp_sum == fixedSum;

    }
}

