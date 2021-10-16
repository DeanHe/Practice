package bitManipulation;

import java.util.*;

/*
Given 2*n + 2 numbers, every numbers occurs twice except two, find them.

Example
Example 1:
	Input:  [1,2,2,3,4,4,5,3]
	Output:  [1,5]

Example 2:
	Input: [1,1,2,3,4,4]
	Output:  [2,3]
	
Challenge
O(n) time, O(1) extra space.

analysis:
In the first pass, we XOR all elements in the array, and get the XOR of the two numbers we need to find.
Note that since the two numbers are distinct, so there must be a set bit (that is, the bit with value '1') in the XOR result. Find
out an arbitrary set bit (for example, the rightmost set bit).

In the second pass, we divide all numbers into two groups, one with the above bit set, another with the above bit unset.
Two different numbers we need to find must fall into these two distinct groups. XOR numbers in each group, we can find a number in either group.
*/
public class SingleNumberIII {
	public int[] singleNumberIII(int[] nums) {
        int xor = 0, group0 = 0, group1 = 0;
        for (int n : nums) {
            xor ^= n;
        }
        
        int lastBit = xor & -xor; // last bit with 1 in xor
        for (int n : nums) {
            if ((lastBit & n) == 0) {
                group0 ^= n;
            } else {
                group1 ^= n;
            }
        }

        return new int[]{group0, group1};
    }
}
