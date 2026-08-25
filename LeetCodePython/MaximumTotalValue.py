"""
You are given two integer arrays value and decay, and an integer m.

value[i] represents the initial value at index i.
decay[i] represents how much the value decreases after each selection of index i.
You may select any index multiple times. The total number of selections across all indices must not exceed m.

If you select index i for the tth time, where t is 1-indexed, the value gained is value[i] - decay[i] * (t - 1).

Return the maximum total value you can obtain. Since the answer may be large, return it modulo 109 + 7.

Example 1:
Input: value = [6,5,4], decay = [2,1,1], m = 4
Output: 19

Explanation:
One optimal sequence of selections is as follows:

By selecting index 0, the value gained is 6.
By selecting index 1, the value gained is 5.
By selecting index 2, the value gained is 4.
By selecting index 0 again, the value gained is 6 - 2 = 4.
The total value is 6 + 5 + 4 + 4 = 19. No other sequence of at most 4 selections gives a higher total value.

Example 2:
Input: value = [7,2,2], decay = [3,2,1], m = 2
Output: 11

Explanation:
One optimal sequence of selections is as follows:

By selecting index 0, the value gained is 7.
By selecting index 0 again, the value gained is 7 - 3 = 4.
The total value is 7 + 4 = 11.

Example 3:
Input: value = [4,3], decay = [5,4], m = 5
Output: 7

Explanation:
One optimal sequence of selections is as follows:

By selecting index 0, the value gained is 4.
By selecting index 1, the value gained is 3.
The total value is 4 + 3 = 7.

Constraints:
1 <= value.length == decay.length <= 10^5
1 <= value[i], decay[i] <= 10^9
1 <= m <= 10^9

hints:
1 For each index, the gained values form a decreasing arithmetic sequence: value[i], value[i] - decay[i], value[i] - 2 * decay[i], and so on.
2 Since you may use fewer than m selections, never take a non-positive gain.
3 Binary search the minimum gain threshold that should be included among the selected values.
4 For a fixed threshold g, compute how many terms in each arithmetic sequence are at least g, and also compute their sum using the arithmetic progression formula.
5 If more than m terms are counted at the final threshold, subtract the extra copies of that threshold value.

analysis:
arithmetic progression
binary search
TC: O(log10^9 + N)
"""

class MaximumTotalValue:
    def maxTotalValue(self, value: list[int], decay: list[int], m: int) -> int:
        MOD = 10 ** 9 + 7
        n = len(value)
        res = 0

        def count_val_gt(threshold):
            res = 0
            for i in range(n):
                if value[i] >= threshold:
                    res += (value[i] - threshold) // decay[i] + 1
                    if res >= m:
                        return res
            return res

        s = 1
        e = 10 ** 9
        threshold = 0
        while s <= e:
            mid = s + (e - s) // 2
            if count_val_gt(mid) >= m:
                threshold = mid
                s = mid + 1
            else:
                e = mid - 1

        used = 0
        for i in range(n):
            if value[i] > threshold:
                cnt = (value[i] - (threshold + 1)) // decay[i] + 1
                used += cnt
                last = value[i] - (cnt - 1) * decay[i]
                res += cnt * (value[i] + last) // 2
        res += (m - used) * threshold
        return res % MOD

