"""
Design a data structure that efficiently finds the majority element of a given subarray.

The majority element of a subarray is an element that occurs threshold times or more in the subarray.

Implementing the MajorityChecker class:

MajorityChecker(int[] arr) Initializes the instance of the class with the given array arr.
int query(int left, int right, int threshold) returns the element in the subarray arr[left...right] that occurs at least threshold times, or -1 if no such element exists.

Example 1:
Input
["MajorityChecker", "query", "query", "query"]
[[[1, 1, 2, 2, 1, 1]], [0, 5, 4], [0, 3, 3], [2, 3, 2]]
Output
[null, 1, -1, 2]

Explanation
MajorityChecker majorityChecker = new MajorityChecker([1, 1, 2, 2, 1, 1]);
majorityChecker.query(0, 5, 4); // return 1
majorityChecker.query(0, 3, 3); // return -1
majorityChecker.query(2, 3, 2); // return 2

Constraints:
1 <= arr.length <= 2 * 104
1 <= arr[i] <= 2 * 104
0 <= left <= right < arr.length
threshold <= right - left + 1
2 * threshold > right - left + 1
At most 104 calls will be made to query.

hint:
1 What's special about a majority element ?
2 A majority element appears more than half the length of the array number of times.
3 If we tried a random index of the array, what's the probability that this index has a majority element ?
4 It's more than 50% if that array has a majority element.
5 Try a random index for a proper number of times so that the probability of not finding the answer tends to zero.
"""
import bisect
import collections
import random
from typing import List


class MajorityChecker:

    def __init__(self, arr: List[int]):
        self.arr = arr
        self.idx_map = collections.defaultdict(list)
        for i, n in enumerate(arr):
            self.idx_map[n].append(i)

    def query(self, left: int, right: int, threshold: int) -> int:
        for _ in range(20):
            n = self.arr[random.randint(left, right)]
            indexes = self.idx_map[n]
            l = bisect.bisect_left(indexes, left)
            r = bisect.bisect_right(indexes, right)
            if threshold <= r - l:
                return n
        return -1

# Your MajorityChecker object will be instantiated and called as such:
# obj = MajorityChecker(arr)
# param_1 = obj.query(left,right,threshold)
