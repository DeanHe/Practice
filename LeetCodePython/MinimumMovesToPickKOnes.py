"""
You are given a 0-indexed binary array nums of length n, a positive integer k and a non-negative integer maxChanges.

Dylan Smith plays a game, where the goal is for Dylan to pick up k ones from nums using the minimum number of moves. When the game starts, Dylan picks up any index dylanIndex in the range [0, n - 1] and stands there. If nums[dylanIndex] == 1 , Dylan picks up the one and nums[dylanIndex] becomes 0(this does not count as a move).
After this, Dylan can make any number of moves (including zero) where in each move Dylan must perform exactly one of the following actions:

Select any index j != dylanIndex such that nums[j] == 0 and set nums[j] = 1. This action can be performed at most maxChanges times.
Select any two adjacent indices x and y (|x - y| == 1) such that nums[x] == 1, nums[y] == 0, then swap their values (set nums[y] = 1 and nums[x] = 0). If y == dylanIndex, Dylan picks up the one after this move and nums[y] becomes 0.
Return the minimum number of moves required by Dylan to pick exactly k ones.

Example 1:
Input: nums = [1,1,0,0,0,1,1,0,0,1], k = 3, maxChanges = 1

Output: 3

Explanation: Dylan can pick up 3 ones in 3 moves, if Dylan performs the following actions in each move when standing at dylanIndex == 1:

 At the start of the game Dylan picks up the one and nums[1] becomes 0. nums becomes [1,0,1,0,0,1,1,0,0,1].
Select j == 2 and perform an action of the first type. nums becomes [1,0,1,0,0,1,1,0,0,1]
Select x == 2 and y == 1, and perform an action of the second type. nums becomes [1,1,0,0,0,1,1,0,0,1]. As y == dylanIndex, Dylan picks up the one and nums becomes [1,0,0,0,0,1,1,0,0,1].
Select x == 0 and y == 1, and perform an action of the second type. nums becomes [0,1,0,0,0,1,1,0,0,1]. As y == dylanIndex, Dylan picks up the one and nums becomes [0,0,0,0,0,1,1,0,0,1].
Note that it may be possible for Dylan to pick up 3 ones using some other sequence of 3 moves.

Example 2:
Input: nums = [0,0,0,0], k = 2, maxChanges = 3

Output: 4
Explanation: Dylan can pick up 2 ones in 4 moves, if Dylan performs the following actions in each move when standing at dylanIndex == 0:

Select j == 1 and perform an action of the first type. nums becomes [0,1,0,0].
Select x == 1 and y == 0, and perform an action of the second type. nums becomes [1,0,0,0]. As y == dylanIndex, Dylan picks up the one and nums becomes [0,0,0,0].
Select j == 1 again and perform an action of the first type. nums becomes [0,1,0,0].
Select x == 1 and y == 0 again, and perform an action of the second type. nums becomes [1,0,0,0]. As y == dylanIndex, Dylan picks up the one and nums becomes [0,0,0,0].

Constraints:
2 <= n <= 10^5
0 <= nums[i] <= 1
1 <= k <= 10^5
0 <= maxChanges <= 10^5
maxChanges + sum(nums) >= k

hints:
1 Ones created using a change require 2 moves. Hence except for the immediate neighbors of the index where we move all the ones, we should try to use change operations.
2 For some subset of ones, it is always better to pick dylanIndex to the median position.
3 We only need to be concerned with the indices where nums[i] == 1.
"""
from math import inf
from typing import List


class MinimumMovesToPickKOnes:
    def minimumMoves(self, nums: List[int], k: int, maxChanges: int) -> int:
        res = inf
        ones_idx = [i for i, num in enumerate(nums) if num == 1]
        ones_cnt = len(ones_idx)
        pre_sum = [0] * (ones_cnt + 1)
        for i in range(ones_cnt):
            pre_sum[i + 1] = pre_sum[i] + ones_idx[i]
        m = max(0, k - maxChanges)
        for window in range(m, min(ones_cnt, m + 3, k) + 1):
            for i in range(ones_cnt - window + 1):
                cur = pre_sum[i + window] - pre_sum[i + window - window // 2] - (pre_sum[i + window // 2] - pre_sum[i])
                res = min(res, cur + (k - window) * 2)
        return res
