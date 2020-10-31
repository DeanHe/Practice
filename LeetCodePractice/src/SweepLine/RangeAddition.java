package SweepLine;

import java.util.TreeMap;

/*Assume you have an array of length n initialized with all 0's and are given k update operations.

Each operation is represented as a triplet: [startIndex, endIndex, inc] which increments each element of subarray A[startIndex ... endIndex] (startIndex and endIndex inclusive) with inc.

Return the modified array after all k operations were executed.

Example
Given:
length = 5,
updates = 
[
[1,  3,  2],
[2,  4,  3],
[0,  2, -2]
]
return [-2, 0, 3, 5, 3]

Explanation:
Initial state:
[ 0, 0, 0, 0, 0 ]
After applying operation [1, 3, 2]:
[ 0, 2, 2, 2, 0 ]
After applying operation [2, 4, 3]:
[ 0, 2, 5, 5, 3 ]
After applying operation [0, 2, -2]:
[-2, 0, 3, 5, 3 ]
// time complexity O(N)
*/
public class RangeAddition {
	/**
     * @param length: the length of the array
     * @param updates: update operations
     * @return: the modified array after all k operations were executed
     */
    public int[] getModifiedArray(int length, int[][] updates) {
        // Write your code here
		TreeMap<Integer, Integer> axis = new TreeMap<>();
		int[] res = new int[length];
		for(int[] update : updates){
			axis.put(update[0], axis.getOrDefault(update[0], 0) + update[2]);
			axis.put(update[1] + 1, axis.getOrDefault(update[1] + 1, 0) - update[2]);
		}
		int preSum = 0;
		for(int i = 0; i < length; i++){
			preSum += axis.getOrDefault(i, 0);
			res[i] = preSum;
		}
		return res;
    }
}
