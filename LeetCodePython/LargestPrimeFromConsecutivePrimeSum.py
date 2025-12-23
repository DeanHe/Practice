"""
You are given an integer n.

Return the largest prime number less than or equal to n that can be expressed as the sum of one or more consecutive prime numbers starting from 2. If no such number exists, return 0.

Example 1:
Input: n = 20
Output: 17

Explanation:
The prime numbers less than or equal to n = 20 which are consecutive prime sums are:

2 = 2
5 = 2 + 3
17 = 2 + 3 + 5 + 7
The largest is 17, so it is the answer.

Example 2:
Input: n = 2
Output: 2

Explanation:
The only consecutive prime sum less than or equal to 2 is 2 itself.

Constraints:
1 <= n <= 5 * 10^5

hints:
1 Generate all prime numbers up to n (use sieve or trial division).
2 Compute consecutive sums starting from 2 until the sum exceeds n.
3 Find the largest sum that is also a prime.
"""

class LargestPrimeFromConsecutivePrimeSum:
    def largestPrime(self, n: int) -> int:
        res = 0
        is_prime = [True] * (n + 1)
        is_prime[0] = False
        is_prime[1] = False
        for i in range(2, int(n ** 0.5) + 1):
            if is_prime[i]:
                for j in range(i * i, n + 1, i):
                    is_prime[j] = False
        total = 0
        for i in range(n + 1):
            if is_prime[i]:
                total += i
                if total <= n and is_prime[total]:
                    res = max(res, total)
                if total > n:
                    break
        return res

