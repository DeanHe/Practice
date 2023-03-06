"""
There is a test that has n types of questions. You are given an integer target and a 0-indexed 2D integer array types where types[i] = [counti, marksi] indicates that there are counti questions of the ith type, and each one of them is worth marksi points.

Return the number of ways you can earn exactly target points in the exam. Since the answer may be too large, return it modulo 109 + 7.

Note that questions of the same type are indistinguishable.

For example, if there are 3 questions of the same type, then solving the 1st and 2nd questions is the same as solving the 1st and 3rd questions, or the 2nd and 3rd questions.

Example 1:
Input: target = 6, types = [[6,1],[3,2],[2,3]]
Output: 7
Explanation: You can earn 6 points in one of the seven ways:
- Solve 6 questions of the 0th type: 1 + 1 + 1 + 1 + 1 + 1 = 6
- Solve 4 questions of the 0th type and 1 question of the 1st type: 1 + 1 + 1 + 1 + 2 = 6
- Solve 2 questions of the 0th type and 2 questions of the 1st type: 1 + 1 + 2 + 2 = 6
- Solve 3 questions of the 0th type and 1 question of the 2nd type: 1 + 1 + 1 + 3 = 6
- Solve 1 question of the 0th type, 1 question of the 1st type and 1 question of the 2nd type: 1 + 2 + 3 = 6
- Solve 3 questions of the 1st type: 2 + 2 + 2 = 6
- Solve 2 questions of the 2nd type: 3 + 3 = 6

Example 2:
Input: target = 5, types = [[50,1],[50,2],[50,5]]
Output: 4
Explanation: You can earn 5 points in one of the four ways:
- Solve 5 questions of the 0th type: 1 + 1 + 1 + 1 + 1 = 5
- Solve 3 questions of the 0th type and 1 question of the 1st type: 1 + 1 + 1 + 2 = 5
- Solve 1 questions of the 0th type and 2 questions of the 1st type: 1 + 2 + 2 = 5
- Solve 1 question of the 2nd type: 5

Example 3:
Input: target = 18, types = [[6,1],[3,2],[2,3]]
Output: 1
Explanation: You can only earn 18 points by answering all questions.


Constraints:
1 <= target <= 1000
n == types.length
1 <= n <= 50
types[i].length == 2
1 <= counti, marksi <= 50

hints:
1 Use Dynamic Programming
2 Let ways[i][points] be the number of ways to score a given number of points after solving some questions of the first i types.
3 ways[i][points] is equal to the sum of ways[i-1][points - solved * marks[i] over 0 <= solved <= count_i

analysis:
TC: O(target * len(type) * max(amount))
"""
from functools import cache
from typing import List


class NumberOfWaysToEarnPoints:
    def waysToReachTarget(self, target: int, types: List[List[int]]) -> int:
        MOD = 10 ** 9 + 7

        @cache
        def dfs(i, t):
            if t == 0:
                return 1
            if i >= len(types) or t < 0:
                return 0
            amount, score = types[i]
            return sum(dfs(i + 1, t - j * score) for j in range(amount + 1)) % MOD

        return dfs(0, target)

    def waysToReachTargetDP(self, target: int, types: List[List[int]]) -> int:
        MOD = 10 ** 9 + 7
        dp = [1] + [0] * target
        for amount, score in types:
            for i in range(target, -1, -1):
                for k in range(1, min(amount, i // score) + 1):
                    dp[i] = (dp[i] + dp[i - k * score]) % MOD
        return dp[target]
