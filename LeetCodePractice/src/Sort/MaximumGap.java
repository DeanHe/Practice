package Sort;

import java.util.Arrays;

/*
#164
Given an integer array nums, return the maximum difference between two successive elements in its sorted form. If the array contains less than two elements, return 0.



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


Follow up: Could you solve it in linear time/space?
 */
public class MaximumGap {
    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2)
            return 0;
        // get the max and min value of the array
        int least = nums[0];
        int most = nums[0];
        for (int i : nums) {
            least = Math.min(least, i);
            most = Math.max(most, i);
        }
        // the minimum possible gap, ceiling of the integer division
        int gap = (int) Math.ceil((double) (most - least) / (nums.length - 1));
        int[] bucketsMIN = new int[nums.length - 1]; // store the min value in that bucket
        int[] bucketsMAX = new int[nums.length - 1]; // store the max value in that bucket
        Arrays.fill(bucketsMIN, Integer.MAX_VALUE);
        Arrays.fill(bucketsMAX, Integer.MIN_VALUE);
        // put numbers into buckets
        for (int i : nums) {
            if (i == least || i == most)
                continue;
            int idx = (i - least) / gap; // index of the right position in the buckets
            bucketsMIN[idx] = Math.min(i, bucketsMIN[idx]);
            bucketsMAX[idx] = Math.max(i, bucketsMAX[idx]);
        }
        // scan the buckets for the max gap
        int maxGap = Integer.MIN_VALUE;
        int previous = least;
        for (int i = 0; i < nums.length - 1; i++) {
            if (bucketsMIN[i] == Integer.MAX_VALUE && bucketsMAX[i] == Integer.MIN_VALUE)
                // empty bucket
                continue;
            // min value minus the previous value is the current gap
            maxGap = Math.max(maxGap, bucketsMIN[i] - previous);
            // update previous bucket value
            previous = bucketsMAX[i];
        }
        maxGap = Math.max(maxGap, most - previous); // update the final max value gap
        return maxGap;
    }
}
