"""
We want to split a group of n people (labeled from 1 to n) into two groups of any size. Each person may dislike some other people, and they should not go into the same group.

Given the integer n and the array dislikes where dislikes[i] = [ai, bi] indicates that the person labeled ai does not like the person labeled bi, return true if it is possible to split everyone into two groups in this way.

Example 1:
Input: n = 4, dislikes = [[1,2],[1,3],[2,4]]
Output: true
Explanation: group1 [1,4] and group2 [2,3].

Example 2:
Input: n = 3, dislikes = [[1,2],[1,3],[2,3]]
Output: false

Example 3:
Input: n = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
Output: false

Constraints:
1 <= n <= 2000
0 <= dislikes.length <= 10^4
dislikes[i].length == 2
1 <= dislikes[i][j] <= n
ai < bi
All the pairs of dislikes are unique.
"""
from collections import defaultdict, deque
from typing import List


class PossibleBipartition:
    def possibleBipartition(self, n: int, dislikes: List[List[int]]) -> bool:
        graph = defaultdict(list)
        group = {}
        q = deque()
        for a, b in dislikes:
            graph[a].append(b)
            graph[b].append(a)
        for i in range(1, n + 1):
            if i not in group:
                q.append(i)
                group[i] = 1
                while q:
                    cur = q.popleft()
                    color = group[cur]
                    for nb in graph[cur]:
                        if nb in group:
                            if group[nb] == color:
                                return False
                        else:
                            group[nb] = -color
                            q.append(nb)
        return len(group) == n