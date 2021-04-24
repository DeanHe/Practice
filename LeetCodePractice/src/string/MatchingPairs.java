package string;

import java.util.HashSet;
import java.util.Set;

/*
Given two strings s and t of length N, find the maximum number of possible matching pairs in strings s and t after swapping exactly two characters within s.
A swap is switching s[i] and s[j], where s[i] and s[j] denotes the character that is present at the ith and jth index of s, respectively. The matching pairs of the two strings are defined as the number of indices for which s[i] and t[i] are equal.
Note: This means you must swap two characters at different indices.
Signature
int matchingPairs(string s, string t)
Input
s and t are strings of length N
N is between 2 and 1,000,000
Output
Return an integer denoting the maximum number of matching pairs
Example 1
s = "abcd"
t = "adcb"
output = 4
Explanation:
Using 0-based indexing, and with i = 1 and j = 3, s[1] and s[3] can be swapped, making it  "adcb".
Therefore, the number of matching pairs of s and t will be 4.
Example 2
s = "mno"
t = "mno"
output = 1
Explanation:
Two indices have to be swapped, regardless of which two it is, only one letter will remain the same. If i = 0 and j=1, s[0] and s[1] are swapped, making s = "nmo", which shares only "o" with t.
 */
public class MatchingPairs {
    int matchingPairs(String s, String t) {
        // Write your code here
        int res = 0;
        int len = s.length();
        Set<String> diff = new HashSet<>();
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == t.charAt(i)) {
                res++;
            } else {
                diff.add("" + s.charAt(i) + t.charAt(i));
            }
        }
        for (String m : diff) {
            if (diff.contains("" + m.charAt(1) + m.charAt(0))) {
                return res + 2;
            }
        }
        if (diff.size() == 1) {
            return res - 1;
        }
        if (diff.size() == 0) {
            return res - 2;
        }
        return res;
    }
}
