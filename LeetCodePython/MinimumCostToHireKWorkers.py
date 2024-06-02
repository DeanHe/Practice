"""
There are n workers. You are given two integer arrays quality and wage where quality[i] is the quality of the ith worker and wage[i] is the minimum wage expectation for the ith worker.

We want to hire exactly k workers to form a paid group. To hire a group of k workers, we must pay them according to the following rules:

Every worker in the paid group must be paid at least their minimum wage expectation.
In the group, each worker's pay must be directly proportional to their quality. This means if a worker’s quality is double that of another worker in the group, then they must be paid twice as much as the other worker.
Given the integer k, return the least amount of money needed to form a paid group satisfying the above conditions. Answers within 10-5 of the actual answer will be accepted.

Example 1:
Input: quality = [10,20,5], wage = [70,50,30], k = 2
Output: 105.00000
Explanation: We pay 70 to 0th worker and 35 to 2nd worker.

Example 2:
Input: quality = [3,1,10,10,1], wage = [4,8,2,2,7], k = 3
Output: 30.66667
Explanation: We pay 4 to 0th worker, 13.33333 to 2nd and 3rd workers separately.


Constraints:
n == quality.length == wage.length
1 <= k <= n <= 10^4
1 <= quality[i], wage[i] <= 10^4

analysis:
to minimize the total wage, we want a small ratio.
So we sort all workers with their expected ratio, and pick up K first worker.
Now we have a minimum possible ratio for K worker and we count their total quality.

As we pick up next worker with bigger ratio, we increase the ratio for whole group.
Meanwhile we remove a worker with highest quality so that we keep K workers in the group.
We calculate the current ratio * total quality = total wage for this group.

We redo the process and we can find the minimum total wage.
Because workers are sorted by ratio of wage/quality.
For every ratio, we find the minimum possible total quality of K workers.

tag: sort + heap
TC: O(NlogN)
"""
import heapq
import math
from typing import List

class MinimumCostToHireKWorkers:
    def mincostToHireWorkers(self, quality: List[int], wage: List[int], k: int) -> float:
        sz = len(quality)
        res = math.inf
        workers = []
        for i in range(sz):
            workers.append((wage[i] / quality[i], quality[i], wage[i]))
        workers.sort()
        pq = []
        quality_total = 0
        for worker in workers:
            ratio, quality, wage = worker
            if len(pq) >= k:
                quality_most= -heapq.heappop(pq)
                quality_total -= quality_most
            heapq.heappush(pq, -quality)
            quality_total += quality
            if len(pq) == k:
                res = min(res, quality_total * ratio)
        return res
