"""
ou are given two 1-indexed integer arrays, nums and, changeIndices, having lengths n and m, respectively.

Initially, all indices in nums are unmarked. Your task is to mark all indices in nums.

In each second, s, in order from 1 to m (inclusive), you can perform one of the following operations:

Choose an index i in the range [1, n] and decrement nums[i] by 1.
Set nums[changeIndices[s]] to any non-negative value.
Choose an index i in the range [1, n], where nums[i] is equal to 0, and mark index i.
Do nothing.
Return an integer denoting the earliest second in the range [1, m] when all indices in nums can be marked by choosing operations optimally, or -1 if it is impossible.

Example 1:
Input: nums = [3,2,3], changeIndices = [1,3,2,2,2,2,3]
Output: 6
Explanation: In this example, we have 7 seconds. The following operations can be performed to mark all indices:
Second 1: Set nums[changeIndices[1]] to 0. nums becomes [0,2,3].
Second 2: Set nums[changeIndices[2]] to 0. nums becomes [0,2,0].
Second 3: Set nums[changeIndices[3]] to 0. nums becomes [0,0,0].
Second 4: Mark index 1, since nums[1] is equal to 0.
Second 5: Mark index 2, since nums[2] is equal to 0.
Second 6: Mark index 3, since nums[3] is equal to 0.
Now all indices have been marked.
It can be shown that it is not possible to mark all indices earlier than the 6th second.
Hence, the answer is 6.

Example 2:
Input: nums = [0,0,1,2], changeIndices = [1,2,1,2,1,2,1,2]
Output: 7
Explanation: In this example, we have 8 seconds. The following operations can be performed to mark all indices:
Second 1: Mark index 1, since nums[1] is equal to 0.
Second 2: Mark index 2, since nums[2] is equal to 0.
Second 3: Decrement index 4 by one. nums becomes [0,0,1,1].
Second 4: Decrement index 4 by one. nums becomes [0,0,1,0].
Second 5: Decrement index 3 by one. nums becomes [0,0,0,0].
Second 6: Mark index 3, since nums[3] is equal to 0.
Second 7: Mark index 4, since nums[4] is equal to 0.
Now all indices have been marked.
It can be shown that it is not possible to mark all indices earlier than the 7th second.
Hence, the answer is 7.

Example 3:
Input: nums = [1,2,3], changeIndices = [1,2,3]
Output: -1
Explanation: In this example, it can be shown that it is impossible to mark all indices, as we don't have enough seconds.
Hence, the answer is -1.

Constraints:
1 <= n == nums.length <= 5000
0 <= nums[i] <= 10^9
1 <= m == changeIndices.length <= 5000
1 <= changeIndices[i] <= n

hints:
1 We need at least n seconds, and at most sum(nums[i]) + n seconds.
2 We can binary search the earliest second where all indices can be marked.
3 If there is an operation where we change nums[changeIndices[i]] to a non-negative value, it is best for it to satisfy the following constraints:
nums[changeIndices[i]] should not be equal to 0.
nums[changeIndices[i]] should be changed to 0.
It should be the first position where changeIndices[i] occurs in changeIndices.
There should be another second, j, where changeIndices[i] will be marked. j is in the range [i + 1, m].
4 Let time_needed = sum(nums[i]) + n.
To check if we can mark all indices at some second x, we need to make time_needed <= x,
using non-negative change operations as described previously.
5 Using a non-negative change operation on some nums[changeIndices[i]] that satisfies the constraints described previously reduces time_needed by nums[changeIndices[i]] - 1.
So, we need to maximize the sum of (nums[changeIndices[i]] - 1) while ensuring that the non-negative change operations still satisfy the constraints.
6 Maximizing the sum of (nums[changeIndices[i]] - 1) can be done greedily using a min-priority queue and going in reverse starting from second x to second 1,
maximizing the sum of the values in the priority queue and ensuring that for every non-negative change operation on nums[changeIndices[i]] chosen,
there is another second j in the range [i + 1, x] where changeIndices[i] can be marked.
7 The answer is the first value of x in the range [1, m] where it is possible to make time_needed <= x, or -1 if there is no such second.

analysis:
binary search
TC: O(MlogM), where M is the length of changeIndices.
"""
import heapq
from typing import List


class EarliestSecondToMarkIndicesII:
    def earliestSecondToMarkIndices(self, nums: List[int], changeIndices: List[int]) -> int:
        changeIndices_len = len(changeIndices)
        # convert to 0 based index
        for i in range(changeIndices_len):
            changeIndices[i] -= 1
        earliest_time_to_zero_num = {}
        for i, idx in enumerate(changeIndices):
            if nums[idx] > 0 and idx not in earliest_time_to_zero_num:
                earliest_time_to_zero_num[idx] = i
        earliest_time_to_zero_num_inverse = {i: idx for idx, i in earliest_time_to_zero_num.items()}

        def can_mark(max_cost):
            pq = []
            available_mark_count = 0
            for i in range(max_cost - 1, -1, -1):
                if i in earliest_time_to_zero_num_inverse:
                    heapq.heappush(pq, nums[earliest_time_to_zero_num_inverse[i]])
                    if available_mark_count > 0:
                        available_mark_count -= 1
                    else:
                        heapq.heappop(pq)
                        available_mark_count += 1
                else:
                    available_mark_count += 1
            # worst case {sum(nums[i]) + n} minus all elements in pq
            decrement_mark_cost = sum(nums) + len(nums) - (sum(pq) + len(pq))
            zero_mark_cost = len(pq) * 2
            return decrement_mark_cost + zero_mark_cost <= max_cost

        s, e = 1, changeIndices_len
        while s < e:
            mid = (s + e) // 2
            if can_mark(mid):
                e = mid
            else:
                s = mid + 1
        if can_mark(s):
            return s
        return -1
