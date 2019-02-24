package Palindrome;
/*Given a string s, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.

Example 1:

Input: "aacecaaa"
Output: "aaacecaaa"
Example 2:

Input: "abcd"
Output: "dcbabcd"
https://leetcode.com/problems/shortest-palindrome/discuss/60098/My-7-lines-recursive-Java-solution
*/
public class ShortestPalindrome {
	public String shortestPalindrome(String s) {
        int j = 0;
        int len = s.length();
        for(int i = len - 1; i >= 0; i--){
        	if(s.charAt(j) == s.charAt(i)){
        		j++;
        	}
        }
        if(j == len){
        	return s;
        }
        String suffix = s.substring(j);
        String suffixReverse = new StringBuffer(suffix).reverse().toString();
        return suffixReverse + shortestPalindrome(s.substring(0, j)) + suffix;
    }
	
	public String shortestPalindromeBrutalForce(String s) {
        int len = s.length();
        String reverse = new StringBuffer(s).reverse().toString();
        for(int i = 0; i < len; i++){
        	if(s.startsWith(reverse.substring(i))){
        		return reverse.substring(0, i) + s;
        	}
        }
        return reverse + s;
    }
}
