package greedy;
/*
Given an array of non-negative integers, you are initially positioned at the first index of the array.
Each element in the array represents your maximum jump length at that position.
Your goal is to reach the last index in the minimum number of jumps.

Example:
Input: [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2.
Jump 1 step from index 0 to 1, then 3 steps to the last index.

Note:
You can assume that you can always reach the last index.
*/
public class JumpGameII {
	public int jump(int[] A) {
        if(A == null || A.length == 0){
            return 0;
        }
        int len = A.length, step = 0, maxReach = 0, end = 0;
        for(int i = 0; i < len - 1; i++){
            maxReach = Math.max(maxReach, i + A[i]);
            if(i == end){
                step++;
                end = maxReach;
            }
        }
        return step;
    }
}
