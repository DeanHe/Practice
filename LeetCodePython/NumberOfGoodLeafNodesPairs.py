"""
You are given the root of a binary tree and an integer distance. A pair of two different leaf nodes of a binary tree is said to be good if the length of the shortest path between them is less than or equal to distance.

Return the number of good leaf node pairs in the tree.

Example 1:
Input: root = [1,2,3,null,4], distance = 3
Output: 1
Explanation: The leaf nodes of the tree are 3 and 4 and the length of the shortest path between them is 3. This is the only good pair.

Example 2:
Input: root = [1,2,3,4,5,6,7], distance = 3
Output: 2
Explanation: The good pairs are [4,5] and [6,7] with shortest path = 2. The pair [4,6] is not good because the length of ther shortest path between them is 4.

Example 3:
Input: root = [7,1,4,6,null,5,3,null,null,null,null,null,2], distance = 3
Output: 1
Explanation: The only good pair is [2,5].

Constraints:
The number of nodes in the tree is in the range [1, 210].
1 <= Node.val <= 100
1 <= distance <= 10

hints:
1 Start DFS from each leaf node. stop the DFS when the number of steps done > distance.
2 If you reach another leaf node within distance steps, add 1 to the answer.
3 Note that all pairs will be counted twice so divide the answer by 2.

Analysis:
DFS LCA
TC: O(hN^2), where h is the height of the tree and N is the number of nodes.
SC: O(h + N)
"""
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class NumberOfGoodLeafNodesPairs:
    def countPairs(self, root: TreeNode, distance: int) -> int:
        res = 0
        self.root_path = {}
        self.leaves = []

        def dfs(node, path):
            if not node:
                return
            path.append(node)
            if not node.left and not node.right:
                self.leaves.append(node)
                self.root_path[node] = path.copy()
            dfs(node.left, path)
            dfs(node.right, path)
            path.pop()

        dfs(root, [])
        for i in range(len(self.leaves)):
            for j in range(i + 1, len(self.leaves)):
                root_path_a, root_path_b = self.root_path[self.leaves[i]], self.root_path[self.leaves[j]]
                for k in range(min(len(root_path_a), len(root_path_b))):
                    if root_path_a[k] != root_path_b[k]:
                        shortest_dist = len(root_path_a) - k + len(root_path_b) - k
                        if shortest_dist <= distance:
                            res += 1
                        break
        return res

