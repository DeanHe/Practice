package sort;

import java.util.Arrays;
/*
#164
Given an integer array nums, return the maximum difference between two successive elements in its sorted form. If the array contains less than two elements, return 0.
You must write an algorithm that runs in linear time and uses linear extra space.

Example 1:
Input: nums = [3,6,9,1]
Output: 3
Explanation: The sorted form of the array is [1,3,6,9], either (3,6) or (6,9) has the maximum difference 3.

Example 2:
Input: nums = [10]
Output: 0
Explanation: The array contains less than 2 elements, therefore return 0.

Constraints:
1 <= nums.length <= 10^4
0 <= nums[i] <= 10^9

analysis:
bucket sort
Suppose there are N elements in the array, the min value is min and the max value is max. Then the maximum gap will be no smaller than ceiling[(max - min ) / (N - 1)].
TC O(N)
 */
public class MaximumGap {
    public int maximumGap(int[] nums) {
        int len = nums.length;
        if (len < 2)
            return 0;
        // get the max and min value of the array
        int least = nums[0];
        int most = nums[0];
        for (int n : nums) {
            least = Math.min(least, n);
            most = Math.max(most, n);
        }
        // the minimum possible gap, ceiling of the integer division
        int gap = (most - least + len - 2) / (len - 1);
        int[] bucketsMIN = new int[len - 1]; // store the min value in that bucket
        int[] bucketsMAX = new int[len - 1]; // store the max value in that bucket
        Arrays.fill(bucketsMIN, Integer.MAX_VALUE);
        Arrays.fill(bucketsMAX, Integer.MIN_VALUE);
        // put numbers into buckets
        for (int n : nums) {
            if (n == least || n == most)
                continue;
            int idx = (n - least) / gap; // index of the right position in the buckets
            bucketsMIN[idx] = Math.min(n, bucketsMIN[idx]);
            bucketsMAX[idx] = Math.max(n, bucketsMAX[idx]);
        }
        // scan the buckets for the max gap
        int res = Integer.MIN_VALUE;
        int pre = least;
        for (int i = 0; i < bucketsMAX.length; i++) {
            if (bucketsMIN[i] == Integer.MAX_VALUE && bucketsMAX[i] == Integer.MIN_VALUE)
                // empty bucket
                continue;
            // min value minus the previous value is the current gap
            res = Math.max(res, bucketsMIN[i] - pre);
            // update previous bucket value
            pre = bucketsMAX[i];
        }
        res = Math.max(res, most - pre); // update the final max value gap
        return res;
    }
}
