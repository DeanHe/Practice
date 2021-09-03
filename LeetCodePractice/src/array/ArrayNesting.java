package array;
/*
You are given an integer array nums of length n where nums is a permutation of the numbers in the range [0, n - 1].

You should build a set s[k] = {nums[k], nums[nums[k]], nums[nums[nums[k]]], ... } subjected to the following rule:

The first element in s[k] starts with the selection of the element nums[k] of index = k.
The next element in s[k] should be nums[nums[k]], and then nums[nums[nums[k]]], and so on.
We stop adding right before a duplicate element occurs in s[k].
Return the longest length of a set s[k].



Example 1:

Input: nums = [5,4,0,3,1,6,2]
Output: 4
Explanation:
nums[0] = 5, nums[1] = 4, nums[2] = 0, nums[3] = 3, nums[4] = 1, nums[5] = 6, nums[6] = 2.
One of the longest sets s[k]:
s[0] = {nums[0], nums[5], nums[6], nums[2]} = {5, 6, 2, 0}
Example 2:

Input: nums = [0,1,2]
Output: 1


Constraints:

1 <= nums.length <= 10^5
0 <= nums[i] < nums.length
All the values of nums are unique.

analysis:
mark the element visited as -1, greedy start from each element for a circle

TC: O(N)
SC: O(1)
 */
public class ArrayNesting {
    public int arrayNesting(int[] nums) {
        int res = 0, len = nums.length;
        for(int i = 0; i < len; i++){
            int circle = 0;
            for(int j = i; nums[j] >= 0; circle++){
                int next = nums[j];
                nums[j] = -1;
                j = next;
            }
            res = Math.max(res, circle);
        }
        return res;
    }
}

