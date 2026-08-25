"""
You are given a circular integer array nums of length n.

An index i is a peak if its value is strictly greater than its neighbors:

The previous neighbor of i is nums[i - 1] if i > 0, otherwise nums[n - 1].
The next neighbor of i is nums[i + 1] if i < n - 1, otherwise nums[0].
You are allowed to perform the following operation any number of times:

Choose any index i and increase nums[i] by 1.
Return an integer denoting the minimum number of operations required to make the array contain at least k peaks. If it is impossible, return -1.

Example 1:
Input: nums = [2,1,2], k = 1
Output: 1

Explanation:
To achieve at least k = 1 peak, we can increase nums[2] = 2 to 3.
After this operation, nums[2] = 3 is strictly greater than its neighbors nums[0] = 2 and nums[1] = 1.
Therefore, the minimum number of operations required is 1.

Example 2:
Input: nums = [4,5,3,6], k = 2
Output: 0

Explanation:
The array already contains at least k = 2 peaks with zero operations.
Index 1: nums[1] = 5 is strictly greater than its neighbors nums[0] = 4 and nums[2] = 3.
Index 3: nums[3] = 6 is strictly greater than its neighbors nums[2] = 3 and nums[0] = 4.
Therefore, the minimum number of operations required is 0.

Example 3:
Input: nums = [3,7,3], k = 2
Output: -1

Explanation:
It is impossible to have at least k = 2 peaks in this array. Therefore, the answer is -1.


Constraints:
2 <= n == nums.length <= 5000
-10^5 <= nums[i] <= 10^5
0 <= k <= n

hints:
1 Use dynamic programming.
2 There are a few cases to handle: when the first element is a peak, when the last element is a peak, and when neither the first nor the last element is a peak.
3 After fixing the case, solve with dynamic programming states dp[i][j], where dp[i][j] is the minimum number of operations needed to make j peaks from the first i values in nums.

analysis:
convert circular array to linear array <cost> to analysis:
case 1: skip taking idx 0
case 2: taking idx 0
TC:O(n * k)
"""
import math


class MinimumOperationsToAchieveAtLeastKPeaks:
    def minOperations(self, nums: list[int], k: int) -> int:
        res = math.inf
        n = len(nums)
        if k > n // 2:
            return -1
        if k == 0:
            return 0
        if n == 2:
            return 1 if nums[0] == nums[1] else 0
        cost = [0] * n
        for i in range(n):
            pre = nums[(i - 1 + n) % n]
            nxt = nums[(i + 1) % n]
            cost[i] = max(0, max(pre, nxt) + 1 - nums[i])

        def dfs(l, r, t):
            # dp[i][j] means minimum cost using first i elements to pick j peaks
            dp = [[math.inf] * (t + 1) for _ in range(n + 2)]
            dp[l][0] = 0
            for i in range(l, r + 1):
                for j in range(t + 1):
                    if dp[i][j] != math.inf:
                        # skip current index
                        dp[i + 1][j] = min(dp[i + 1][j], dp[i][j])
                        # take current index
                        if j + 1 <= t:
                            dp[i + 2][j + 1] = min(dp[i + 2][j + 1], dp[i][j] + cost[i])
            return min(dp[r + 1][t], dp[r + 2][t])

        # case 1: skip index 0
        res = min(res, dfs(1, n - 1, k))

        # casa 2: take index 0
        if k >= 1:
            res = min(res, cost[0] + dfs(2, n - 2, k - 1))
        return res if res != math.inf else -1


