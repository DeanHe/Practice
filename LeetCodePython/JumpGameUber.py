"""
Given an array of integers arr, you are initially positioned at the first index of the array.

In one step you can jump from index i to index:

i + 1 where: i + 1 < arr.length.
i + prime number ended with 3

return the minimum steps to reach the last index of the array (index n - 1)
"""


class JumpGameUber:

    def max_jump_score(nums):
        n = len(nums)

        def sieve(n):
            is_prime = [True] * n
            is_prime[0] = is_prime[1] = False
            for i in range(2, int(n ** 0.5) + 1):
                if is_prime[i]:
                    for j in range(i * i, n, i):
                        is_prime[j] = False
            return is_prime

        is_prime = sieve(n)
        valid = set(i for i in range(n) if is_prime[i] and i % 10 == 3)

        dp = [0] * n
        best = float('-inf')  # min(dp[j] - j)

        for i in range(1, n):
            # step 1: 从 i-1 来
            dp[i] = dp[i - 1] + 1

            # step 2: 从质数点跳 dp[i] = min(dp[i], dp[j] + (i - j))
            if best != float('-inf'):
                dp[i] = min(dp[i], best + i)

            # 更新 best（如果 i 是合法点）
            if i in valid:
                best = min(best, dp[i] - i)

        return dp[n-1]
