package Contest;

import java.util.ArrayList;
import java.util.List;

/*
Given an array nums that represents a permutation of integers from 1 to n. We are going to construct a binary search tree (bst) by inserting the elements of nums in order into an initially empty bst. Find the number of different ways to reorder nums so that the constructed bst is identical to that formed from the original array nums.

For example, given nums = [2,1,3], we will have 2 as the root, 1 as a left child, and 3 as a right child. The array [2,3,1] also yields the same bst but [3,2,1] yields a different bst.

Return the number of ways to reorder nums such that the bst formed is identical to the original bst formed from nums.

Since the answer may be very large, return it modulo 10^9 + 7.



Example 1:



Input: nums = [2,1,3]
Output: 1
Explanation: We can reorder nums to be [2,3,1] which will yield the same bst. There are no other ways to reorder nums which will yield the same bst.
Example 2:



Input: nums = [3,4,5,1,2]
Output: 5
Explanation: The following 5 arrays will yield the same bst:
[3,1,2,4,5]
[3,1,4,2,5]
[3,1,4,5,2]
[3,4,1,2,5]
[3,4,1,5,2]
Example 3:



Input: nums = [1,2,3]
Output: 0
Explanation: There are no other orderings of nums that will yield the same bst.
Example 4:



Input: nums = [3,1,2,5,4,6]
Output: 19
Example 5:

Input: nums = [9,4,2,1,3,6,5,7,8,14,11,10,12,13,16,15,17,18]
Output: 216212978
Explanation: The number of ways to reorder nums to get the same bst is 3216212999. Taking this number modulo 10^9 + 7 gives 216212978.


Constraints:

1 <= nums.length <= 1000
1 <= nums[i] <= nums.length
All integers in nums are distinct.

analysis:
time complexity: O(nlgn) ~O(n^2)
space complexity : O(nlgn) ~O(n^2)
 */
public class NumberOfWaysToReorderArrayToGetSameBST {
    int MOD = (int) (1e9 + 7);
    long[][] pascalTable;

    public int numOfWays(int[] nums) {
        int len = nums.length;
        pascalTable = getTriangle(len + 1);
        List<Integer> ls = new ArrayList<>();
        for (int n : nums) {
            ls.add(n);
        }
        return (int) dfs(ls) - 1;

    }

    private long[][] getTriangle(int n) {
        long[][] triangle = new long[n][n];
        for (int i = 0; i < n; i++) {
            triangle[i][0] = triangle[i][i] = 1;
        }
        for (int i = 2; i < n; i++) {
            for (int j = 1; j < i; j++) {
                triangle[i][j] = (triangle[i - 1][j] + triangle[i - 1][j - 1]) % MOD;
            }
        }
        return triangle;
    }

    private long dfs(List<Integer> nums) {
        int len = nums.size();
        if (len <= 2) {
            return 1;
        }
        int root = nums.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for (int i = 1; i < len; i++) {
            int n = nums.get(i);
            if (n < root) {
                left.add(n);
            } else {
                right.add(n);
            }
        }
        long comb = getComb(left.size() + right.size(), left.size());
        return (comb * (dfs(left) % MOD) % MOD) * dfs(right) % MOD;
    }

    private long getComb(int i, int j) {
        return pascalTable[i][j];
    }
}
