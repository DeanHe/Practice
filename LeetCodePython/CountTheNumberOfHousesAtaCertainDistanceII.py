"""
You are given three positive integers n, x, and y.

In a city, there exist houses numbered 1 to n connected by n streets. There is a street connecting the house numbered i with the house numbered i + 1 for all 1 <= i <= n - 1 . An additional street connects the house numbered x with the house numbered y.

For each k, such that 1 <= k <= n, you need to find the number of pairs of houses (house1, house2) such that the minimum number of streets that need to be traveled to reach house2 from house1 is k.

Return a 1-indexed array result of length n where result[k] represents the total number of pairs of houses such that the minimum streets required to reach one house from the other is k.

Note that x and y can be equal.

Example 1:
Input: n = 3, x = 1, y = 3
Output: [6,0,0]
Explanation: Let's look at each pair of houses:
- For the pair (1, 2), we can go from house 1 to house 2 directly.
- For the pair (2, 1), we can go from house 2 to house 1 directly.
- For the pair (1, 3), we can go from house 1 to house 3 directly.
- For the pair (3, 1), we can go from house 3 to house 1 directly.
- For the pair (2, 3), we can go from house 2 to house 3 directly.
- For the pair (3, 2), we can go from house 3 to house 2 directly.

Example 2:
Input: n = 5, x = 2, y = 4
Output: [10,8,2,0,0]
Explanation: For each distance k the pairs are:
- For k == 1, the pairs are (1, 2), (2, 1), (2, 3), (3, 2), (2, 4), (4, 2), (3, 4), (4, 3), (4, 5), and (5, 4).
- For k == 2, the pairs are (1, 3), (3, 1), (1, 4), (4, 1), (2, 5), (5, 2), (3, 5), and (5, 3).
- For k == 3, the pairs are (1, 5), and (5, 1).
- For k == 4 and k == 5, there are no pairs.

Example 3:
Input: n = 4, x = 1, y = 1
Output: [6,4,2,0]
Explanation: For each distance k the pairs are:
- For k == 1, the pairs are (1, 2), (2, 1), (2, 3), (3, 2), (3, 4), and (4, 3).
- For k == 2, the pairs are (1, 3), (3, 1), (2, 4), and (4, 2).
- For k == 3, the pairs are (1, 4), and (4, 1).
- For k == 4, there are no pairs.

Constraints:
2 <= n <= 10^5
1 <= x, y <= n

hints:
1 If there were no additional street connecting house x to house y, there would be 2 * (n - i) pairs of houses at distance i.
2 The shortest distance between house i and house j (j < i) is along one of these paths: - i -> j - i -> y---x -> j
3 Try to change the distances calculated by path i ->j to the other path.
4 Can we use prefix sums to compute the answer?

analysis:
prefix sum, sweep line
TC: O(N)
"""
from itertools import accumulate
from math import inf
from typing import List


class CountTheNumberOfHousesAtaCertainDistanceII:
    def countOfPairs(self, n: int, x: int, y: int) -> List[int]:
        x, y = min(x, y), max(x, y)
        axis = [0] * n
        for i in range(1, n + 1):
            # start moving on two direction for each position i
            axis[0] += 2
            # end one direction when reach position 1
            axis[min(i - 1, x + abs(i - y))] -= 1
            # end one direction when reach position n
            axis[min(n - i, 1 + n - y + abs(i - x))] -= 1
            # split one more direction when reach position x
            axis[min(abs(i - x), abs(i - y) + 1)] += 1
            # split one more direction when reach position x
            axis[min(abs(i - y), abs(i - x) + 1)] += 1
            # stores the distance to the closest of x or y
            r = max(x - i, 0) + max(i - y, 0)
            # end one direction when reach position in mid of x and y
            axis[r + (y - x) // 2] -= 1
            axis[r + (y - x + 1) // 2] -= 1
        return list(accumulate(axis))

    def countOfPairsFloyed(self, n: int, x: int, y: int) -> List[int]:
        res = [0] * n
        dist = [[inf] * n for _ in range(n)]
        for i in range(n - 1):
            dist[i][i + 1] = 1
            dist[i + 1][i] = 1
        if x != y:
            dist[x - 1][y - 1] = 1
            dist[y - 1][x - 1] = 1
        for k in range(n):
            for i in range(n):
                for j in range(n):
                    dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j])
        for i in range(n):
            for j in range(n):
                if dist[i][j] != inf and i != j:
                    res[(int)(dist[i][j]) - 1] += 1
        return res

