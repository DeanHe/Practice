package Stack.MonotonicStack;
/*
Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.

Example 1:

Input: "bcabc"
Output: "abc"
Example 2:

Input: "cbacdcbc"
Output: "acdb"

analysis:
The runtime is O(26 * n) = O(n).
tag:greedy
stack
*/
public class RemoveDuplicateLetters {
	public String removeDuplicateLetters(String s) {
		if(s == null || s.length() <= 1){
			return s;
		}
        int[] count = new int[26];
        int len = s.length();
        for(int i = 0; i < len; i++){
        	int index = s.charAt(i) - 'a';
        	count[index]++;
        }
     // the position for the smallest s[i]
        int pos = 0;
        for(int i = 0; i < len; i++){
        	if(s.charAt(i) < s.charAt(pos)){
        		pos = i;
        	}
        	int index = s.charAt(i) - 'a';
        	count[index]--;
        	if(count[index] == 0){
        		break;
        	}
        }
        if(s.length() == 0){
        	return "";
        }
        String remained = s.substring(pos + 1).replaceAll(String.valueOf(s.charAt(pos)), "");
        return s.charAt(pos) + removeDuplicateLetters(remained);
    }
}
