package binarySearch;
/*
You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once.

Return the single element that appears only once.

Your solution must run in O(log n) time and O(1) space.



Example 1:

Input: nums = [1,1,2,3,3,4,4,8,8]
Output: 2
Example 2:

Input: nums = [3,3,7,7,10,11,11]
Output: 10


Constraints:

1 <= nums.length <= 10^5
0 <= nums[i] <= 10^5

analysis:
we can base on mid index is even or odd, and relations of nums[mid - 1], nums[mid], nums[mid + 1]
to decide whether single element is on left or right
 */
public class SingleElementInaSortedArray {
    public int singleNonDuplicate(int[] nums) {
        int len = nums.length, s = 0, e = len - 1;
        while(s < e){
            int mid = s + (e - s) / 2;
            if((mid % 2 == 0 && nums[mid] == nums[mid + 1])
                    || (mid % 2 == 1 && nums[mid] == nums[mid - 1])){
                s = mid + 1;
            } else {
                e = mid;
            }
        }
        return nums[s];
    }
}
