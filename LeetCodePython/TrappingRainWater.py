"""
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.

Example 1:
Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.

Example 2:
Input: height = [4,2,0,3,2,5]
Output: 9

Constraints:
n == height.length
1 <= n <= 2 * 104
0 <= height[i] <= 105
"""
from typing import List


class TrappingRainWater:
    def trap(self, height: List[int]) -> int:
        s, e, max_left, max_right, res = 0, len(height) - 1, 0, 0, 0
        while s < e:
            if height[s] < height[e]:
                if height[s] >= max_left:
                    max_left = height[s]
                else:
                    res += max_left - height[s]
                s += 1
            else:
                if height[e] >= max_right:
                    max_right = height[e]
                else:
                    res += max_right - height[e]
                e -= 1
        return res
