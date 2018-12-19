package HashMap;

import java.util.HashMap;
import java.util.Map;

/*Given a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum frequency of any one of its elements.

Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums.

Example
Input: [1, 2, 2, 3, 1]
Output: 2
Explanation:
The input array has a degree of 2 because both elements 1 and 2 appear twice.
Of the subarrays that have the same degree:
[1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
The shortest length is 2. So return 2.

Notice
nums.length will be between 1 and 50,000.
nums[i] will be an integer between 0 and 49,999.*/
public class DegreeOfAnArray {
	/**
     * @param nums: a list of integers
     * @return: return a integer
     */
    public int findShortestSubArray(int[] nums) {
        Map<Integer, Integer> startIndexMap = new HashMap<>();
        Map<Integer, Integer> countMap = new HashMap<>();
        int len = nums.length;
        int maxFreq = 0, res = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
			countMap.put(nums[i], countMap.getOrDefault(nums[i], 0) + 1);
			if(!startIndexMap.containsKey(nums[i])){
				startIndexMap.put(nums[i], i);
			}
			if(countMap.get(nums[i]) > maxFreq){
				res = i - startIndexMap.get(nums[i]) + 1;
				maxFreq = countMap.get(nums[i]);
			} else if(countMap.get(nums[i]) == maxFreq){
				res = Math.min(res, i - startIndexMap.get(nums[i]) + 1);
			}		
		}
        return res;
    }
}
