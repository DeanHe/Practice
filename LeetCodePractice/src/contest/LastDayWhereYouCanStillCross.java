package contest;

import java.util.LinkedList;
import java.util.Queue;

/*
There is a 1-based binary matrix where 0 represents land and 1 represents water. You are given integers row and col representing the number of rows and columns in the matrix, respectively.
Initially on day 0, the entire matrix is land. However, each day a new cell becomes flooded with water. You are given a 1-based 2D array cells, where cells[i] = [ri, ci] represents that on the ith day, the cell on the rith row and cith column (1-based coordinates) will be covered with water (i.e., changed to 1).
You want to find the last day that it is possible to walk from the top to the bottom by only walking on land cells. You can start from any cell in the top row and end at any cell in the bottom row. You can only travel in the four cardinal directions (left, right, up, and down).
Return the last day where it is possible to walk from the top to the bottom by only walking on land cells.


Example 1:
Input: row = 2, col = 2, cells = [[1,1],[2,1],[1,2],[2,2]]
Output: 2
Explanation: The above image depicts how the matrix changes each day starting from day 0.
The last day where it is possible to cross from top to bottom is on day 2.

Example 2:
Input: row = 2, col = 2, cells = [[1,1],[1,2],[2,1],[2,2]]
Output: 1
Explanation: The above image depicts how the matrix changes each day starting from day 0.
The last day where it is possible to cross from top to bottom is on day 1.
Example 3:


Input: row = 3, col = 3, cells = [[1,2],[2,1],[3,3],[2,2],[1,1],[1,3],[2,3],[3,2],[3,1]]
Output: 3
Explanation: The above image depicts how the matrix changes each day starting from day 0.
The last day where it is possible to cross from top to bottom is on day 3.


Constraints:

2 <= row, col <= 2 * 10^4
4 <= row * col <= 2 * 10^4
cells.length == row * col
1 <= ri <= row
1 <= ci <= col
All the values of cells are unique.

hint:
What graph algorithm allows us to find whether a path exists?
Can we use binary search to help us solve the problem?

analysis:
TC O(N log N)
 */
public class LastDayWhereYouCanStillCross {
    int[] dirs = {0, 1, 0, -1, 0};
    public int latestDayToCross(int row, int col, int[][] cells) {
        int days = cells.length;
        int res = 0, s = 1, e = days;
        while(s <= e){
            int mid = s + (e - s) / 2;
            if(canWalk(row, col, cells, mid)){
                res = mid;
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }
        return res;
    }

    private boolean canWalk(int row, int col, int[][] cells, int day) {
        boolean[][] board = new boolean[row][col];
        for(int i = 0; i < day; i++){
            int r = cells[i][0] - 1;
            int c = cells[i][1] - 1;
            board[r][c] = true;
        }
        Queue<int[]> q = new LinkedList<>();
        for(int c = 0; c < col; c++){
            if(!board[0][c]){
                q.offer(new int[]{0, c});
                board[0][c] = true;
            }
        }
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
            if(r == row - 1){
                return true;
            }
            for(int i = 0; i + 1 < dirs.length; i++){
                int nb_r = r  + dirs[i];
                int nb_c = c  + dirs[i + 1];
                if(nb_r >= 0 && nb_r < row && nb_c >= 0 && nb_c < col && !board[nb_r][nb_c]){
                    q.offer(new int[]{nb_r, nb_c});
                    board[nb_r][nb_c] = true;
                }
            }
        }
        return false;
    }
}

