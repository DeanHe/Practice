"""
You are given a 0-indexed integer array nums, and you are allowed to traverse between its indices. You can traverse between index i and index j, i != j, if and only if gcd(nums[i], nums[j]) > 1, where gcd is the greatest common divisor.

Your task is to determine if for every pair of indices i and j in nums, where i < j, there exists a sequence of traversals that can take us from i to j.

Return true if it is possible to traverse between all such pairs of indices, or false otherwise.

Example 1:
Input: nums = [2,3,6]
Output: true
Explanation: In this example, there are 3 possible pairs of indices: (0, 1), (0, 2), and (1, 2).
To go from index 0 to index 1, we can use the sequence of traversals 0 -> 2 -> 1, where we move from index 0 to index 2 because gcd(nums[0], nums[2]) = gcd(2, 6) = 2 > 1, and then move from index 2 to index 1 because gcd(nums[2], nums[1]) = gcd(6, 3) = 3 > 1.
To go from index 0 to index 2, we can just go directly because gcd(nums[0], nums[2]) = gcd(2, 6) = 2 > 1. Likewise, to go from index 1 to index 2, we can just go directly because gcd(nums[1], nums[2]) = gcd(3, 6) = 3 > 1.

Example 2:
Input: nums = [3,9,5]
Output: false
Explanation: No sequence of traversals can take us from index 0 to index 2 in this example. So, we return false.

Example 3:
Input: nums = [4,3,12,8]
Output: true
Explanation: There are 6 possible pairs of indices to traverse between: (0, 1), (0, 2), (0, 3), (1, 2), (1, 3), and (2, 3). A valid sequence of traversals exists for each pair, so we return true.

Constraints:
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^5

hints:
1 Create a (prime) factor-numbers list for all the indices.
2 Add an edge between the neighbors of the (prime) factor-numbers list. The order of the numbers doesn’t matter.
We only need edges between 2 neighbors instead of edges for all pairs.
3 The problem is now similar to checking if all the numbers (nodes of the graph) are in the same connected component.
4 Any algorithm (i.e., BFS, DFS, or Union-Find Set) should work to find or check connected components

analysis:
In addition to the original n nodes, add a dummy node for each prime number not exceeding MAX_VAL
Union Find
TC: O(N * log(Max_Val))
"""
from typing import List


class GreatestCommonDivisorTraversal:
    def canTraverseAllPairs(self, nums: List[int]) -> bool:
        num_len = len(nums)
        MAX = 10 ** 5
        nums_set = set(nums)
        if num_len == 1:
            return True
        if 1 in nums_set:
            return False
        prime_factor = [0] * (MAX + 1)
        for i in range(2, MAX + 1):
            if prime_factor[i] == 0:
                for j in range(i, MAX + 1, i):
                    prime_factor[j] = i

        #DSU
        parent = list(range(2 * MAX + 1))
        size = [1] * (2 * MAX + 1)

        def find_root(x):
            root = x
            while parent[root] != root:
                root = parent[root]
            while parent[x] != root:
                fa = parent[x]
                parent[x] = root
                x = fa
            return root

        def union(a, b):
            root_a, root_b = find_root(a), find_root(b)
            if root_a != root_b:
                if size[root_a] > size[root_b]:
                    parent[root_b] = root_a
                    size[root_a] += size[root_b]
                    size[root_b] = 0
                else:
                    parent[root_a] = root_b
                    size[root_b] += size[root_a]
                    size[root_a] = 0

        for num in nums:
            tmp = num
            while tmp > 1:
                prime = prime_factor[tmp]
                root = MAX + prime
                if find_root(root) != find_root(num):
                    union(root, num)
                while tmp % prime == 0:
                    tmp //= prime

        groups = 0
        for num in range(2, MAX + 1):
            if num in nums_set and find_root(num) == num:
                groups += 1
        return groups == 1

