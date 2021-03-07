package Window;

import java.util.*;

/*Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

Example
Example 1:

MovingAverage m = new MovingAverage(3);
m.next(1) = 1 // return 1.00000
m.next(10) = (1 + 10) / 2 // return 5.50000
m.next(3) = (1 + 10 + 3) / 3 // return 4.66667
m.next(5) = (10 + 3 + 5) / 3 // return 6.00000
*/
public class MovingAverageFromDataStream {
	Queue<Integer> queue;
	double sum;
	int size;
	/*
	 * @param size: An integer
	 */public MovingAverageFromDataStream(int size) {
		 queue = new LinkedList<>();
		 sum = 0;
		 this.size = size;
	    }

	/*
	 * @param val: An integer
	 * 
	 * @return:
	 */
	public double next(int val) {
		// write your code here
		sum += val;
		queue.offer(val);
		if(queue.size() > size){
			sum -= queue.poll();
		}
		return sum / queue.size();
	}
}
