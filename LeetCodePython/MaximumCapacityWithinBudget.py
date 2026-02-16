"""
You are given two integer arrays costs and capacity, both of length n, where costs[i] represents the purchase cost of the ith machine and capacity[i] represents its performance capacity.

You are also given an integer budget.

You may select at most two distinct machines such that the total cost of the selected machines is strictly less than budget.

Return the maximum achievable total capacity of the selected machines.

Example 1:
Input: costs = [4,8,5,3], capacity = [1,5,2,7], budget = 8
Output: 8

Explanation:
Choose two machines with costs[0] = 4 and costs[3] = 3.
The total cost is 4 + 3 = 7, which is strictly less than budget = 8.
The maximum total capacity is capacity[0] + capacity[3] = 1 + 7 = 8.

Example 2:
Input: costs = [3,5,7,4], capacity = [2,4,3,6], budget = 7
Output: 6

Explanation:
Choose one machine with costs[3] = 4.
The total cost is 4, which is strictly less than budget = 7.
The maximum total capacity is capacity[3] = 6.

Example 3:
Input: costs = [2,2,2], capacity = [3,5,4], budget = 5
Output: 9

Explanation:
Choose two machines with costs[1] = 2 and costs[2] = 2.
The total cost is 2 + 2 = 4, which is strictly less than budget = 5.
The maximum total capacity is capacity[1] + capacity[2] = 5 + 4 = 9.


Constraints:
1 <= n == costs.length == capacity.length <= 10^5
1 <= costs[i], capacity[i] <= 10^5
1 <= budget <= 2 * 10^5

hints:
1 Sort machines by increasing costs, keeping capacity aligned.
2 Build a prefix array where prefMax[i] stores the maximum capacity among machines with index <= i.
3 For selecting one machine, take the maximum capacity among all machines with cost < budget.
4 For selecting two machines, fix machine i and binary search the largest index j < i with costs[j] < budget - costs[i].
5 Update the answer with capacity[i] + prefMax[j] whenever such j exists.

analysis:
two pointer, sort, pre max
TC: O(NlogN + N)
"""
from typing import List


class MaximumCapacityWithinBudget:
    def maxCapacity(self, costs: List[int], capacity: List[int], budget: int) -> int:
        sz = len(costs)
        res = 0
        cost_cap = sorted(zip(costs, capacity))
        pre_max_cap = []
        for _, cap in cost_cap:
            if not pre_max_cap:
                pre_max_cap.append(cap)
            else:
                pre_max_cap.append(max(pre_max_cap[-1], cap))
        j = sz - 1
        for i in range(sz):
            cost, cap = cost_cap[i]
            if cost >= budget:
                break
            res = max(res, cap)
            while j >= 0 and cost_cap[j][0] + cost >= budget:
                j -= 1
            # make sure the other machine index is < i
            another = min(i - 1, j)
            if another >= 0:
                res = max(res, cap + pre_max_cap[another])
        return res


