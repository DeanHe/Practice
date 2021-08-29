package designDataStructure;

import java.util.Stack;

/*
Design a max stack that supports push, pop, top, peekMax and popMax.
push(x) -- Push element x onto stack.
pop() -- Remove the element on top of the stack and return it.
top() -- Get the element on the top.
peekMax() -- Retrieve the maximum element in the stack.
popMax() -- Retrieve the maximum element in the stack, and remove it. If you find more than one maximum elements, only remove the top-most one.

Example 1:
MaxStack stack = new MaxStack();
stack.push(5);
stack.push(1);
stack.push(5);
stack.top(); -> 5
stack.popMax(); -> 5
stack.top(); -> 1
stack.peekMax(); -> 5
stack.pop(); -> 1
stack.top(); -> 5

Note:
-1e7 <= x <= 1e7
Number of operations won't exceed 10000.
The last four operations won't be called when stack is empty.
 */
public class MaxStack {
    Stack<Integer> realStack = new Stack<>();
    Stack<Integer> maxStack = new Stack<>();

    public void push(int x) {
        if(maxStack.isEmpty() || maxStack.peek() >= x){
            maxStack.push(x);
        }
        realStack.push(x);
    }

    public int pop() {
        if (maxStack.peek().equals(realStack.peek())) {
            maxStack.pop();
        }
        return realStack.pop();
    }

    public int top() {
        return realStack.peek();
    }

    public int peekMax() {
        return maxStack.peek();
    }

    public int popMax() {
        int most = peekMax();
        Stack<Integer> buffer = new Stack<>();
        while(top() != most){
            buffer.push(pop());
        }
        pop();
        while(!buffer.isEmpty()){
            push(buffer.pop());
        }
        return most;
    }
}

