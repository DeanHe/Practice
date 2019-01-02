package Stack;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class FlattenNestedListIterator {
	private class NestedIterator implements Iterator<Integer> {
		Stack<NestedInteger> stack;
		public NestedIterator(List<NestedInteger> nestedList) {
			stack = new Stack<>();
			// Initialize your data structure here
			for(int i = 0; i < nestedList.size(); i++){
				stack.push(nestedList.get(i));
			}
		}

		// @return {boolean} true if the iteration has more element or false
		@Override
		public boolean hasNext() {
			while(!stack.isEmpty()){
				NestedInteger cur = stack.peek();
				if(cur.isInteger()){
					return true;
				}
				stack.pop();
				for(int i = cur.getList().size() - 1; i >= 0; i--){
					stack.push(cur.getList().get(i));
				}
			}
			return false;
		}

		// @return {int} the next element in the iteration
		@Override
		public Integer next() {
			return stack.pop().getInteger();
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
