"""
In the universe Earth C-137, Rick discovered a special form of magnetic force between two balls if they are put in his new invented basket. Rick has n empty baskets, the ith basket is at position[i], Morty has m balls and needs to distribute the balls into the baskets such that the minimum magnetic force between any two balls is maximum.
Rick stated that magnetic force between two different balls at positions x and y is |x - y|.
Given the integer array position and the integer m. Return the required force.

Example 1:
Input: position = [1,2,3,4,7], m = 3
Output: 3
Explanation: Distributing the 3 balls into baskets 1, 4 and 7 will make the magnetic force between ball pairs [3, 3, 6]. The minimum magnetic force is 3. We cannot achieve a larger minimum magnetic force than 3.

Example 2:
Input: position = [5,4,3,2,1,1000000000], m = 2
Output: 999999999
Explanation: We can use baskets 1 and 1000000000.

Constraints:
n == position.length
2 <= n <= 10^5
1 <= position[i] <= 10^9
All integers in position are distinct.
2 <= m <= position.length

hints:
1 If you can place balls such that the answer is x then you can do it for y where y < x.
2 Similarly if you cannot place balls such that the answer is x then you can do it for y where y > x.
3 Binary search on the answer and greedily see if it is possible.
"""
from typing import List


class MagneticForceBetweenTwoBalls:
    def maxDistance(self, position: List[int], m: int) -> int:
        position.sort()
        s, e = 0, position[-1]

        def can_place(min_gap):
            dist = 0
            balls = 1
            for i in range(1, len(position)):
                dist += position[i] - position[i - 1]
                if dist >= min_gap:
                    balls += 1
                    dist = 0
                    if balls >= m:
                        return True
            return False

        while s + 1 < e:
            mid = (s + e) // 2
            if can_place(mid):
                s = mid
            else:
                e = mid
        if can_place(e):
            return e
        if can_place(s):
            return s
        return 0
