package dp.TimeSequenceType;
/*Given a sequence of integers, find the longest increasing subsequence (LIS).

You code should return the length of the LIS.

Example
Example 1:
	Input:  [5,4,1,2,3]
	Output:  3
	
	Explanation:
	LIS is [1,2,3]


Example 2:
	Input: [4,2,4,5,3,7]
	Output:  4
	
	Explanation: 
	LIS is [2,4,5,7]

Challenge
Time complexity O(n^2) or O(nlogn)

Clarification
What's the definition of longest increasing subsequence?

The longest increasing subsequence problem is to find a subsequence of a given sequence in which the subsequence's elements are in sorted order,
lowest to highest, and in which the subsequence is as long as possible. This subsequence is not necessarily contiguous, or unique.

analysis:
use binary search for TC O(nlogn)
array lis saves the current lis ended with nums[i]

*/
public class LongestIncreasingSubsequence {
	/**
     * @param nums: The integer array
     * @return: The length of LIS (longest increasing subsequence)
     */
    public int longestIncreasingSubsequence(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int len = nums.length;
        // dp[i] means the LIS ends in nums[i]
        int[] dp = new int[len];
        int res = 1;
        for(int i = 0; i < len; i++){
            dp[i] = 1;
            for(int j = 0; j < i; j++){
                if(nums[j] < nums[i]){
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    /**
     * @param nums: The integer array
     * @return: The length of LIS (longest increasing subsequence)
     */
    public int longestIncreasingSubsequenceII(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int len = nums.length;
        int[] lis = new int[len];
        int res = 0;
        for(int i = 0; i < len; i++){
            int idx = binarySearch(lis, 0, res - 1, nums[i]);
            lis[idx] = nums[i];
            //System.out.println(Arrays.toString(lis));
            if(idx == res){
                res++;
            }
        }
        return res;
    }

    private int binarySearch(int[] arr, int s, int e, int target) {
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }
        return s;
    }
}
