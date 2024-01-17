"""
You are given an array routes representing bus routes where routes[i] is a bus route that the ith bus repeats forever.

For example, if routes[0] = [1, 5, 7], this means that the 0th bus travels in the sequence 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... forever.
You will start at the bus stop source (You are not on any bus initially), and you want to go to the bus stop target. You can travel between bus stops by buses only.

Return the least number of buses you must take to travel from source to target. Return -1 if it is not possible.

Example 1:
Input: routes = [[1,2,7],[3,6,7]], source = 1, target = 6
Output: 2
Explanation: The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.

Example 2:
Input: routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
Output: -1

Constraints:
1 <= routes.length <= 500.
1 <= routes[i].length <= 10^5
All the values of routes[i] are unique.
sum(routes[i].length) <= 10^5
0 <= routes[i][j] < 10^6
0 <= source, target < 10^6

analysis:
BFS by route as node
TC: O(M^2 * K) where M is the count of routes, and K is the maximum stops of a single route
"""
from collections import defaultdict, deque
from typing import List


class BusRoutes:
    def numBusesToDestination(self, routes: List[List[int]], source: int, target: int) -> int:
        if source == target:
            return 0
        stop_to_routes = defaultdict(list)
        for r, route in enumerate(routes):
            for stop in route:
                stop_to_routes[stop].append(r)
        q = deque()
        visited_routes = set()
        for r in stop_to_routes[source]:
            q.append(r)
            visited_routes.add(r)
        res = 1
        while q:
            sz = len(q)
            for _ in range(sz):
                r = q.popleft()
                for stop in routes[r]:
                    if stop == target:
                        return res
                    for nb_r in stop_to_routes[stop]:
                        if nb_r not in visited_routes:
                            q.append(nb_r)
                            visited_routes.add(nb_r)
            res += 1
        return -1

