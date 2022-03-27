"""
Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

Examples:
[2,3,4] , the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Your job is to output the median array for each window in the original array.

For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

window position                Median
---------------               -----
[1  3  -1] -3  5  3  6  7       1
 1 [3  -1  -3] 5  3  6  7       -1
 1  3 [-1  -3  5] 3  6  7       -1
 1  3  -1 [-3  5  3] 6  7       3
 1  3  -1  -3 [5  3  6] 7       5
 1  3  -1  -3  5 [3  6  7]      6
Therefore, return the median sliding window as [1,-1,-1,3,5,6].

Note:
You may assume k is always valid, ie: k is always smaller than input array's size for non-empty array.


Constraints:
1 <= k <= nums.length <= 10^5
-2^31 <= nums[i] <= 2^31 - 1

analysis:
remember add to the lower heap(maxHeap first). then pass one to the upper heap(minHeap),
then make maxHeap.size() >= minHeap.size()

Time Complexity
Priority Queue would have been O(k) for remove(element) giving us an overall time complexity of O(nk)
Using BST remove can be O(logK), which gives total O(nlogK)
"""
import heapq
from typing import List


class SlidingWindowMedian:
    def medianSlidingWindow(self, nums: List[int], k: int) -> List[float]:
        min_heap, max_heap, res = [], [], []

        def add(n, i):
            heapq.heappush(max_heap, (-n, i))
            x, y = heapq.heappop(max_heap)
            heapq.heappush(min_heap, (-x, y))
            if len(min_heap) > len(max_heap):
                x, y = heapq.heappop(min_heap)
                heapq.heappush(max_heap, (-x, y))

        def remove(n, i):
            if (-n, i) in max_heap:
                max_heap.remove((-n, i))
                heapq.heapify(max_heap)
            elif (n, i) in min_heap:
                min_heap.remove((n, i))
                heapq.heapify(min_heap)
            if len(min_heap) > len(max_heap):
                x, y = heapq.heappop(min_heap)
                heapq.heappush(max_heap, (-x, y))
            elif len(max_heap) - len(min_heap) > 1:
                x, y = heapq.heappop(max_heap)
                heapq.heappush(min_heap, (-x, y))

        def get_median():
            if not min_heap and not max_heap:
                return 0
            if len(min_heap) == len(max_heap):
                return (-max_heap[0][0] + min_heap[0][0]) / 2
            else:
                return -max_heap[0][0]

        for i, n in enumerate(nums):
            add(n, i)
            if i >= k - 1:
                res.append(get_median())
                remove(nums[i - k + 1], i - k + 1)
        return res

