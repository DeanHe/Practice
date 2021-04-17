package window;

import java.util.LinkedList;

/*
Given an array of integers nums and an integer k. A subarray is called nice if there are k odd numbers on it.

Return the number of nice sub-arrays.

Example 1:

Input: nums = [1,1,2,1,1], k = 3
Output: 2
Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].
Example 2:

Input: nums = [2,4,6], k = 1
Output: 0
Explanation: There is no odd numbers in the array.
Example 3:

Input: nums = [2,2,2,1,2,2,1,2,2,2], k = 2
Output: 16
 

Constraints:

1 <= nums.length <= 50000
1 <= nums[i] <= 10^5
1 <= k <= nums.length

analysis:
two pointer, track for each odd # of nums[:i] ended with
*/
public class CountNumberOfNiceSubarrays {
	public int numberOfSubarrays(int[] nums, int k) {
		int len = nums.length, oddCnt = 0, res = 0;
        int[] map = new int[len + 1]; // map[i] means for preSum odd i, # of preSubArray
        map[0] = 1;
        for(int i = 0; i < len; i++){
        	oddCnt += nums[i] % 2 == 1 ? 1 : 0;
        	map[oddCnt]++;
        	if(oddCnt >= k){
        		res += map[oddCnt - k];
        	}
        }
        return res;
    }
	
	public int numberOfSubarraysII(int[] nums, int k) {
		int len = nums.length, res = 0;
        LinkedList<Integer> ls = new LinkedList<>();
        ls.add(-1);
        for(int i = 0; i < len; i++){
        	if(nums[i] % 2 == 1){
        		ls.add(i);
        	}
        	if(ls.size() > k + 1){
        		ls.pop();
        	}
        	if(ls.size() == k + 1){
        		res += ls.get(1)  - ls.get(0);
        	}
        }
        return res;
    }
}
