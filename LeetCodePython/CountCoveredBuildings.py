"""
You are given a positive integer n, representing an n x n city. You are also given a 2D grid buildings, where buildings[i] = [x, y] denotes a unique building located at coordinates [x, y].

A building is covered if there is at least one building in all four directions: left, right, above, and below.

Return the number of covered buildings.

Example 1:
Input: n = 3, buildings = [[1,2],[2,2],[3,2],[2,1],[2,3]]

Output: 1
Explanation:
Only building [2,2] is covered as it has at least one building:
above ([1,2])
below ([3,2])
left ([2,1])
right ([2,3])
Thus, the count of covered buildings is 1.

Example 2:
Input: n = 3, buildings = [[1,1],[1,2],[2,1],[2,2]]
Output: 0
Explanation:
No building has at least one building in all four directions.

Example 3:
Input: n = 5, buildings = [[1,3],[3,2],[3,3],[3,5],[5,3]]

Output: 1
Explanation:
Only building [3,3] is covered as it has at least one building:
above ([1,3])
below ([5,3])
left ([3,2])
right ([3,5])
Thus, the count of covered buildings is 1.


Constraints:
2 <= n <= 10^5
1 <= buildings.length <= 10^5
buildings[i] = [x, y]
1 <= x, y <= n
All coordinates of buildings are unique.

hints:
1 Group buildings with the same x or y value together, and sort each group.
2 In each sorted list, the buildings that are not at the first or last positions are covered in that direction.
"""
from bisect import bisect_left
from collections import defaultdict
from typing import List


class CountCoveredBuildings:
    def countCoveredBuildings(self, n: int, buildings: List[List[int]]) -> int:
        res = 0
        x_axis = defaultdict(list)
        y_axis = defaultdict(list)
        for x, y in buildings:
            x_axis[x].append(y)
            y_axis[y].append(x)
        for ls in x_axis.values():
            ls.sort()
        for ls in y_axis.values():
            ls.sort()
        for x, y in buildings:
            idx_y = bisect_left(x_axis[x], y)
            idx_x = bisect_left(y_axis[y], x)
            if idx_y != 0 and idx_y != len(x_axis[x]) - 1 and idx_x != 0 and idx_x != len(y_axis[y]) - 1:
                res += 1
        return res