package DP;

import java.util.*;

public class LengthOfLongestFibonacciSubsequence {
	public int lenLongestFibSubseqDP(int[] A) {
        int len = A.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
			map.put(A[i], i);
		}
        int ans = 0;
        // dp[i][j] represents fib sequence ends with '... A[j], A[i]' length
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
