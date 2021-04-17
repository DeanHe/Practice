package window;

import java.util.HashMap;
import java.util.Map;

/*
Given an array A of positive integers, call a (contiguous, not necessarily distinct) subarray of A good if the number of different integers in that subarray is exactly K.

(For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.)

Return the number of good subarrays of A.

Example 1:
Input: A = [1,2,1,2,3], K = 2
Output: 7
Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].

Example 2:
Input: A = [1,2,1,3,4], K = 3
Output: 3
Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].

Time Complexity: O(n), where n is the length of A.
Space Complexity: O(n).
*/
public class SubarraysWithKDifferentIntegers {
	public int subarraysWithKDistinct(int[] A, int K) {
		Map<Integer, Integer> freq = new HashMap<>();
        int res = 0, uniqueCnt = 0, preDupCnt = 0;
        for(int l = 0, r = 0; r < A.length; r++){
        	if(freq.getOrDefault(A[r], 0) == 0){
        		uniqueCnt++;
        	}
        	freq.put(A[r], freq.getOrDefault(A[r], 0) + 1);
        	if(uniqueCnt > K){
        		freq.put(A[l], freq.get(A[l]) - 1);
        		l++;
        		uniqueCnt--;
        		preDupCnt = 0;
        	}
        	while(freq.get(A[l]) > 1){
        		freq.put(A[l], freq.get(A[l]) - 1);
        		l++;
        		preDupCnt++;
        	}
        	if(uniqueCnt == K){
        		res += preDupCnt + 1;
        	}
        }
        return res;
    }
}
