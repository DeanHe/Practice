package TwoPointers.Window;
/*
given an array with all positive numbers, find all subarrays which sum to target
 */
public class PositiveSubarrayWithSumEqualsTarget {
	void findSubarrays(int[] arr, int target){
		int windowSum = 0;
		int len = arr.length;
		int start = 0, end = 0;
		while(end < len){
			windowSum += arr[end];
			while(windowSum >= target){
				if(windowSum == target){
					System.out.println(start + "->" + end);
				}
				windowSum -= arr[start];
				start++;
			}
			end++;
		}
	}
}
