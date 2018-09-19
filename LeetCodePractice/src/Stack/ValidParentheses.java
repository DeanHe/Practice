package Stack;

import java.util.*;

public class ValidParentheses {
	public boolean isValid(String s) {
		char[] temp = s.toCharArray();
		Map<Character, Character> map = new HashMap<Character, Character>();
		map.put('{', '}');
		map.put('(', ')');
		map.put('[', ']');
		Stack<Character> stack = new Stack<>();
		for (char c : temp) {
			if (map.keySet().contains(c)) {
				stack.push(c);
			} else if (map.values().contains(c)) {
				if (!stack.isEmpty() && c == map.get(stack.peek())) {
					stack.pop();
				} else {
					return false;
				}

			}
		}
		return stack.isEmpty();
	}
}
