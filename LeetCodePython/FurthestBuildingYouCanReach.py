"""
You are given an integer array heights representing the heights of buildings, some bricks, and some ladders.

You start your journey from building 0 and move to the next building by possibly using bricks or ladders.

While moving from building i to building i+1 (0-indexed),

If the current building's height is greater than or equal to the next building's height, you do not need a ladder or bricks.
If the current building's height is less than the next building's height, you can either use one ladder or (h[i+1] - h[i]) bricks.
Return the furthest building index (0-indexed) you can reach if you use the given ladders and bricks optimally.

Example 1:
Input: heights = [4,2,7,6,9,14,12], bricks = 5, ladders = 1
Output: 4
Explanation: Starting at building 0, you can follow these steps:
- Go to building 1 without using ladders nor bricks since 4 >= 2.
- Go to building 2 using 5 bricks. You must use either bricks or ladders because 2 < 7.
- Go to building 3 without using ladders nor bricks since 7 >= 6.
- Go to building 4 using your only ladder. You must use either bricks or ladders because 6 < 9.
It is impossible to go beyond building 4 because you do not have any more bricks or ladders.

Example 2:
Input: heights = [4,12,2,7,3,18,20,3,19], bricks = 10, ladders = 2
Output: 7

Example 3:
Input: heights = [14,3,19,3], bricks = 17, ladders = 0
Output: 3


Constraints:
1 <= heights.length <= 105
1 <= heights[i] <= 106
0 <= bricks <= 109
0 <= ladders <= heights.length

hint:
1 Assume the problem is to check whether you can reach the last building or not.
2 You'll have to do a set of jumps, and choose for each one whether to do it using a ladder or bricks.
It's always optimal to use ladders in the largest jumps.
3 Iterate on the buildings, maintaining the largest r jumps and the sum of the remaining ones so far, and stop whenever this sum exceeds b.

analysis:
use priority queue to track the gap from greatest to smallest. and use ladders to cover them in order
"""
import heapq
from typing import List


class FurthestBuildingYouCanReach:
    def furthestBuilding(self, heights: List[int], bricks: int, ladders: int) -> int:
        i, total_gap = 0, 0
        pq = []
        while i + 1 < len(heights):
            if heights[i] < heights[i + 1]:
                gap = heights[i + 1] - heights[i]
                heapq.heappush(pq, -gap)
                total_gap += gap
                if total_gap > bricks:
                    if ladders > 0:
                        ladders -= 1
                        total_gap -= -heapq.heappop(pq)
                    else:
                        return i
            i += 1
        return i

