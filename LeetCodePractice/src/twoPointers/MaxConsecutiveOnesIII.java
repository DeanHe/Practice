package twoPointers;
/*
Given an array A of 0s and 1s, we may change up to K values from 0 to 1.

Return the length of the longest (contiguous) subarray that contains only 1s. 

 

Example 1:

Input: A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
Output: 6
Explanation: 
[1,1,1,0,0,1,1,1,1,1,1]
Bolded numbers were flipped from 0 to 1.  The longest subarray is underlined.
Example 2:

Input: A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
Output: 10
Explanation: 
[0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
Bolded numbers were flipped from 0 to 1.  The longest subarray is underlined.
 

Note:

1 <= A.length <= 20000
0 <= K <= A.length
A[i] is 0 or 1
*/
public class MaxConsecutiveOnesIII {
    public int longestOnes(int[] A, int K) {
        int res  = 0, start = 0, zeroCnt = 0;
        int len = A.length;
        for(int end = 0; end < len; end++){
        	if(A[end] == 0){
        		zeroCnt++;
        	}
        	while(zeroCnt > K){
        		if(A[start] == 0){
        			zeroCnt--;
        		}
        		start++;
        	}
        	res = Math.max(res, end - start + 1);
        }
        return res;
    }
}
