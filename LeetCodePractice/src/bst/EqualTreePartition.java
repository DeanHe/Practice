package bst;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/*
Given a binary tree with n nodes, your task is to check if it's possible to partition the tree to two trees which have the equal sum of values after removing exactly one edge on the original tree.

Example 1:

Input:
    5
   / \
  10 10
    /  \
   2   3

Output: True
Explanation:
    5
   /
  10

Sum: 15

   10
  /  \
 2    3

Sum: 15


Example 2:

Input:
    1
   / \
  2  10
    /  \
   2   20

Output: False
Explanation: You can't split the tree into two trees with equal sum after removing exactly one edge on the tree.


Note:

The range of tree node value is in the range of [-100000, 100000].
1 <= n <= 10000

analysis:
one pass
 */
public class EqualTreePartition {
    public boolean checkEqualTree(TreeNode root){
        Map<Integer, Integer> sumCnt = new HashMap<>();
        int sum = dfs(root, sumCnt);
        if(sum == 0){
            return sumCnt.get(0) > 1;
        }
        return sum % 2 == 0 && sumCnt.containsKey(sum / 2);
    }

    private int dfs(TreeNode root, Map<Integer, Integer> sumCnt) {
        if(root == null){
            return 0;
        }
        int res = root.val;
        res += dfs(root.left, sumCnt);
        res += dfs(root.right, sumCnt);
        sumCnt.put(res, sumCnt.getOrDefault(res, 0) + 1);
        return res;
    }
}

