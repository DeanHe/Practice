package contest;

import java.util.HashMap;
import java.util.Map;

/*
Given an array nums of positive integers, return the longest possible length of an array prefix of nums, such that it is possible to remove exactly one element from this prefix so that every number that has appeared in it will have the same number of occurrences.
If after removing one element there are no remaining elements, it's still considered that every appeared number has the same number of ocurrences (0).

Example 1:

Input: nums = [2,2,1,1,5,3,3,5]
Output: 7
Explanation: For the subarray [2,2,1,1,5,3,3] of length 7, if we remove nums[4]=5, we will get [2,2,1,1,3,3], so that each number will appear exactly twice.
Example 2:

Input: nums = [1,1,1,2,2,2,3,3,3,4,4,4,5]
Output: 13
Example 3:

Input: nums = [1,1,1,2,2,2]
Output: 5
Example 4:

Input: nums = [10,2,8,9,3,8,1,5,2,3,7,6]
Output: 8
 
Constraints:

2 <= nums.length <= 10^5
1 <= nums[i] <= 10^5
*/
public class MaximumEqualFrequency {
	public int maxEqualFreq(int[] nums) {
		//element: cnt
		Map<Integer, Integer> freqMap = new HashMap<>();
		//cnt: elements count of this cnt
		Map<Integer, Integer> freqCntMap = new HashMap<>();
        int res = 0;
        for(int i = 0; i < nums.length; i++) {
        	int element = nums[i];
        	freqMap.put(element, freqMap.getOrDefault(element, 0) + 1);
        	int freq = freqMap.get(element);
        	freqCntMap.put(freq, freqCntMap.getOrDefault(freq, 0) + 1);
        	/*
        	 Case 1:
			 frequency * (number of elements with that frequency) == length AND i != nums.length - 1
			 E.g. [1,1,2,2,3]
			 When the iteration is at index 3, the count will be equal to the length. It should update the result with (length + 1) as it should take an extra element in order to fulfil the condition.

			 Case 2:
			 frequency * (number of elements with that frequency) == length - 1
			 E.g. [1,1,1,2,2,3]
			 When the iteration is at index 4, the count will be equal to (length - 1). It should update the result with length as it fulfil the condition.
        	 */
        	int total = freqCntMap.get(freq) * freq;
        	if(total == i + 1 && i != nums.length - 1) {
        		res = Math.max(res, i + 2);
        	} else if(total == i) {
        		res = Math.max(res, i + 1);
        	}
        }
        return res;
    }

}
