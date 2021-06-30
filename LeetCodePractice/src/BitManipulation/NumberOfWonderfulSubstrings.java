package BitManipulation;

import java.util.HashMap;
import java.util.Map;

/*
A wonderful string is a string where at most one letter appears an odd number of times.

For example, "ccjjc" and "abab" are wonderful, but "ab" is not.
Given a string word that consists of the first ten lowercase English letters ('a' through 'j'), return the number of wonderful non-empty substrings in word. If the same substring appears multiple times in word, then count each occurrence separately.

A substring is a contiguous sequence of characters in a string.



Example 1:

Input: word = "aba"
Output: 4
Explanation: The four wonderful substrings are underlined below:
- "aba" -> "a"
- "aba" -> "b"
- "aba" -> "a"
- "aba" -> "aba"
Example 2:

Input: word = "aabb"
Output: 9
Explanation: The nine wonderful substrings are underlined below:
- "aabb" -> "a"
- "aabb" -> "aa"
- "aabb" -> "aab"
- "aabb" -> "aabb"
- "aabb" -> "a"
- "aabb" -> "abb"
- "aabb" -> "b"
- "aabb" -> "bb"
- "aabb" -> "b"
Example 3:

Input: word = "he"
Output: 2
Explanation: The two wonderful substrings are underlined below:
- "he" -> "h"
- "he" -> "e"


Constraints:

1 <= word.length <= 10^5
word consists of lowercase English letters from 'a' to 'j'.

hint:
For each prefix of the string, check which characters are of even frequency and which are not and represent it by a bitmask.
Find the other prefixes whose masks differs from the current prefix mask by at most one bit.

analysis:
state for even is always 0, for odd can be different

similar to 1371. Find the Longest Substring Containing Vowels in Even Counts

TC O(10 * N) -> O(N)

 */
public class NumberOfWonderfulSubstrings {
    public long wonderfulSubstrings(String word) {
        long res = 0;
        Map<Integer, Integer> stateCnt = new HashMap<>();
        int state = 0;
        stateCnt.put(state, 1);
        for(char c : word.toCharArray()){
            state ^= toMask(c);
            // Add counts of word[:i] with only one character in odd counts.
            for(char x = 'a'; x <= 'j'; x++){

                res += stateCnt.getOrDefault(state ^ toMask(x), 0);
            }
            // Add counts of word[:i] with all characters in even counts.
            res += stateCnt.getOrDefault(state, 0);
            stateCnt.put(state, stateCnt.getOrDefault(state, 0) + 1);
        }
        return res;
    }

    private int toMask(char c){
        return 1 << (c - 'a');
    }
}

