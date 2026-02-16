"""
Given an array nums, you can perform the following operation any number of times:

Select the adjacent pair with the minimum sum in nums. If multiple such pairs exist, choose the leftmost one.
Replace the pair with their sum.
Return the minimum number of operations needed to make the array non-decreasing.

An array is said to be non-decreasing if each element is greater than or equal to its previous element (if it exists).

Example 1:
Input: nums = [5,2,3,1]
Output: 2

Explanation:
The pair (3,1) has the minimum sum of 4. After replacement, nums = [5,2,4].
The pair (2,4) has the minimum sum of 6. After replacement, nums = [5,6].
The array nums became non-decreasing in two operations.

Example 2:
Input: nums = [1,2,2]
Output: 0

Explanation:
The array nums is already sorted.

Constraints:
1 <= nums.length <= 10^5
-10^9 <= nums[i] <= 10^9

hints:
1 We can perform the simulation using data structures.
2 Maintain an array index and value using a map since we need to find the next and previous ones.
3 Maintain the indices to be removed using a hash set.
4 Maintain the neighbor sums with the smaller indices (set or priority queue).
5 Keep the 3 structures in sync during the removals.

analysis:
TC:O(NlogN)
"""
import heapq
from typing import List


# define a double linklist node
class Node:
    def __init__(self, value, idx):
        self.value = value
        self.idx = idx
        self.pre = None
        self.next = None


class PQItem:
    def __init__(self, cost, first, second):
        self.cost = cost
        self.first = first
        self.second = second

    def __lt__(self, other):
        if self.cost == other.cost:
            return self.first.idx < other.first.idx
        return self.cost < other.cost

class MinimumPairRemovalToSortArrayII:

    def minimumPairRemoval(self, nums: List[int]) -> int:
        pq = []
        head = Node(nums[0], 0)
        cur = head
        merged = [False] * len(nums)
        dec_cnt = 0
        res = 0
        for i in range(1, len(nums)):
            post = Node(nums[i], i)
            cur.next = post
            post.pre = cur
            heapq.heappush(pq, PQItem(cur.value + post.value, cur, post))
            if nums[i] < nums[i - 1]:
                dec_cnt += 1
            cur = cur.next
        while dec_cnt > 0:
            item = heapq.heappop(pq)
            first, second, cost = item.first, item.second, item.cost
            if not merged[first.idx] and not merged[second.idx] and first.value + second.value == cost:
                res += 1
                if first.value > second.value:
                    dec_cnt -= 1
                # merge first and second
                first.next = second.next
                if first.next:
                    first.next.pre = first

                # check first.pre and first pair
                if first.pre:
                    if first.pre.value > first.value and first.pre.value <= cost:
                        dec_cnt -= 1
                    elif first.pre.value <= first.value and first.pre.value > cost:
                        dec_cnt += 1
                    heapq.heappush(pq, PQItem(first.pre.value + cost, first.pre, first))

                # check second and second.next pair
                if second.next:
                    if second.value > second.next.value and cost <= second.next.value:
                        dec_cnt -= 1
                    elif second.value <= second.next.value and cost > second.next.value:
                        dec_cnt += 1
                    heapq.heappush(pq, PQItem(second.next.value + cost, first, second.next))

                first.value = cost
                merged[second.idx] = True
        return res

