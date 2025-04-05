"""
You are given an integer array groups, where groups[i] represents the size of the ith group. You are also given an integer array elements.

Your task is to assign one element to each group based on the following rules:

An element j can be assigned to a group i if groups[i] is divisible by elements[j].
If there are multiple elements that can be assigned, assign the element with the smallest index j.
If no element satisfies the condition for a group, assign -1 to that group.
Return an integer array assigned, where assigned[i] is the index of the element chosen for group i, or -1 if no suitable element exists.

Note: An element may be assigned to more than one group.

Example 1:
Input: groups = [8,4,3,2,4], elements = [4,2]
Output: [0,0,-1,1,0]
Explanation:
elements[0] = 4 is assigned to groups 0, 1, and 4.
elements[1] = 2 is assigned to group 3.
Group 2 cannot be assigned any element.

Example 2:
Input: groups = [2,3,5,7], elements = [5,3,3]
Output: [-1,1,0,-1]
Explanation:
elements[1] = 3 is assigned to group 1.
elements[0] = 5 is assigned to group 2.
Groups 0 and 3 cannot be assigned any element.

Example 3:
Input: groups = [10,21,30,41], elements = [2,1]
Output: [0,1,0,1]
Explanation:
elements[0] = 2 is assigned to the groups with even values, and elements[1] = 1 is assigned to the groups with odd values.

Constraints:
1 <= groups.length <= 10^5
1 <= elements.length <= 10^5
1 <= groups[i] <= 10^5
1 <= elements[i] <= 10^5

hints：
1 Can a sieve-like approach be applied here?
2 Starting from the smallest index, iterate through the multiples of the element and assign it to groups divisible by that value.
3 Process each element once.
4 Find all divisors of each group, then match them with elements.

Analysis:
TC: O(N * sqrt(M)) where n is the len(group), M is the max value of groupSize
"""
import math
from typing import List


class AssignElementsToGroupsWithConstraints:
    def assignElements(self, groups: List[int], elements: List[int]) -> List[int]:
        res = []
        first_idx = {}
        for i, e in enumerate(elements):
            if e not in first_idx:
                first_idx[e] = i
        def find_smallest_index_divisor(num):
            res = math.inf
            i = 1
            while i * i <= num:
                if num % i == 0:
                    if i in first_idx:
                        res = min(res, first_idx[i])
                    divisor = num // i
                    if divisor != i and divisor in first_idx:
                        res = min(res, first_idx[divisor])
                i += 1
            return res if res != math.inf else -1

        for num in groups:
            res.append(find_smallest_index_divisor(num))
        return res