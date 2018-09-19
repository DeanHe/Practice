
public class SuperWashingMachines {
	/**
     * @param machines: an integer array representing the number of dresses in each washing machine from left to right on the line
     * @return: the minimum number of moves to make all the washing machines have the same number of dresses
     */
    public int findMinMoves(int[] machines) {
        // Write your code here
        //https://leetcode.com/problems/super-washing-machines/discuss/99185/Super-Short-and-Easy-Java-O(n)-Solution
        int total = 0;
        int len = machines.length;
        for(int m : machines){
            total += m;
        }
        if(total % len != 0){
            return -1;
        }
        int max = 0, cnt = 0;
        int avg = total / len;
        for(int load : machines){
            cnt += load - avg;
            max = Math.max(Math.max(Math.abs(cnt), max), load - avg);
        }
        return max;
    }
}
