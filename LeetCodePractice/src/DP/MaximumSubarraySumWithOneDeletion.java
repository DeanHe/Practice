package DP;
/*
Given an array of integers, return the maximum sum for a non-empty subarray (contiguous elements) with at most one element deletion. In other words,
you want to choose a subarray and optionally delete one element from it so that there is still at least one element left and the sum of the remaining elements is maximum possible.

Note that the subarray needs to be non-empty after deleting one element.

Example 1:

Input: arr = [1,-2,0,3]
Output: 4
Explanation: Because we can choose [1, -2, 0, 3] and drop -2, thus the subarray [1, 0, 3] becomes the maximum value.
Example 2:

Input: arr = [1,-2,-2,3]
Output: 3
Explanation: We just choose [3] and it's the maximum sum.
Example 3:

Input: arr = [-1,-1,-1,-1]
Output: -1
Explanation: The final subarray needs to be non-empty. You can't choose [-1] and delete -1 from it, then get an empty subarray to make the sum equals to 0.
 
Constraints:
1 <= arr.length <= 10^5
-10^4 <= arr[i] <= 10^4
*/
public class MaximumSubarraySumWithOneDeletion {
    public int maximumSum(int[] arr) {
    	if(arr == null || arr.length == 0){
    		return -1;
    	}
        int res = arr[0];
        int len = arr.length;
        int[] maxEndHere = new int[len];
        maxEndHere[0] = arr[0];
        for(int i = 1; i < len; i++){
        	if(maxEndHere[i - 1] > 0){
        		maxEndHere[i] = arr[i] + maxEndHere[i - 1];
        	} else {
        		maxEndHere[i] = arr[i];
        	}
        	res = Math.max(res, maxEndHere[i]);
        }
        int[] maxStartHere = new int[len];
        maxStartHere[len - 1] = arr[len - 1];
        for(int i = len - 2; i >= 0; i--){
        	if(maxStartHere[i + 1] > 0){
        		maxStartHere[i] = arr[i] + maxStartHere[i + 1];
        	} else {
        		maxStartHere[i] = arr[i];
        	}
        	res = Math.max(res, maxStartHere[i]);
        }
        for(int i = 1; i < len - 1; i++){
        	res = Math.max(res, maxEndHere[i - 1] + maxStartHere[i + 1]);
        }
        return res;
    }
}
