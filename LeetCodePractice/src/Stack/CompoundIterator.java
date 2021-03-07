package Stack;

import java.util.Iterator;
import java.util.Stack;

/*

#Apple

Write a CompoundIterator. CompoundIterator takes an array of Iterators in its constructor;
CompoundIterator itself should implement the java.util.Iterator interface,
such that it iterates over the elements of the passed-in Iterators as if they were a single Iterator.
If any of the Iterators in the passed-in array is null or empty,
it should simply be skipped (note that this is not the same thing as saying that if the Iterators themselves return null elements from next(),
those elements should be skipped — they should not be). It is expected that CompoundIterator will exhaust the passed-in Iterators.
You do not need to provide a working implementation of the remove() method.

 */
public class CompoundIterator implements Iterator {
    Stack<Iterator> stack;
    public CompoundIterator(Iterator[] iterators){
        // TODO: Implement me
        stack = new Stack<>();
        for(int i = iterators.length - 1; i >= 0; i--){
            stack.push(iterators[i]);
        }
    }

    public boolean hasNext(){
        // TODO: Implement me
        while(!stack.isEmpty() && (stack.peek() == null || stack.peek().hasNext())){
            stack.pop();
        }
        return !stack.isEmpty();
    }

    public Object next(){
        // TODO: Implement me
        return stack.peek().next();
    }

    public void remove() throws UnsupportedOperationException {
        throw new UnsupportedOperationException(getClass().getName() + "does not support the remove() operation.");
    }
}
