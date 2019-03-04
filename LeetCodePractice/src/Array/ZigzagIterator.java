package Array;
import java.util.*;
import java.util.Iterator;
/*Given two 1d vectors, implement an iterator to return their elements alternately.

Example
Example1

Input: v1 = [1, 2] and v2 = [3, 4, 5, 6]
Output: [1, 3, 2, 4, 5, 6]
Explanation: 
By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1, 3, 2, 4, 5, 6].
Example2

Input: v1 = [1, 1, 1, 1] and v2 = [3, 4, 5, 6]
Output: [1, 3, 1, 4, 1, 5, 1, 6]*/
public class ZigzagIterator {
	Iterator<Integer> it1;
	Iterator<Integer> it2;
	int turns;
	
	public ZigzagIterator(List<Integer> v1, List<Integer> v2){
		this.it1 = v1.iterator();
		this.it2 = v2.iterator();
		turns = 0;
	}
	/*
     * @return: An integer
     */
	public int next(){
		if(!hasNext()){
			return 0;
		}
		turns++;
		if((turns % 2 == 1 && it1.hasNext()) || (!it2.hasNext())){
			return it1.next();
		} else if((turns % 2 == 0 && it2.hasNext()) || (!it1.hasNext())){
			return it2.next();
		}
		return 0;
	}
	/*
     * @return: True if has next
     */
	public boolean hasNext(){
		return it1.hasNext() || it2.hasNext();
	}
	/**
	 * Your ZigzagIterator object will be instantiated and called as such:
	 * ZigzagIterator solution = new ZigzagIterator(v1, v2);
	 * while (solution.hasNext()) result.add(solution.next());
	 * Output result
	 */
}
