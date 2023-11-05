"""
(This problem is an interactive problem.)

You may recall that an array arr is a mountain array if and only if:

arr.length >= 3
There exists some i with 0 < i < arr.length - 1 such that:
arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
Given a mountain array mountainArr, return the minimum index such that mountainArr.get(index) == target. If such an index does not exist, return -1.

You cannot access the mountain array directly. You may only access the array using a MountainArray interface:

MountainArray.get(k) returns the element of the array at index k (0-indexed).
MountainArray.length() returns the length of the array.
Submissions making more than 100 calls to MountainArray.get will be judged Wrong Answer. Also, any solutions that attempt to circumvent the judge will result in disqualification.

Example 1:
Input: array = [1,2,3,4,5,3,1], target = 3
Output: 2
Explanation: 3 exists in the array, at index=2 and index=5. Return the minimum index, which is 2.

Example 2:
Input: array = [0,1,2,4,2,1], target = 3
Output: -1
Explanation: 3 does not exist in the array, so we return -1.

Constraints:
3 <= mountain_arr.length() <= 10^4
0 <= target <= 10^9
0 <= mountain_arr.get(index) <= 10^9

hints:
1 Based on whether A[i-1] < A[i] < A[i+1], A[i-1] < A[i] > A[i+1], or A[i-1] > A[i] > A[i+1], we are either at the left side, peak, or right side of the mountain. We can binary search to find the peak. After finding the peak,
we can binary search two more times to find whether the value occurs on either side of the peak.
"""

# """
# This is MountainArray's API interface.
# You should not implement it, or speculate about its implementation
# """
class MountainArray:
    def get(self, index: int) -> int:
        return -1
    def length(self) -> int:
        return -1

class FindInMountainArray:
    def findInMountainArray(self, target: int, mountain_arr: 'MountainArray') -> int:
        sz = mountain_arr.length()
        s, e = 0, sz - 1
        peak = 0
        while s + 1 < e:
            mid = (s + e) // 2
            if mountain_arr.get(mid) < mountain_arr.get(mid + 1):
                s = mid
            else:
                e = mid
        if mountain_arr.get(s) < mountain_arr.get(e):
            peak = e
        else:
            peak = s

        s, e = 0, peak
        while s <= e:
            mid = (s + e) // 2
            if mountain_arr.get(mid) == target:
                return mid
            elif mountain_arr.get(mid) < target:
                s = mid + 1
            else:
                e = mid - 1

        s, e = peak, sz - 1
        while s <= e:
            mid = (s + e) // 2
            if mountain_arr.get(mid) == target:
                return mid
            elif mountain_arr.get(mid) < target:
                e = mid - 1
            else:
                s = mid + 1
        return -1
