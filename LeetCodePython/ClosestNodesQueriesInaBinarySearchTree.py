"""
You are given the root of a binary search tree and an array queries of size n consisting of positive integers.

Find a 2D array answer of size n where answer[i] = [mini, maxi]:

mini is the largest value in the tree that is smaller than or equal to queries[i]. If a such value does not exist, add -1 instead.
maxi is the smallest value in the tree that is greater than or equal to queries[i]. If a such value does not exist, add -1 instead.
Return the array answer.

Example 1:
Input: root = [6,2,13,1,4,9,15,null,null,null,null,null,null,14], queries = [2,5,16]
Output: [[2,2],[4,6],[15,-1]]
Explanation: We answer the queries in the following way:
- The largest number that is smaller or equal than 2 in the tree is 2, and the smallest number that is greater or equal than 2 is still 2. So the answer for the first query is [2,2].
- The largest number that is smaller or equal than 5 in the tree is 4, and the smallest number that is greater or equal than 5 is 6. So the answer for the second query is [4,6].
- The largest number that is smaller or equal than 16 in the tree is 15, and the smallest number that is greater or equal than 16 does not exist. So the answer for the third query is [15,-1].

Example 2:
Input: root = [4,null,9], queries = [3]
Output: [[-1,4]]
Explanation: The largest number that is smaller or equal to 3 in the tree does not exist, and the smallest number that is greater or equal to 3 is 4. So the answer for the query is [-1,4].

Constraints:
The number of nodes in the tree is in the range [2, 105].
1 <= Node.val <= 10^6
n == queries.length
1 <= n <= 10^5
1 <= queries[i] <= 10^6

hints:
1 Try to first convert the tree into a sorted array.
2 How do you solve each query in O(log(n)) time using the array of the tree?
"""
from functools import lru_cache
from typing import Optional, List


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def closestNodes(self, root: Optional[TreeNode], queries: List[int]) -> List[List[int]]:
        res = []
        sorted_arr = []

        def dfs(cur):
            if not cur:
                return
            dfs(cur.left)
            sorted_arr.append(cur.val)
            dfs(cur.right)

        def binary_search(target):
            s, e = 0, n - 1
            while s + 1 < e:
                mid = (s + e) // 2
                if sorted_arr[mid] == target:
                    return [target, target]
                elif sorted_arr[mid] < target:
                    s = mid
                else:
                    e = mid
            if sorted_arr[s] == target or sorted_arr[e] == target:
                return [target, target]
            if sorted_arr[e] < target:
                return [sorted_arr[e], -1]
            if target < sorted_arr[s]:
                return [-1, sorted_arr[s]]
            return [sorted_arr[s], sorted_arr[e]]

        dfs(root)
        n = len(sorted_arr)
        for q in queries:
            res.append(binary_search(q))
        return res
