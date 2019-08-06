package Greedy;

import java.util.*;
/*Given an array nums sorted in ascending order, return true if and only if you can split it into 1 or more subsequences such that each subsequence consists of consecutive integers and has length at least 3.

Example 1:

Input: [1,2,3,3,4,5]
Output: True
Explanation:
You can split them into two consecutive subsequences : 
1, 2, 3
3, 4, 5

Example 2:

Input: [1,2,3,3,4,4,5,5]
Output: True
Explanation:
You can split them into two consecutive subsequences : 
1, 2, 3, 4, 5
3, 4, 5

Example 3:

Input: [1,2,3,4,4,5]
Output: False
 

Constraints:

1 <= nums.length <= 10000*/

public class SplitArrayIntoConsecutiveSubsequences {
	public boolean isPossible(int[] nums) {
		Map<Integer, Integer> freq = new HashMap<>();
		Map<Integer, Integer> appendFreq = new HashMap<>(); // record the next element's need count in subsequence
		for(int n : nums){
			freq.put(n, freq.getOrDefault(n, 0) + 1);
		}
		for(int n : nums){
			if(freq.get(n) == 0){
				continue;
			} else if(appendFreq.getOrDefault(n, 0) > 0){
				// append to existing subsequences
				appendFreq.put(n, appendFreq.get(n) - 1);
				appendFreq.put(n + 1, appendFreq.getOrDefault(n + 1, 0) + 1);
			} else if(freq.getOrDefault(n + 1, 0) > 0 && freq.getOrDefault(n + 2, 0) > 0){
				// a new subsequence start
				freq.put(n + 1, freq.get(n + 1) - 1);
				freq.put(n + 2, freq.get(n + 2) - 1);
				appendFreq.put(n + 3, appendFreq.getOrDefault(n + 3, 0) + 1);
			} else {
				return false;
			}
			freq.put(n, freq.get(n) - 1);
		}
		return true;
	}
}
  