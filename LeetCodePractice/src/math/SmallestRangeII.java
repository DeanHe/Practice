package math;

import java.util.Arrays;

/*
Given an array A of integers, for each integer A[i] we need to choose either x = -K or x = K, and add x to A[i] (only once).

After this process, we have some array B.

Return the smallest possible difference between the maximum value of B and the minimum value of B.



Example 1:

Input: A = [1], K = 0
Output: 0
Explanation: B = [1]
Example 2:

Input: A = [0,10], K = 2
Output: 6
Explanation: B = [2,8]
Example 3:

Input: A = [1,3,6], K = 3
Output: 3
Explanation: B = [4,6,3]


Note:

1 <= A.length <= 10000
0 <= A[i] <= 10000
0 <= K <= 10000

analysis:
the answer is in form (A[0]+K, A[1]+K, ..., A[i]+K, A[i+1]-K, ..., A[n]-K)
need to find the optimal breaking point i
 */
public class SmallestRangeII {
    public int smallestRangeII(int[] A, int K) {
        int len = A.length;
        Arrays.sort(A);
        int res = A[len - 1] - A[0];
        for (int i = 0; i + 1 < len; i++) {
            int most = Math.max(A[len - 1] - K, A[i] + K);
            int least = Math.min(A[0] + K, A[i + 1] - K);
            res = Math.min(res, most - least);
        }
        return res;
    }
}
