package math;

import java.util.*;

/*Given a permutation which may contain repeated numbers, find its index in all the permutations of these numbers, which are ordered in lexicographical order. The index begins at 1.

Example
Example 1:

Input :[1,4,2,2]
Output:3
Example 2:

Input :[1,6,5,3,1]
Output:24*/
public class PermutationIndexII {
	/**
     * @param A: An array of integers
     * @return: A long integer
     */
    public long permutationIndexII(int[] A) {
        // write your code here
    	long res = 0, factorial = 1, dup = 1;
    	int len = A.length;
    	Map<Integer, Integer> freqMap = new HashMap<>();
    	for(int i = len - 1; i >= 0; i--){
    		freqMap.put(A[i], freqMap.getOrDefault(A[i], 0) + 1);
    		dup *= freqMap.get(A[i]);
    		int count = 0;
    		for(int j = i + 1; j < len; j++){
    			if(A[i] > A[j]){
    				count++;
    			}
    		}
    		res += count * factorial / dup;
    		factorial *= len - i;
    	}
    	res++;
    	return res;
    }
}
