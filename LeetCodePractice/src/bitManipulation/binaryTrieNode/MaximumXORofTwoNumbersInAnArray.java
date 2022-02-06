package bitManipulation.binaryTrieNode;

import java.util.HashSet;
import java.util.Set;

/*
Given an integer array nums, return the maximum result of nums[i] XOR nums[j], where 0 ≤ i ≤ j < n.

Follow up: Could you do this in O(n) runtime?



Example 1:

Input: nums = [3,10,5,25,2,8]
Output: 28
Explanation: The maximum result is 5 XOR 25 = 28.
Example 2:

Input: nums = [0]
Output: 0
Example 3:

Input: nums = [2,4]
Output: 6
Example 4:

Input: nums = [8,10,2]
Output: 10
Example 5:

Input: nums = [14,70,53,83,49,91,36,80,92,51,66,70]
Output: 127


Constraints:

1 <= nums.length <= 2 * 10^4
0 <= nums[i] <= 2^31 - 1

analysis: greedy + trie

To make the XOR produce big value, the pair are at most 1's complement of each other
for each num try to find its greedy XOR pair (by finding the 1's complement of this number
bit by bit from highest to lowest)

time complexity: O(31*n)
space complexity: O(31*n)
 */
public class MaximumXORofTwoNumbersInAnArray {
    public int findMaximumXOR(int[] nums){
        int res = 0, mask = 0;
        for(int i = 31; i >= 0; i--){
            mask = mask | (1 << i);
            Set<Integer> prefixes = new HashSet<>();
            for(int n : nums){
                prefixes.add(n & mask);
            }
            int candidate = res | (1 << i);
            for(int prefix : prefixes){
                if(prefixes.contains(prefix ^ candidate)){
                    res = candidate;
                    break;
                }
            }
        }
        return res;
    }
    public int findMaximumXORtrie(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        BinaryTrieNode root = new BinaryTrieNode();
        buildTrie(root, nums);
        int res = Integer.MIN_VALUE;
        for (int n : nums) {
            BinaryTrieNode cur = root;
            int temp = 0;
            for (int i = 31; i >= 0; i--) {
                int bit = (n >> i) & 1;
                if (cur.arr[bit ^ 1] != null) {
                    temp += (1 << i);
                    cur = cur.arr[bit ^ 1];
                } else {
                    cur = cur.arr[bit];
                }
            }
            res = Math.max(res, temp);
        }
        return res;
    }

    private void buildTrie(BinaryTrieNode root, int[] nums) {
        for (int n : nums) {
            BinaryTrieNode cur = root;
            for (int i = 31; i >= 0; i--) {
                int bit = (n >> i) & 1;
                if (cur.arr[bit] == null) {
                    cur.arr[bit] = new BinaryTrieNode();
                }
                cur = cur.arr[bit];
            }
        }
    }
}
