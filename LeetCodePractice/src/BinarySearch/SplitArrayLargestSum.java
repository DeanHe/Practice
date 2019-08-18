package BinarySearch;
/*Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays. Write an algorithm to minimize the largest sum among these m subarrays.

Note:
If n is the length of array, assume the following constraints are satisfied:

1 ≤ n ≤ 1000
1 ≤ m ≤ min(50, n)
Examples:

Input:
nums = [7,2,5,10,8]
m = 2

Output:
18

Explanation:
There are four ways to split nums into two subarrays.
The best way is to split it into [7,2,5] and [10,8],
where the largest sum among the two subarrays is only 18.*/

/*The answer is between maximum value of input array numbers and sum of those numbers.
Use binary search to approach the correct answer. We have l = max number of array; r = sum of all numbers in the array;Every time we do mid = (l + r) / 2;
Use greedy to narrow down left and right boundaries in binary search.
3.1 Cut the array from left.
3.2 Try our best to make sure that the sum of numbers between each two cuts (inclusive) is large enough but still less than mid.
3.3 We'll end up with two results: either we can divide the array into more than m subarrays or we cannot.
If we can, it means that the mid value we pick is too small because we've already tried our best to make sure each part holds as many non-negative numbers as we can but we still have numbers left. So, it is impossible to cut the array into m parts and make sure each parts is no larger than mid. We should increase m. This leads to l = mid + 1;
If we can't, it is either we successfully divide the array into m parts and the sum of each part is less than mid, or we used up all numbers before we reach m. Both of them mean that we should lower mid because we need to find the minimum one. This leads to r = mid - 1;
*/
public class SplitArrayLargestSum {
	public int splitArray(int[] nums, int m) {
        long start = 0, end = 0;
        for(int n : nums){
        	start = Math.max(start, n);
        	end += n;
        }
        if(m == 1){
        	return (int)end;
        }
        while(start <= end){
        	long largestSubarraySum = start + (end - start) / 2;
        	if(canSplit(nums, m, largestSubarraySum)){
        		start = largestSubarraySum + 1;
        	} else {
        		end = largestSubarraySum - 1;
        	}
        }
        return (int)start;
    }
	//nums can have m greedy subarray with sum less than max
	private boolean canSplit(int[] nums, int m, long largestSubarraySum){
		int count = 0, sum = 0;
		for(int n : nums){
			if(sum + n > largestSubarraySum){
				count++;
				sum = 0;
			}
            sum += n;
			if(count == m){
				//means max is too small;
				return true;
			}
		}
		return false;
	}
}
