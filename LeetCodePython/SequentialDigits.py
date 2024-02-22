"""
An integer has sequential digits if and only if each digit in the number is one more than the previous digit.

Return a sorted list of all the integers in the range [low, high] inclusive that have sequential digits.

Example 1:
Input: low = 100, high = 300
Output: [123,234]

Example 2:
Input: low = 1000, high = 13000
Output: [1234,2345,3456,4567,5678,6789,12345]

Constraints:
10 <= low <= high <= 10^9

hints:
1 Generate all numbers with sequential digits and check if they are in the given range.
2 Fix the starting digit then do a recursion that tries to append all valid digits.

TC: O(1)
"""
from collections import deque
from typing import List


class SequentialDigits:
    def sequentialDigits(self, low: int, high: int) -> List[int]:
        res = []
        q = deque(range(1, 10))
        while q:
            cur = q.popleft()
            if low <= cur <= high:
                res.append(cur)
            last_digit = cur % 10
            if last_digit < 9:
                q.append(cur * 10 + last_digit + 1)
        return res
