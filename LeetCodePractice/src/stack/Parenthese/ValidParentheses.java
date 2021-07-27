package stack.Parenthese;

import java.util.*;

public class ValidParentheses {
	public boolean isValid(String s) {
		Map<Character, Character> map = new HashMap<>();
		map.put('}', '{');
		map.put(')', '(');
		map.put(']', '[');
		Stack<Character> stack = new Stack<>();
		for (char c : s.toCharArray()) {
			if (map.containsKey(c)) {
				if(stack.isEmpty()){
					return false;
				} else {
					char top = stack.pop();
					if(top != map.get(c)){
						return false;
					}
				}
			} else {
				stack.push(c);
			}
		}
		return stack.isEmpty();
	}
}
