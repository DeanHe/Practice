package Window;
/*
Given a string s consisting only of characters a, b and c.

        Return the number of substrings containing at least one occurrence of all these characters a, b and c.



        Example 1:

        Input: s = "abcabc"
        Output: 10
        Explanation: The substrings containing at least one occurrence of the characters a, b and c are "abc", "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab", "cabc" and "abc" (again).
        Example 2:

        Input: s = "aaacb"
        Output: 3
        Explanation: The substrings containing at least one occurrence of the characters a, b and c are "aaacb", "aacb" and "acb".
        Example 3:

        Input: s = "abc"
        Output: 1


        Constraints:

        3 <= s.length <= 5 x 10^4
        s only consists of a, b or c characters.
*/
public class NumberOfSubstringsContainingAllThreeCharacters {
    public int numberOfSubstrings(String s) {
        int len = s.length(), l = 0, res = 0;
        int[] count = {0, 0, 0};
        for(int r = 0; r < len; r++){
            int r_idx = s.charAt(r) - 'a';
            count[r_idx]++;
            while(count[0] > 0 && count[1] > 0 && count[2] > 0){
                res += len - r;  // count of all substring [l, r...s.length() - 1]
                int l_idx = s.charAt(l) - 'a';
                count[l_idx]--;
                l++;
            }
        }
        return res;
    }
}
