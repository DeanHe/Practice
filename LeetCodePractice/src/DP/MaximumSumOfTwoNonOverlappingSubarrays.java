package DP;
/*Given an array A of non-negative integers, return the maximum sum of elements in two non-overlapping (contiguous) subarrays, which have lengths L and M.  (For clarification, the L-length subarray could occur before or after the M-length subarray.)

Formally, return the largest V for which V = (A[i] + A[i+1] + ... + A[i+L-1]) + (A[j] + A[j+1] + ... + A[j+M-1]) and either:

0 <= i < i + L - 1 < j < j + M - 1 < A.length, or
0 <= j < j + M - 1 < i < i + L - 1 < A.length.
 

Example 1:

Input: A = [0,6,5,2,2,5,1,9,4], L = 1, M = 2
Output: 20
Explanation: One choice of subarrays is [9] with length 1, and [6,5] with length 2.
Example 2:

Input: A = [3,8,1,3,2,1,8,9,0], L = 3, M = 2
Output: 29
Explanation: One choice of subarrays is [3,8,1] with length 3, and [8,9] with length 2.
Example 3:

Input: A = [2,1,5,6,0,9,5,0,3,8], L = 4, M = 3
Output: 31
Explanation: One choice of subarrays is [5,6,0,9] with length 4, and [3,8] with length 3.*/
// similar to Best Time To Buy and Sell Stock III
public class MaximumSumOfTwoNonOverlappingSubarrays {
	public int maxSumTwoNoOverlap(int[] A, int L, int M) {
        int len = A.length;
        int[] preSum = new int[len];
        preSum[0] = A[0];
        for(int i = 1; i < len; i++){
            preSum[i] = preSum[i - 1] + A[i];
        }
        int res = preSum[L + M - 1], Lmax = preSum[L - 1], Mmax = preSum[M - 1];
        for(int i = L + M; i < len; i++){
            // the largest subarray of length L before i - M position
            Lmax = Math.max(Lmax, preSum[i - M] - preSum[i - M - L]); 
            // the largest subarray of length M before i - L position
            Mmax = Math.max(Mmax, preSum[i - L] - preSum[i - M - L]);
            int tmp = Math.max(Lmax + preSum[i] - preSum[i - M], Mmax + preSum[i] - preSum[i - L]);
            res = Math.max(res, tmp);
        }
        return res;
    }
}
