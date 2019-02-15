package Window;

import java.util.HashMap;
import java.util.Map;

/*Given an array A of positive integers, call a (contiguous, not necessarily distinct) subarray of A good if the number of different integers in that subarray is exactly K.

(For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.)

Return the number of good subarrays of A.

Example 1:
Input: A = [1,2,1,2,3], K = 2
Output: 7
Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].

Example 2:
Input: A = [1,2,1,3,4], K = 3
Output: 3
Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].*/
public class SubarraysWithKDifferentIntegers {
	class Window {
		Map<Integer, Integer> count;
		int nonzero;
		Window(){
			count = new HashMap<>();
		}
		void add(int x){
			count.put(x, count.getOrDefault(x, 0) + 1);
			if(count.get(x) == 1){
				nonzero++;
			}
		}
		void remove(int x){
			count.put(x, count.get(x) - 1);
			if(count.get(x) == 0){
				nonzero--;
			}
		}
		int different(){
			return nonzero;
		}
	}
	public int subarraysWithKDistinct(int[] A, int K) {
		Window w1 = new Window();
        Window w2 = new Window();
        int res = 0, left1 = 0, left2 = 0;
        for(int right = 0; right < A.length; right++){
        	w1.add(A[right]);
            w2.add(A[right]);
            while(w1.different() > K){
        		w1.remove(A[left1++]);
        	}
        	while(w2.different() >= K){
        		w2.remove(A[left2++]);
        	}
            res += left2 - left1;
        }
        return res;
    }
}
