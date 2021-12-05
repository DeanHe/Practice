package array;

import java.util.ArrayList;
import java.util.List;

/*
Problem:
Given three integer arrays arr1, arr2 and arr3 sorted in strictly increasing order, return a sorted array of only the integers that appeared in all three arrays.



Example 1:

Input: arr1 = [1,2,3,4,5], arr2 = [1,2,5,7,9], arr3 = [1,3,4,5,8]
Output: [1,5]
Explanation: Only 1 and 5 appeared in the three arrays.


Constraints:

1 <= arr1.length, arr2.length, arr3.length <= 1000
1 <= arr1[i], arr2[i], arr3[i] <= 2000
 */
public class IntersectionOfThreeSortedArrays {
    public int[] arraysIntersection(int[] arr1, int[] arr2, int[] arr3){
        int[] tmp = arraysIntersection(arr1, arr2);
        int[] res = arraysIntersection(tmp, arr3);
        return res;
    }

    private int[] arraysIntersection(int[] arr1, int[] arr2){
        List<Integer> ls = new ArrayList<>();
        int i = 0, j = 0;
        while(i < arr1.length && j < arr2.length){
            if(arr1[i] == arr2[j]){
                i++;
                j++;
            } else if(arr1[i] < arr2[j]){
                i++;
            } else {
                j++;
            }
        }
        int[] res = ls.stream().mapToInt(x -> x).toArray();
        return res;
    }
}
