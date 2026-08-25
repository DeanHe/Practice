"""
You are given an integer array nums of length n.

Alice and Bob are playing a game. Alice chooses:

An integer k such that k > 1.
Two integers l and r such that 0 <= l <= r < n.
Initially, both Alice's and Bob's scores are 0.

For each index i in the range [l, r] (inclusive):

If nums[i] is divisible by k, Alice's score increases by nums[i].
Otherwise, Bob's score increases by nums[i].
The score difference is Alice's score minus Bob's score.

Alice wants to maximize the score difference. If there are multiple values of k that achieve the maximum score difference, she chooses the smallest such k.

Return the product of the maximum score difference and the chosen value of k. Since the result can be large, return it modulo 10^9 + 7.

Example 1:
Input: nums = [1,4,6,8]
Output: 36

Explanation:
Alice can choose k = 2, l = 1, and r = 3.
All values in nums[1..3] are divisible by 2, so Alice's score is 4 + 6 + 8 = 18, while Bob's score is 0.
The score difference is 18, which is the maximum possible. Among all values of k that achieve this score difference, the smallest is 2.
Therefore, the answer is 18 * 2 = 36.

Example 2:
Input: nums = [2,1,2]
Output: 6

Explanation:
Alice can choose k = 2, l = 0, and r = 2.
The values nums[0] and nums[2] are divisible by 2, so Alice's score is 2 + 2 = 4. The value nums[1] is not divisible by 2, so Bob's score is 1.
The score difference is 4 - 1 = 3, which is the maximum possible. Among all values of k that achieve this score difference, the smallest is 2.
Therefore, the answer is 3 * 2 = 6.

Example 3:
Input: nums = [1]
Output: 1000000005

Explanation:
Alice must choose some k > 1. The smallest possible choice is k = 2.
Since nums[0] is not divisible by 2, Alice's score is 0, while Bob's score is 1.
The score difference is -1, which is the maximum possible.
Therefore, the answer is -1 * 2 = -2. Modulo 109 + 7, this equals 1000000005.

Constraints:
1 <= nums.length <= 1000
1 <= nums[i] <= 10^6

hints:
1 For a fixed k, transform each nums[i] into nums[i] if it is divisible by k, and into -nums[i] otherwise.
2 After this transformation, the best range [l, r] is the maximum subarray sum of the transformed array.
3 It is enough to check values of k that divide at least one element of nums. Also handle k = 2 for cases where no element is divisible by any chosen k.
4 Enumerate all divisors greater than one from the elements of nums, and run Kadane's algorithm for each candidate k.
5 When comparing candidates, maximize the score difference first, then choose the smaller k in case of a tie.

analysis:
kadane's algorithm
TC:O(N*K), where k is the number of distinct divisors
"""
import math


class DivisibleGame:
    def divisibleGame(self, nums: list[int]) -> int:
        MOD = 10 ** 9 + 7
        best_score = -min(nums)
        best_divisor = 2
        divisors = set()

        def divisible(num):
            i = 2
            while i * i <= num:
                while num % i == 0:
                    divisors.add(i)
                    num //= i
                i += 1
            if num > 1:
                divisors.add(num)

        for num in nums:
            divisible(num)
        for d in divisors:
            cur = 0
            accu = -math.inf
            for num in nums:
                if num % d == 0:
                    val = num
                else:
                    val = -num
                cur = max(val, cur + val)
                accu = max(accu, cur)
            if accu > best_score:
                best_score = accu
                best_divisor = d
            elif accu == best_score and d < best_divisor:
                best_divisor = d
        best_score = (best_score % MOD + MOD) % MOD
        return (best_score * best_divisor) % MOD