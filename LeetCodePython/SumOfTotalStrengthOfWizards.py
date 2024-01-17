"""
As the ruler of a kingdom, you have an army of wizards at your command.

You are given a 0-indexed integer array strength, where strength[i] denotes the strength of the ith wizard. For a contiguous group of wizards (i.e. the wizards' strengths form a subarray of strength), the total strength is defined as the product of the following two values:

The strength of the weakest wizard in the group.
The total of all the individual strengths of the wizards in the group.
Return the sum of the total strengths of all contiguous groups of wizards. Since the answer may be very large, return it modulo 109 + 7.

A subarray is a contiguous non-empty sequence of elements within an array.

Example 1:
Input: strength = [1,3,1,2]
Output: 44
Explanation: The following are all the contiguous groups of wizards:
- [1] from [1,3,1,2] has a total strength of min([1]) * sum([1]) = 1 * 1 = 1
- [3] from [1,3,1,2] has a total strength of min([3]) * sum([3]) = 3 * 3 = 9
- [1] from [1,3,1,2] has a total strength of min([1]) * sum([1]) = 1 * 1 = 1
- [2] from [1,3,1,2] has a total strength of min([2]) * sum([2]) = 2 * 2 = 4
- [1,3] from [1,3,1,2] has a total strength of min([1,3]) * sum([1,3]) = 1 * 4 = 4
- [3,1] from [1,3,1,2] has a total strength of min([3,1]) * sum([3,1]) = 1 * 4 = 4
- [1,2] from [1,3,1,2] has a total strength of min([1,2]) * sum([1,2]) = 1 * 3 = 3
- [1,3,1] from [1,3,1,2] has a total strength of min([1,3,1]) * sum([1,3,1]) = 1 * 5 = 5
- [3,1,2] from [1,3,1,2] has a total strength of min([3,1,2]) * sum([3,1,2]) = 1 * 6 = 6
- [1,3,1,2] from [1,3,1,2] has a total strength of min([1,3,1,2]) * sum([1,3,1,2]) = 1 * 7 = 7
The sum of all the total strengths is 1 + 9 + 1 + 4 + 4 + 4 + 3 + 5 + 6 + 7 = 44.

Example 2:
Input: strength = [5,4,6]
Output: 213
Explanation: The following are all the contiguous groups of wizards:
- [5] from [5,4,6] has a total strength of min([5]) * sum([5]) = 5 * 5 = 25
- [4] from [5,4,6] has a total strength of min([4]) * sum([4]) = 4 * 4 = 16
- [6] from [5,4,6] has a total strength of min([6]) * sum([6]) = 6 * 6 = 36
- [5,4] from [5,4,6] has a total strength of min([5,4]) * sum([5,4]) = 4 * 9 = 36
- [4,6] from [5,4,6] has a total strength of min([4,6]) * sum([4,6]) = 4 * 10 = 40
- [5,4,6] from [5,4,6] has a total strength of min([5,4,6]) * sum([5,4,6]) = 4 * 15 = 60
The sum of all the total strengths is 25 + 16 + 36 + 36 + 40 + 60 = 213.

Constraints:
1 <= strength.length <= 10^5
1 <= strength[i] <= 10^9

hints:
1 Consider the contribution of each wizard to the answer.
2 Can you efficiently calculate the total contribution to the answer for all subarrays that end at each index?
3 Denote the total contribution of all subarrays ending at index i as solve[i]. Can you express solve[i] in terms of solve[m] for some m < i?

analysis:
prefix sum + monotonic stack
TC: O(N)
"""
from typing import List


class SumOfTotalStrengthOfWizards:
    def totalStrength(self, strength: List[int]) -> int:
        MOD = 10 ** 9 + 7
        sz = len(strength)
        stack = []
        res = 0
        total = 0
        acc_pre_sum = [0] * (sz + 2)
        for r in range(sz + 1):
            n = 0
            if r < sz:
                n = strength[r]
            total += n
            acc_pre_sum[r + 1] = acc_pre_sum[r] + total
            while stack and n < strength[stack[-1]]:
                i = stack.pop()
                l = -1
                if stack:
                    l = stack[-1]
                left_acc = acc_pre_sum[i]
                if l >= 0:
                    left_acc = acc_pre_sum[i] - acc_pre_sum[l]
                right_acc = acc_pre_sum[r] - acc_pre_sum[i]
                left_cnt, right_cnt = i - l, r - i
                res = (res + strength[i] * (right_acc * left_cnt - left_acc * right_cnt) % MOD) % MOD
            stack.append(r)
        return res