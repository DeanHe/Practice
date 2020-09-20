package BitManipulation;

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

1 <= nums.length <= 2 * 104
0 <= nums[i] <= 2^31 - 1

analysis: Greedy + Trie
for each num try to find its gree  dy XOR pair
 */
public class MaximumXOROfTwoNumbersInAnArray {
    public int findMaximumXOR(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        BinaryNode root = new BinaryNode();
        buildTrie(root, nums);
        int res = Integer.MIN_VALUE;
        for (int n : nums) {
            BinaryNode cur = root;
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

    private void buildTrie(BinaryNode root, int[] nums) {
        for (int n : nums) {
            BinaryNode cur = root;
            for (int i = 31; i >= 0; i--) {
                int bit = (n >> i) & 1;
                if (cur.arr[bit] == null) {
                    cur.arr[bit] = new BinaryNode();
                }
                cur = cur.arr[bit];
            }
        }
    }

    private class BinaryNode {
        BinaryNode[] arr;

        public BinaryNode() {
            arr = new BinaryNode[2];
        }
    }
}
