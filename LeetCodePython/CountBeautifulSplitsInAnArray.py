"""
You are given an array nums.

A split of an array nums is beautiful if:

The array nums is split into three non-empty subarrays: nums1, nums2, and nums3, such that nums can be formed by concatenating nums1, nums2, and nums3 in that order.
The subarray nums1 is a prefix of nums2 OR nums2 is a prefix of nums3.
Return the number of ways you can make this split.

A subarray is a contiguous non-empty sequence of elements within an array.

A prefix of an array is a subarray that starts from the beginning of the array and extends to any point within it.

Example 1:
Input: nums = [1,1,2,1]
Output: 2
Explanation:
The beautiful splits are:
A split with nums1 = [1], nums2 = [1,2], nums3 = [1].
A split with nums1 = [1], nums2 = [1], nums3 = [2,1].

Example 2:
Input: nums = [1,2,3,4]
Output: 0
Explanation:
There are 0 beautiful splits.

Constraints:
1 <= nums.length <= 5000
0 <= nums[i] <= 50

hints:
1 Use 2D dynamic programming to find the maximum matching prefix.

analysis:
Rolling Hash
TC: O(N^2)
"""
from typing import List


class CountBeautifulSplitsInAnArray:
    def beautifulSplits(self, nums: List[int]) -> int:
        res = 0
        sz = len(nums)
        if sz < 3:
            return 0
        MOD = 10 ** 9 + 7
        base = 31
        # Hash for prefix [0..i)
        prefix_hash = [0] * (sz + 1)
        pow = [0] * (sz + 1)
        pow[0] = 1
        for i in range(sz):
            pow[i + 1] = (pow[i] * base) % MOD
            prefix_hash[i + 1] = (prefix_hash[i] * base + nums[i]) % MOD

        def is_prefix(hash, s1, e1, s2, e2):
            sz1 = e1 - s1
            sz2 = e2 - s2
            if sz2 < sz1:
                return False
            hash1 = (hash[e1] - (hash[s1] * pow[e1]) % MOD + MOD) % MOD
            hash2 = (hash[s2 + sz1] - (hash[s2] * pow[sz1]) % MOD + MOD) % MOD
            return hash1 == hash2

        for i in range(1, sz - 1):
            for j in range(i + 1, sz):
                valid = False
                if is_prefix(prefix_hash, 0, i, i, j):
                    valid = True
                if not valid and is_prefix(prefix_hash, i, j, j, sz):
                    valid = True
                if valid:
                    res += 1
        return res

