"""
You are given a string s consisting of uppercase English letters.

You are allowed to insert at most one uppercase English letter at any position (including the beginning or end) of the string.

Return the maximum number of "LCT" subsequences that can be formed in the resulting string after at most one insertion.

Example 1:
Input: s = "LMCT"
Output: 2
Explanation:
We can insert a "L" at the beginning of the string s to make "LLMCT", which has 2 subsequences, at indices [0, 3, 4] and [1, 3, 4].

Example 2:
Input: s = "LCCT"
Output: 4
Explanation:
We can insert a "L" at the beginning of the string s to make "LLCCT", which has 4 subsequences, at indices [0, 2, 4], [0, 3, 4], [1, 2, 4] and [1, 3, 4].

Example 3:
Input: s = "L"
Output: 0
Explanation:
Since it is not possible to obtain the subsequence "LCT" by inserting a single letter, the result is 0.

Constraints:
1 <= s.length <= 10^5
s consists of uppercase English letters.

hints:
1 Precompute preL, preLC, sufT, and sufCT arrays to count L’s, LC’s, T’s, and CT’s at each position.
2 Compute base as the sum over all i of preLC[i] * sufT[i].
3 For each insert position i, compute gains sufCT[i] for ‘L’, preL[i] * sufT[i] for ‘C’, and preLC[i] for ‘T’, and take the maximum of base and base + gain.

Analysis:
3 choices of inserting L, T or C
prefix sum
TC:O(N)
"""

class MaximumNumberOfSubsequencesAfterOneInserting:
    def numOfSubsequences(self, s: str) -> int:
        res_with_L = res_with_T = res_with_C = 0
        best_insert_for_C = 0
        sz = len(s)
        L_presum = [0] * (sz + 1)
        L_presum[0] = s[0] == 'L'
        for i in range(sz):
            L_presum[i + 1] = L_presum[i]
            if s[i] == 'L':
                L_presum[i + 1] += 1
        T_suffix_sum = [0] * (sz + 1)
        for i in range(sz - 1, -1, -1):
            T_suffix_sum[i] = T_suffix_sum[i + 1]
            if s[i] == 'T':
                T_suffix_sum[i] += 1
        for i in range(sz):
            if s[i] == 'C':
                res_with_L += (L_presum[i] + 1) * T_suffix_sum[i + 1]
                res_with_T += L_presum[i] * (T_suffix_sum[i + 1] + 1)
                res_with_C += L_presum[i] * T_suffix_sum[i + 1]
            else:
                best_insert_for_C = max(best_insert_for_C, L_presum[i] * T_suffix_sum[i])
        res_with_C += best_insert_for_C
        return max(res_with_L, res_with_C, res_with_T)