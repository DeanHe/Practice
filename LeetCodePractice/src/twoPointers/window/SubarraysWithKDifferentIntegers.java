package twoPointers.window;

import java.util.HashMap;
import java.util.Map;

/*
Given an array nums of positive integers, call a (contiguous, not necessarily distinct) subarray of nums good if the number of different integers in that subarray is exactly k.

(For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.)

Return the number of good subarrays of nums.



Example 1:

Input: nums = [1,2,1,2,3], k = 2
Output: 7
Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
Example 2:

Input: nums = [1,2,1,3,4], k = 3
Output: 3
Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].


Note:

1 <= nums.length <= 20000
1 <= nums[i] <= nums.length
1 <= k <= nums.length

analysis:
Time Complexity: O(n), where n is the length of A.
Space Complexity: O(n).
*/
public class SubarraysWithKDifferentIntegers {
	public int subarraysWithKDistinct(int[] A, int K) {
		return subarraysWithAtMostKDistinct(A, K) - subarraysWithAtMostKDistinct(A, K - 1);
    }

	private int subarraysWithAtMostKDistinct(int[] A, int K) {
		Map<Integer, Integer> freq = new HashMap<>();
		int res = 0, s = 0, e = 0;
		while(e < A.length){
			freq.put(A[e], freq.getOrDefault(A[e], 0) + 1);
			while(freq.size() > K){
				freq.put(A[s], freq.get(A[s]) - 1);
				if(freq.get(A[s]) == 0){
					freq.remove(A[s]);
				}
				s++;
			}
			res += e - s + 1;
			e++;
		}
		return res;
	}

	public int subarraysWithKDistinctOnePass(int[] A, int K) {
		Map<Integer, Integer> map = new HashMap<>();
		int res = 0, l = 0, uniqueCnt = 0, firstElementCnt = 0;
		for(int r = 0; r < A.length; r++){
			if(map.getOrDefault(A[r], 0) == 0){
				uniqueCnt++;
			}
			map.put(A[r], map.getOrDefault(A[r], 0) + 1);
			if(uniqueCnt > K){
				map.put(A[l], map.get(A[l]) - 1);
				l++;
				uniqueCnt--;
				firstElementCnt = 0;
			}
			while(map.get(A[l]) > 1){
				map.put(A[l], map.get(A[l]) - 1);
				l++;
				firstElementCnt++;
			}
			if(uniqueCnt == K){
				res += firstElementCnt + 1;
			}
		}
		return res;
	}
}
