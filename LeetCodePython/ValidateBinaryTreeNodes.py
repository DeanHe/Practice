"""
You have n binary tree nodes numbered from 0 to n - 1 where node i has two children leftChild[i] and rightChild[i],
return true if and only if all the given nodes form exactly one valid binary tree.
If node i has no left child then leftChild[i] will equal -1, similarly for the right child.
Note that the nodes have no values and that we only use the node numbers in this problem.

Example 1:
Input: n = 4, leftChild = [1,-1,3,-1], rightChild = [2,-1,-1,-1]
Output: true

Example 2:
Input: n = 4, leftChild = [1,-1,3,-1], rightChild = [2,3,-1,-1]
Output: false

Example 3:
Input: n = 2, leftChild = [1,0], rightChild = [-1,-1]
Output: false

Constraints:
n == leftChild.length == rightChild.length
1 <= n <= 10^4
-1 <= leftChild[i], rightChild[i] <= n - 1
"""
import collections
from typing import List


class ValidateBinaryTreeNodes:
    def validateBinaryTreeNodes(self, n: int, leftChild: List[int], rightChild: List[int]) -> bool:
        root = 0
        children = set(leftChild + rightChild)
        for i in range(n):
            if i not in children:
                root = i
        visited = set()
        q = collections.deque([root])
        while q:
            cur = q.popleft()
            if cur in visited:
                return False
            visited.add(cur)
            if leftChild[cur] != -1:
                q.append(leftChild[cur])
            if rightChild[cur] != -1:
                q.append(rightChild[cur])
        return len(visited) == n