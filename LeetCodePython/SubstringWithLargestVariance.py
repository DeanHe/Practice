"""
The variance of a string is defined as the largest difference between the number of occurrences of any 2 characters present in the string. Note the two characters may or may not be the same.
Given a string s consisting of lowercase English letters only, return the largest variance possible among all substrings of s.
A substring is a contiguous sequence of characters within a string.

Example 1:
Input: s = "aababbb"
Output: 3
Explanation:
All possible variances along with their respective substrings are listed below:
- Variance 0 for substrings "a", "aa", "ab", "abab", "aababb", "ba", "b", "bb", and "bbb".
- Variance 1 for substrings "aab", "aba", "abb", "aabab", "ababb", "aababbb", and "bab".
- Variance 2 for substrings "aaba", "ababbb", "abbb", and "babb".
- Variance 3 for substring "babbb".
Since the largest possible variance is 3, we return it.

Example 2:
Input: s = "abcde"
Output: 0
Explanation:
No letter occurs more than once in s, so the variance of every substring is 0.


Constraints:
1 <= s.length <= 10^4
s consists of lowercase English letters.

hints:
1 Think about how to solve the problem if the string had only two distinct characters.
2 If we replace all occurrences of the first character by +1 and those of the second character by -1, can we efficiently calculate the largest possible variance of a string with only two distinct characters?
3 Now, try finding the optimal answer by taking all possible pairs of characters into consideration.

analysis:
Kadane's algorithm is a dynamic programming algorithm that finds the maximum subarray sum in an array of integers.
TC: O(N * K^2) Let N be the length of the input string s and K be the number of distinct characters in s.
"""
import collections


class SubstringWithLargestVariance:
    def largestVariance(self, s: str) -> int:
        cnt = collections.Counter(s)
        max_variance = 0
        for i in range(ord('a'), ord('z') + 1):
            a = chr(i)
            for j in range(ord('a'), ord('z') + 1):
                b = chr(j)
                if a == b or cnt[a] == 0 or cnt[b] == 0:
                    continue
                b_remain_cnt = cnt[b]
                a_cnt = b_cnt = 0
                for c in s:
                    if c == a:
                        a_cnt += 1
                    elif c == b:
                        b_cnt += 1
                        b_remain_cnt -= 1
                    if b_cnt > 0:
                        max_variance = max(max_variance, a_cnt - b_cnt)
                    if a_cnt < b_cnt and b_remain_cnt > 0:
                        a_cnt = 0
                        b_cnt = 0
        return max_variance