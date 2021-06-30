package segmentTree;

/*
Description
Given an integer arr nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

The update(i, val) function modifies nums by updating the element at index i to val.
1.The arr is only modifiable by the update function.
2.You may assume the number of calls to update and sumRange function is distributed evenly.

Example
Given nums = [1, 3, 5]

sumRange(0, 2) -> 9
update(1, 2)
sumRange(0, 2) -> 8
*/
public class RangeSumQueryMutableZKW {

    class NumArray {
        int[] arr;
        int len;

        public NumArray(int[] nums) {
            len = nums.length;
            arr = new int[2 * len];
            for (int i = 0; i < len; i++) {
                update(i, nums[i]);
            }
        }

        private void update(int index, int val) {
            int diff = val - arr[index + len];
            for (int i = index + len; i > 0; i /= 2) {
                arr[i] += diff;
            }
        }

        public int sumRange(int start, int end) {
            int res = 0;
            for (int i = start + len, j = end + len; i <= j; i /= 2, j /= 2) {
                if (i % 2 == 1) {
                    res += arr[i++];
                }
                if (j % 2 == 0) {
                    res += arr[j--];
                }
            }
            return res;
        }
    }

}
