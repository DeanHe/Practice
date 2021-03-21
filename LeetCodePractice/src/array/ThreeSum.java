package array;

import java.util.*;
/*
Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note:

The solution set must not contain duplicate triplets.

Example:

Given array nums = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]
 */
public class ThreeSum {
	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		if (nums == null || nums.length < 3) {
			return res;
		}
		int len = nums.length;
		Arrays.sort(nums);
		for(int i = 0; i < len - 2; i++){
			if(i == 0 || nums[i] != nums[i - 1]){
				int l = i + 1, r = len - 1;
				while(l < r){
					if(nums[i] + nums[l] + nums[r] == 0){
						res.add(Arrays.asList(nums[i], nums[l], nums[r]));
						while(l < r && nums[l] == nums[l + 1]){
							l++;
						}
						while(l < r && nums[r] == nums[r - 1]){
							r--;
						}
						l++;
						r--;
					} else if(nums[i] + nums[l] + nums[r] < 0){
						l++;
					} else {
						r--;
					}
				}
			}
		}
		return res;
	}

	public List<List<Integer>> threeSumII(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		if (nums == null || nums.length < 3) {
			return res;
		}
		int len = nums.length;
		Arrays.sort(nums);
		int a, b, c;
		for (int i = 0; i < len - 2; i++) {
			a = nums[i];
			for (int j = i + 1, k = len - 1; j < k;) {
				b = nums[j];
				c = nums[k];
				if (a + b + c == 0) {
					List<Integer> temp = new ArrayList<>();
					temp.add(a);
					temp.add(b);
					temp.add(c);
					res.add(temp);
					j++;
					k--;
				} else if (a + b + c < 0) {
					j++;
				} else {
					k--;
				}
			}
		}
		// remove duplicate points
		Set<List<Integer>> set = new HashSet<>(res);
		res.clear();
		res.addAll(set);
		return res;

	}
}
