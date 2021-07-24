package twoPointers;
/*
Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number sorted in non-decreasing order.



Example 1:

Input: nums = [-4,-1,0,3,10]
Output: [0,1,9,16,100]
Explanation: After squaring, the array becomes [16,1,0,9,100].
After sorting, it becomes [0,1,9,16,100].
Example 2:

Input: nums = [-7,-3,2,3,11]
Output: [4,9,9,49,121]


Constraints:

1 <= nums.length <= 104
-104 <= nums[i] <= 104
nums is sorted in non-decreasing order.


Follow up: Squaring each element and sorting the new array is very trivial, could you find an O(n) solution using a different approach?

analysis:
two pointer as the original array is sorted, each time the max element must be one of the two ends
TC O(N)
 */
public class SquaresOfaSortedArray {
    public int[] sortedSquares(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];
        int s = 0, e = len - 1;
        for(int i = len - 1; i >= 0; i--){
            if(Math.abs(nums[s]) > Math.abs(nums[e])){
                res[i] = nums[s] * nums[s];
                s++;
            } else {
                res[i] = nums[e] * nums[e];
                e--;
            }
        }
        return res;
    }
}

