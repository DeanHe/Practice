"""
An attendance record for a student can be represented as a string where each character signifies whether the student was absent, late, or present on that day. The record only contains the following three characters:

'A': Absent.
'L': Late.
'P': Present.
Any student is eligible for an attendance award if they meet both of the following criteria:

The student was absent ('A') for strictly fewer than 2 days total.
The student was never late ('L') for 3 or more consecutive days.
Given an integer n, return the number of possible attendance records of length n that make a student eligible for an attendance award. The answer may be very large, so return it modulo 109 + 7.

Example 1:
Input: n = 2
Output: 8
Explanation: There are 8 records with length 2 that are eligible for an award:
"PP", "AP", "PA", "LP", "PL", "AL", "LA", "LL"
Only "AA" is not eligible because there are 2 absences (there need to be fewer than 2).

Example 2:
Input: n = 1
Output: 3

Example 3:
Input: n = 10101
Output: 183236316

Constraints:
1 <= n <= 10^5

analysis:
Dynamic Programming
TC: O(N)
"""
from functools import cache


class StudentAttendanceRecordII:
    def checkRecord(self, n: int) -> int:
        MOD = 10 ** 9 + 7
        mem = [[[-1] * 3 for _ in range(2)] for _ in range(n)]

        # total count of A; prefix count of L
        def dfs(idx, cnt_A, pre_L):
            if idx == n:
                return 1
            if mem[idx][cnt_A][pre_L] != -1:
                return mem[idx][cnt_A][pre_L]
            next_A = dfs(idx + 1, cnt_A + 1, 0) if cnt_A == 0 else 0
            next_L = 0 if pre_L == 2 else dfs(idx + 1, cnt_A, pre_L + 1)
            next_P = dfs(idx + 1, cnt_A, 0)
            mem[idx][cnt_A][pre_L] = (next_A + next_L + next_P) % MOD
            return mem[idx][cnt_A][pre_L]

        return dfs(0, 0, 0)
