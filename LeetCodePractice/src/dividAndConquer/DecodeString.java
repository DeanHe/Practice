package dividAndConquer;

import java.time.temporal.ChronoField;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
Given an encoded string, return its decoded string.
The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

Example 1:

Input: s = "3[a]2[bc]"
Output: "aaabcbc"
Example 2:

Input: s = "3[a2[c]]"
Output: "accaccacc"
Example 3:

Input: s = "2[abc]3[cd]ef"
Output: "abcabccdcdcdef"
Example 4:

Input: s = "abc3[cd]xyz"
Output: "abccdcdcdxyz"


Constraints:

1 <= s.length <= 30
s consists of lowercase English letters, digits, and square brackets '[]'.
s is guaranteed to be a valid input.
All the integers in s are in the range [1, 300].

analysis:
TC O(max_repeat * N)
SC O(N)
*/
public class DecodeString {
	/**
     * @param s: an expression includes numbers, letters and brackets
     * @return: a string
     */
	public String decodeStringRecursion(String s) {
		Queue<Character> q = new LinkedList<>();
		for(char c : s.toCharArray()){
			q.offer(c);
		}
		return helper(q);
	}

	private String helper(Queue<Character> q){
		int cnt = 0;
		StringBuilder sb = new StringBuilder();
		while(!q.isEmpty()){
			char c = q.poll();
			if(Character.isDigit(c)){
				cnt = 10 * cnt + c - '0';
			} else if(c == '['){
				String repeat = helper(q);
				for(int i = 0; i < cnt; i++){
					sb.append(repeat);
				}
				cnt = 0;
			} else if(c == ']'){
				break;
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public String decodeString(String s) {
        char[] arr = s.toCharArray();
    	Stack<String> stack = new Stack<>();
    	int count = 0;
    	for(char c : arr) {
    		if(c == '[') {
    			stack.push(String.valueOf(count));
    			count = 0;
    		} else if(c == ']'){
    			String sub = getRepeat(stack);
    			stack.push(sub);
    		} else if(Character.isDigit(c)){
    			count = count * 10 + c - '0';
    		} else {
    			stack.push(String.valueOf(c));
    		}
    	}
    	return getRepeat(stack);
    }
    private String getRepeat(Stack<String> stack) {
    	StringBuilder repeat = new StringBuilder();
    	int count = 1;
    	while(!stack.isEmpty()) {
    		String cur = stack.pop();
    		if(isNumber(cur)) {
    			count = Integer.parseInt(cur);
    			break;
    		} else {
    			repeat.insert(0, cur);
    		}
    	}
    	StringBuilder ans = new StringBuilder();
    	for(int i = 0; i < count; i++) {
		    ans.append(repeat.toString());
	    }
    	return ans.toString();
    }
    private boolean isNumber(String s) {
    	if(s.isEmpty()) {
    		return false;
    	}
    	char[] arr = s.toCharArray();
    	for(char c : arr) {
    		if(!Character.isDigit(c)) {
    			return false;
    		}
    	}
    	return true;
    }
}
