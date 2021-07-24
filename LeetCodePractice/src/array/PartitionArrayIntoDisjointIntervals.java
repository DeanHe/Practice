package array;
/*
Given an array nums, partition it into two (contiguous) subarrays left and right so that:

Every element in left is less than or equal to every element in right.
left and right are non-empty.
left has the smallest possible size.
Return the length of left after such a partitioning.  It is guaranteed that such a partitioning exists.



Example 1:

Input: nums = [5,0,3,8,6]
Output: 3
Explanation: left = [5,0,3], right = [8,6]
Example 2:

Input: nums = [1,1,1,0,6,12]
Output: 4
Explanation: left = [1,1,1,0], right = [6,12]


Note:

2 <= nums.length <= 30000
0 <= nums[i] <= 10^6
It is guaranteed there is at least one way to partition nums as described.

analysis:
suppose the original left subarray is from 0 to partitionIdx, the max value of that is localMax.
If it is a valid partition, every value from partitionIdx + 1 to end should be >= localMax.
But if we find a value in the right part, a[i], is smaller than localMax, which means the partition is not correct
and we have to incorporate a[i] to form the left subarray.
So the partitionIdx is set to be i, and we have to recalculate the max value of the new left subarray.(recorded in max)
 */
public class PartitionArrayIntoDisjointIntervals {
    public int partitionDisjoint(int[] nums) {
        int leftMost = nums[0], most = nums[0], partition = 0, len = nums.length;
        for(int i = 1; i < len; i++){
            if(leftMost > nums[i]){
                leftMost = most;
                partition = i;
            } else {
                most = Math.max(most, nums[i]);
            }
        }
        return partition + 1;
    }
}

