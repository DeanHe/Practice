"""
Two strings X and Y are similar if we can swap two letters (in different positions) of X, so that it equals Y. Also two strings X and Y are similar if they are equal.

For example, "tars" and "rats" are similar (swapping at positions 0 and 2), and "rats" and "arts" are similar, but "star" is not similar to "tars", "rats", or "arts".

Together, these form two connected groups by similarity: {"tars", "rats", "arts"} and {"star"}.  Notice that "tars" and "arts" are in the same group even though they are not similar.  Formally, each group is such that a word is in the group if and only if it is similar to at least one other word in the group.

We are given a list strs of strings where every string in strs is an anagram of every other string in strs. How many groups are there?

Example 1:
Input: strs = ["tars","rats","arts","star"]
Output: 2

Example 2:
Input: strs = ["omv","ovm"]
Output: 1

Constraints:
1 <= strs.length <= 300
1 <= strs[i].length <= 300
strs[i] consists of lowercase letters only.
All words in strs have the same length and are anagrams of each other.
"""
from typing import List


class Solution:
    def numSimilarGroups(self, strs: List[str]) -> int:
        n = len(strs)
        self.group = n
        parent = list(range(n))

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
                parent[root_a] = root_b
                self.group -= 1

        def is_similar(a, b):
            if len(a) != len(b):
                return False
            a_len = len(a)
            diff = 0
            first = second = -1
            for i in range(a_len):
                if a[i] != b[i]:
                    diff += 1
                    if diff == 1:
                        first = i
                    if diff == 2:
                        second = i
                if diff > 2:
                    return False
                if diff == 2:
                    if a[first] != b[second]:
                        return False
            return True

        for i in range(n):
            for j in range(i + 1, n):
                if is_similar(strs[i], strs[j]):
                    union(i, j)
        return self.group