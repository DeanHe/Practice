package Stack;

import java.util.*;
//https://www.geeksforgeeks.org/sort-stack-using-temporary-stack/
public class SortaStackUsingaTemporaryStack {
	//make it ascending order
		public static Stack<Integer> sort(Stack<Integer> s){
			Stack<Integer> r = new Stack<>();
			while(!s.isEmpty()) {
				int cur = s.pop();
				while(!r.isEmpty() && r.peek() < cur) {
					s.push(r.pop());
				}
				r.push(cur);
			}
			return r;
		}
}
