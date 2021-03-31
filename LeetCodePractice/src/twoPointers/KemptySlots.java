package twoPointers;

/*
Given an array flowers consists of number from 1 to N. Each number in the array represents the place where the flower will open in that day.
For example, flowers[i] = x means that the unique flower that blooms at day i will be at position x, where i and x will be in the range from 1 to N.
Also given an integer k, you need to output in which day there exists two flowers in the status of blooming,
and also the number of flowers between them is k and these flowers are not blooming.
If there isn't such day, output -1.

Example 1:
Input: 
flowers: [1,3,2]
k: 1
Output: 2
Explanation: In the second day, the first and the third flower have become blooming.
Example 2:
Input: 
flowers: [1,2,3]
k: 1
Output: -1
https://www.programcreek.com/2012/04/leetcode-k-empty-slots-java/
*/

import java.util.TreeSet;

public class KemptySlots {
	public int kEmptySlots(int[] flowers, int k) {
		TreeSet<Integer> treeSet = new TreeSet<>();
		int len = flowers.length;
		for(int i = 0; i < len; i++){
			Integer next = treeSet.higher(flowers[i]);
			Integer pre = treeSet.lower(flowers[i]);
			if(pre != null && pre == flowers[i] - k - 1){
				return i + 1;
			}
			if(next != null && pre == flowers[i] - k - 1){
				return i + 1;
			}
			treeSet.add(flowers[i]);
		}
		return -1;
	}
}
