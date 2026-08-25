"""
You are given a 2D integer array units of size m × n where units[i][j] represents the capacity of the jth unit in the ith device. Each device contains exactly n units.

The rating of a device is the minimum capacity among all its units.

You may perform the following operation any number of times (including zero):

Choose a device i that has not been used as a source before.
Remove exactly one unit from device i and add it to any different device.
Then mark the device i as used, so it cannot be chosen again as a source.
Return the maximum possible sum of the ratings of all devices after any number of such operations.

Note:
Devices can receive units from multiple devices, regardless of whether they have been selected.
The rating of an empty device is 0.


Example 1:
Input: units = [[1,3],[2,2]]
Output: 4

Explanation:
Select device i = 0 and transfer units[0][0] = 1 to device i = 1.
After the transfer, the ratings are:
Device 0 = [3]: rating[0] = 3
Device 1 = [2, 2, 1]: rating[1] = 1
Thus, the sum of ratings is 3 + 1 = 4.

Example 2:
Input: units = [[1,2,3],[4,5,6]]
Output: 6

Explanation:
Select device i = 1 and transfer units[1][0] = 4 to device i = 0.
After the transfer, the ratings are:
Device 0 = [1, 2, 3, 4]: rating[0] = 1
Device 1 = [5, 6]: rating[1] = 5
Thus, the sum of ratings is 1 + 5 = 6.

Example 3:
Input: units = [[5,5,5],[1,1,1]]
Output: 6
Explanation:
No transfers increase the sum of ratings. Thus, the sum of ratings is 5 + 1 = 6.

Constraints:
1 <= m == units.length <= 10^5
1 <= n == units[i].length <= 10^5
m * n <= 2 * 10^5
1 <= units[i][j] <= 10^5

hints:
1 For each device, only its smallest and second-smallest capacities can affect the answer.
2 To improve a device’s rating, remove its smallest unit. Send all removed units to one device, which acts as the destination for the smallest capacities.
3 The destination device’s final rating is the smallest capacity among all devices. To maximize the remaining contribution, choose as the destination the device with the smallest second-smallest capacity.

analysis:
Greedy:
TC:O(N)
"""
import math
from typing import List


class MaximizeSumOfDeviceRatings:
    def maxRatings(self, units: List[List[int]]) -> int:
        global_least = global_second_least = math.inf
        res = 0
        for unit in units:
            least = second_least = math.inf
            for num in unit:
                if num < least:
                    if least != math.inf:
                        second_least = least
                    least = num
                elif num < second_least:
                    second_least = num

            if second_least != math.inf:
                global_least = min(global_least, least)
                res += second_least
            else:
                res += least
            if second_least != math.inf and second_least < global_second_least:
                global_second_least = second_least

        # print(res, global_least, global_second_least)
        if global_second_least != math.inf:
            return global_least + res - global_second_least
        else:
            return res
