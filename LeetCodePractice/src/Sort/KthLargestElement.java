package Sort;

/*find K-th largest element in an array.

Example
Example 1:

Input:
n = 1, nums = [1,3,4,2]
Output:
4
Example 2:

Input:
n = 3, nums = [9,3,2,4,8]
Output:
4
Challenge
O(n) time, O(1) extra memory.

Notice
You can swap elements in the array

O(N) best case runtime or O(N ^ 2) worst case + O(1) memeory
The smart approach is to use the selection algorithm (based on the partition method - the same one as used in quicksort).
*/
public class KthLargestElement {
    /**
     * @param k:    An integer
     * @param nums: An array
     * @return: the Kth largest element
     */
    public int kthLargestElement(int k, int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (k <= 0) {
            return 0;
        }
        return getKth(nums, 0, nums.length - 1, nums.length - k);
    }

    private int getKth(int[] nums, int start, int end, int k) {
        if (start == end) {
            return nums[start];
        }
        int index = partition(nums, start, end);
        if (index == k) {
            return nums[index];
        } else if (index < k) {
            return getKth(nums, index + 1, end, k);
        } else {
            return getKth(nums, start, index - 1, k);
        }
    }

    private int partition(int[] nums, int start, int end) {
        int pivot = nums[end];
        int i = start;
        for (int j = start; j < end; j++) {
            if (nums[j] <= pivot) {
                swap(nums, i++, j);
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
