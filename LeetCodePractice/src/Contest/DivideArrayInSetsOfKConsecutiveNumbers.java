package Contest;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

/*
Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into sets of k consecutive numbers
Return True if its possible otherwise return False.

Example 1:

Input: nums = [1,2,3,3,4,4,5,6], k = 4
Output: true
Explanation: array can be divided into [1,2,3,4] and [3,4,5,6].
Example 2:

Input: nums = [3,2,1,2,3,4,3,4,5,9,10,11], k = 3
Output: true
Explanation: array can be divided into [1,2,3] , [2,3,4] , [3,4,5] and [9,10,11].
Example 3:

Input: nums = [3,3,2,2,1,1], k = 3
Output: true
Example 4:

Input: nums = [1,2,3,4], k = 3
Output: false
Explanation: Each array should be divided in subarrays of size 3.
 

Constraints:

1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9
1 <= k <= nums.length
*/
public class DivideArrayInSetsOfKConsecutiveNumbers {
	public boolean isPossibleDivide(int[] nums, int k) {
		int len = nums.length;
		if(len % k != 0){
			return false;
		}
		Map<Integer, Integer> cntMap = new HashMap<>();
		PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int n : nums){
        	cntMap.put(n, cntMap.getOrDefault(n, 0) + 1);
        }
        for(int n : cntMap.keySet()){
        	pq.offer(n);
        }
        while (!pq.isEmpty()) {
			int cur = pq.poll();
			int count = cntMap.get(cur);
			if(count == 0){
				continue;
			}
			for(int i = 0; i < k; i++){
				if(!cntMap.containsKey(cur + i) || cntMap.get(cur + i) < count){
					return false;
				}
				cntMap.put(cur + i, cntMap.get(cur + i) - count);
			}
			len -= k * count;
		}
        return len == 0;
    }
}
