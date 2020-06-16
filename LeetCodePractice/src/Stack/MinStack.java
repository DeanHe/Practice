package Stack;

import java.util.*;
// min stack is decreasing order
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
