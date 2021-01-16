package Stack;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/*
Implement an iterator to flatten a 2d vector.

Example
Example 1:

Input:[[1,2],[3],[4,5,6]]
Output:[1,2,3,4,5,6]
Example 2:

Input:[[7,9],[5]]
Output:[7,9,5]
*/
public class Vector2D implements Iterator<Integer> {
	Stack<Integer> stack;
	Stack<List<Integer>> listStack;

	public Vector2D(List<List<Integer>> vec2d) {
		// Initialize your data structure here
		stack = new Stack<>();
		listStack = new Stack<>();
		pushNestedListToStack(vec2d);
	}

	private void pushNestedListToStack(List<List<Integer>> vec2d) {
		Stack<List<Integer>> temp = new Stack<>();
		for (List<Integer> ls : vec2d) {
			temp.push(ls);
		}
		while (!temp.isEmpty()) {
			listStack.push(temp.pop());
		}
	}

	private void pushListToStack(List<Integer> vec) {
		Stack<Integer> temp = new Stack<>();
		for (int i : vec) {
			temp.push(i);
		}
		while (!temp.isEmpty()) {
			stack.push(temp.pop());
		}
	}

	@Override
	public boolean hasNext() {
		while (stack.isEmpty() && !listStack.isEmpty()) {
			List<Integer> vec = listStack.pop();
			pushListToStack(vec);
		}
		return !stack.isEmpty();
	}

	@Override
	public Integer next() {
		if (!hasNext()) {
			return null;
		}
		return stack.pop();
	}

	@Override
	public void remove() {
	}
}
/**
 * Your Vector2D object will be instantiated and called as such: Vector2D i =
 * new Vector2D(vec2d); while (i.hasNext()) v[f()] = i.next();
 */