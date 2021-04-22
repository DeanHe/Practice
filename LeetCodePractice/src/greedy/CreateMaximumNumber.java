package greedy;

/*Given two arrays of length m and n with digits 0-9 representing two numbers. Create the maximum number of length k <= m + n from digits of the two. The relative order of the digits from the same array must be preserved. Return an array of the k digits.

Note: You should try to optimize your time and space complexity.

Example 1:

Input:
nums1 = [3, 4, 6, 5]
nums2 = [9, 1, 2, 5, 8, 3]
k = 5
Output:
[9, 8, 6, 5, 3]
Example 2:

Input:
nums1 = [6, 7]
nums2 = [6, 0, 4]
k = 5
Output:
[6, 7, 6, 0, 4]
Example 3:

Input:
nums1 = [3, 9]
nums2 = [8, 9]
k = 3
Output:
[9, 8, 9]*/
public class CreateMaximumNumber {
	public int[] maxNumber(int[] nums1, int[] nums2, int k) {
		int len1 = nums1.length;
		int len2 = nums2.length;
		int[] res = new int[k];
		for(int i = Math.max(0,  k - len2); i <= k && i <= len1; i++){
			int[] candidate = merge(maxArray(nums1, i), maxArray(nums2, k - i), k);
			if(greater(candidate, 0, res, 0)){
				res = candidate;
			}
		}
		return res;
	}
	
	private int[] merge(int[] nums1, int[] nums2, int k){
		int[] res = new int[k];
		for(int i = 0, j = 0, index = 0; index < k; index++){
			if(greater(nums1, i, nums2, j)){
				res[index] = nums1[i];
				i++;
			} else {
				res[index] = nums2[j];
				j++;
			}
		}
		return res;
	}
	
	private boolean greater(int[] nums1, int i, int[] nums2, int j){
		int len1 = nums1.length;
		int len2 = nums2.length;
		while(i < len1 && j < len2 && nums1[i] == nums2[j]){
			i++;
			j++;
		}
		if(j == len2){
			return true;
		}
		if(i < len1 && nums1[i] > nums2[j]){
			return true;
		}
		return false;
	}
	
	private int[] maxArray(int[] nums, int k){
		int len = nums.length;
		int[] res = new int[k];
		for(int i = 0, index = 0; i < len; i++){
			while(index > 0 && len - i + index > k && res[index - 1] < nums[i]){
				index--;
			}
			if(index < k){
				res[index] = nums[i];
				index++;
			}
		}
		return res;
	}
}
