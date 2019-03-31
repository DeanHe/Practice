package DP;
/*
Given two integer arrays A and B, return the maximum length of an subarray that appears in both arrays.

Example 1:

Input:
A: [1,2,3,2,1]
B: [3,2,1,4,7]
Output: 3
Explanation: 
The repeated subarray with maximum length is [3, 2, 1].
Note:

1 <= len(A), len(B) <= 1000
0 <= A[i], B[i] < 100
*/
/*Since a common subarray of A and B must start at some A[i] and B[j], let dp[i][j] be the longest common prefix of A[i:] and B[j:]. Whenever A[i] == B[j], we know dp[i][j] = dp[i+1][j+1] + 1. Also, the answer is max(dp[i][j]) over all i, j.
We can perform bottom-up dynamic programming to find the answer based on this recurrence. Our loop invariant is that the answer is already calculated correctly and stored in dp for any larger i, j.*/
public class MaximumLengthOfRepeatedSubarray {
	public int findLength(int[] A, int[] B) {
        int res = 0;
        int Alen = A.length;
        int Blen = B.length;
        //dp[i][j] means the largest common subarray starts from A[i:] and B[j:] 
        int[][] dp = new int[Alen + 1][Blen + 1];
        for(int i = Alen - 1; i >= 0; i--){
        	for(int j = Blen - 1; j >= 0; j--){
        		if(A[i] == B[j]){
        			dp[i][j] = dp[i + 1][j + 1] + 1;
        			if(res < dp[i][j]){
        				res = dp[i][j];
        			}
        		}
        	}
        }
        return res;
    }
}
