"""
You are given a string s of lowercase English letters and a 2D integer array shifts where shifts[i] = [starti, endi, directioni]. For every i, shift the characters in s from the index starti to the index endi (inclusive) forward if directioni = 1, or shift the characters backward if directioni = 0.

Shifting a character forward means replacing it with the next letter in the alphabet (wrapping around so that 'z' becomes 'a'). Similarly, shifting a character backward means replacing it with the previous letter in the alphabet (wrapping around so that 'a' becomes 'z').

Return the final string after all such shifts to s are applied.

Example 1:
Input: s = "abc", shifts = [[0,1,0],[1,2,1],[0,2,1]]
Output: "ace"
Explanation: Firstly, shift the characters from index 0 to index 1 backward. Now s = "zac".
Secondly, shift the characters from index 1 to index 2 forward. Now s = "zbd".
Finally, shift the characters from index 0 to index 2 forward. Now s = "ace".

Example 2:
Input: s = "dztz", shifts = [[0,0,0],[1,1,1]]
Output: "catz"
Explanation: Firstly, shift the characters from index 0 to index 0 backward. Now s = "cztz".
Finally, shift the characters from index 1 to index 1 forward. Now s = "catz".

Constraints:
1 <= s.length, shifts.length <= 5 * 10^4
shifts[i].length == 3
0 <= start_i <= endi < s.length
0 <= direction_i <= 1
s consists of lowercase English letters.

hints:
1 Instead of shifting every character in each shift, could you keep track of which characters are shifted and by how much across all shifts?
2 Try marking the start and ends of each shift, then perform a prefix sum of the shifts.

Analysis:
prefix sum, sweep line, Difference Array
TC: O(N + M) where M is len of shifts
"""
from typing import List


class ShiftingLettersII:
    def shiftingLetters(self, s: str, shifts: List[List[int]]) -> str:
        sz = len(s)
        res = list(s)
        pre_sum = [0] * sz
        for l, r, d in shifts:
            if d == 1:
                pre_sum[l] += 1
                if r + 1 < sz:
                    pre_sum[r + 1] -= 1
            else:
                pre_sum[l] -= 1
                if r + 1 < sz:
                    pre_sum[r + 1] += 1
        cur = 0
        for i in range(sz):
            cur = (cur + pre_sum[i]) % 26
            if cur < 0:
                cur += 26
            c = chr((ord(s[i]) - ord('a') + cur) % 26 + ord('a'))
            res[i] = c
        return ''.join(res)
