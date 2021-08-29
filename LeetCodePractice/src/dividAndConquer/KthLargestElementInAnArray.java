package dividAndConquer;

/*
#215

Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order,
not the kth distinct element.

Example 1:

Input: [3,2,1,5,6,4] and k = 2
Output: 5
Example 2:

Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4
Note:
You may assume k is always valid, 1 ≤ k ≤ array's length.

analysis:
quick select
 based on selection algorithm (based on the partition method - the same one as used in quicksort).
O(N) best case / O(N^2) worst case running time + O(1) memory
 */
public class KthLargestElementInAnArray {
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        return getKth(nums, 0, nums.length - 1, nums.length - k);
    }

    private int getKth(int[] nums, int start, int end, int k) {
        if (start == end) {
            return nums[start];
        }
        int idx = partition(nums, start, end);
        if (idx == k) {
            return nums[idx];
        } else if (idx < k) {
            return getKth(nums, idx + 1, end, k);
        } else {
            return getKth(nums, start, idx - 1, k);
        }
    }

    private int partition(int[] nums, int start, int end) {
        int pivot = nums[end];
        int i = start;
        for (int j = start; j < end; j++) {
            if (nums[j] <= pivot) {
                swap(nums, i, j);
                i++;
            }
        }
        swap(nums, i, end);
        return i;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
