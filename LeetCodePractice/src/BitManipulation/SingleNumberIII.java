package BitManipulation;

import java.util.*;

/*Given 2*n + 2 numbers, every numbers occurs twice except two, find them.

Example
Example 1:
	Input:  [1,2,2,3,4,4,5,3]
	Output:  [1,5]

Example 2:
	Input: [1,1,2,3,4,4]
	Output:  [2,3]
	
Challenge
O(n) time, O(1) extra space.*/
public class SingleNumberIII {
	public List<Integer> singleNumberIII(int[] A) {
        int xor = 0;
        for (int i = 0; i < A.length; i++) {
            xor ^= A[i];
        }
        
        int lastBit = xor - (xor & (xor - 1));
        int group0 = 0, group1 = 0;
        for (int i = 0; i < A.length; i++) {
            if ((lastBit & A[i]) == 0) {
                group0 ^= A[i];
            } else {
                group1 ^= A[i];
            }
        }
        
        ArrayList<Integer> result = new ArrayList<Integer>();
        result.add(group0);
        result.add(group1);
        return result;
    }
}
