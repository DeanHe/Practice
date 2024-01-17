"""
You are given a 0-indexed integer array nums. A pair of integers x and y is called a strong pair if it satisfies the condition:

|x - y| <= min(x, y)
You need to select two integers from nums such that they form a strong pair and their bitwise XOR is the maximum among all strong pairs in the array.

Return the maximum XOR value out of all possible strong pairs in the array nums.

Note that you can pick the same integer twice to form a pair.

Example 1:
Input: nums = [1,2,3,4,5]
Output: 7
Explanation: There are 11 strong pairs in the array nums: (1, 1), (1, 2), (2, 2), (2, 3), (2, 4), (3, 3), (3, 4), (3, 5), (4, 4), (4, 5) and (5, 5).
The maximum XOR possible from these pairs is 3 XOR 4 = 7.

Example 2:
Input: nums = [10,100]
Output: 0
Explanation: There are 2 strong pairs in the array nums: (10, 10) and (100, 100).
The maximum XOR possible from these pairs is 10 XOR 10 = 0 since the pair (100, 100) also gives 100 XOR 100 = 0.

Example 3:
Input: nums = [500,520,2500,3000]
Output: 1020
Explanation: There are 6 strong pairs in the array nums: (500, 500), (500, 520), (520, 520), (2500, 2500), (2500, 3000) and (3000, 3000).
The maximum XOR possible from these pairs is 500 XOR 520 = 1020 since the only other non-zero XOR value is 2500 XOR 3000 = 636.

Constraints:
1 <= nums.length <= 5 * 10^4
1 <= nums[i] <= 2^20 - 1

hints:
1 Sort the array, now let x <= y which means |x - y| <= min(x, y) can now be written as y - x <= x or in other words, y <= 2 * x.
2 If x and y have the same number of bits, try making y’s bits different from x if possible for each bit starting from the second most significant bit.
3 If y has 1 more bit than x and y <= 2 * x use the idea about Digit DP to make y’s prefix smaller than 2 * x + 1 as well as trying to make each bit different from x using a Hashmap.
4 Alternatively, use Trie data structure to find the pair with maximum XOR.

"""
from typing import List


class MaximumStrongPairXORII:
    def maximumStrongPairXor(self, nums: List[int]) -> int:
        nums.sort()
        l = res = 0
        trie = Trie()
        for r in range(len(nums)):
            trie.insert(nums[r])
            while l < r and nums[r] - nums[l] > nums[l]:
                trie.delete(nums[l])
                l += 1
            res = max(res, trie.find_max(nums[r]))
        return res


class Trie:
    def __init__(self):
        self.root = TrieNode()

    def insert(self, val):
        self.traverse(val, 1)
    def delete(self, val):
        self.traverse(val, -1)

    def traverse(self, val, inc):
        bits = self.binary(val)
        cur = self.root
        for bit in bits:
            bit = int(bit)
            if cur.children[bit] is None:
                cur.children[bit] = TrieNode()
            cur = cur.children[bit]
            cur.cnt += inc

    def find_max(self, val):
        res = 0
        bits = self.binary(val)
        cur = self.root
        for bit in bits:
            bit = int(bit)
            if cur.children[1 ^ bit] and cur.children[1 ^ bit].cnt > 0:
                cur = cur.children[1 ^ bit]
                res = res * 2 + 1
            elif cur.children[bit].cnt > 0:
                cur = cur.children[bit]
                res = res * 2
        return res

    def binary(self, val):
        bit_string = bin(val)[2:]
        if len(bit_string) < 20:
            bit_string = "0" * (20 - len(bit_string)) + bit_string
        return bit_string

class TrieNode:
    def __init__(self):
        self.children = [None, None]
        self.cnt = 0