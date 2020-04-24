package PrefixSum;

import java.util.*;


/*
Given an array, find the maximum sum of subarray close to k but not larger than k.

solution:
https://www.programcreek.com/2016/08/maximum-sum-of-subarray-close-to-k/
*/
public class MaximumSumOfSubarrayCloseToK {
	public int getLargestSumCloseToK(int[] arr, int k){
		int prefixSum = 0;
		TreeSet<Integer> set = new TreeSet<>();
		set.add(0);
		int len = arr.length;
		int res = Integer.MIN_VALUE;
		for(int i = 0; i < len; i++){
			prefixSum += arr[i];
			Integer ceiling = set.ceiling(prefixSum - k);
			if(ceiling != null){
				res = Math.max(res, prefixSum - ceiling);
			}
			set.add(prefixSum);
		}
		return res;
	}
}
