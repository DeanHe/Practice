package PrefixSum;
/*
#1781

The beauty of a string is the difference in frequencies between the most frequent and least frequent characters.

For example, the beauty of "abaacc" is 3 - 1 = 2.
Given a string s, return the sum of beauty of all of its substrings.



Example 1:

Input: s = "aabcb"
Output: 5
Explanation: The substrings with non-zero beauty are ["aab","aabc","aabcb","abcb","bcb"], each with beauty equal to 1.
Example 2:

Input: s = "aabcbaa"
Output: 17


Constraints:

1 <= s.length <= 500
s consists of only lowercase English letters.

 */
public class SumOfBeautyOfAllSubstrings {
    public int beautySum(String s) {
        int len = s.length(), res = 0;
        for(int i = 0; i < len; i++){
            int[] freq = new int[26];
            for(int j = i; j < len; j++){
                char c = s.charAt(j);
                freq[c - 'a']++;
                res += helper(freq);
            }
        }
        return res;
    }

    private int helper(int[] freq) {
        int least = Integer.MAX_VALUE, most = Integer.MIN_VALUE;
        for(int n : freq){
            if(n != 0){
                least = Math.min(least, n);
                most = Math.max(most, n);
            }
        }
        return most - least;
    }
}
