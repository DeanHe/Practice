"""
Given an integer array nums, return the maximum result of nums[i] XOR nums[j], where 0 <= i <= j < n.



Example 1:

Input: nums = [3,10,5,25,2,8]
Output: 28
Explanation: The maximum result is 5 XOR 25 = 28.
Example 2:

Input: nums = [14,70,53,83,49,91,36,80,92,51,66,70]
Output: 127


Constraints:
1 <= nums.length <= 2 * 10^5
0 <= nums[i] <= 2^31 - 1

anlaysis:
binary Trie + 1's complement number
TC:O(N)
"""
from typing import List

class TrieNode:

    def __init__(self):
        self.children = [None, None]

class MaximumXORofTwoNumbersInAnArray:
    def findMaximumXOR(self, nums: List[int]) -> int:
        root = TrieNode()
        res = 0
        bits_arr = []
        for n in nums:
            bits = bin(n)[2:]
            if len(bits) < 31:
                bits = "0" * (31 - len(bits)) + bits
            bits_arr.append(bits)
            cur = root
            for bit in bits:
                bit = int(bit)
                if not cur.children[bit]:
                    cur.children[bit] = TrieNode()
                cur = cur.children[bit]

        for bits in bits_arr:
            cur = root
            total = 0
            for bit in bits:
                bit = int(bit)
                if cur.children[bit ^ 1]:
                    total = 2 * total + 1
                    cur = cur.children[bit ^ 1]
                else:
                    cur = cur.children[bit]
                    total = 2 * total
            res = max(res, total)
        return res


