package DividAndConquer;

import java.util.Arrays;

/*
    Given an array nums, we call (i, j) an important reverse pair if i < j and nums[i] > 2*nums[j].

        You need to return the number of important reverse pairs in the given array.

        Example1:

        Input: [1,3,2,3,1]
        Output: 2
        Example2:

        Input: [2,4,3,5,1]
        Output: 3
        Note:
        The length of the given array will not exceed 50,000.
        All the numbers in the input array are in the range of 32-bit integer.

        analysis:
        merge sort O(NlgN)
*/
public class ReversePairs {
    public int reversePairs(int[] nums) {
        int[] temp = new int[nums.length];
        return mergeSort(nums, 0, nums.length - 1, temp);
    }

    private int mergeSort(int[] nums, int start, int end, int[] temp){
        if (start >= end) {
            return 0;
        }
        int mid = start + (end - start) / 2;
        int res = mergeSort(nums, start, mid, temp) + mergeSort(nums, mid + 1, end, temp);
        for(int i = start, j = mid + 1; i <= mid; i++){
            while(j <= end && nums[i] > 2 * nums[j]){
                j++;
            }
            res += j - mid - 1;

        }
        merge(nums, start, mid, end, temp);
        return res;
    }

    private void merge(int[] nums, int start, int mid, int end, int[] temp){
        int l = start, r = mid + 1, i = start;
        while(l <= mid && r <= end){
            if(nums[l] < nums[r]){
                temp[i++] = nums[l++];
            } else {
                temp[i++] = nums[r++];
            }
        }
        while(l <= mid){
            temp[i++] = nums[l++];
        }
        while(r <= end){
            temp[i++] = nums[r++];
        }
        for(i = start; i <= end; i++){
            nums[i] = temp[i];
        }
    }
}
