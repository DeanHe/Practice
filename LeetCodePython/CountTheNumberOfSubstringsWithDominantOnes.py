"""
You are given a binary string s.
Return the number of substrings with dominant ones.
A string has dominant ones if the number of ones in the string is greater than or equal to the square of the number of zeros in the string.

Example 1:
Input: s = "00011"
Output: 5
Explanation:
The substrings with dominant ones are shown in the table below.
i	j	s[i..j]	Number of Zeros	Number of Ones
3	3	1	               0	1
4	4	1	               0	1
2	3	01	               1	1
3	4	11	               0	2
2	4	011	               1	2

Example 2:
Input: s = "101101"
Output: 16
Explanation:
The substrings with non-dominant ones are shown in the table below.
Since there are 21 substrings total and 5 of them have non-dominant ones, it follows that there are 16 substrings with dominant ones.
i	j	s[i..j]	Number of Zeros	Number of Ones
1	1	0	           1	0
4	4	0	           1	0
1	4	0110	       2	2
0	4	10110	       2	3
1	5	01101	       2	3


Constraints:
1 <= s.length <= 4 * 10^4
s consists only of characters '0' and '1'.

hints:
1 Let us fix the starting index l of the substring and count the number of indices r such that l <= r and the substring s[l..r] has dominant ones.
2 A substring with dominant ones has at most sqrt(n) zeros.
3 We cannot iterate over every r and check if the s[l..r] has dominant ones. Instead, we iterate over the next sqrt(n) zeros to the left of l and count the number of substrings with dominant ones where the current zero is the rightmost zero of the substring.

analysis:
Sliding window
an important conclusion: the maximum number of zeros in a valid substring can't exceed the square root of the string's length.

TC: O(N * sqrt(N))
"""
from collections import deque


class CountTheNumberOfSubstringsWithDominantOnes:
    def numberOfSubstrings(self, s: str) -> int:
        res = 0
        sz = len(s)
        for possible_0_cnt in range(1, int(sz ** 0.5) + 1):
            zeros = deque()
            first_0 = -1
            one_cnt = 0
            for r in range(sz):
                if s[r] == '0':
                    zeros.append(r)
                    while len(zeros) > possible_0_cnt:
                        one_cnt -= (zeros[0] - first_0 - 1)
                        first_0 = zeros.popleft()
                else:
                    one_cnt += 1
                if len(zeros) == possible_0_cnt and possible_0_cnt ** 2 <= one_cnt:
                    res += min(zeros[0] - first_0, one_cnt - possible_0_cnt ** 2 + 1)
        # Handle all-ones substrings
        i = 0
        while i < sz:
            if s[i] == '0':
                i += 1
                continue
            one_cnt = 0
            while i < sz and s[i] == '1':
                one_cnt += 1
                i += 1
            # Add number of all-ones substrings
            res += (one_cnt * (one_cnt + 1)) // 2
        return res

