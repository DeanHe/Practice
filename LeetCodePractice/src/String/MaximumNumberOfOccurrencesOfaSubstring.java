package String;

import java.util.*;

/*
Given a string s, return the maximum number of ocurrences of any substring under the following rules:

        The number of unique characters in the substring must be less than or equal to maxLetters.
        The substring size must be between minSize and maxSize inclusive.


        Example 1:

        Input: s = "aababcaab", maxLetters = 2, minSize = 3, maxSize = 4
        Output: 2
        Explanation: Substring "aab" has 2 ocurrences in the original string.
        It satisfies the conditions, 2 unique letters and size 3 (between minSize and maxSize).
        Example 2:

        Input: s = "aaaa", maxLetters = 1, minSize = 3, maxSize = 3
        Output: 2
        Explanation: Substring "aaa" occur 2 times in the string. It can overlap.
        Example 3:

        Input: s = "aabcabcab", maxLetters = 2, minSize = 2, maxSize = 3
        Output: 3
        Example 4:

        Input: s = "abcde", maxLetters = 2, minSize = 3, maxSize = 3
        Output: 0


        Constraints:

        1 <= s.length <= 10^5
        1 <= maxLetters <= 26
        1 <= minSize <= maxSize <= min(26, s.length)
        s only contains lowercase English letters.
*/
public class MaximumNumberOfOccurrencesOfaSubstring {
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        Map<String, Integer> freq = new HashMap<>();
        int len = s.length(), res = 0, l = 0, unique = 0;
        int[] letterCnt = new int[26];
        char[] arr = s.toCharArray();
        for (int r = 0; r < len; r++) {
            int r_idx = arr[r] - 'a';
            letterCnt[r_idx]++;
            if (letterCnt[r_idx] == 1) {
                unique++;
            }
            while (unique > maxLetters || r - l + 1 > minSize) {
                int l_idx = arr[l] - 'a';
                letterCnt[l_idx]--;
                if (letterCnt[l_idx] == 0) {
                    unique--;
                }
                l++;
            }
            if (r - l + 1 == minSize) {
                String cand = s.substring(l, r + 1);
                freq.put(cand, freq.getOrDefault(cand, 0) + 1);
                res = Math.max(res, freq.get(cand));
            }
        }
        return res;
    }
}
