package designDataStructure;

import java.util.*;

/*
#155

Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.


Example 1:

Input
["MinStack","push","push","push","getMin","pop","top","getMin"]
[[],[-2],[0],[-3],[],[],[],[]]

Output
[null,null,null,null,-3,null,0,-2]

Explanation
MinStack maxStack = new MinStack();
maxStack.push(-2);
maxStack.push(0);
maxStack.push(-3);
maxStack.getMin(); // return -3
maxStack.pop();
maxStack.top();    // return 0
maxStack.getMin(); // return -2


Constraints:

Methods pop, top and getMin operations will always be called on non-empty stacks.

analysis:
min stack is decreasing order
 */

public class MinStack {
	Stack<Integer> realStack = new Stack<>();
	Stack<Integer> minStack = new Stack<>();
	
    public void push(int x) {
	    if(minStack.isEmpty() || minStack.peek() >= x){
	    	minStack.push(x);
	    }
        realStack.push(x);
    }

   public void pop() {
		if (minStack.peek().equals(realStack.peek())) {
			minStack.pop();
		}
		realStack.pop();
	}

    public int top() {
        return realStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
