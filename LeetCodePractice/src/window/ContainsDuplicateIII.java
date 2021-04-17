package window;

import java.util.*;

/*
Given an array of integers, find out whether there are two distinct indices i and j in the array such that the absolute difference between nums[i] and nums[j] is at most t and the absolute difference between i and j is at most k.
Example 1:

Input: nums = [1,2,3,1], k = 3, t = 0
Output: true
Example 2:

Input: nums = [1,0,1,1], k = 1, t = 2
Output: true
Example 3:

Input: nums = [1,5,9,1,5,9], k = 2, t = 3
Output: false

analysis:
maintaining the tree of size k will result in time complexity O(N lg K).
In order to check if there exists any value of range abs(nums[i] - nums[j])
to simple queries can be executed both of time complexity O(lg K)
*/
public class ContainsDuplicateIII {
	// Balanced Binary Search Tree
	public boolean containsNearbyAlmostDuplicateBST(int[] nums, int k, int t) {
        if(nums == null || nums.length <= 1 || k < 1 || t < 0) {
        	return false;
        }
        TreeSet<Integer> treeSet = new TreeSet<>();
        int len = nums.length;
        for(int i = 0; i < len; i++) {
        	Integer floor = treeSet.floor(nums[i]);
        	if(floor != null && Long.valueOf(nums[i]) - Long.valueOf(floor) <= t) {
        		return true;
        	}
        	Integer ceil = treeSet.ceiling(nums[i]);
        	if(ceil != null && Long.valueOf(ceil) - Long.valueOf(nums[i]) <= t) {
        		return true;
        	}
        	treeSet.add(nums[i]);
        	if(i - k >= 0) {
        		treeSet.remove(nums[i - k]);
        	}
        }
        return false;
    }
	public boolean containsNearbyAlmostDuplicateBucketSort(int[] nums, int k, int t) {
        if(nums == null || nums.length <= 1 || k < 1 || t < 0) {
        	return false;
        }
        int min = Integer.MAX_VALUE;
        for(int n : nums) {
        	min = Math.min(min, n);
        }
        long diff = Long.valueOf(t) + 1;
        int len = nums.length;
        HashMap<Long, Integer> map = new HashMap<>();
        for(int i = 0; i < len; i++) {
        	long index = (Long.valueOf(nums[i]) - Long.valueOf(min)) / diff;
        	// check left bucket
        	if(map.containsKey(index - 1)) {
        		int leftVal = map.get(index - 1);
        		if(Long.valueOf(nums[i]) - Long.valueOf(leftVal) <= t) {
        			return true;
        		}
        	}
        	// check right bucket
        	if(map.containsKey(index + 1)) {
        		int rightVal = map.get(index + 1);
        		if(Long.valueOf(rightVal) - Long.valueOf(nums[i]) <= t) {
        			return true;
        		}
        	}
        	if(map.containsKey(index)) {
        		int curVal = map.get(index);
        		if(Math.abs(Long.valueOf(curVal) - Long.valueOf(nums[i])) <= t) {
        			return true;
        		}
        	}
        	map.put(index, nums[i]);
        	if(i - k >= 0) {
        		long passedIndex = (Long.valueOf(nums[i - k]) - Long.valueOf(min)) / diff;
        		map.remove(passedIndex);
        	}
        }
        return false;
    }
}
