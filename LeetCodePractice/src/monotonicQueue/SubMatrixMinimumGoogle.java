package monotonicQueue;
/*
Given a matrix M and window size W, collect all minimums of all sub-matrix of size WxW, e.g.
M = [
1 3 2
4 6 5
7 8 9
]
W = 2
All minimums of all sub-matrix of 2x2 will essentially be another matrix

OUTPUT = [
1 2
4 5
]

analysis:
sliding window minimum on all rows, then on all columns
TC O(N^2)

*/

import java.util.ArrayDeque;
import java.util.Deque;

public class SubMatrixMinimumGoogle {
    public int[][] minimumSubMatrix(int[][] nums, int w) {
        int rows = nums.length, cols = nums[0].length;
        int[][] res = new int[rows - w + 1][cols - w + 1];
        int[][] temp = new int[rows][cols - w + 1];
        Deque<Integer> deque = new ArrayDeque<>();
        for(int r = 0; r < rows; r++){
            deque.clear();
            for(int c = 0; c < cols; c++){
                while(!deque.isEmpty() && c - deque.peekFirst() >= w){
                    deque.pollFirst();
                }
                while(!deque.isEmpty() && nums[r][c] <= nums[r][deque.peekLast()]){
                    deque.pollLast();
                }
                deque.offerLast(c);
                int i = c - w + 1;
                if(i >= 0){
                    temp[r][i] = nums[r][deque.peekFirst()];
                }
            }
        }
        for(int c = 0; c < cols - w + 1; c++){
            deque.clear();
            for(int r = 0; r < rows; r++){
                while(!deque.isEmpty() && r - deque.peekFirst() >= w){
                    deque.pollFirst();
                }
                while(!deque.isEmpty() && temp[r][c] <= temp[deque.peekLast()][c]){
                    deque.pollLast();
                }
                deque.offerLast(r);
                int i = r - w + 1;
                if(i >= 0){
                    res[i][c] = temp[deque.peekFirst()][c];
                }
            }
        }
        return res;
    }
}
