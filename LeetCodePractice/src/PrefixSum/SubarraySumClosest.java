package PrefixSum;

import java.util.Arrays;

/*
Given an integer array, find a subarray with sum closest to zero. Return the indexes of the first number and last number.

Example
Example1

Input:
[-3,1,1,-3,5]
Output:
[0,2]
Explanation: [0,2], [1,3], [1,1], [2,2], [0,4]

Challenge
O(nlogn) time

Notice
It is guaranteed that the sum of any numbers is in [-2^31, 2^31 - 1]
 */
public class SubarraySumClosest {
    /*
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number and the index of the last number
     */
    public int[] subarraySumClosest(int[] nums) {
        int[] res = new int[2];
        if(nums == null || nums.length == 0){
            return res;
        }
        int len = nums.length;
        if(len == 1){
            res[0] = 0;
            res[1] = 0;
            return res;
        }
        Pair[] preSum = new Pair[len];
        preSum[0] = new Pair(nums[0], 0);
        for(int i = 1; i <= len; i++){
            preSum[i] = new Pair(preSum[i - 1].sum + nums[i - 1], i);
        }
        Arrays.sort(preSum, (a, b) -> a.sum - b.sum);
        int least = Math.abs(preSum[0].sum);
        res[0] = 0;
        res[1] = preSum[0].idx;
        for(int i = 1; i <= len; i++){
            if(preSum[i].sum - preSum[i - 1].sum < least){
                least = preSum[i].sum - preSum[i - 1].sum;
                int[] arr = new int[]{preSum[i - 1].idx, preSum[i].idx};
                Arrays.sort(arr);
                res[0] = arr[0] + 1;
                res[1] = arr[1];
            }
        }
        return res;
    }

    class Pair {
        int sum, idx;
        public Pair(int sum, int idx){
            this.sum = sum;
            this.idx = idx;
        }
    }
}
