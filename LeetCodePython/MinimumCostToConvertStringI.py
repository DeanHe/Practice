"""
You are given two 0-indexed strings source and target, both of length n and consisting of lowercase English letters. You are also given two 0-indexed character arrays original and changed, and an integer array cost, where cost[i] represents the cost of changing the character original[i] to the character changed[i].

You start with the string source. In one operation, you can pick a character x from the string and change it to the character y at a cost of z if there exists any index j such that cost[j] == z, original[j] == x, and changed[j] == y.

Return the minimum cost to convert the string source to the string target using any number of operations. If it is impossible to convert source to target, return -1.

Note that there may exist indices i, j such that original[j] == original[i] and changed[j] == changed[i].

Example 1:
Input: source = "abcd", target = "acbe", original = ["a","b","c","c","e","d"], changed = ["b","c","b","e","b","e"], cost = [2,5,5,1,2,20]
Output: 28
Explanation: To convert the string "abcd" to string "acbe":
- Change value at index 1 from 'b' to 'c' at a cost of 5.
- Change value at index 2 from 'c' to 'e' at a cost of 1.
- Change value at index 2 from 'e' to 'b' at a cost of 2.
- Change value at index 3 from 'd' to 'e' at a cost of 20.
The total cost incurred is 5 + 1 + 2 + 20 = 28.
It can be shown that this is the minimum possible cost.

Example 2:
Input: source = "aaaa", target = "bbbb", original = ["a","c"], changed = ["c","b"], cost = [1,2]
Output: 12
Explanation: To change the character 'a' to 'b' change the character 'a' to 'c' at a cost of 1, followed by changing the character 'c' to 'b' at a cost of 2, for a total cost of 1 + 2 = 3. To change all occurrences of 'a' to 'b', a total cost of 3 * 4 = 12 is incurred.

Example 3:
Input: source = "abcd", target = "abce", original = ["a"], changed = ["e"], cost = [10000]
Output: -1
Explanation: It is impossible to convert source to target because the value at index 3 cannot be changed from 'd' to 'e'.

Constraints:
1 <= source.length == target.length <= 10^5
source, target consist of lowercase English letters.
1 <= cost.length == original.length == changed.length <= 2000
original[i], changed[i] are lowercase English letters.
1 <= cost[i] <= 10^6
original[i] != changed[i]

hints:
1 Construct a graph with each letter as a node, and construct an edge (a, b) with weight c if we can change from character a to letter b with cost c.
(Keep the one with the smallest cost in case there are multiple edges between a and b).
2 Calculate the shortest path for each pair of characters (source[i], target[i]). The sum of cost over all i in the range [0, source.length - 1].
If there is no path between source[i] and target[i], the answer is -1.
3 Any shortest path algorithms will work since we only have 26 nodes. Since we only have at most 26 * 26 pairs, we can save the result to avoid re-calculation.
4 We can also use Floyd Warshall's algorithm to precompute all the results.

Analysis：
Floyd Warshall's algorithm
Let n be the length of source and m be the length of the original array.
Populating minCosts with the initial conversion costs takes O(m) time.
Each of the three nested loops runs 26 times. Thus, the overall time taken is O(26^3）= O(1).
To calculate the totalCost, the algorithm loops over the source string, which takes linear time.
Thus, the time complexity of the algorithm is O(m)+O(1)+O(n), which simplifies to O(m+n).
"""
from typing import List


class MinimumCostToConvertStringI:
    def minimumCost(self, source: str, target: str, original: List[str], changed: List[str], cost: List[int]) -> int:
        res = 0
        dist = [[None] * 26 for _ in range(26)]
        for i in range(len(original)):
            s = ord(original[i]) - ord('a')
            e = ord(changed[i]) - ord('a')
            if not dist[s][e]:
                dist[s][e] = cost[i]
            else:
                dist[s][e] = min(dist[s][e], cost[i])
        for k in range(26):
            for i in range(26):
                for j in range(26):
                    if dist[i][k] and dist[k][j]:
                        if not dist[i][j]:
                            dist[i][j] = dist[i][k] + dist[k][j]
                        else:
                            dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j])
        for i in range(len(source)):
            if source[i] != target[i]:
                s = ord(source[i]) - ord('a')
                e = ord(target[i]) - ord('a')
                if not dist[s][e]:
                    return -1
                res += dist[s][e]
        return res
