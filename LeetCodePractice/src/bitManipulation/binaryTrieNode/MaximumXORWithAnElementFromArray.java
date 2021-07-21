package bitManipulation.binaryTrieNode;

import java.util.Arrays;

/*
You are given an array nums consisting of non-negative integers. You are also given a queries array, where queries[i] = [xi, mi].

The answer to the ith query is the maximum bitwise XOR value of xi and any element of nums that does not exceed mi. In other words, the answer is max(nums[j] XOR xi) for all j such that nums[j] <= mi. If all elements in nums are larger than mi, then the answer is -1.

Return an integer array answer where answer.length == queries.length and answer[i] is the answer to the ith query.



Example 1:

Input: nums = [0,1,2,3,4], queries = [[3,1],[1,3],[5,6]]
Output: [3,3,7]
Explanation:
1) 0 and 1 are the only two integers not greater than 1. 0 XOR 3 = 3 and 1 XOR 3 = 2. The larger of the two is 3.
2) 1 XOR 2 = 3.
3) 5 XOR 2 = 7.
Example 2:

Input: nums = [5,2,4,6,6,3], queries = [[12,4],[8,1],[6,3]]
Output: [15,-1,5]


Constraints:

1 <= nums.length, queries.length <= 10^5
queries[i].length == 2
0 <= nums[j], xi, mi <= 10^9

tag: trie
analysis:
similar to
Checking Existence of Edge Length Limited Path
Maximum XOR of Two Numbers in an array

1 sort the query and input
2 build the trie structure on the fly,
    insert num <= query limit to the trie
    search the trie for the max XOR

Time Complexity : O (N log(N) + K log(K) )
where N is the number of elements in the nums array and K is the total number of queries.
Here we need to note that every element is added to the trie at most once.
For adding each element in the trie constant time is required (c = 32).
The log(n) factor gets added because we are sorting both the nums array and queries array.
 */
public class MaximumXORWithAnElementFromArray {
    public int[] maximizeXor(int[] nums, int[][] queries) {
        int[] res = new int[queries.length];
        int[][] queriesCopy = new int[queries.length][3];
        for(int i = 0; i < queries.length; i++){
            queriesCopy[i][0] = queries[i][0];
            queriesCopy[i][1] = queries[i][1];
            queriesCopy[i][2] = i;
        }
        Arrays.sort(nums);
        Arrays.sort(queriesCopy, (a, b) -> a[1] - b[1]);
        BinaryTrieNode root = new BinaryTrieNode();
        int i = 0;
        for (int[] q : queriesCopy) {
            while (i < nums.length && nums[i] <= q[1]) {
                insert(root, nums[i]);
                i++;
            }
            res[q[2]] = search(root, q[0]);
        }
        return res;
    }

    private void insert(BinaryTrieNode root, int num) {
        BinaryTrieNode cur = root;
        for (int i = 31; i >= 0; i--) {
            int bit = (num >> i) & 1;
            if (cur.arr[bit] == null) {
                cur.arr[bit] = new BinaryTrieNode();
            }
            cur = cur.arr[bit];
        }
    }

    private int search(BinaryTrieNode root, int num) {
        int sum = 0;
        BinaryTrieNode cur = root;
        for (int i = 31; i >= 0; i--) {
            if(cur == null){
                return -1;
            }
            int bit = (num >> i) & 1;
            int complement = 1 - bit;
            if (cur.arr[complement] != null) {
                sum |= (1 << i);
                cur = cur.arr[complement];
            } else {
                cur = cur.arr[bit];
            }
        }
        return sum;
    }
}
