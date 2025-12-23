"""
You are given a circular array balance of length n, where balance[i] is the net balance of person i.

In one move, a person can transfer exactly 1 unit of balance to either their left or right neighbor.

Return the minimum number of moves required so that every person has a non-negative balance. If it is impossible, return -1.

Note: You are guaranteed that at most 1 index has a negative balance initially.

Example 1:
Input: balance = [5,1,-4]
Output: 4

Explanation:
One optimal sequence of moves is:
Move 1 unit from i = 1 to i = 2, resulting in balance = [5, 0, -3]
Move 1 unit from i = 0 to i = 2, resulting in balance = [4, 0, -2]
Move 1 unit from i = 0 to i = 2, resulting in balance = [3, 0, -1]
Move 1 unit from i = 0 to i = 2, resulting in balance = [2, 0, 0]
Thus, the minimum number of moves required is 4.

Example 2:
Input: balance = [1,2,-5,2]
Output: 6

Explanation:
One optimal sequence of moves is:
Move 1 unit from i = 1 to i = 2, resulting in balance = [1, 1, -4, 2]
Move 1 unit from i = 1 to i = 2, resulting in balance = [1, 0, -3, 2]
Move 1 unit from i = 3 to i = 2, resulting in balance = [1, 0, -2, 1]
Move 1 unit from i = 3 to i = 2, resulting in balance = [1, 0, -1, 0]
Move 1 unit from i = 0 to i = 1, resulting in balance = [0, 1, -1, 0]
Move 1 unit from i = 1 to i = 2, resulting in balance = [0, 0, 0, 0]
Thus, the minimum number of moves required is 6.

Example 3:
Input: balance = [-3,2]
Output: -1
Explanation:
It is impossible to make all balances non-negative for balance = [-3, 2], so the answer is -1.


Constraints:
1 <= n == balance.length <= 10^5
-109 <= balance[i] <= 10^9
There is at most one negative value in balance initially.

hints:
1 If there is no negative value, then the answer is 0. If the total sum is less than 0, then the answer is -1.
2 Sort the positive values in nums by their distance from the index with the negative value.
3 Greedily use as many values as needed from the sorted nums to offset the current negative value.

analysis:
TC: O(N)
j means initial negative index
"""
from typing import List


class MinimumMovesToBalanceCircularArray:
    def minMoves(self, balance: List[int]) -> int:
        res = 0
        sz = len(balance)
        j = -1
        total = 0
        for i, bal in enumerate(balance):
            if bal < 0:
                j = i
            total += bal
        if total < 0:
            return -1
        if j == -1:
            return 0
        d = 0
        while balance[j] < 0:
            d += 1
            compensate = balance[(j + d) % sz] + balance[j - d]
            res += min(-balance[j], compensate) * d
            balance[j] += compensate
        return res