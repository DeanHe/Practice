package PrefixSum;

import java.util.Arrays;

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
[-2, 0, 3, 5, 3 ]*/
// time complexity O(N)
public class RangeAddition {
	/**
     * @param length: the length of the array
     * @param updates: update operations
     * @return: the modified array after all k operations were executed
     */
    public int[] getModifiedArray(int length, int[][] updates) {
        // Write your code here
    	int[] prefixSum = new int[length];
    	int[] modify = new int[length];
    	for(int i = 0; i < updates.length; i++){
    		int start = updates[i][0];
    		int end = updates[i][1];
    		int change = updates[i][2];
    		modify[start] += change;
    		if(end + 1 < length){
    			modify[end + 1] -= change;
    		}
    	}
    	prefixSum[0] = modify[0];
    	for(int i = 1; i < length; i++){
    		prefixSum[i] = prefixSum[i - 1] + modify[i];
    	}
    	return prefixSum;
    }
}
