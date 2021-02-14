package DesignDataStructure;

import java.util.Stack;

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
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin(); // return -3
minStack.pop();
minStack.top();    // return 0
minStack.getMin(); // return -2


Constraints:

Methods pop, top and getMin operations will always be called on non-empty stacks.

analysis:
min stack is decreasing order
 */

public class MinStackGeneric<E extends Comparable<E>>  {
	Stack<E> realStack = new Stack<>();
	Stack<E> minStack = new Stack<>();
	
    public void push(E x) {
	    if(minStack.isEmpty() || minStack.peek().compareTo(x) >= 0){
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

    public E top() {
        return realStack.peek();
    }

    public E getMin() {
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
