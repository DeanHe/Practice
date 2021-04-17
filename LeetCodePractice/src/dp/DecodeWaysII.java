package dp;

/*A message containing letters from A-Z is being encoded to numbers using the following mapping way:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Beyond that, now the encoded string can also contain the character '*', which can be treated as one of the numbers from 1 to 9.

Given the encoded message containing digits and the character '*', return the total number of ways to decode it.

Also, since the answer may be very large, you should return the output mod 10^9 + 7.

Example 1:
Input: "*"
Output: 9
Explanation: The encoded message can be decoded to the string: "A", "B", "C", "D", "E", "F", "G", "H", "I".
Example 2:
Input: "1*"
Output: 9 + 9 = 18
*/
public class DecodeWaysII {
    /**
     * @param s: a string,  encoded message
     * @return: an integer, the number of ways decoding
     */
    int MOD = (int) (1e9 + 7);

    public int numDecodings(String s) {
        Integer[] mem = new Integer[s.length()];
        return dfs(s, s.length() - 1, mem);
    }

    private int dfs(String s, int i, Integer[] mem) {
        if (i < 0) {
            return 1;
        }
        if (mem[i] != null) {
            return mem[i];
        }
        if (s.charAt(i) == '*') {
            long res = 9 * dfs(s, i - 1, mem);
            if (i > 0) {
                if (s.charAt(i - 1) == '1') {
                    res = (res + 9 * dfs(s, i - 2, mem)) % MOD;
                } else if (s.charAt(i - 1) == '2') {
                    res = (res + 6 * dfs(s, i - 2, mem)) % MOD;
                } else if (s.charAt(i - 1) == '*') {
                    res = (res + 15 * dfs(s, i - 2, mem)) % MOD;
                }
            }
            return mem[i] = (int) res;
        }
        long res = 0;
        if (s.charAt(i) != '0') {
            res = dfs(s, i - 1, mem);
        }
        if (i > 0) {
            if (s.charAt(i - 1) == '1') {
                res = (res + dfs(s, i - 2, mem)) % MOD;
            } else if (s.charAt(i - 1) == '2' && s.charAt(i) <= '6') {
                res = (res + dfs(s, i - 2, mem)) % MOD;
            } else if (s.charAt(i - 1) == '*') {
                if (s.charAt(i) <= '6') {
                    res = (res + 2 * dfs(s, i - 2, mem)) % MOD;
                } else {
                    res = (res + dfs(s, i - 2, mem)) % MOD;
                }
            }
        }
        return mem[i] = (int) res;
    }
}
