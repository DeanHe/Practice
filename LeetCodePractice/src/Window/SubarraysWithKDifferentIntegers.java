package Window;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.HashMap;
import java.util.Map;

/*Given an array A of positive integers, call a (contiguous, not necessarily distinct) subarray of A good if the number of different integers in that subarray is exactly K.

(For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.)

Return the number of good subarrays of A.

Example 1:
Input: A = [1,2,1,2,3], K = 2
Output: 7
Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].

Example 2:
Input: A = [1,2,1,3,4], K = 3
Output: 3
Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].*/
public class SubarraysWithKDifferentIntegers {
	public int subarraysWithKDistinct(int[] A, int K) {
		Map<Integer, Integer> map = new HashMap<>();
        int res = 0, left = 0, uniqueCnt = 0, preDupCnt = 0;
        for(int right = 0; right < A.length; right++){
        	if(map.getOrDefault(A[right], 0) == 0){
        		uniqueCnt++;
        	}
        	map.put(A[right], map.getOrDefault(A[right], 0) + 1);
        	if(uniqueCnt > K){
        		map.put(A[left], map.get(A[left]) - 1);
        		left++;
        		uniqueCnt--;
        		preDupCnt = 0;
        	}
        	while(map.get(A[left]) > 1){
        		map.put(A[left], map.get(A[left]) - 1);
        		left++;
        		preDupCnt++;
        	}
        	if(uniqueCnt == K){
        		res += preDupCnt + 1;
        	}
        }
        return res;
    }
}
