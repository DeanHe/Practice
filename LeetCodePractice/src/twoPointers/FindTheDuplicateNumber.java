package twoPointers;
/*
    Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

        Example 1:

        Input: [1,3,4,2,2]
        Output: 2
        Example 2:

        Input: [3,1,3,4,2]
        Output: 3
        Note:

        You must not modify the array (assume the array is read only).
        You must use only constant, O(1) extra space.
        Your runtime complexity should be less than O(n2).
        There is only one duplicate number in the array, but it could be repeated more than once.

        similar to LinkedListCycle
        Because each number in nums is between 1 and n, it will necessarily point to an index that exists.
        Therefore, the list can be traversed infinitely, which implies that there is a cycle.
        Additionally, because 0 cannot appear as a value in nums, nums[0] cannot be part of the cycle.
        Therefore, traversing the array in this manner from nums[0] is equivalent to traversing a cyclic linked list.
*/
public class FindTheDuplicateNumber {
    public int findDuplicate(int[] nums) {
        int slow = nums[0], fast = nums[nums[0]];
        while(slow != fast){
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        fast = nums[0];
        while(nums[slow] != fast){
            fast = nums[fast];
            slow = nums[slow];
        }
        return fast;
    }
}
