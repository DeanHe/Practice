"""
You are given the root of a binary tree with unique values.
In one operation, you can choose any two nodes at the same level and swap their values.
Return the minimum number of operations needed to make the values at each level sorted in a strictly increasing order.
The level of a node is the number of edges along the path between it and the root node.

Example 1:
Input: root = [1,4,3,7,6,8,5,null,null,null,null,9,null,10]
Output: 3
Explanation:
- Swap 4 and 3. The 2nd level becomes [3,4].
- Swap 7 and 5. The 3rd level becomes [5,6,8,7].
- Swap 8 and 7. The 3rd level becomes [5,6,7,8].
We used 3 operations so return 3.
It can be proven that 3 is the minimum number of operations needed.

Example 2:
Input: root = [1,3,2,7,6,5,4]
Output: 3
Explanation:
- Swap 3 and 2. The 2nd level becomes [2,3].
- Swap 7 and 4. The 3rd level becomes [4,6,5,7].
- Swap 6 and 5. The 3rd level becomes [4,5,6,7].
We used 3 operations so return 3.
It can be proven that 3 is the minimum number of operations needed.

Example 3:
Input: root = [1,2,3,4,5,6]
Output: 0
Explanation: Each level is already sorted in increasing order so return 0.

Constraints:
The number of nodes in the tree is in the range [1, 105].
1 <= Node.val <= 10^5
All the values of the tree are unique.

hints:
1 We can group the values level by level and solve each group independently.
2 Do BFS to group the value level by level.
3 Find the minimum number of swaps to sort the array of each level.
4 While iterating over the array, check the current element, and if not in the correct index, replace that element with the index of the element which should have come.

analysis:
TC: O(N log N)
SC: O(N)
"""
from typing import Optional


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class MinimumNumberOfOperationsToSortaBinaryTreeByLevel:
    def minimumOperations(self, root: Optional[TreeNode]) -> int:
        res = 0
        q = [root]

        def swap():
            cnt = 0
            vals = [n.val for n in q]
            origin_val_idx = {v: i for i, v in enumerate(vals)}
            for sorted_id, sorted_val in enumerate(sorted(vals)):
                origin_id = origin_val_idx[sorted_val]
                another_val = vals[sorted_id]
                if vals[sorted_id] != vals[origin_id]:
                    cnt += 1
                    vals[origin_id], vals[sorted_id] = vals[sorted_id], vals[origin_id]
                    origin_val_idx[sorted_val], origin_val_idx[another_val] = sorted_id, origin_id
            return cnt

        while q:
            res += swap()
            next_q = []
            for node in q:
                if node.left:
                    next_q.append(node.left)
                if node.right:
                    next_q.append(node.right)
            q = next_q
        return res

