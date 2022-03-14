package hashMap.Anagram;
/*
You are given two strings s and t. In one step, you can append any character to either s or t.

Return the minimum number of steps to make s and t anagrams of each other.

An anagram of a string is a string that contains the same characters with a different (or the same) ordering.



Example 1:

Input: s = "leetcode", t = "coats"
Output: 7
Explanation:
- In 2 steps, we can append the letters in "as" onto s = "leetcode", forming s = "leetcodeas".
- In 5 steps, we can append the letters in "leede" onto t = "coats", forming t = "coatsleede".
"leetcodeas" and "coatsleede" are now anagrams of each other.
We used a total of 2 + 5 = 7 steps.
It can be shown that there is no way to make them anagrams of each other with less than 7 steps.
Example 2:

Input: s = "night", t = "thing"
Output: 0
Explanation: The given strings are already anagrams of each other. Thus, we do not need any further steps.


Constraints:

1 <= s.length, t.length <= 2 * 10^5
s and t consist of lowercase English letters.

hint:
1 Notice that for anagrams, the order of the letters is irrelevant.
2 For each letter, we can count its frequency in s and t.
3 For each letter, its contribution to the answer is the absolute difference between its frequency in s and t.
 */
public class MinimumNumberOfStepsToMakeTwoStringsAnagramII {
    public int minSteps(String s, String t) {
        int[] cnts = new int[26];
        int[] cntt = new int[26];
        for(char c : s.toCharArray()){
            cnts[c -'a']++;
        }
        for(char c : t.toCharArray()){
            cntt[c -'a']++;
        }
        int res = 0;
        for(int i = 0; i < 26; i++){
            res += Math.abs(cnts[i] - cntt[i]);
        }
        return res;
    }
}
