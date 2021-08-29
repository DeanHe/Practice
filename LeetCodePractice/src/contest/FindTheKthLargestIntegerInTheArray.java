package contest;
/*
You are given an array of strings nums and an integer k. Each string in nums represents an integer without leading zeros.

Return the string that represents the kth largest integer in nums.

Note: Duplicate numbers should be counted distinctly. For example, if nums is ["1","2","2"], "2" is the first largest integer, "2" is the second-largest integer, and "1" is the third-largest integer.



Example 1:

Input: nums = ["3","6","7","10"], k = 4
Output: "3"
Explanation:
The numbers in nums sorted in non-decreasing order are ["3","6","7","10"].
The 4th largest integer in nums is "3".
Example 2:

Input: nums = ["2","21","12","1"], k = 3
Output: "2"
Explanation:
The numbers in nums sorted in non-decreasing order are ["1","2","12","21"].
The 3rd largest integer in nums is "2".
Example 3:

Input: nums = ["0","0"], k = 2
Output: "0"
Explanation:
The numbers in nums sorted in non-decreasing order are ["0","0"].
The 2nd largest integer in nums is "0".


Constraints:

1 <= k <= nums.length <= 10^4
1 <= nums[i].length <= 100
nums[i] consists of only digits.
nums[i] will not have any leading zeros.
 */
public class FindTheKthLargestIntegerInTheArray {
    public String kthLargestNumber(String[] nums, int k) {
        int len = nums.length;
        return helper(nums, 0, len - 1, len - k);
    }

    private String helper(String[] nums, int s, int e, int k){
        if(s == e){
           return nums[s];
        }
        int idx = partition(nums, s, e);
        if(idx == k){
            return nums[idx];
        } else if(idx < k){
            return helper(nums, idx + 1, e, k);
        } else {
            return helper(nums, s, idx - 1, k);
        }
    }

    private int partition(String[] nums, int s, int e){
        String pivot = nums[e];
        int i = s;
        for(int j = s; j < e; j++){
            if(compare(nums[j], pivot) <= 0){
                swap(nums, i, j);
                i++;
            }
        }
        swap(nums, i, e);
        return i;
    }

    private void swap(String[] nums, int i, int j){
        String temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private int compare(String a, String b){
        if(a.length() != b.length()){
            return a.length() - b.length();
        }
        return a.compareTo(b);
    }
}
