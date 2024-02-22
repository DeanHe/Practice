"""
You are visiting a farm that has a single row of fruit trees arranged from left to right. The trees are represented by an integer array fruits where fruits[i] is the type of fruit the ith tree produces.

You want to collect as much fruit as possible. However, the owner has some strict rules that you must follow:

You only have two baskets, and each basket can only hold a single type of fruit. There is no limit on the amount of fruit each basket can hold.
Starting from any tree of your choice, you must pick exactly one fruit from every tree (including the start tree) while moving to the right. The picked fruits must fit in one of your baskets.
Once you reach a tree with fruit that cannot fit in your baskets, you must stop.
Given the integer array fruits, return the maximum number of fruits you can pick.



Example 1:

Input: fruits = [1,2,1]
Output: 3
Explanation: We can pick from all 3 trees.
Example 2:

Input: fruits = [0,1,2,2]
Output: 3
Explanation: We can pick from trees [1,2,2].
If we had started at the first tree, we would only pick from trees [0,1].
Example 3:

Input: fruits = [1,2,3,2,2]
Output: 4
Explanation: We can pick from trees [2,3,2,2].
If we had started at the first tree, we would only pick from trees [1,2].

Constraints:
1 <= fruits.length <= 10^5
0 <= fruits[i] < fruits.length

analysis:
sliding window
TC: O(N)
"""
from typing import List


class FruitIntoBaskets:
    def totalFruit(self, fruits: List[int]) -> int:
        n = len(fruits)
        res = cnt = 0
        first, second = 0, -1
        for i in range(n):
            if fruits[i] == fruits[first]:
                first = i
                cnt += 1
            elif second == -1 or fruits[i] == fruits[second]:
                second = i
                cnt += 1
            else:
                res = max(res, cnt)
                cnt = abs(first - second) + 1
                first = i - 1
                second = i
        res = max(res, cnt)
        return res

    def totalFruit2(self, fruits: List[int]) -> int:
        n = len(fruits)
        basket = {}
        res = s = 0
        for e in range(n):
            basket[fruits[e]] = basket.get(fruits[e], 0) + 1
            while len(basket) > 2:
                basket[fruits[s]] -= 1
                if basket[fruits[s]] == 0:
                    del basket[fruits[s]]
                s += 1
            res = max(res, e - s + 1)
        return res