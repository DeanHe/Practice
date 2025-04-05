"""
You are given a 0-indexed array of positive integers nums and a positive integer limit.

In one operation, you can choose any two indices i and j and swap nums[i] and nums[j] if |nums[i] - nums[j]| <= limit.

Return the lexicographically smallest array that can be obtained by performing the operation any number of times.

An array a is lexicographically smaller than an array b if in the first position where a and b differ, array a has an element that is less than the corresponding element in b. For example, the array [2,10,3] is lexicographically smaller than the array [10,2,3] because they differ at index 0 and 2 < 10.

Example 1:
Input: nums = [1,5,3,9,8], limit = 2
Output: [1,3,5,8,9]
Explanation: Apply the operation 2 times:
- Swap nums[1] with nums[2]. The array becomes [1,3,5,9,8]
- Swap nums[3] with nums[4]. The array becomes [1,3,5,8,9]
We cannot obtain a lexicographically smaller array by applying any more operations.
Note that it may be possible to get the same result by doing different operations.

Example 2:
Input: nums = [1,7,6,18,2,1], limit = 3
Output: [1,6,7,18,1,2]
Explanation: Apply the operation 3 times:
- Swap nums[1] with nums[2]. The array becomes [1,6,7,18,2,1]
- Swap nums[0] with nums[4]. The array becomes [2,6,7,18,1,1]
- Swap nums[0] with nums[5]. The array becomes [1,6,7,18,1,2]
We cannot obtain a lexicographically smaller array by applying any more operations.

Example 3:
Input: nums = [1,7,28,19,10], limit = 3
Output: [1,7,28,19,10]
Explanation: [1,7,28,19,10] is the lexicographically smallest array we can obtain because we cannot apply the operation on any two indices.

Constraints:
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9
1 <= limit <= 10^9

hints:
1 Construct a virtual graph where all elements in nums are nodes and the pairs satisfying the condition have an edge between them.
2 Instead of constructing all edges, we only care about the connected components.
3 Can we use DSU?
4 Sort nums. Now we just need to consider if the consecutive elements have an edge to check if they belong to the same connected component. Hence, all connected components become a list of position-consecutive elements after sorting.
5 For each index of nums from 0 to nums.length - 1 we can change it to the current minimum value we have in its connected component and remove that value from the connected component.

Analysis：
Sorting + Grouping
TC: O(NlogN)
"""
from collections import deque
from typing import List


class MakeLexicographicallySmallestArrayBySwappingElements:

    def lexicographicallySmallestArray(self, nums: List[int], limit: int) -> List[int]:
        sz = len(nums)
        res = []
        nums_sorted = sorted(nums)
        g = 0
        num_to_group = {nums_sorted[0]: g}
        group_to_list = {g: deque([nums_sorted[0]])}
        for i in range(1, sz):
            if limit < nums_sorted[i] - nums_sorted[i - 1]:
                g += 1
            num_to_group[nums_sorted[i]] = g
            if g not in group_to_list:
                group_to_list[g] = deque()
            group_to_list[g].append(nums_sorted[i])
        for num in nums:
            g = num_to_group[num]
            res.append(group_to_list[g].popleft())
        return res

    def lexicographicallySmallestArray2(self, nums: List[int], limit: int) -> List[int]:
        sz = len(nums)
        arr = []
        res = [-1] * sz
        for i, num in enumerate(nums):
            arr.append((num, i))
        arr.sort(key=lambda x: x[0])
        groups = [[arr[0]]]
        for i in range(1, sz):
            if arr[i][0] - arr[i - 1][0] <= limit:
                groups[-1].append(arr[i])
            else:
                groups.append([arr[i]])
        for group in groups:
            idx = []
            for _, i in group:
                idx.append(i)
            idx.sort()
            for j in range(len(group)):
                res[idx[j]] = group[j][0]
        return res
