"""
There are several stones arranged in a row, and each stone has an associated value which is an integer given in the array stoneValue.

In each round of the game, Alice divides the row into two non-empty rows (i.e. left row and right row), then Bob calculates the value of each row which is the sum of the values of all the stones in this row. Bob throws away the row which has the maximum value, and Alice's score increases by the value of the remaining row. If the value of the two rows are equal, Bob lets Alice decide which row will be thrown away. The next round starts with the remaining row.

The game ends when there is only one stone remaining. Alice's score is initially zero.

Return the maximum score that Alice can obtain.

Example 1:
Input: stoneValue = [6,2,3,4,5,5]
Output: 18
Explanation: In the first round, Alice divides the row to [6,2,3], [4,5,5]. The left row has the value 11 and the right row has value 14. Bob throws away the right row and Alice's score is now 11.
In the second round Alice divides the row to [6], [2,3]. This time Bob throws away the left row and Alice's score becomes 16 (11 + 5).
The last round Alice has only one choice to divide the row which is [2], [3]. Bob throws away the right row and Alice's score is now 18 (16 + 2). The game ends because only one stone is remaining in the row.

Example 2:
Input: stoneValue = [7,7,7,7,7,7,7]
Output: 28

Example 3:
Input: stoneValue = [4]
Output: 0

Constraints:
1 <= stoneValue.length <= 500
1 <= stoneValue[i] <= 10^6

hints:
1 We need to try all possible divisions for the current row to get the max score.
2 As calculating all possible divisions will lead us to calculate some sub-problems more than once, we need to think of dynamic programming.

analysis:
DP dfs + pruning
TC:O(N^3)
"""
from functools import cache
from typing import List


class StoneGameV:
    def stoneGameV(self, stoneValue: List[int]) -> int:
        pre_sum = [0]
        for val in stoneValue:
            pre_sum.append(pre_sum[-1] + val)

        @cache
        def dfs(l, r):
            if l == r:
                return 0
            if l + 1 == r:
                return min(stoneValue[l], stoneValue[r])
            res = 0
            left = 0
            right = pre_sum[r + 1] - pre_sum[l]
            for i in range(l, r):
                left += stoneValue[i]
                right -= stoneValue[i]
                if left > right:
                    if res >= 2 * right:
                        continue
                    res = max(res, right + dfs(i + 1, r))
                elif left < right:
                    if res >= 2 * left:
                        continue
                    res = max(res, left + dfs(l, i))
                else:
                    res = max(res, right + dfs(i + 1, r), left + dfs(l, i))
            return res

        return dfs(0, len(stoneValue) - 1)
