"""
You are given an array nums of n positive integers and an integer k.

Initially, you start with a score of 1. You have to maximize your score by applying the following operation at most k times:

Choose any non-empty subarray nums[l, ..., r] that you haven't chosen previously.
Choose an element x of nums[l, ..., r] with the highest prime score. If multiple such elements exist, choose the one with the smallest index.
Multiply your score by x.
Here, nums[l, ..., r] denotes the subarray of nums starting at index l and ending at the index r, both ends being inclusive.

The prime score of an integer x is equal to the number of distinct prime factors of x. For example, the prime score of 300 is 3 since 300 = 2 * 2 * 3 * 5 * 5.

Return the maximum possible score after applying at most k operations.

Since the answer may be large, return it modulo 10^9 + 7.

Example 1:
Input: nums = [8,3,9,3,8], k = 2
Output: 81
Explanation: To get a score of 81, we can apply the following operations:
- Choose subarray nums[2, ..., 2]. nums[2] is the only element in this subarray. Hence, we multiply the score by nums[2]. The score becomes 1 * 9 = 9.
- Choose subarray nums[2, ..., 3]. Both nums[2] and nums[3] have a prime score of 1, but nums[2] has the smaller index. Hence, we multiply the score by nums[2]. The score becomes 9 * 9 = 81.
It can be proven that 81 is the highest score one can obtain.

Example 2:
Input: nums = [19,12,14,6,10,18], k = 3
Output: 4788
Explanation: To get a score of 4788, we can apply the following operations:
- Choose subarray nums[0, ..., 0]. nums[0] is the only element in this subarray. Hence, we multiply the score by nums[0]. The score becomes 1 * 19 = 19.
- Choose subarray nums[5, ..., 5]. nums[5] is the only element in this subarray. Hence, we multiply the score by nums[5]. The score becomes 19 * 18 = 342.
- Choose subarray nums[2, ..., 3]. Both nums[2] and nums[3] have a prime score of 2, but nums[2] has the smaller index. Hence, we multipy the score by nums[2]. The score becomes 342 * 14 = 4788.
It can be proven that 4788 is the highest score one can obtain.


Constraints:
1 <= nums.length == n <= 10^5
1 <= nums[i] <= 10^5
1 <= k <= min(n * (n + 1) / 2, 10^9)

hints:
1 Calculate nums[i]'s prime score s[i] by factoring in O(sqrt(nums[i])) time.
2 For each nums[i], find the nearest index left[i] on the left (if any) such that s[left[i]] >= s[i]. if none is found, set left[i] to -1. Similarly, find the nearest index right[i] on the right (if any) such that s[right[i]] > s[i]. If none is found, set right[i] to n.
3 Use a monotonic stack to compute right[i] and left[i].
4 For each index i, if left[i] + 1 <= l <= i <= r <= right[i] - 1, then s[i] is the maximum value in the range [l, r]. For this particular i, there are ranges[i] = (i - left[i]) * (right[i] - i) ranges where index i will be chosen.
5 Loop over all elements of nums by non-increasing prime score, each element will be chosen min(ranges[i], remainingK) times, where reaminingK denotes the number of remaining operations. Therefore, the score will be multiplied by s[i]^min(ranges[i],remainingK).
6 Use fast exponentiation to quickly calculate A^B mod C.
"""
from typing import List


class Solution:
    MOD = 10 ** 9 + 7

    def maximumScore(self, nums: List[int], k: int) -> int:
        res = 1
        n = len(nums)
        upper_bound = max(nums) + 1

        prime = [True] * upper_bound
        prime_score = [0] * upper_bound
        prime[0] = prime[1] = False

        for i in range(2, upper_bound):
            if prime[i]:
                for j in range(i, upper_bound, i):
                    prime[j] = False
                    prime_score[j] += 1

        next_greater_or_equal_element = [0] * n
        st = []
        for i in range(n - 1, -1, -1):
            while st and prime_score[nums[i]] >= prime_score[nums[st[-1]]]:
                st.pop()
            next_greater_or_equal_element[i] = st[-1] if st else n
            st.append(i)

        prev_greater_element = [0] * n
        st.clear()
        for i in range(n):
            while st and prime_score[nums[i]] > prime_score[nums[st[-1]]]:
                st.pop()
            prev_greater_element[i] = st[-1] if st else -1
            st.append(i)

        tuples = [[nums[i], i] for i in range(n)]
        tuples.sort(reverse=True)
        for num, idx in tuples:
            operations = min((idx - prev_greater_element[idx]) * (next_greater_or_equal_element[idx] - idx), k)
            res = (res * self.pow(num, operations)) % self.MOD
            k -= operations
            if k == 0:
                return res

        return res

    def pow(self, x: int, n: int) -> int:
        res = 1
        while n > 0:
            if n % 2 == 1:
                res = (res * x) % self.MOD
            x = (x * x) % self.MOD
            n //= 2
        return res
