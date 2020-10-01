package PrefixSum;

import java.util.HashMap;
import java.util.Map;

/*
Given an array A of integers, return the number of (contiguous, non-empty) subarrays that have a sum divisible by K.



Example 1:

Input: A = [4,5,0,-2,-3,1], K = 5
Output: 7
Explanation: There are 7 subarrays with a sum divisible by K = 5:
[4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]


Note:

1 <= A.length <= 30000
-10000 <= A[i] <= 10000
2 <= K <= 10000
 */
public class SubarraySumsDivisibleByK {
    public int subarraysDivByK(int[] A, int K) {
        int res = 0;
        Map<Integer, Integer> preSumCnt = new HashMap<>();
        preSumCnt.put(0, 1);
        int sum = 0;
        for(int n : A){
            sum = (sum + n) % K;
            if(sum < 0){
                sum += K;
            }
            if(preSumCnt.containsKey(sum)){
                res += preSumCnt.get(sum);
            }
            preSumCnt.put(sum, preSumCnt.getOrDefault(sum, 0) + 1);
        }
        return res;
    }
}
