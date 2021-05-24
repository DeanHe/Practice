package designDataStructure.iterators;

import java.util.LinkedList;
import java.util.Queue;

/*
Write an iterator that iterates through a run-length encoded sequence.

The iterator is initialized by RLEIterator(int[] encoding), where encoding is a run-length encoding of some sequence.  More specifically, for all even i, encoding[i] tells us the number of times that the non-negative integer value encoding[i+1] is repeated in the sequence.

The iterator supports one function: next(int n), which exhausts the next n elements (n >= 1) and returns the last element exhausted in this way.  If there is no element left to exhaust, next returns -1 instead.

For example, we start with encoding = [3,8,0,9,2,5], which is a run-length encoding of the sequence [8,8,8,5,5].  This is because the sequence can be read as "three eights, zero nines, two fives".



Example 1:

Input: ["RLEIterator","next","next","next","next"], [[[3,8,0,9,2,5]],[2],[1],[1],[2]]
Output: [null,8,8,5,-1]
Explanation:
RLEIterator is initialized with RLEIterator([3,8,0,9,2,5]).
This maps to the sequence [8,8,8,5,5].
RLEIterator.next is then called 4 times:

.next(2) exhausts 2 terms of the sequence, returning 8.  The remaining sequence is now [8, 5, 5].

.next(1) exhausts 1 term of the sequence, returning 8.  The remaining sequence is now [5, 5].

.next(1) exhausts 1 term of the sequence, returning 5.  The remaining sequence is now [5].

.next(2) exhausts 2 terms, returning -1.  This is because the first term exhausted was 5,
but the second term did not exist.  Since the last term exhausted does not exist, we return -1.

Note:

0 <= encoding.length <= 1000
encoding.length is an even integer.
0 <= encoding[i] <= 109
There are at most 1000 calls to RLEIterator.next(int n) per test case.
Each call to RLEIterator.next(int n) will have 1 <= n <= 10^9.
 */
public class RLEIterator {
    int i = 0;
    int[] encoding;
    public RLEIterator(int[] encoding) {
        this.encoding = encoding;
    }

    public int next(int n) {
        while(n > 0){
            if(i >= encoding.length){
                return -1;
            }
            if(n <= encoding[i]){
                encoding[i] -= n;
                return encoding[i + 1];
            } else {
                n -= encoding[i];
                encoding[i] = 0;
                i += 2;
            }
        }
        return -1;
    }
}

