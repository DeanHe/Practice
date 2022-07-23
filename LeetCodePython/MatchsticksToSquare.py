"""
You are given an integer array matchsticks where matchsticks[i] is the length of the ith matchstick.
You want to use all the matchsticks to make one square. You should not break any stick, but you can link them up, and each matchstick must be used exactly one time.
Return true if you can make this square and false otherwise.

Example 1:
Input: matchsticks = [1,1,2,2,2]
Output: true
Explanation: You can form a square with length 2, one side of the square came two sticks with length 1.

Example 2:
Input: matchsticks = [3,3,3,3,4]
Output: false
Explanation: You cannot find a way to form a square with all the matchsticks.

Constraints:
1 <= matchsticks.length <= 15
1 <= matchsticks[i] <= 108

hint:
1 Treat the matchsticks as an array. Can we split the array into 4 equal halves?
2 Every matchstick can belong to either of the 4 sides. We don't know which one. Maybe try out all options!
3 For every matchstick, we have to try out each of the 4 options i.e. which side it can belong to. We can make use of recursion for this.
"""
from typing import List


class MatchsticksToSquare:
    def makesquare(self, matchsticks: List[int]) -> bool:
        if len(matchsticks) < 4:
            return False
        matchsticks.sort()
        total = sum(matchsticks)
        if total % 4 != 0:
            return False
        side = total // 4
        square = [0] * 4

        def dfs(idx):
            for n in square:
                if n > side:
                    return False
            if idx == -1:
                return True
            for i in range(4):
                if i > 0 and square[i] == square[i - 1]:
                    continue
                square[i] += matchsticks[idx]
                if dfs(idx - 1):
                    return True
                square[i] -= matchsticks[idx]
            return False

        return dfs(len(matchsticks) - 1)