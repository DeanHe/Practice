package dp;

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

hint:
1 Use dynamic programming. dp[i][j] will be the answer for inputs A[i:], B[j:].

analysis:
Since a common subarray of A and B must start at some A[i] and B[j], let dp[i][j] be the longest common prefix of A[:i] and B[:j].
Whenever A[i] == B[j], we know dp[i][j] = dp[i-1][j-1] + 1. Also, the answer is max(mem[i][j]) over all i, j.
We can perform bottom-up dynamic programming to find the answer based on this recurrence.
Our loop invariant is that the answer is already calculated correctly and stored in dp for any less i, j.

*/
public class MaximumLengthOfRepeatedSubarray {
    public int findLength(int[] nums1, int[] nums2) {
        int l1 = nums1.length, l2 = nums2.length, res = 0;
        int[][] dp = new int[l1 + 1][l2 + 1];
        for (int i = 0; i < l1; i++) {
            for (int j = 0; j < l2; j++) {
                if (nums1[i] == nums2[j]) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                }
                res = Math.max(dp[i + 1][j + 1], res);
            }
        }
        return res;
    }
}
