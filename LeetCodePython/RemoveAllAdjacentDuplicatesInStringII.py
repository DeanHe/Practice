"""
You are given a string s and an integer k, a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them, causing the left and the right side of the deleted substring to concatenate together.

We repeatedly make k duplicate removals on s until we no longer can.

Return the final string after all such duplicate removals have been made. It is guaranteed that the answer is unique.

Example 1:
Input: s = "abcd", k = 2
Output: "abcd"
Explanation: There's nothing to delete.

Example 2:
Input: s = "deeedbbcccbdaa", k = 3
Output: "aa"
Explanation:
First delete "eee" and "ccc", get "ddbbbdaa"
Then delete "bbb", get "dddaa"
Finally delete "ddd", get "aa"

Example 3:
Input: s = "pbbcggttciiippooaais", k = 2
Output: "ps"

Constraints:
1 <= s.length <= 10^5
2 <= k <= 10^4
s only contains lower case English letters.

hint:
1 Use a stack to store the characters, when there are k same characters, delete them.
2 To make it more efficient, use a pair to store the value and the count of each character.
"""
import collections


class RemoveAllAdjacentDuplicatesInStringII:
    def removeDuplicates(self, s: str, k: int) -> str:
        q = collections.deque()
        for _, c in enumerate(s):
            if q and q[-1][0] == c:
                q[-1][1] += 1
            else:
                q.append([c, 1])
            if q[-1][1] == k:
                q.pop()
        res = []
        while q:
            letter, cnt = q.popleft()
            for i in range(cnt):
                res.append(letter)
        return ''.join(res)
