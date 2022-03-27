package Palindrome;
/*
Given an integer array queries and a positive integer intLength, return an array answer where answer[i] is either the queries[i]th smallest positive palindrome of length intLength or -1 if no such palindrome exists.

A palindrome is a number that reads the same backwards and forwards. Palindromes cannot have leading zeros.


Example 1:
Input: queries = [1,2,3,4,5,90], intLength = 3
Output: [101,111,121,131,141,999]
Explanation:
The first few palindromes of length 3 are:
101, 111, 121, 131, 141, 151, 161, 171, 181, 191, 201, ...
The 90th palindrome of length 3 is 999.

Example 2:
Input: queries = [2,4,6], intLength = 4
Output: [1111,1331,1551]
Explanation:
The first six palindromes of length 4 are:
1001, 1111, 1221, 1331, 1441, and 1551.


Constraints:

1 <= queries.length <= 5 * 10^4
1 <= queries[i] <= 10^9
1 <= intLength <= 15

hint:
1 For any value of queries[i] and intLength, how can you check if there exists at least queries[i] palindromes of length intLength?
2 Since a palindrome reads the same forwards and backwards, consider how you can efficiently find the first half (ceil(intLength/2) digits) of the palindrome.
*/
public class FindPalindromeWithFixedLength {
    public long[] kthPalindrome(int[] queries, int intLength) {
        long[] res = new long[queries.length];
        long half = (intLength + 1) / 2;
        long s = (long) Math.pow(10, half - 1);
        long e = (long) Math.pow(10, half)  - 1;
        for(int i = 0; i < queries.length; i++){
            int query = queries[i];
            if(query <= e - s + 1){
                String prefix = String.valueOf(s + query - 1);
                String suffix = (new StringBuilder(prefix)).reverse().toString();
                res[i] = Long.valueOf(prefix + suffix.substring(intLength % 2));
            } else {
                res[i] = -1;
            }
        }
        return res;
    }
}
