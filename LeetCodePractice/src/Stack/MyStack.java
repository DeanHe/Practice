package Stack;
// https://leetcode.com/problems/implement-stack-using-queues/description/
/*
Example£º
push 1
queue£º1 size 1
push 2
queue£º2 1 size 2
queue£º1 2
push 3
queue£º3 1 2  size 3
queue£º2 3 1
queue£º1 2 3
 */
import java.util.*;

public class MyStack {
	Queue<Integer> queue = new LinkedList<Integer>();
    // Push element x onto stack.
    public void push(int x) {
        queue.add(x);
        int size = queue.size();
        for(int i = 0; i < size -1; i++){
            queue.add(queue.remove());
        }
    }

    // Removes the element on top of the stack.
    public void pop() {
        queue.remove();
    }

    // Get the top element.
    public int top() {
        return queue.peek();
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return queue.isEmpty();
    }
}
