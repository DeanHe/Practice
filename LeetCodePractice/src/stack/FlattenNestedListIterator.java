package stack;
/*
Given a nested list of integers, implement an iterator to flatten it.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Example 1:

Input: [[1,1],2,[1,1]]
Output: [1,1,2,1,1]
Explanation: By calling next repeatedly until hasNext returns false, 
             the order of elements returned by next should be: [1,1,2,1,1].
Example 2:

Input: [1,[4,[6]]]
Output: [1,4,6]
Explanation: By calling next repeatedly until hasNext returns false, 
             the order of elements returned by next should be: [1,4,6].
*/
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class FlattenNestedListIterator {
	/**
	 * // This is the interface that allows for creating nested lists.
	 * // You should not implement it, or speculate about its implementation
	 * public interface NestedInteger {
	 *
	 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
	 *     public boolean isInteger();
	 *
	 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
	 *     // Return null if this NestedInteger holds a nested list
	 *     public Integer getInteger();
	 *
	 *     // @return the nested list that this NestedInteger holds, if it holds a nested listd
	 *     // Return null if this NestedInteger holds a single integer
	 *     public List<NestedInteger> getList();
	 * }
	 */
	private class NestedIterator implements Iterator<Integer> {
		Stack<NestedInteger> stack;
		public NestedIterator(List<NestedInteger> nestedList) {
			stack = new Stack<>();
			pushAll(nestedList);
		}

		// @return {boolean} true if the iteration has more element or false
		@Override
		public boolean hasNext() {
			while(!stack.isEmpty() && !stack.peek().isInteger()){
				List<NestedInteger> ls = stack.pop().getList();
				pushAll(ls);
			}
			return !stack.isEmpty();
		}

		// @return {int} the next element in the iteration
		@Override
		public Integer next() {
			return stack.pop().getInteger();
		}

		private void pushAll(List<NestedInteger> nestedList){
			int len = nestedList.size();
			for(int i = len - 1; i >= 0; i--){
				stack.push(nestedList.get(i));
			}
		}
		
		public void remove() {
			
		}
		/**
		 * Your NestedIterator object will be instantiated and called as such:
		 * NestedIterator i = new NestedIterator(nestedList);
		 * while (i.hasNext()) v.add(i.next());
		 */
		
	}
}
