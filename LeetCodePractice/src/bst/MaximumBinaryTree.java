package bst;

import java.util.Deque;
import java.util.LinkedList;

/*
You are given an integer array nums with no duplicates. A maximum binary tree can be built recursively from nums using the following algorithm:
Create a root node whose value is the maximum value in nums.
Recursively build the left subtree on the subarray prefix to the left of the maximum value.
Recursively build the right subtree on the subarray suffix to the right of the maximum value.
Return the maximum binary tree built from nums.

Example 1:
Input: nums = [3,2,1,6,0,5]
Output: [6,3,5,null,2,0,null,null,1]
Explanation: The recursive calls are as follow:
- The largest value in [3,2,1,6,0,5] is 6. Left prefix is [3,2,1] and right suffix is [0,5].
    - The largest value in [3,2,1] is 3. Left prefix is [] and right suffix is [2,1].
        - Empty array, so no child.
        - The largest value in [2,1] is 2. Left prefix is [] and right suffix is [1].
            - Empty array, so no child.
            - Only one element, so child is a node with value 1.
    - The largest value in [0,5] is 5. Left prefix is [0] and right suffix is [].
        - Only one element, so child is a node with value 0.
        - Empty array, so no child.

Example 2:
Input: nums = [3,2,1]
Output: [3,null,2,null,1]


Constraints:
1 <= nums.length <= 1000
0 <= nums[i] <= 1000
All integers in nums are unique.

analysis:
1 trivial recursion approach:
TC O(N^2) SC O(N)

2 deque approach:
TC O(N)
 */
public class MaximumBinaryTree {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return dfs(nums, 0, nums.length - 1);
    }

    private TreeNode dfs(int[] nums, int s, int e) {
        if(s > e){
            return null;
        }
        int most = Integer.MIN_VALUE, idx = -1;
        for(int i = s; i <= e; i++){
            if(most < nums[i]){
                most = nums[i];
                idx = i;
            }
        }
        TreeNode root = new TreeNode(most);
        root.left = dfs(nums, s, idx - 1);
        root.right = dfs(nums, idx + 1, e);
        return root;
    }

    public TreeNode constructMaximumBinaryTreeStack(int[] nums) {
        Deque<TreeNode> deque = new LinkedList<>();
        for(int i = 0; i < nums.length; i++){
            TreeNode cur = new TreeNode(nums[i]);
            while(!deque.isEmpty() && deque.peekLast().val < nums[i]){
                cur.left = deque.pollLast();
            }
            if(!deque.isEmpty()){
                deque.peekLast().right = cur;
            }
            deque.offerLast(cur);
        }
        if(deque.isEmpty()){
            return null;
        }
        return deque.pollFirst();
    }
}
