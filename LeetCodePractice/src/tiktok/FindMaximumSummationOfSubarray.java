package tiktok;
/*
Given an integer array nums, find the contiguous subarray (containing at least one
number) which has the largest sum and return its sum.

Example 1:
Input:
[-2, 1, -3, 4, -1, 2, 1, -5, 4]
Output:
6
Explanation:
[4, -1, 2, 1] has the largest sum = 6.

Example 2:
Input:
[ 2]
Output:
2

Example 3:
Input:
[-1, 0, -2]
output :
0
*/
public class FindMaximumSummationOfSubarray {
    public int maxSubarraySum(int[] arr){
        int sum = Integer.MIN_VALUE, cur = 0;
        for(int n : arr){
            cur += n;
            if(cur > sum){
                sum = cur;
            }
            if(cur < 0){
                cur = 0;
            }
        }
        return sum;
    }
}
