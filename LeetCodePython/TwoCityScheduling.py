"""
There are 2N people a company is planning to interview. The cost of flying the i-th person to city A is costs[i][0], and the cost of flying the i-th person to city B is costs[i][1].
Return the minimum cost to fly every person to a city such that exactly N people arrive in each city.

Example 1:
Input: [[10,20],[30,200],[400,50],[30,20]]
Output: 110
Explanation:
The first person goes to city A for a cost of 10.
The second person goes to city A for a cost of 30.
The third person goes to city B for a cost of 50.
The fourth person goes to city B for a cost of 20.

The total minimum cost is 10 + 30 + 50 + 20 = 110 to have half the people interviewing in each city.

Note:
1 <= costs.length <= 100
It is guaranteed that costs.length is even.
1 <= costs[i][0], costs[i][1] <= 1000

analysis:
sort: TC: O(N log N)
"""
from typing import List


class TwoCityScheduling:
    def twoCitySchedCost(self, costs: List[List[int]]) -> int:
        res = 0
        costs.sort(key=lambda a: a[0] - a[1])
        for i, [cost_a, cost_b] in enumerate(costs):
            if i < len(costs) // 2:
                res += cost_a
            else:
                res += cost_b
        return res
