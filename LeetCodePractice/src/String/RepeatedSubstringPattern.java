package String;
/*Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring together. You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.

Example 1:

Input: "abab"
Output: True
Explanation: It's the substring "ab" twice.
Example 2:

Input: "aba"
Output: False
Example 3:

Input: "abcabcabcabc"
Output: True
Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
*/
public class RepeatedSubstringPattern {
	public boolean repeatedSubstringPattern(String s) {
        for(int len = s.length() / 2; len >= 1; len--){
        	if(s.length() % len == 0){
        		int repeat = s.length() / len;
        		String part = s.substring(0, len);
        		StringBuilder sb = new StringBuilder();
        		for(int i = 0; i < repeat; i++){
        			sb.append(part);
        		}
        		if(sb.toString().equals(s)){
        			return true;
        		}
        	}
        }
        return false;
    }
}
