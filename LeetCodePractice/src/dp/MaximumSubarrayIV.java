package dp;
/*Given an integer arrays, find a contiguous subarray which has the largest sum and length should be greater or equal to given length k.
Return the largest sum, return 0 if there are fewer than k elements in the array.*/
public class MaximumSubarrayIV {
	/**
     * @param nums an array of integers
     * @param k an integer
     * @return the largest sum
     */
    public int maxSubarray4(int[] nums, int k) {
        // Write your code here
        int len = nums.length;
        if (len < k)
            return 0;

        int res = 0;
        for (int i = 0; i < k; ++i)
            res += nums[i];

        int[] preSum = new int[len + 1];
        preSum[0] = 0;
        
        int min_prefix = 0;
        for (int i = 1; i <= len; ++i) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
            if (i >= k) {
                res = Math.max(res, preSum[i] - min_prefix);
                min_prefix = Math.min(min_prefix, preSum[i - k + 1]);
            }
        }
        return res;
    }
}
