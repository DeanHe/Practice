"""
You are given an integer num. You can swap two digits at most once to get the maximum valued number.

Return the maximum valued number you can get.

Example 1:
Input: num = 2736
Output: 7236
Explanation: Swap the number 2 and the number 7.
Example 2:

Input: num = 9973
Output: 9973
Explanation: No swap.

Constraints:
0 <= num <= 108
"""

class MaximumSwap:
    def maximumSwap(self, num: int) -> int:
        num = [int(x) for x in str(num)]
        max_idx = len(num) - 1
        a = b = 0
        for i in range(len(num) - 1, -1, -1):
            if num[i] > num[max_idx]:
                max_idx = i
            elif num[i] < num[max_idx]:
                a = i
                b = max_idx
        num[a], num[b] = num[b], num[a]
        return int("".join([str(x) for x in num]))
