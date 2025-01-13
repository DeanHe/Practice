"""
You are given an integer array gifts denoting the number of gifts in various piles. Every second, you do the following:

Choose the pile with the maximum number of gifts.
If there is more than one pile with the maximum number of gifts, choose any.
Leave behind the floor of the square root of the number of gifts in the pile. Take the rest of the gifts.
Return the number of gifts remaining after k seconds.

Example 1:
Input: gifts = [25,64,9,4,100], k = 4
Output: 29
Explanation:
The gifts are taken in the following way:
- In the first second, the last pile is chosen and 10 gifts are left behind.
- Then the second pile is chosen and 8 gifts are left behind.
- After that the first pile is chosen and 5 gifts are left behind.
- Finally, the last pile is chosen again and 3 gifts are left behind.
The final remaining gifts are [5,8,9,4,3], so the total number of gifts remaining is 29.

Example 2:
Input: gifts = [1,1,1,1], k = 4
Output: 4
Explanation:
In this case, regardless which pile you choose, you have to leave behind 1 gift in each pile.
That is, you can't take any pile with you.
So, the total gifts remaining are 4.

Constraints:
1 <= gifts.length <= 10^3
1 <= gifts[i] <= 10^9
1 <= k <= 10^3

hints:
1 How can you keep track of the largest gifts in the array
2 What is an efficient way to find the square root of a number?
3 Can you keep adding up the values of the gifts while ensuring they are in a certain order?
4 Can we use a priority queue or heap here?

Analysis:
TC: O(N + KlogN)
"""
import heapq
import math
from typing import List


class TakeGiftsFromTheRichestPile:
    def pickGifts(self, gifts: List[int], k: int) -> int:
        pq = [-n for n in gifts]
        heapq.heapify(pq)
        for _ in range(k):
            cur = -heapq.heappop(pq)
            heapq.heappush(pq, -math.floor(math.sqrt(cur)))
        return -sum(pq)