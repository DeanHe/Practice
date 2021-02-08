package Contest;

/*
You are given two strings a and b that consist of lowercase letters. In one operation, you can change any character in a or b to any lowercase letter.

Your goal is to satisfy one of the following three conditions:

Every letter in a is strictly less than every letter in b in the alphabet.
Every letter in b is strictly less than every letter in a in the alphabet.
Both a and b consist of only one distinct letter.
Return the minimum number of operations needed to achieve your goal.



Example 1:

Input: a = "aba", b = "caa"
Output: 2
Explanation: Consider the best way to make each condition true:
1) Change b to "ccc" in 2 operations, then every letter in a is less than every letter in b.
2) Change a to "bbb" and b to "aaa" in 3 operations, then every letter in b is less than every letter in a.
3) Change a to "aaa" and b to "aaa" in 2 operations, then a and b consist of one distinct letter.
The best way was done in 2 operations (either condition 1 or condition 3).
Example 2:

Input: a = "dabadd", b = "cda"
Output: 3
Explanation: The best way is to make condition 1 true by changing b to "eee".


Constraints:

1 <= a.length, b.length <= 10^5
a and b consist only of lowercase letters.

analysis:
operation1 calculate cost of change all characters of a below bound, all characters of b above or equals bound

analysis:
prefix Sum
 */
public class ChangeMinimumCharactersToSatisfyOneOfThreeConditions {
    public int minCharacters(String a, String b) {
        int[] cnt_a = new int[26];
        int[] cnt_b = new int[26];
        int alen = a.length(), blen = b.length(), res = alen + blen;
        for (char c : a.toCharArray()) {
            cnt_a[c - 'a']++;
        }
        for (char c : b.toCharArray()) {
            cnt_b[c - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            res = Math.min(res, alen + blen - cnt_a[i] - cnt_b[i]);
            if (i > 0) {
                cnt_a[i] += cnt_a[i - 1];
                cnt_b[i] += cnt_b[i - 1];
            }
            if (i < 25) {
                res = Math.min(res, cnt_a[i] + blen - cnt_b[i]);
                res = Math.min(res, cnt_b[i] + alen - cnt_a[i]);
            }
        }
        return res;
    }
}
