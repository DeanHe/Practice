"""
You are given a string s and two integers x and y. You can perform two types of operations any number of times.

Remove substring "ab" and gain x points.
For example, when removing "ab" from "cabxbae" it becomes "cxbae".
Remove substring "ba" and gain y points.
For example, when removing "ba" from "cabxbae" it becomes "cabxe".
Return the maximum points you can gain after applying the above operations on s.

Example 1:
Input: s = "cdbcbbaaabab", x = 4, y = 5
Output: 19
Explanation:
- Remove the "ba" underlined in "cdbcbbaaabab". Now, s = "cdbcbbaaab" and 5 points are added to the score.
- Remove the "ab" underlined in "cdbcbbaaab". Now, s = "cdbcbbaa" and 4 points are added to the score.
- Remove the "ba" underlined in "cdbcbbaa". Now, s = "cdbcba" and 5 points are added to the score.
- Remove the "ba" underlined in "cdbcba". Now, s = "cdbc" and 5 points are added to the score.
Total score = 5 + 4 + 5 + 5 = 19.

Example 2:
Input: s = "aabbaaxybbaabb", x = 5, y = 4
Output: 20

Constraints:
1 <= s.length <= 10^5
1 <= x, y <= 10^4
s consists of lowercase English letters.

hints:
1 Note that it is always more optimal to take one type of substring before another
2 You can use a stack to handle erasures

analysis:
TC: O(N)
"""

class MaximumScoreFromRemovingSubstrings:
    def maximumGain(self, s: str, x: int, y: int) -> int:
        res = 0
        big = max(x, y)
        small = min(x, y)
        first = 'a' if x > y else 'b'
        second = 'b' if x > y else 'a'
        stack1 = []
        for c in s:
            if stack1 and stack1[-1] == first and c == second:
                stack1.pop()
                res += big
            else:
                stack1.append(c)
        stack2 = []
        while stack1:
            c = stack1.pop()
            if stack2 and stack2[-1] == first and c == second:
                stack2.pop()
                res += small
            else:
                stack2.append(c)
        return res