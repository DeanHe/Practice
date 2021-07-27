package array;

import java.util.Arrays;
/*
Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. Return the sum of the three integers. You may assume that each input would have exactly one solution.



Example 1:

Input: nums = [-1,2,1,-4], target = 1
Output: 2
Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).


Constraints:

3 <= nums.length <= 10^3
-10^3 <= nums[i] <= 10^3
-10^4 <= target <= 10^4
 */
public class ThreeSumClosest {
	public int threeSumClosest(int[] nums, int target) {
        int a, b, c, sum, len = nums.length;
		int res = nums[0] + nums[1] + nums[len - 1];

		Arrays.sort(nums);
		for (int i = 0; i < len - 2; i++) {
			a = nums[i];
			for (int j = i + 1, k = len - 1; j < k;) {
				b = nums[j];
				c = nums[k];
				sum = a + b + c;
				if (Math.abs(target - sum) < Math.abs(target - res)) {
					res = sum;
				}
				if (sum > target) {
					k--;
				} else if (sum < target) {
					j++;
				}
				// sum == target
				else {
					return target;
				}
			}
		}
		return res;
    }
}
