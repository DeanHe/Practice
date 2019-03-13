package Math;

import java.util.*;

/*Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies:
Si % Sj = 0 or Sj % Si = 0.
If there are multiple solutions, return any subset is fine.

Example 1:
Input: [1,2,3]
Output: [1,2] (of course, [1,3] will also be ok)

Example 2:
Input: [1,2,4,8]
Output: [1,2,4,8]

1.Sort all array elements in increasing order. The purpose of sorting is to make sure that all divisors of an element appear before it.
2.Create an array divCount[] of same size as input array. divCount[i] stores size of divisible subset ending with arr[i] (In sorted array). The minimum value of divCount[i] would be 1.
3.Traverse all array elements. For every element, find a divisor arr[j] with largest value of divCount[j] and store the value of divCount[i] as divCount[j] + 1.
*/
public class LargestDivisibleSubset {
	public List<Integer> largestDivisibleSubset(int[] nums) {
        
    }
}
