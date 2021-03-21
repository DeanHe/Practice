package binarySearch;

import java.util.*;
/*
Given an array, find two numbers in the array and their sum is closest to the target value but does not exceed the target value, return their sum.
*/
public class ClosestTargetValue {
	/**
     * @param target: the target
     * @param array: an array
     * @return: the closest value
     */
    public int closestTargetValue(int target, int[] array) {
        // Write your code here
        Arrays.sort(array);
        int i = 0, j = array.length - 1;
        int closest = Integer.MIN_VALUE;
        while(i < j){
            if(array[i] + array[j] < target){
                closest = Math.max(closest, array[i] + array[j]);
                i++;
            } else if(array[i] + array[j] == target){
                closest = array[i] + array[j];
                return closest;
            } else {
                j--;
            }
        }
        if(closest == Integer.MIN_VALUE){
            return -1;
        } else {
            return closest;
        }
    }
}
