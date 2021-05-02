package contest;

import java.util.*;

/*Given an integer array arr and an integer difference, return the length of the longest subsequence in arr which is an arithmetic sequence such that the difference between adjacent elements in the subsequence equals difference.

Example 1:

Input: arr = [1,2,3,4], difference = 1
Output: 4
Explanation: The longest arithmetic subsequence is [1,2,3,4].
Example 2:

Input: arr = [1,3,5,7], difference = 1
Output: 1
Explanation: The longest arithmetic subsequence is any single element.
Example 3:

Input: arr = [1,5,7,8,5,3,4,2,1], difference = -2
Output: 4
Explanation: The longest arithmetic subsequence is [7,5,3,1].

math dp
*/
public class LongestArithmeticSubsequenceOfGivenDifference {
	public int longestSubsequence(int[] arr, int difference) {
        Map<Integer, Integer> dp = new HashMap<>();
        int res = 0;
        for(int i = 0; i < arr.length; i++){
        	int cnt = 1;
        	if(dp.containsKey(arr[i] - difference)){
        		cnt += dp.get(arr[i] - difference);
        	}
        	res = Math.max(res, cnt);
        	dp.put(arr[i], cnt);
        }
        return res;
    }
}
