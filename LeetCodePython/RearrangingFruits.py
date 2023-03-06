"""
You have two fruit baskets containing n fruits each. You are given two 0-indexed integer arrays basket1 and basket2 representing the cost of fruit in each basket. You want to make both baskets equal. To do so, you can use the following operation as many times as you want:

Chose two indices i and j, and swap the ith fruit of basket1 with the jth fruit of basket2.
The cost of the swap is min(basket1[i],basket2[j]).
Two baskets are considered equal if sorting them according to the fruit cost makes them exactly the same baskets.

Return the minimum cost to make both the baskets equal or -1 if impossible.


Example 1:
Input: basket1 = [4,2,2,2], basket2 = [1,4,1,2]
Output: 1
Explanation: Swap index 1 of basket1 with index 0 of basket2, which has cost 1. Now basket1 = [4,1,2,2] and basket2 = [2,4,1,2]. Rearranging both the arrays makes them equal.

Example 2:
Input: basket1 = [2,3,4,1], basket2 = [3,2,5,1]
Output: -1
Explanation: It can be shown that it is impossible to make both the baskets equal.
 
Constraints:
basket1.length == bakste2.length
1 <= basket1.length <= 10^5
1 <= basket1[i],basket2[i] <= 10^9

hints:
1 Create two frequency maps for both arrays, and find the minimum element among all elements of both arrays.
2 Check if the sum of frequencies of an element in both arrays is odd, if so return -1
3 Store the elements that need to be swapped in a vector, and sort it.
4 Can we reduce swapping cost with the help of minimum element?
5 Calculate the minimum cost of swapping.
"""
from collections import Counter
from typing import List


class Solution:
    def minCost(self, basket1: List[int], basket2: List[int]) -> int:
        to_swap = []
        cnt = Counter(basket1)
        for n in basket2:
            cnt[n] -= 1
        for k, freq in cnt.items():
            if freq % 2 != 0:
                return -1
            to_swap += [k] * abs(freq // 2)
        to_swap.sort()
        print(cnt)
        print(to_swap)
        least = min(basket1 + basket2)
        return sum(min(2 * least, x) for x in to_swap[:len(to_swap) // 2])