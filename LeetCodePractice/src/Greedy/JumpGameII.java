package Greedy;

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
