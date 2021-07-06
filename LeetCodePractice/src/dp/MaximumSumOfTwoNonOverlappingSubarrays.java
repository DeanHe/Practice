package dp;
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
Explanation: One choice of subarrays is [5,6,0,9] with length 4, and [3,8] with length 3.

similar to Best Time To Buy and Sell Stock III
*/
public class MaximumSumOfTwoNonOverlappingSubarrays {
	public int maxSumTwoNoOverlap(int[] A, int L, int M) {
        int len = A.length;
        int[] preSum = new int[len + 1];
        preSum[0] = A[0];
        for(int i = 0; i < len; i++){
            preSum[i + 1] = preSum[i] + A[i];
        }
        return Math.max(getMax(preSum, L, M), getMax(preSum, M, L));
    }

    private int getMax(int[] preSum, int L, int M) {
        int res = 0;
        int len = preSum.length;
        for(int i = L + M, maxL = 0; i < len; i++){
            maxL = Math.max(maxL, preSum[i - M] - preSum[i - M - L]);
            res = Math.max(res, maxL + preSum[i] - preSum[i - M]);
        }
        return res;
    }

    // mimic best sell stock III
	public int maxSumTwoNoOverlapII(int[] A, int L, int M) {
		int len = A.length;
        int[] preSum = new int[len + 1];
        for(int i = 1; i <= len; i++){
            preSum[i] = preSum[i - 1] + A[i - 1];
        }
        int[] Lleft = new int[len + 1];
        int[] Lright = new int[len + 1];
        int[] Mleft = new int[len + 1];
        int[] Mright = new int[len + 1];
        for(int i = L; i <= len; i++){     	
        	Lleft[i] = Math.max(Lleft[i - 1], preSum[i] - preSum[i - L]);
        }
        //System.out.println(Arrays.toString(Lleft));
        for(int i = M; i <= len; i++){      		
    		Mleft[i] = Math.max(Mleft[i - 1], preSum[i] - preSum[i - M]);
    	}
        //System.out.println(Arrays.toString(Mleft));
        for(int i = len - L; i >= 0; i--){
        	Lright[i] = Math.max(Lright[i + 1], preSum[i + L] - preSum[i]);
        }
        //System.out.println(Arrays.toString(Lright));
        for(int i = len - M; i >= 0; i--){   		
        	Mright[i] = Math.max(Mright[i + 1], preSum[i + M] - preSum[i]);
        }
        //System.out.println(Arrays.toString(Mright));
        int res = Integer.MIN_VALUE;
        for(int i = L; i + M <= len; i++){
            int tmp = Lleft[i] + Mright[i];
            res = Math.max(res, tmp);
        }
        for(int i = M; i + L <= len; i++){
            int tmp = Mleft[i] + Lright[i];
            res = Math.max(res, tmp);
        }
        return res;
    }
}
