"""
You are given a binary string s consisting only of characters '0' and '1'.

A string is balanced if it contains an equal number of '0's and '1's.

You can perform at most one swap between any two characters in s. Then, you select a balanced substring from s.

Return an integer representing the maximum length of the balanced substring you can select.

A substring is a contiguous sequence of characters within a string.

Example 1:
Input: s = "100001"
Output: 4

Explanation:
Swap "100001". The string becomes "101000".
Select the substring "101000", which is balanced because it has two '0's and two '1's.

Example 2:
Input: s = "111"
Output: 0

Explanation:
Choose not to perform any swaps.
Select the empty substring, which is balanced because it has zero '0's and zero '1's.

Constraints:
1 <= s.length <= 10^5
s consists only of the characters '0' and '1'.

hints:
1 A balanced substring has the same number of 0s and 1s.
2 Even after one swap, the answer cannot exceed 2 * min(cnt0, cnt1).
3 The swap is only useful if it can improve one chosen window.
4 Look for a substring whose balance can be fixed by moving one mismatched character in or out.
5 Track prefix balance and check how far a valid segment can extend after one swap.

analysis:
prefix sum
TC:O(N)
"""


class LongestBalancedSubstringAfterOneSwap:
    def longestBalanced(self, s: str) -> int:
        n = len(s)
        ones = zeros = 0
        pre_ones = [0] * n
        pre_zeros = [0] * n
        diff = [0] * n
        pre_diff_idx = {0: -1}  # first index of diff
        pre_diff_idx_after_1one = {}
        pre_diff_idx_after_1zero = {}
        for i, c in enumerate(s):
            if c == '0':
                zeros += 1
            else:
                ones += 1
            pre_zeros[i] = zeros
            pre_ones[i] = ones
            diff[i] = ones - zeros
        res = 0
        for r, d in enumerate(diff):
            if d in pre_diff_idx:
                res = max(res, r - pre_diff_idx[d])
            if d + 2 in pre_diff_idx:  # subarray has excess of 0s
                l = pre_diff_idx[d + 2]
                subarray_ones = pre_ones[r] - (pre_ones[l] if l >= 0 else 0)
                if ones > subarray_ones:  # extra ones on the right
                    res = max(res, r - l)
                elif d + 2 in pre_diff_idx_after_1one:
                    res = max(res, r - pre_diff_idx_after_1one[d + 2])
            if d - 2 in pre_diff_idx:  # subarray has excess of 1s
                l = pre_diff_idx[d - 2]
                subarray_zeros = pre_zeros[r] - (pre_zeros[l] if l >= 0 else 0)
                if zeros > subarray_zeros:  # extra zeros on the right
                    res = max(res, r - l)
                elif d - 2 in pre_diff_idx_after_1zero:
                    res = max(res, r - pre_diff_idx_after_1zero[d - 2])
            if d not in pre_diff_idx:
                pre_diff_idx[d] = r
            if d not in pre_diff_idx_after_1one and pre_ones[r] > 0:
                pre_diff_idx_after_1one[d] = r
            if d not in pre_diff_idx_after_1zero and pre_zeros[r] > 0:
                pre_diff_idx_after_1zero[d] = r
        return res
