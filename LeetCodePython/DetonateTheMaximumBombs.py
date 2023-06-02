"""
You are given a list of bombs. The range of a bomb is defined as the area where its effect can be felt. This area is in the shape of a circle with the center as the location of the bomb.

The bombs are represented by a 0-indexed 2D integer array bombs where bombs[i] = [xi, yi, ri]. xi and yi denote the X-coordinate and Y-coordinate of the location of the ith bomb, whereas ri denotes the radius of its range.

You may choose to detonate a single bomb. When a bomb is detonated, it will detonate all bombs that lie in its range. These bombs will further detonate the bombs that lie in their ranges.

Given the list of bombs, return the maximum number of bombs that can be detonated if you are allowed to detonate only one bomb.

Example 1:
Input: bombs = [[2,1,3],[6,1,4]]
Output: 2
Explanation:
The above figure shows the positions and ranges of the 2 bombs.
If we detonate the left bomb, the right bomb will not be affected.
But if we detonate the right bomb, both bombs will be detonated.
So the maximum bombs that can be detonated is max(1, 2) = 2.

Example 2:
Input: bombs = [[1,1,5],[10,10,5]]
Output: 1
Explanation:
Detonating either bomb will not detonate the other bomb, so the maximum number of bombs that can be detonated is 1.

Example 3:
Input: bombs = [[1,2,3],[2,3,1],[3,4,2],[4,5,3],[5,6,4]]
Output: 5
Explanation:
The best bomb to detonate is bomb 0 because:
- Bomb 0 detonates bombs 1 and 2. The red circle denotes the range of bomb 0.
- Bomb 2 detonates bomb 3. The blue circle denotes the range of bomb 2.
- Bomb 3 detonates bomb 4. The green circle denotes the range of bomb 3.
Thus all 5 bombs are detonated.

Constraints:
1 <= bombs.length <= 100
bombs[i].length == 3
1 <= xi, yi, ri <= 10^5

hints:
1 How can we model the relationship between different bombs? Can "graphs" help us?
2 Bombs are nodes and are connected to other bombs in their range by directed edges.
3 If we know which bombs will be affected when any bomb is detonated, how can we find the total number of bombs that will be detonated if we start from a fixed bomb?
4 Run a Depth First Search (DFS) from every node, and all the nodes it reaches are the bombs that will be detonated.
"""
from collections import defaultdict
from typing import List


class DetonateTheMaximumBombs:
    def maximumDetonation(self, bombs: List[List[int]]) -> int:
        n = len(bombs)
        res = 0
        graph = defaultdict(list)
        for i in range(n):
            for j in range(n):
                if i != j:
                    if bombs[i][2] ** 2 >= (bombs[i][0] - bombs[j][0]) ** 2 + (bombs[i][1] - bombs[j][1]) ** 2:
                        graph[i].append(j)

        def dfs(cur, visited):
            for nb in graph[cur]:
                if nb not in visited:
                    visited.add(nb)
                    dfs(nb, visited)

        for i in range(n):
            visited = set([i])
            dfs(i, visited)
            res = max(res, len(visited))
        return res
