package DP;
/*
A sequence X_1, X_2, ..., X_n is fibonacci-like if:

n >= 3
X_i + X_{i+1} = X_{i+2} for all i + 2 <= n
Given a strictly increasing array A of positive integers forming a sequence, find the length of the longest fibonacci-like subsequence of A.  If one does not exist, return 0.

(Recall that a subsequence is derived from another sequence A by deleting any number of elements (including none) from A, without changing the order of the remaining elements.  For example, [3, 5, 8] is a subsequence of [3, 4, 5, 6, 7, 8].)

Example 1:

Input: [1,2,3,4,5,6,7,8]
Output: 5
Explanation:
The longest subsequence that is fibonacci-like: [1,2,3,5,8].
Example 2:

Input: [1,3,7,11,12,14,18]
Output: 3
Explanation:
The longest subsequence that is fibonacci-like:
[1,11,12], [3,11,14] or [7,11,18].
*/
import java.util.*;

public class LengthOfLongestFibonacciSubsequence {
	public int lenLongestFibSubseqDP(int[] A) {
        int len = A.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
        	//val : idx
			map.put(A[i], i);
		}
        int ans = 0;
        // mem[i][j] represents fib sequence ends with '... A[j], A[i]' length
        int[][] dp = new int[len][len]; 
        for(int i = 1; i < len; i++){
        	for(int j = i - 1; j >= 0; j--){
        		int c = A[i];
        		int b = A[j];
        		int a = c - b;
        		if(a < b && map.containsKey(a)){
        			dp[i][j] = dp[j][map.get(a)] + 1;
        		} else {
        			dp[i][j] = 2;
        		}
        		ans = Math.max(ans, dp[i][j]);
        	}
        }
        return ans >= 3 ? ans : 0; 
    }
	
	//method 2 use hashset
	public int lenLongestFibSubseq(int[] A) {
        int len = A.length;
        Set<Integer> set = new HashSet<>();
        for(int i : A){
            set.add(i);
        }
        int ans = 0;
        for(int i = 0; i < len; i++){
            for(int j = i + 1; j < len; j++){
                int a = A[i];
                int b = A[j];
                int c = a + b;
                int curLen = 2;
                while(set.contains(c)){
                    curLen++;
                    a = b;
                    b = c;
                    c = a + b;
                    ans = Math.max(ans, curLen);
                }
            }
        }
        return ans >= 3 ? ans : 0; 
    }
}
