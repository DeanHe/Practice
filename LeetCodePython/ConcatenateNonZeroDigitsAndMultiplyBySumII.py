"""
You are given a string s of length m consisting of digits. You are also given a 2D integer array queries, where queries[i] = [li, ri].

For each queries[i], extract the substring s[li..ri]. Then, perform the following:

Form a new integer x by concatenating all the non-zero digits from the substring in their original order. If there are no non-zero digits, x = 0.
Let sum be the sum of digits in x. The answer is x * sum.
Return an array of integers answer where answer[i] is the answer to the ith query.

Since the answers may be very large, return them modulo 10^9 + 7.

Example 1:
Input: s = "10203004", queries = [[0,7],[1,3],[4,6]]
Output: [12340, 4, 9]

Explanation:
s[0..7] = "10203004"
x = 1234
sum = 1 + 2 + 3 + 4 = 10
Therefore, answer is 1234 * 10 = 12340.
s[1..3] = "020"
x = 2
sum = 2
Therefore, the answer is 2 * 2 = 4.
s[4..6] = "300"
x = 3
sum = 3
Therefore, the answer is 3 * 3 = 9.

Example 2:
Input: s = "1000", queries = [[0,3],[1,1]]
Output: [1, 0]

Explanation:
s[0..3] = "1000"
x = 1
sum = 1
Therefore, the answer is 1 * 1 = 1.
s[1..1] = "0"
x = 0
sum = 0
Therefore, the answer is 0 * 0 = 0.

Example 3:
Input: s = "9876543210", queries = [[0,9]]
Output: [444444137]

Explanation:
s[0..9] = "9876543210"
x = 987654321
sum = 9 + 8 + 7 + 6 + 5 + 4 + 3 + 2 + 1 = 45
Therefore, the answer is 987654321 * 45 = 44444444445.
We return 44444444445 modulo (109 + 7) = 444444137.

Constraints:
1 <= m == s.length <= 10^5
s consists of digits only.
1 <= queries.length <= 10^5
queries[i] = [li, ri]
0 <= li <= ri < m

hints:
1 Track only nonzero digits: store their values and positions and keep a prefix sum for digit sums.
2 Also build prefix concatenation values P, pow10, and set mod = 10^9+7 so any compressed substring number is obtainable from prefixes.
3 Map each query [l, r] to the compressed list using precomputed mapping arrays (first nonzero at or after i
4 If the mapped range is empty return 0; otherwise get x from P, get sum from the digit-prefix, and return (x * sum) % mod.

analysis:
TC: O(N)
"""
from typing import List


class Solution:
    def sumAndMultiply(self, s: str, queries: List[List[int]]) -> List[int]:
        MOD = 10 ** 9 + 7
        res = []
        digit_cnt = 0
        idx = [0]
        pre_digit_sum = [0]
        pre_digit_concat = [0]
        pow10 = [1]
        for c in s:
            if c != '0':
                digit_cnt += 1
                pre_digit_sum.append(pre_digit_sum[-1] + int(c))
                pre_digit_concat.append((10 * pre_digit_concat[-1] + int(c)) % MOD)
                pow10.append(pow10[-1] * 10 % MOD)
            idx.append(digit_cnt)
        for l, r in queries:
            li = idx[l]
            ri = idx[r + 1]
            digit_sum = pre_digit_sum[ri] - pre_digit_sum[li]
            x = pre_digit_concat[ri] - pre_digit_concat[li] * (pow10[ri - li]) % MOD
            res.append((x * digit_sum) % MOD)
        return res
