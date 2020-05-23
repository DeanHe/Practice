package Stack.Parenthese;

import java.util.*;

public class ValidParentheses {
	public boolean isValid(String s) {
		char[] temp = s.toCharArray();
		Map<Character, Character> map = new HashMap<Character, Character>();
		map.put('}', '{');
		map.put(')', '(');
		map.put(']', '[');
		Stack<Character> stack = new Stack<>();
		for (char c : temp) {
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
