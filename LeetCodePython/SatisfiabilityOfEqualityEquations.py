"""
You are given an array of strings equations that represent relationships between variables where each string equations[i] is of length 4 and takes one of two different forms: "xi==yi" or "xi!=yi".Here, xi and yi are lowercase letters (not necessarily different) that represent one-letter variable names.

Return true if it is possible to assign integers to variable names so as to satisfy all the given equations, or false otherwise.



Example 1:

Input: equations = ["a==b","b!=a"]
Output: false
Explanation: If we assign say, a = 1 and b = 1, then the first equation is satisfied, but not the second.
There is no way to assign the variables to satisfy both equations.
Example 2:

Input: equations = ["b==a","a==b"]
Output: true
Explanation: We could assign a = 1 and b = 1 to satisfy both equations.

Constraints:
1 <= equations.length <= 500
equations[i].length == 4
equations[i][0] is a lowercase letter.
equations[i][1] is either '=' or '!'.
equations[i][2] is '='.
equations[i][3] is a lowercase letter.
"""
from typing import List


class SatisfiabilityOfEqualityEquations:
    def equationsPossible(self, equations: List[str]) -> bool:
        parent = list(range(26))

        def find_root(x):
            root = x
            while root != parent[root]:
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

        for s in equations:
            if s[1] == '=':
                union(ord(s[0]) - ord('a'), ord(s[3]) - ord('a'))

        for s in equations:
            if s[1] == '!':
                root_a, root_b = find_root(ord(s[0]) - ord('a')), find_root(ord(s[3]) - ord('a'))
                if root_a == root_b:
                    return False
        return True
