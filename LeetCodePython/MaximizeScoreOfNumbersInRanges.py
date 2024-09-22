"""
You are given an array of integers start and an integer d, representing n intervals [start[i], start[i] + d].
You are asked to choose n integers where the ith integer must belong to the ith interval. The score of the chosen integers is defined as the minimum absolute difference between any two integers that have been chosen.
Return the maximum possible score of the chosen integers.

Example 1:
Input: start = [6,0,3], d = 2
Output: 4
Explanation:
The maximum possible score can be obtained by choosing integers: 8, 0, and 4. The score of these chosen integers is min(|8 - 0|, |8 - 4|, |0 - 4|) which equals 4.

Example 2:
Input: start = [2,6,13,13], d = 5
Output: 5
Explanation:
The maximum possible score can be obtained by choosing integers: 2, 7, 13, and 18. The score of these chosen integers is min(|2 - 7|, |2 - 13|, |2 - 18|, |7 - 13|, |7 - 18|, |13 - 18|) which equals 5.

Constraints:
2 <= start.length <= 10^5
0 <= start[i] <= 10^9
0 <= d <= 10^9

hints:
1 Can we use binary search here?
2 Suppose that the answer is x. We can find a valid configuration of integers by sorting start, the first integer should be start[0], then each subsequent integer should be the smallest one in [start[i], start[i] + d] that is greater than last_chosen_value + x.
3 Binary search over x

Analysis:
TC: O(NlogN) + O(NlogM) where N is length of start, M is max_diff: start[-1] + d - start[0]
"""
import math
from typing import List


class MaximizeScoreOfNumbersInRanges:
    def maxPossibleScore(self, start: List[int], d: int) -> int:
        start.sort()
        s = 0
        e = start[-1] + d - start[0]
        def can_make(target):
            pre_pick = start[0]
            for i in range(1, len(start)):
                cur_pick = max(pre_pick + target, start[i])
                if cur_pick <= start[i] + d:
                    pre_pick = cur_pick
                else:
                    return False
            return True

        while s + 1 < e:
            mid = (s + e) // 2
            if can_make(mid):
                s = mid
            else:
                e = mid
        if can_make(e):
            return e
        return s

