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
        int step = 0;
        int maxReach = 0;
        int lastMaxReach = 0;
        for(int i = 0; i <= maxReach && i < A.length; i++){
            if(i > lastMaxReach){
                step++;
                lastMaxReach = maxReach;
            }
            maxReach = Math.max(maxReach, A[i] + i);
        }
        if(maxReach < A.length - 1){
            return 0;
        }
        return step;
    }
}
