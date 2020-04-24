package DP.memorization;
/*
A program was supposed to print an array of integers.
The program forgot to print whitespaces and the array is printed as a string of digits and all we know is that all integers in the array were in the range [1, k] and there are no leading zeros in the array.
Given the string s and the integer k. There can be multiple ways to restore the array.
Return the number of possible array that can be printed as a string s using the mentioned program.
The number of ways could be very large so return it modulo 10^9 + 7

        Example 1:
        Input: s = "1000", k = 10000
        Output: 1
        Explanation: The only possible array is [1000]

        Example 2:
        Input: s = "1000", k = 10
        Output: 0
        Explanation: There cannot be an array that was printed this way and has all integer >= 1 and <= 10.

        Example 3:
        Input: s = "1317", k = 2000
        Output: 8
        Explanation: Possible arrays are [1317],[131,7],[13,17],[1,317],[13,1,7],[1,31,7],[1,3,17],[1,3,1,7]

        Example 4:
        Input: s = "2020", k = 30
        Output: 1
        Explanation: The only possible array is [20,20]. [2020] is invalid because 2020 > 30. [2,020] is ivalid because 020 contains leading zeros.

        Example 5:
        Input: s = "1234567890", k = 90
        Output: 34

        Constraints:
        1 <= s.length <= 10^5.
        s consists of only digits and doesn't contain leading zeros.
        1 <= k <= 10^9.

        DFS + Memorization
        Complexity

        Time: O(n * log10(k)), where n <= 10^5 is the length of string s, k <= 10^9 => log10(k) <= 9
        Explain: There are total n states, each state needs maximum log10(k) iteration loops to calculate the result.
        Space: O(n)
*/

import java.util.Arrays;

public class RestoreTheArray {
    String s;
    int k, MOD = (int) (1e9 + 7);
    long[] mem;

    public int numberOfArrays(String s, int k) {
        this.s = s;
        this.k = k;
        int len = s.length();
        mem = new long[len];
        Arrays.fill(mem, -1);
        return (int) dfs(0);
    }

    private long dfs(int pos) { // number of ways to restore string s[i:]
        if (pos == s.length()) {
            return 1;
        }
        if (mem[pos] != -1) {
            return mem[pos];
        }
        if (s.charAt(pos) == '0') {
            mem[pos] = 0;
            return mem[pos];
        }
        long res = 0, num = 0;
        for (int i = pos; i < s.length(); i++) {
            num = num * 10 + s.charAt(i) - '0';
            if (num > k) {
                break;
            }
            res = (res + dfs(i + 1)) % MOD;
        }
        mem[pos] = res;
        return mem[pos];
    }
}
