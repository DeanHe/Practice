"""
An integer array original is transformed into a doubled array changed by appending twice the value of every element in original, and then randomly shuffling the resulting array.

Given an array changed, return original if changed is a doubled array. If changed is not a doubled array, return an empty array. The elements in original may be returned in any order.



Example 1:

Input: changed = [1,3,4,2,6,8]
Output: [1,3,4]
Explanation: One possible original array could be [1,3,4]:
- Twice the value of 1 is 1 * 2 = 2.
- Twice the value of 3 is 3 * 2 = 6.
- Twice the value of 4 is 4 * 2 = 8.
Other original arrays could be [4,3,1] or [3,1,4].
Example 2:

Input: changed = [6,3,0,1]
Output: []
Explanation: changed is not a doubled array.
Example 3:

Input: changed = [1]
Output: []
Explanation: changed is not a doubled array.


Constraints:

1 <= changed.length <= 105
0 <= changed[i] <= 105

hint:
1 If changed is a doubled array, you should be able to delete elements and their doubled values until the array is empty.
2 Which element is guaranteed to not be a doubled value? It is the smallest element.
3 After removing the smallest element and its double from changed, is there another number that is guaranteed to not be a doubled value?
"""
import collections
from typing import List


class FindOriginalArrayFromDoubledArray:
    def findOriginalArray(self, changed: List[int]) -> List[int]:
        cnt = collections.Counter(changed)
        if cnt[0] % 2 == 1:
            return []
        for n in sorted(cnt):
            if cnt[n] > cnt[2 * n]:
                return []
            if n != 0:
                cnt[2 * n] -= cnt[n]
            else:
                cnt[2 * n] -= cnt[n] // 2
        return list(cnt.elements())