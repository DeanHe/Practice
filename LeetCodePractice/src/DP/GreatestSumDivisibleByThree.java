package DP;
/*
Given an array nums of integers, we need to find the maximum possible sum of elements of the array such that it is divisible by three.

        Example 1:

        Input: nums = [3,6,5,1,8]
        Output: 18
        Explanation: Pick numbers 3, 6, 1 and 8 their sum is 18 (maximum sum divisible by 3).
        Example 2:

        Input: nums = [4]
        Output: 0
        Explanation: Since 4 is not divisible by 3, do not pick any number.
        Example 3:

        Input: nums = [1,2,3,4,4]
        Output: 12
        Explanation: Pick numbers 1, 3, 4 and 4 their sum is 12 (maximum sum divisible by 3).


        Constraints:

        1 <= nums.length <= 4 * 10^4
        1 <= nums[i] <= 10^4
*/
public class GreatestSumDivisibleByThree {
    public int maxSumDivThree(int[] nums) {
        int sum = 0, leftOne = Integer.MAX_VALUE, leftTwo = Integer.MAX_VALUE;
        for(int n : nums){
            sum += n;
            if(sum % 3 == 1) {
                leftTwo = Math.min(leftTwo, leftOne + n);
                leftOne = Math.min(leftOne, n);
            } else if(sum % 3 == 2){
                leftOne = Math.min(leftOne, leftTwo + n);
                leftTwo = Math.min(leftTwo, n);
            }
        }
        if((sum % 3) == 0) {
            return sum;
        } else if((sum % 2) == 0){
            return sum - leftTwo;
        } else {
            return sum - leftOne;
        }
    }
}
