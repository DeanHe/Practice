"""
You are given a string word and an integer k.

A substring s of word is complete if:

Each character in s occurs exactly k times.
The difference between two adjacent characters is at most 2. That is, for any two adjacent characters c1 and c2 in s, the absolute difference in their positions in the alphabet is at most 2.
Return the number of complete substrings of word.

A substring is a non-empty contiguous sequence of characters in a string.

Example 1:
Input: word = "igigee", k = 2
Output: 3
Explanation: The complete substrings where each character appears exactly twice and the difference between adjacent characters is at most 2 are: igigee, igigee, igigee.

Example 2:
Input: word = "aaabbbccc", k = 3
Output: 6
Explanation: The complete substrings where each character appears exactly three times and the difference between adjacent characters is at most 2 are: aaabbbccc, aaabbbccc, aaabbbccc, aaabbbccc, aaabbbccc, aaabbbccc.

Constraints:
1 <= word.length <= 10^5
word consists only of lowercase English letters.
1 <= k <= word.length

hints:
1 There are at most 26 different lengths of the complete substrings: k *1, k * 2, … k * 26.****
2 For each length, we can use sliding window to count the frequency of each letter in the window.
3 We still need to check for all characters in the window that abs(word[i] - word[i - 1]) <= 2.
We do this by maintaining the values of abs(word[i] - word[i - 1]) in the sliding window dynamically in an ordered multiset or priority queue,
so that we know the maximum value at each iteration.

analysis:
sliding window
"""
from collections import defaultdict


class CountCompleteSubstrings:
    def countCompleteSubstrings(self, word: str, k: int) -> int:

        def has_equal_freq(char_freq):
            for cnt in char_freq.values():
                if cnt != k and cnt != 0:
                    return False
            return True
        def count_substring(w):
            res = 0
            for i in range(1, 27):
                if i * k > len(w):
                    break
                l = 0
                r = l + i * k - 1
                char_freq = defaultdict(int)
                for i in range(l, min(r, len(w))):
                    char_freq[w[i]] += 1
                while r < len(w):
                    char_freq[w[r]] += 1
                    if has_equal_freq(char_freq):
                        res += 1
                    char_freq[w[l]] -= 1
                    l += 1
                    r += 1
            return res

        pre = 0
        res = 0
        for i in range(1, len(word)):
            if abs(ord(word[i]) - ord(word[i - 1])) > 2:
                res += count_substring(word[pre:i])
                pre = i
        res += count_substring(word[pre:])
        return res