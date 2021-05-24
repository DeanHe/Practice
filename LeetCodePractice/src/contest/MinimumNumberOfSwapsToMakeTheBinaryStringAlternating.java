package contest;

/*
Given a binary string s, return the minimum number of character swaps to make it alternating, or -1 if it is impossible.

The string is called alternating if no two adjacent characters are equal. For example, the strings "010" and "1010" are alternating, while the string "0100" is not.

Any two characters may be swapped, even if they are not adjacent.



Example 1:

Input: s = "111000"
Output: 1
Explanation: Swap positions 1 and 4: "111000" -> "101010"
The string is now alternating.
Example 2:

Input: s = "010"
Output: 0
Explanation: The string is already alternating, no swaps are needed.
Example 3:

Input: s = "1110"
Output: -1


Constraints:

1 <= s.length <= 1000
s[i] is either '0' or '1'.

tag: array
 */

public class MinimumNumberOfSwapsToMakeTheBinaryStringAlternating {
    public int minSwaps(String s) {
        int ones = 0, twos = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') {
                ones++;
            } else {
                twos++;
            }
        }
        if (Math.abs(ones - twos) > 1) {
            return -1;
        }
        int startOneDiff = 0, startZeroDiff = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i % 2 == 0) {
                if (s.charAt(i) == '1') {
                    startZeroDiff++;
                } else {
                    startOneDiff++;
                }
            } else {
                if (s.charAt(i) == '1') {
                    startOneDiff++;
                } else {
                    startZeroDiff++;
                }
            }
        }
        if (ones > twos) {
            return startOneDiff / 2;
        } else if (ones < twos) {
            return startZeroDiff / 2;
        } else {
            return Math.min(startOneDiff / 2, startZeroDiff / 2);
        }
    }
}

