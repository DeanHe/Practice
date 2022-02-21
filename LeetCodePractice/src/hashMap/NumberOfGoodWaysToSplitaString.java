package hashMap;

import java.util.HashMap;
import java.util.Map;

/*
You are given a string s, a split is called good if you can split s into 2 non-empty strings p and q where its concatenation is equal to s and the number of distinct letters in p and q are the same.

Return the number of good splits you can make in s.

Example 1:
Input: s = "aacaba"
Output: 2
Explanation: There are 5 ways to split "aacaba" and 2 of them are good.
("a", "acaba") Left string and right string contains 1 and 3 different letters respectively.
("aa", "caba") Left string and right string contains 1 and 3 different letters respectively.
("aac", "aba") Left string and right string contains 2 and 2 different letters respectively (good split).
("aaca", "ba") Left string and right string contains 2 and 2 different letters respectively (good split).
("aacab", "a") Left string and right string contains 3 and 1 different letters respectively.

Example 2:
Input: s = "abcd"
Output: 1
Explanation: Split the string as follows ("ab", "cd").

Example 3:
Input: s = "aaaaa"
Output: 4
Explanation: All possible splits are good.

Example 4:
Input: s = "acbadbaada"
Output: 2

Constraints:
s contains only lowercase English letters.
1 <= s.length <= 10^5

analysis:
two pass with prefix sum
TC O(N)
 */
public class NumberOfGoodWaysToSplitaString {
    public int numSplits(String s) {
        int len = s.length();
        Map<Character, Integer> freq = new HashMap<>();
        int[] preCnt = new int[len];
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            freq.put(c, freq.getOrDefault(c, 0) + 1);
            preCnt[i] = freq.size();
        }
        freq.clear();
        int[] suffCnt = new int[len];
        for (int i = len - 1; i >= 0; i--) {
            char c = s.charAt(i);
            freq.put(c, freq.getOrDefault(c, 0) + 1);
            suffCnt[i] = freq.size();
        }
        int res = 0;
        for (int i = 0; i + 1 < len; i++) {
            if (preCnt[i] == suffCnt[i + 1]) {
                res++;
            }
        }
        return res;
    }
}

