package contest;

import sweepLine.TextNormalization;

import java.util.*;

/*
The appeal of a string is the number of distinct characters found in the string.

For example, the appeal of "abbca" is 3 because it has 3 distinct characters: 'a', 'b', and 'c'.
Given a string s, return the total appeal of all of its substrings.

A substring is a contiguous sequence of characters within a string.

Example 1:
Input: s = "abbca"
Output: 28
Explanation: The following are the substrings of "abbca":
- Substrings of length 1: "a", "b", "b", "c", "a" have an appeal of 1, 1, 1, 1, and 1 respectively. The sum is 5.
- Substrings of length 2: "ab", "bb", "bc", "ca" have an appeal of 2, 1, 2, and 2 respectively. The sum is 7.
- Substrings of length 3: "abb", "bbc", "bca" have an appeal of 2, 2, and 3 respectively. The sum is 7.
- Substrings of length 4: "abbc", "bbca" have an appeal of 3 and 3 respectively. The sum is 6.
- Substrings of length 5: "abbca" has an appeal of 3. The sum is 3.
The total sum is 5 + 7 + 7 + 6 + 3 = 28.

Example 2:
Input: s = "code"
Output: 20
Explanation: The following are the substrings of "code":
- Substrings of length 1: "c", "o", "d", "e" have an appeal of 1, 1, 1, and 1 respectively. The sum is 4.
- Substrings of length 2: "co", "od", "de" have an appeal of 2, 2, and 2 respectively. The sum is 6.
- Substrings of length 3: "cod", "ode" have an appeal of 3 and 3 respectively. The sum is 6.
- Substrings of length 4: "code" has an appeal of 4. The sum is 4.
The total sum is 4 + 6 + 6 + 4 = 20.


Constraints:
1 <= s.length <= 10^5
s consists of lowercase English letters.

hint:
1 Consider the set of substrings that end at a certain index i. Then, consider a specific alphabetic character.
How do you count the number of substrings ending at index i that contain that character?

2 The number of substrings that contain the alphabetic character is equivalent to 1 plus the index of the last occurrence of the character before index i + 1.
3 The total appeal of all substrings ending at index i is the total sum of the number of substrings that contain each alphabetic character.
4 To find the total appeal of all substrings, we simply sum up the total appeal for each index.
 */
public class TotalAppealOfAString {
    public long appealSum(String s) {
        int len = s.length();
        long res = 0, cur = 0;
        long[] pre = new long[26];
        for(int i = 0; i < len; i++){
            int idx = s.charAt(i) - 'a';
            cur += i + 1 - pre[idx];
            pre[idx] = i + 1;
            res += cur;
        }
        return res;
    }
}
