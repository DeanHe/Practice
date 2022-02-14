package stack.parenthese;

import java.util.*;

/*
Given a string s of '(' , ')','[' , ']','{' , '}' and lowercase English characters.

Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.

Formally, a parentheses string is valid if and only if:

It is the empty string, contains only lowercase characters, or
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.

Example 1:
Input: s = "abc({d}ef])hig"
Output: "abc({d}ef)hig"
*/
public class MinimumRemoveToMakeValidParenthesesFB {
	public String minRemoveToMakeValid(String s) {
		Map<Character, Stack<Integer>> stacks = new HashMap<>();
		stacks.put('(', new Stack<>());
		stacks.put('{', new Stack<>());
		stacks.put('[', new Stack<>());
		Map<Character, Character> flip = new HashMap<>();
		flip.put(')', '(');
		flip.put('}', '{');
		flip.put(']', '[');
		char[] arr = s.toCharArray();
		for(int i = 0; i < arr.length; i++){
			char c = arr[i];
			if(String.valueOf("({[").contains(String.valueOf(c))){
				stacks.get(c).push(i);
			} else if(String.valueOf(")]}").contains(String.valueOf(c))){
				Stack<Integer> st = stacks.get(flip.get(c));
				if(!st.isEmpty()){
					st.pop();
				} else {
					arr[i] = '*'; // mark redundant ')'
				}
			}
		}
		for(Character c : stacks.keySet()){
			Stack<Integer> st = stacks.get(c);
			while(!st.isEmpty()){
				arr[st.pop()] = '*'; // mark redundant '('
			}
		}

		StringBuilder sb = new StringBuilder();
		for(char c : arr){
			if(c != '*'){
				sb.append(c);
			}
		}
		return sb.toString();
    }
}
