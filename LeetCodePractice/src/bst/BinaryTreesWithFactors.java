package bst;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
Given an array of unique integers, arr, where each integer arr[i] is strictly greater than 1.

We make a binary tree using these integers, and each number may be used for any number of times. Each non-leaf node's value should be equal to the product of the values of its children.

Return the number of binary trees we can make. The answer may be too large so return the answer modulo 10^9 + 7.



Example 1:

Input: arr = [2,4]
Output: 3
Explanation: We can make these trees: [2], [4], [4, 2, 2]
Example 2:

Input: arr = [2,4,5,10]
Output: 7
Explanation: We can make these trees: [2], [4], [5], [10], [4, 2, 2], [10, 2, 5], [10, 5, 2].


Constraints:

1 <= arr.length <= 1000
2 <= arr[i] <= 10^9

analysis:
dp

Time Complexity: O(N^2), where N is the length of A. This comes from the two for-loops iterating i and j.

Space Complexity: O(N), the space used by dp and index.


 */
public class BinaryTreesWithFactors {
    int MOD = (int) (1e9 + 7);

    public int numFactoredBinaryTrees(int[] arr) {
        int len = arr.length;
        Arrays.sort(arr);
        // dp[i] means # of binary Trees with root in arr[i]
        long[] dp = new long[len];
        Arrays.fill(dp, 1);
        Map<Integer, Integer> idxMap = new HashMap<>();
        for (int i = 0; i < len; i++) {
            idxMap.put(arr[i], i);
        }
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] % arr[j] == 0) {
                    int right = arr[i] / arr[j];
                    if (idxMap.containsKey(right)) {
                        dp[i] = (dp[i] + dp[j] * dp[idxMap.get(right)]) % MOD;
                    }
                }
            }
        }
        long res = 0;
        for (long n : dp) {
            res += n;
        }
        return (int) (res % MOD);
    }
}
