package array;
/*
Given two integer arrays of same size, nums and indexes, reorder elements in nums according to given indexes array.

Example 1:
Input: nums = [24, 56, 74, -23, 87, 91], indexes = [1, 2, 3, 0, 4, 5]
Output: [-23, 24, 56, 74, 87, 91]

Example 2:
Input: nums = [10, 11, 12], indexes = [1, 0, 2]
Output: [11, 10, 12]

Expected O(n) time and O(1) space solution.

tag: Google
 */
public class ReorderArrayAccordingToTheGivenIndexes {
    public void reorder(int[] nums, int[] indexes){
        int i = 0, len = nums.length;
        while (i < len){
            int idx = indexes[i];
            if(idx != i){
                swap(nums, i, idx);
                swap(indexes, i, idx);
            } else {
                i++;
            }
        }
        return;
    }

    private void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
