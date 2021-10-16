package twoPointers.window;
/*
You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most k times.
Return the length of the longest substring containing the same letter you can get after performing the above operations.

Example 1:

Input: s = "ABAB", k = 2
Output: 4
Explanation: Replace the two 'A's with two 'B's or vice versa.
Example 2:

Input: s = "AABABBA", k = 1
Output: 4
Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.

Constraints:

1 <= s.length <= 10^5
s consists of only uppercase English letters.
0 <= k <= s.length

analysis:
when r - l + 1 == mostCnt, it means there is  one unique letter in the window
when  k + mostCnt < r - l + 1, it means can't make a valid window by using k replacement
as long as k + mostCnt >= r - l + 1, the window is a valid candidate,
note mostCnt is invalid after l move, but we do not need the accurate mostCnt of the current window, we only care if the mostCnt exceeds the historical mostCnt;
TC O(N)
 */
public class LongestRepeatingCharacterReplacement {
    public int characterReplacement(String s, int k) {
        int len = s.length(), res = 0, l = 0, mostCnt = 0;
        int[] cnt = new int[26];
        for(int r = 0; r < len; r++){
            mostCnt = Math.max(mostCnt, ++cnt[s.charAt(r) - 'A']);
            if (k + mostCnt < r - l + 1){
                cnt[s.charAt(l) - 'A']--;
                l++;
            }
            res = Math.max(res, r - l + 1);
        }
        return res;
    }
}
