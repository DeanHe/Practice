"""
You are given two 0-indexed strings source and target, both of length n and consisting of lowercase English characters. You are also given two 0-indexed string arrays original and changed, and an integer array cost, where cost[i] represents the cost of converting the string original[i] to the string changed[i].

You start with the string source. In one operation, you can pick a substring x from the string, and change it to y at a cost of z if there exists any index j such that cost[j] == z, original[j] == x, and changed[j] == y. You are allowed to do any number of operations, but any pair of operations must satisfy either of these two conditions:

The substrings picked in the operations are source[a..b] and source[c..d] with either b < c or d < a. In other words, the indices picked in both operations are disjoint.
The substrings picked in the operations are source[a..b] and source[c..d] with a == c and b == d. In other words, the indices picked in both operations are identical.
Return the minimum cost to convert the string source to the string target using any number of operations. If it is impossible to convert source to target, return -1.

Note that there may exist indices i, j such that original[j] == original[i] and changed[j] == changed[i].

Example 1:
Input: source = "abcd", target = "acbe", original = ["a","b","c","c","e","d"], changed = ["b","c","b","e","b","e"], cost = [2,5,5,1,2,20]
Output: 28
Explanation: To convert "abcd" to "acbe", do the following operations:
- Change substring source[1..1] from "b" to "c" at a cost of 5.
- Change substring source[2..2] from "c" to "e" at a cost of 1.
- Change substring source[2..2] from "e" to "b" at a cost of 2.
- Change substring source[3..3] from "d" to "e" at a cost of 20.
The total cost incurred is 5 + 1 + 2 + 20 = 28.
It can be shown that this is the minimum possible cost.

Example 2:
Input: source = "abcdefgh", target = "acdeeghh", original = ["bcd","fgh","thh"], changed = ["cde","thh","ghh"], cost = [1,3,5]
Output: 9
Explanation: To convert "abcdefgh" to "acdeeghh", do the following operations:
- Change substring source[1..3] from "bcd" to "cde" at a cost of 1.
- Change substring source[5..7] from "fgh" to "thh" at a cost of 3. We can do this operation because indices [5,7] are disjoint with indices picked in the first operation.
- Change substring source[5..7] from "thh" to "ghh" at a cost of 5. We can do this operation because indices [5,7] are disjoint with indices picked in the first operation, and identical with indices picked in the second operation.
The total cost incurred is 1 + 3 + 5 = 9.
It can be shown that this is the minimum possible cost.

Example 3:
Input: source = "abcdefgh", target = "addddddd", original = ["bcd","defgh"], changed = ["ddd","ddddd"], cost = [100,1578]
Output: -1
Explanation: It is impossible to convert "abcdefgh" to "addddddd".
If you select substring source[1..3] as the first operation to change "abcdefgh" to "adddefgh", you cannot select substring source[3..7] as the second operation because it has a common index, 3, with the first operation.
If you select substring source[3..7] as the first operation to change "abcdefgh" to "abcddddd", you cannot select substring source[1..3] as the second operation because it has a common index, 3, with the first operation.

Constraints:
1 <= source.length == target.length <= 1000
source, target consist only of lowercase English characters.
1 <= cost.length == original.length == changed.length <= 100
1 <= original[i].length == changed[i].length <= source.length
original[i], changed[i] consist only of lowercase English characters.
original[i] != changed[i]
1 <= cost[i] <= 10^6

hints:
1 Give each unique string in original and changed arrays a unique id.
There are at most 2 * m unique strings in total where m is the length of the arrays. We can put them into a hash map to assign ids.
2 We can pre-compute the smallest costs between all pairs of unique strings using Floyd Warshall algorithm in O(m ^ 3) time complexity.
3 Let dp[i] be the smallest cost to change the first i characters (prefix) of source into target,
leaving the suffix untouched. We have dp[0] = 0. dp[i] = min(dp[i - 1] if (source[i - 1] == target[i - 1]),
dp[j-1] + cost[x][y] where x is the id of source[j..(i - 1)] and y is the id of target e[j..(i - 1)])
). If neither of the two conditions is satisfied, dp[i] = infinity.
4 We can use Trie to check for the second condition in O(1).
5 The answer is dp[n] where n is source.length.
"""
from typing import List


class MinimumCostToConvertStringII:
    def minimumCost(self, source: str, target: str, original: List[str], changed: List[str], cost: List[int]) -> int:
        idx = {}
        for sub in original:
            if sub not in idx:
                idx[sub] = len(idx)
        for sub in changed:
            if sub not in idx:
                idx[sub] = len(idx)
        idx_size = len(idx)
        dist = [[None] * idx_size for _ in range(idx_size)]
        for i in range(len(original)):
            s = idx[original[i]]
            e = idx[changed[i]]
            if not dist[s][e]:
                dist[s][e] = cost[i]
            else:
                dist[s][e] = min(dist[s][e], cost[i])
        for k in range(idx_size):
            for i in range(idx_size):
                for j in range(idx_size):
                    if dist[i][k] and dist[k][j]:
                        if not dist[i][j]:
                            dist[i][j] = dist[i][k] + dist[k][j]
                        else:
                            dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j])
        dp = [float('inf')] * (len(target) + 1)
        dp[0] = 0
        sub_lens_set = set(len(sub) for sub in changed)
        for i in range(len(target)):
            if dp[i] != float('inf'):
                if source[i] == target[i]:
                    dp[i + 1] = min(dp[i + 1], dp[i])
                for l in sub_lens_set:
                    if i + l < len(dp):
                        sub_s = source[i:i + l]
                        sub_e = target[i:i + l]
                        if sub_s in idx:
                            s = idx[sub_s]
                        else:
                            s = -1
                        if sub_e in idx:
                            e = idx[sub_e]
                        else:
                            e = -1
                        if s >= 0 and e >= 0 and dist[s][e]:
                            dp[i + l] = min(dp[i + l], dp[i] + dist[s][e])
        if dp[-1] != float('inf'):
            return dp[-1]
        else:
            return -1
