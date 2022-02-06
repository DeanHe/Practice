package array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Given two arrays, write a function to compute their intersection.

Example1
Input: 
nums1 = [1, 2, 2, 1], nums2 = [2, 2]
Output: 
[2, 2]

Example2
Input: 
nums1 = [1, 1, 2], nums2 = [1]
Output: 
[1]

Challenge
What if the given array is already sorted? How would you optimize your algorithm?
answer: use two pointer method
What if nums1's size is small compared to num2's size? Which algorithm is better?
What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
answer: use hashmap method

Notice
Each element in the result should appear as many times as it shows in both arrays.
The result can be in any order.

analysis:
create cnt map for nums1, then reduce the cnt for each n in nums2 if it is in the cnt map
*/
public class IntersectionOfTwoArraysII {
	/**
     * @param nums1: an integer array
     * @param nums2: an integer array
     * @return: an integer array
     */
    public int[] intersection(int[] nums1, int[] nums2) {
    	Map<Integer, Integer> freq = new HashMap<>();
    	for(int n : nums1){
    		freq.put(n, freq.getOrDefault(n, 0) + 1);
    	}
    	List<Integer> ls = new ArrayList<>();
    	for(int n : nums2){
    		if(freq.containsKey(n)){
    			int count = freq.get(n);
    			if(count > 0){
    				ls.add(n);
    			}
    			count--;
    			freq.put(n, count);
    		}
    	}
    	int[] res = new int[ls.size()];
    	for(int i = 0; i < ls.size(); i++){
    		res[i] = ls.get(i);
    	}
    	return res;
    }
}
