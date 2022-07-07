"""
Given an array of integers arr, you are initially positioned at the first index of the array.

In one step you can jump from index i to index:

i + 1 where: i + 1 < arr.length.
i - 1 where: i - 1 >= 0.
j where: arr[i] == arr[j] and i != j.
Return the minimum number of steps to reach the last index of the array.

Notice that you can not jump outside of the array at any time.

Example 1:
Input: arr = [100,-23,-23,404,100,23,23,23,3,404]
Output: 3
Explanation: You need three jumps from index 0 --> 4 --> 3 --> 9. Note that index 9 is the last index of the array.

Example 2:
Input: arr = [7]
Output: 0
Explanation: Start index is the last index. You do not need to jump.

Example 3:
Input: arr = [7,6,9,6,9,6,9,7]
Output: 1
Explanation: You can jump directly from index 0 to index 7 which is last index of the array.

Constraints:
1 <= arr.length <= 5 * 104
-108 <= arr[i] <= 108

hint:
1 Build a graph of n nodes where nodes are the indices of the array and edges for node i are nodes i+1, i-1, j where arr[i] == arr[j].
2 Start bfs from node 0 and keep distance. The answer is the distance when you reach node n-1.
"""
import collections
from typing import List


class JumpGameIV:
    def minJumps(self, arr: List[int]) -> int:
        n = len(arr)
        visited = [False] * n
        graph = collections.defaultdict(list)
        for i, num in enumerate(arr):
            graph[num].append(i)
        q = collections.deque()
        q.append(0)
        visited[0] = True
        steps = 0
        while q:
            sz = len(q)
            for _ in range(sz):
                cur = q.popleft()
                if cur == n - 1:
                    return steps
                for nb in [cur - 1, cur + 1] + graph[arr[cur]]:
                    if 0 <= nb < n and not visited[nb]:
                        q.append(nb)
                        visited[nb] = True
                graph[arr[cur]].clear()
            steps += 1
        return -1

