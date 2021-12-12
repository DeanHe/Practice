package hashMap;
/*
Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

Example
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Clarification
Your algorithm should run in O(n) complexity.

analysis:
hashmap save [number : longest sequence length the number belongs]
*/

import java.util.HashMap;
import java.util.Map;

public class LongestConsecutiveSequence {
	/**
     * @param nums: A list of integers
     * @return: An integer
     */
    public int longestConsecutive(int[] nums) {
        // write your code here
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int res = 0;
		Map<Integer, Integer> map = new HashMap<>();
		for (int n : nums) {
			if (!map.containsKey(n)) {
				int downCnt = map.getOrDefault(n - 1, 0);
				int upCnt = map.getOrDefault(n + 1, 0);
				int len = downCnt + upCnt + 1;
				map.put(n, len);
				res = Math.max(res, len);
				map.put(n - downCnt, len);
				map.put(n + upCnt, len);
			}
		}
		return res;
    }
}
