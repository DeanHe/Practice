"""
You are given a string word. A letter c is called special if it appears both in lowercase and uppercase in word, and every lowercase occurrence of c appears before the first uppercase occurrence of c.

Return the number of special letters in word.

Example 1:
Input: word = "aaAbcBC"
Output: 3
Explanation:
The special characters are 'a', 'b', and 'c'.

Example 2:
Input: word = "abc"
Output: 0
Explanation:
There are no special characters in word.

Example 3:
Input: word = "AbBCab"
Output: 0
Explanation:
There are no special characters in word.

Constraints:
1 <= word.length <= 2 * 10^5
word consists of only lowercase and uppercase English letters.
"""

class CountTheNumberOfSpecialCharactersII:
    def numberOfSpecialChars(self, word: str) -> int:
        res = 0
        last_lower_idx = {}
        first_upper_idx = {}
        diff = ord('a') - ord('A')
        for i, c in enumerate(word):
            if 'Z' < c:
                last_lower_idx[c] = i
            else:
                if c not in first_upper_idx:
                    first_upper_idx[c] = i
        for c, last in last_lower_idx.items():
            upper_c = chr(ord(c) - diff)
            if upper_c in first_upper_idx and last < first_upper_idx[upper_c]:
                res += 1
        return res

