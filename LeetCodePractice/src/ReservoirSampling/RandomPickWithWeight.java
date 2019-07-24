package ReservoirSampling;

import java.util.Arrays;
import java.util.Random;

/*Given an array w of positive integers, where w[i] describes the weight of index i, write a function pickIndex which randomly picks an index in proportion to its weight.

Note:

1 <= w.length <= 10000
1 <= w[i] <= 10^5
pickIndex will be called at most 10000 times.
Example 1:

Input: 
["Solution","pickIndex"]
[[[1]],[]]
Output: [null,0]
Example 2:

Input: 
["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
[[[1,3]],[],[],[],[],[]]
Output: [null,0,1,1,1,0]
Explanation of Input Syntax:

The input is two lists: the subroutines called and their arguments. Solution's constructor has one argument, the array w. pickIndex has no arguments. Arguments are always wrapped with a list, even if there aren't any.
*/
public class RandomPickWithWeight {

	int[] preSum;
	Random rand;
	int len;

	public RandomPickWithWeight(int[] w) {
		rand = new Random();
		len = w.length;
		preSum = new int[len];
		preSum[0] = w[0];
		for (int i = 1; i < len; i++) {
			preSum[i] = preSum[i - 1] + w[i];
		}
	}

	public int pickIndex() {
		int idx = rand.nextInt(preSum[len - 1]) + 1;
		int start = 0, end = len - 1;
		while (start < end) {
			int mid = start + (end - start) / 2;
			if (preSum[mid] == idx) {
				return mid;
			} else if (preSum[mid] < idx) {
				start = mid + 1;
			} else {
				end = mid;
			}
		}
		return start;
	}
}
/**
 * Your Solution object will be instantiated and called as such: Solution obj =
 * new Solution(w); int param_1 = obj.pickIndex();
 */
