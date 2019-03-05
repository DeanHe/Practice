package Window;
// given an array with all positive numbers, find all subarrays which sum to target
public class PositiveSubarrayWithSumEqualsTarget {
	void findSubarrays(int[] arr, int target){
		int windowSum = 0;
		int len = arr.length;
		for(int i = 0, j = 0; i <= j && j < len; j++){
			windowSum += arr[j];
			while(windowSum >= target){
				if(windowSum == target){
					System.out.println(i + "->" + j);
				}
				windowSum -= arr[i];
				i++;
			}
		}
	}
}
