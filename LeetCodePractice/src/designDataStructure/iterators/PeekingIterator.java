package designDataStructure.iterators;

import java.util.Iterator;

/*
Design an iterator that supports the peek operation on an existing iterator in addition to the hasNext and the next operations.

Implement the PeekingIterator class:

PeekingIterator(Iterator<int> nums) Initializes the object with the given integer iterator iterator.
int next() Returns the next element in the array and moves the pointer to the next element.
boolean hasNext() Returns true if there are still elements in the array.
int peek() Returns the next element in the array without moving the pointer.
Note: Each language may have a different implementation of the constructor and Iterator, but they all support the int next() and boolean hasNext() functions.



Example 1:

Input
["PeekingIterator", "next", "peek", "next", "next", "hasNext"]
[[[1, 2, 3]], [], [], [], [], []]
Output
[null, 1, 2, 2, 3, false]

Explanation
PeekingIterator peekingIterator = new PeekingIterator([1, 2, 3]); // [1,2,3]
peekingIterator.next();    // return 1, the pointer moves to the next element [1,2,3].
peekingIterator.peek();    // return 2, the pointer does not move [1,2,3].
peekingIterator.next();    // return 2, the pointer moves to the next element [1,2,3]
peekingIterator.next();    // return 3, the pointer moves to the next element [1,2,3]
peekingIterator.hasNext(); // return False


Constraints:

1 <= nums.length <= 1000
1 <= nums[i] <= 1000
All the calls to next and peek are valid.
At most 1000 calls will be made to next, hasNext, and peek.

hint:
1 Think of "looking ahead". You want to cache the next element.
2 Is one variable sufficient? Why or why not?
3 Test your design with call order of peek() before next() vs next() before peek().
4 For a clean implementation, check out Google's guava library source code.
 */
public class PeekingIterator implements Iterator<Integer> {
    Iterator<Integer> it;
    Integer cur;

    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        it = iterator;
        if (it.hasNext()) {
            cur = it.next();
        } else {
            cur = null;
        }
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        return cur;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        Integer res = cur;
        if (it.hasNext()) {
            cur = it.next();
        } else {
            cur = null;
        }
        return res;
    }

    @Override
    public boolean hasNext() {
        return cur != null;
    }
}
