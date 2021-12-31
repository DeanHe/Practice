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

hint:
1 One thing's for sure, we will only flip a zero if it extends an existing window of 1s. Otherwise, there's no point in doing it, right? Think Sliding Window!
2 Since we know this problem can be solved using the sliding window construct, we might as well focus in that direction for hints. Basically,
in a given window, we can never have > K zeros, right?
3 We don't have a fixed size window in this case. The window size can grow and shrink depending upon the number of zeros we have (we don't actually have to flip the zeros here!).
4 The way to shrink or expand a window would be based on the number of zeros that can still be flipped and so on.

analysis:
sliding window, check for each end index what is the maximum window size of {start, end}
TC O(N)
*/
public class MaxConsecutiveOnesIII {
    public int longestOnes(int[] nums, int K) {
        int res  = 0, start = 0, zeroCnt = 0;
        int len = nums.length;
        for(int end = 0; end < len; end++){
        	if(nums[end] == 0){
        		zeroCnt++;
        	}
        	while(zeroCnt > K){
        		if(nums[start] == 0){
        			zeroCnt--;
        		}
        		start++;
        	}
        	res = Math.max(res, end - start + 1);
        }
        return res;
    }
}
