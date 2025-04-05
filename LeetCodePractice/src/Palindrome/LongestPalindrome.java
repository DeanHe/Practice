package Palindrome;

import java.util.*;

/*
Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.

This is case sensitive, for example "Aa" is not considered a palindrome here.

Example
Example 1:

Input : s = "abccccdd"
Output : 7
Explanation :
One longest palindrome that can be built is "dccaccd", whose length is `7`.
Notice
Assume the length of given string will not exceed 1010.
*/
public class LongestPalindrome {
	/**
     * @param s: a string which consists of lowercase or uppercase letters
     * @return: the length of the longest palindromes that can be built
     */
    public int longestPalindrome(String s) {
        // write your code here
    	if(s == null || s.length() == 0){
            return 0;
        }
        Set<Character> oddSet = new HashSet<>();
        char[] arr = s.toCharArray();
        for(char c : arr){
            if(oddSet.contains(c)){
                oddSet.remove(c);
            } else {
                oddSet.add(c);
            }
        }
        if(oddSet.size() > 0){
            return s.length() - oddSet.size() + 1;
        } else {
            return s.length();
        }
    }
}
