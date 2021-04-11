package designDataStructure.iterators;
import java.util.*;
import java.util.Iterator;

/*
Given a string iterator, implement a pair iterator to combine the adjacent string with same value.

Example1

Input: v1 = [foo, foo, bar, foo]
Output: {foo, 2}, {bar, 1}, {foo, 1}
*/
public class PairIterator {

	GoogleIteratorPair cur;
	GoogleIteratorPair post;
	Iterator<String> it;
	public PairIterator(Iterator<String> iterator){
		it = iterator;
		if(it.hasNext()){
			cur = new GoogleIteratorPair(it.next(), 1);
		} else {
			cur = null;
		}
		while(it.hasNext()){
			String nextStr = it.next();
			if(nextStr.equals(cur.val)){
				cur.cnt++;
			} else {
				post = new GoogleIteratorPair(nextStr, 1);
				break;
			}
		}
	}
	/*
     * @return: An integer
     */
	public GoogleIteratorPair next(){
		GoogleIteratorPair res = cur;
		if(post != null){
			cur = post;
		} else {
			cur = null;
		}
		post = null;
		while(it.hasNext()){
			String nextStr = it.next();
			if(nextStr.equals(cur.val)){
				cur.cnt++;
			} else {
				post = new GoogleIteratorPair(nextStr, 1);
				break;
			}
		}
		return res;
	}
	/*
     * @return: True if has next
     */
	public boolean hasNext(){
		return cur != null;
	}
	/**
	 * Your PairIterator object will be instantiated and called as such:
	 * PairIterator solution = new PairIterator(v1);
	 * while (solution.hasNext()) result.add(solution.next());
	 * Output result
	 */

}


