package BitManipulation;
/*
Given a (0-indexed) integer array nums and two integers low and high, return the number of nice pairs.

A nice pair is a pair (i, j) where 0 <= i < j < nums.length and low <= (nums[i] XOR nums[j]) <= high.



Example 1:

Input: nums = [1,4,2,7], low = 2, high = 6
Output: 6
Explanation: All nice pairs (i, j) are as follows:
    - (0, 1): nums[0] XOR nums[1] = 5
    - (0, 2): nums[0] XOR nums[2] = 3
    - (0, 3): nums[0] XOR nums[3] = 6
    - (1, 2): nums[1] XOR nums[2] = 6
    - (1, 3): nums[1] XOR nums[3] = 3
    - (2, 3): nums[2] XOR nums[3] = 5
Example 2:

Input: nums = [9,8,4,2,1], low = 5, high = 14
Output: 8
Explanation: All nice pairs (i, j) are as follows:
​​​​​    - (0, 2): nums[0] XOR nums[2] = 13
    - (0, 3): nums[0] XOR nums[3] = 11
    - (0, 4): nums[0] XOR nums[4] = 8
    - (1, 2): nums[1] XOR nums[2] = 12
    - (1, 3): nums[1] XOR nums[3] = 10
    - (1, 4): nums[1] XOR nums[4] = 9
    - (2, 3): nums[2] XOR nums[3] = 6
    - (2, 4): nums[2] XOR nums[4] = 5


Constraints:

1 <= nums.length <= 2 * 10^4
1 <= nums[i] <= 2 * 10^4
1 <= low <= high <= 2 * 10^4

analysis:
if BoundBit is 1, then means
    1 add to result all nums[i] with same bit value as num[i], so they XOR is 0, and is less than BoundBit 1
    2 continue search on node where all nums[i] with different bit value as num[i],so they is 1, and is same as BoundBit 1
if BoundBit is 0, then means need to find all nums[i] with same bit value as num[i], so they XOR is 0, and is equal to BoundBit 0
 */
public class CountPairsWithXORinaRange {
    public int countPairs(int[] nums, int low, int high) {
        TrieNode root = new TrieNode();
        int res = 0;
        for(int num : nums){
            res += helper(root, num, high + 1) - helper(root, num, low);
            insert(root, num);
        }
        return res;
    }

    // count of XOR pairs end with num that is strictly less than bound
    private int helper(TrieNode root, int num, int bound) {
        int res = 0;
        TrieNode cur = root;
        for(int i = 31; i >= 0; i--){
            if(cur == null){
                break;
            }
            int boundBit = (bound >> i) & 1;
            int bit = (num >> i) & 1;
            int complement = 1 - bit;
            if(boundBit == 1){
                if(cur.arr[bit] != null){
                    res += cur.arr[bit].passCnt;
                }
                cur = cur.arr[complement];
            } else {
                // boundBit == 0
                cur = cur.arr[bit];
            }
        }
        return res;
    }

    private void insert(TrieNode root, int num) {
        TrieNode cur = root;
        for(int i = 31; i >= 0; i--){
            int bit = (num >> i) & 1;
            if(cur.arr[bit] == null){
                cur.arr[bit] = new TrieNode();
            }
            cur.arr[bit].passCnt++;
            cur = cur.arr[bit];
        }
    }

    private class TrieNode {
        TrieNode[] arr;
        int passCnt;

        public TrieNode() {
            arr = new TrieNode[2];
            passCnt = 0;
        }
    }
}
