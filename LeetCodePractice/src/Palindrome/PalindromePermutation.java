package Palindrome;

import java.util.HashSet;
import java.util.Set;

/*
Given a string, determine if a permutation of the string could form a palindrome.

Example
Example1

Input: s = "code"
Output: False
Explanation: 
No solution
Example2

Input: s = "aab"
Output: True
Explanation: 
"aab" --> "aba"
Example3

Input: s = "carerac"
Output: True
Explanation: 
"carerac" --> "carerac"
*/
public class PalindromePermutation {
	/**
     * @param s: the given string
     * @return: if a permutation of the string could form a palindrome
     */
    public boolean canPermutePalindrome(String s) {
        // write your code here
    	Set<Character> set = new HashSet<>();
    	char[] arr = s.toCharArray();
    	for(char c : arr){
    		if(set.contains(c)){
    			set.remove(c);
    		} else {
    			set.add(c);
    		}
    	}
    	return set.size() <= 1;
    }
}
