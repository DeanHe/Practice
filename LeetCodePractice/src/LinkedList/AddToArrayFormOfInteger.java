package LinkedList;

import java.util.*;

/*For a non-negative integer X, the array-form of X is an array of its digits in left to right order.  For example, if X = 1231, then the array form is [1,2,3,1].

Given the array-form A of a non-negative integer X, return the array-form of the integer X+K.

 

Example 1:

Input: A = [1,2,0,0], K = 34
Output: [1,2,3,4]
Explanation: 1200 + 34 = 1234
Example 2:

Input: A = [2,7,4], K = 181
Output: [4,5,5]
Explanation: 274 + 181 = 455
Example 3:

Input: A = [2,1,5], K = 806
Output: [1,0,2,1]
Explanation: 215 + 806 = 1021
Example 4:

Input: A = [9,9,9,9,9,9,9,9,9,9], K = 1
Output: [1,0,0,0,0,0,0,0,0,0,0]
Explanation: 9999999999 + 1 = 10000000000*/
public class AddToArrayFormOfInteger {
	public List<Integer> addToArrayForm(int[] A, int K) {
        int len = A.length;
        int carry = K, digit = 0;
        List<Integer> res = new ArrayList<>();
        for(int i = len - 1; i >= 0 || carry > 0;){
            digit = carry;
            if(i >= 0){
                digit += A[i--];
            }
            carry = digit / 10;
            digit = digit % 10;
            res.add(digit);
        }
        Collections.reverse(res);
        return res;
    }
}
