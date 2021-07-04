package heap;

import java.util.*;

/*
Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

For example,
[2,3,4], the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:

void addNum(int num) - Add a integer number from the data stream to the data structure.
double findMedian() - Return the median of all elements so far.
 

Example:

addNum(1)
addNum(2)
findMedian() -> 1.5
addNum(3) 
findMedian() -> 2
 

Follow up:

If all integer numbers from the stream are between 0 and 100, how would you optimize it?

We can maintain an integer array of length 100 to store the count of each number along with a total count. Then, we can iterate over the array to find the middle value to get our median.

Time and space complexity would be O(100) = O(1).

If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?

In this case, we need an integer array of length 100 and a hashmap for these numbers that are not in [0,100].

analysis:
first add to maxHeap then move from maxHeap to minHeap, then check
TC O(log n) add, O(1) find
*/
public class FindMedianFromDataStream {
	PriorityQueue<Integer> maxHeap; // lower half
	PriorityQueue<Integer> minHeap; // higher half

	public FindMedianFromDataStream() {
		maxHeap = new PriorityQueue<>(1, (a, b) -> b - a);
		minHeap = new PriorityQueue<>();
	}

	// Adds a number into the data structure.
	public void addNum(int num) {
		// make sure maxHeap.size() >= minHeap.size()
		maxHeap.offer(num);
		minHeap.offer(maxHeap.poll());
		if (minHeap.size() > maxHeap.size()) {
			maxHeap.offer(minHeap.poll());
		}
	}

	// Returns the median of current data stream
	public double findMedian() {
		if (maxHeap.size() == minHeap.size()) {
			return (maxHeap.peek() + minHeap.peek()) / 2.0;
		} else {
			return maxHeap.peek();
		}
	}
}

//Your MedianFinder object will be instantiated and called as such:
//MedianFinder mf = new MedianFinder();
//mf.addNum(1);
//mf.findMedian();
