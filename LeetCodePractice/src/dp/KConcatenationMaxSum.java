package dp;
/*Given an integer array arr and an integer k, modify the array by repeating it k times.

For example, if arr = [1, 2] and k = 3 then the modified array will be [1, 2, 1, 2, 1, 2].

Return the maximum sub-array sum in the modified array. Note that the length of the sub-array can be 0 and its sum in that case is 0.

As the answer can be very large, return the answer modulo 10^9 + 7.


Example 1:

Input: arr = [1,2], k = 3
Output: 9
Example 2:

Input: arr = [1,-2,1], k = 5
Output: 2
Example 3:

Input: arr = [-1,-2], k = 7
Output: 0
 
Constraints:

1 <= arr.length <= 10^5
1 <= k <= 10^5
-10^4 <= arr[i] <= 10^4

Analysis:
case 1: sum < 0
	part of array;  part of two array concatenated
case 2: sum > 0
	part of two array concatenated + (k - 2) * sum 
*/
public class KConcatenationMaxSum {
	int mod = (int) Math.pow(10, 9) + 7;
	public int kConcatenationMaxSum(int[] arr, int k) {
		long maxSub = maximumSubarray(arr);
        if(k == 1){
        	return (int)maxSub;
        }
        long maxPreSub = maximumPreFixSubarray(arr);
		long maxSufSub = maximumSuffixSubarray(arr);
        long sum = 0;
        for(int n : arr){
        	sum += n;
        }
        if(sum > 0){
        	return (int)Math.max(maxSub % mod, (maxPreSub % mod + maxSufSub % mod + (k - 2) * sum % mod) % mod);
        } else {
        	return (int)Math.max(maxSub % mod, (maxPreSub % mod + maxSufSub % mod) % mod);
        }
    }
	private long maximumSubarray(int[] arr){
		long sum = 0;
		long res = Integer.MIN_VALUE;
		for(int n : arr){
			sum = sum > 0 ? (sum + n) % mod : n;
			res = Math.max(res, sum);
		}
		return res;
	}
	private long maximumPreFixSubarray(int[] arr){
		long sum = 0;
		long res = Integer.MIN_VALUE;
		for(int i = 0; i < arr.length; i++){
			sum = (sum + arr[i]) % mod;
			res = Math.max(res, sum);
		}
		return res < 0 ? 0 : res;
	}
	private long maximumSuffixSubarray(int[] arr){
		long sum = 0;
		long res = Integer.MIN_VALUE;
		for(int i = arr.length - 1; i >= 0; i--){
			sum = (sum + arr[i]) % mod;
			res = Math.max(res, sum);
		}
		return res;
	}
}
