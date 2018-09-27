package BinarySearch;
// https://leetcode.com/problems/search-insert-position/description/
public class SearchInsertPosition {
	public int searchInsert(int[] nums, int target) {
        int len = nums.length;
        int start = 0;
        int end = len - 1;
        while(start <= end){
            int mid = start + (end - start) / 2;
            if(nums[mid] > target){
                end = mid - 1;
            } else if(nums[mid] == target){
                end = mid;
            } else {
                //nums[mid] < target
                start = mid + 1;
            }
        }
        return start;
    }
}
