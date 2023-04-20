package Activities;

import java.util.Arrays;

public class Activity4 {

    public static void ascesort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && key < arr[j]) {
                arr[j + 1] = arr[j];
                --j;
            }
            arr[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        int[] org_arr={9,5,1,4,3};
        System.out.println("original array is:  "+ Arrays.toString(org_arr));
        ascesort(org_arr);
        System.out.println("Sorted array is: "+ Arrays.toString(org_arr));
    }
}
