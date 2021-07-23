package array;

import java.util.*;
/*
Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:

0 <= a, b, c, d < n
a, b, c, and d are distinct.
nums[a] + nums[b] + nums[c] + nums[d] == target
You may return the answer in any order.



Example 1:

Input: nums = [1,0,-1,0,-2,2], target = 0
Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
Example 2:

Input: nums = [2,2,2,2,2], target = 8
Output: [[2,2,2,2]]


Constraints:

1 <= nums.length <= 200
-10^9 <= nums[i] <= 10^9
-10^9 <= target <= 10^9
 */
public class FourSum {
	public List<List<Integer>> fourSum(int[] num, int target) {
		Arrays.sort(num);
		List<List<Integer>> result = new ArrayList<>();
		if (num == null || num.length < 4) {
			return result;
		}
		for (int a = 0; a < num.length - 3; a++) {
			for (int d = num.length - 1; d > a + 2; d--) {
				int b = a + 1;
				int c = d - 1;
				int remain = target - num[a] - num[d];
				while (b < c) {
					if (num[b] + num[c] > remain) {
						c--;
					} else if (num[b] + num[c] < remain) {
						b++;
					} else {
						result.add(Arrays.asList(num[a], num[b++], num[c--], num[d]));
					}
				}
			}
		}

		Set<List<Integer>> set = new HashSet<>(result);
		result.clear();
		result.addAll(set);
		return result;
	}
}
