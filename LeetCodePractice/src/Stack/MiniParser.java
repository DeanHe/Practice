package Stack;

/*Given a nested list of integers represented as a string, implement a parser to deserialize it.
Each element is either an integer, or a list -- whose elements may also be integers or other lists.
Note: You may assume that the string is well-formed:

string is non-empty.
string does not contain white spaces.
string contains only digits 0-9, [, - ,, ].
Example 1:

Given s = "324",

You should return a NestedInteger object which contains a single integer 324.
Example 2:

Given s = "[123,[456,[789]]]",

Return a NestedInteger object containing a nested list with 2 elements:

1. An integer containing value 123.
2. A nested list containing two elements:
    i.  An integer containing value 456.
    ii. A nested list with one element:
         a. An integer containing value 789.
         
If encounters '[', push current NestedInteger to stack and start a new one.
If encounters ']', end current NestedInteger and pop a NestedInteger from stack to continue.
If encounters ',', append a new number to cur NestedInteger, if this comma is not right after a brackets.
Update index l and r, where l shall point to the start of a integer substring, while r shall points to the end+1 of substring.
*/
import java.util.*;

public class MiniParser {
	public NestedInteger deserialize(String s) {
		if (s == null || s.length() == 0) {
			return null;
		}
		if (s.charAt(0) != '[') {
			return new NestedInteger(Integer.valueOf(s));
		}
		int len = s.length();
		int l = 0; // l shall point to the start of a number substring;
					// r shall point to the end+1 of a number substring;
		NestedInteger cur = null;
		Stack<NestedInteger> stack = new Stack<>();
		for (int i = 0; i < len; i++) {
			char c = s.charAt(i);
			if (c == '[') {
				if (cur != null) {
					stack.push(cur);
				}
				cur = new NestedInteger();
				l = i + 1;
			} else if (c == ']') {
				String num = s.substring(l, i);
				if (!num.isEmpty()) {
					cur.add(new NestedInteger(Integer.valueOf(num)));
				}
				if (!stack.isEmpty()) {
					NestedInteger top = stack.pop();
					top.add(cur);
					cur = top;
				}
				l = i + 1;
			} else if (c == ',') {
				if (s.charAt(i - 1) != ']') {
					String num = s.substring(l, i);
					cur.add(new NestedInteger(Integer.valueOf(num)));
				}
				l = i + 1;
			}
		}
		return cur;
	}

	// This is the interface that allows for creating nested lists.
	// You should not implement it, or speculate about its implementation
	class NestedInteger {
		// Constructor initializes an empty nested list.
		public NestedInteger() {

		};

		// Constructor initializes a single integer.
		public NestedInteger(int value) {

		};

		// @return true if this NestedInteger holds a single integer, rather
		// than a nested list.
		public boolean isInteger() {
			return false;
		};

		// @return the single integer that this NestedInteger holds, if it holds
		// a single integer
		// Return null if this NestedInteger holds a nested list
		public Integer getInteger() {
			;
			return 0;
		}

		// Set this NestedInteger to hold a single integer.
		public void setInteger(int value) {

		};

		// Set this NestedInteger to hold a nested list and adds a nested
		// integer to it.
		public void add(NestedInteger ni) {

		};

		// @return the nested list that this NestedInteger holds, if it holds a
		// nested list
		// Return null if this NestedInteger holds a single integer
		public List<NestedInteger> getList() {
			return new ArrayList<NestedInteger>();
		}
	}
}
