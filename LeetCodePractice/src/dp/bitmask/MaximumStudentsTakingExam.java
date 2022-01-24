package dp.bitmask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Given a m * n matrix seats that represent seats distributions in a classroom. If a seat is broken, it is denoted by '#' character otherwise it is denoted by a '.' character.
Students can see the answers of those sitting next to the left, right, upper left and upper right, but he cannot see the answers of the student sitting directly in front or behind him. Return the maximum number of students that can take the exam together without any cheating being possible..
Students must be placed in seats in good condition.

Example 1:
Input: seats = [["#",".","#","#",".","#"],
[".","#","#","#","#","."],
["#",".","#","#",".","#"]]
Output: 4
Explanation: Teacher can place 4 students in available seats so they don't cheat on the exam.

Example 2:
Input: seats = [[".","#"],
["#","#"],
["#","."],
["#","#"],
[".","#"]]
Output: 3
Explanation: Place all students in available seats.

Example 3:
Input: seats = [["#",".",".",".","#"],
[".","#",".","#","."],
[".",".","#",".","."],
[".","#",".","#","."],
["#",".",".",".","#"]]
Output: 10
Explanation: Place students in available seats in column 1, 3 and 5.

Constraints:
seats contains only characters '.' and'#'.
m == seats.length
n == seats[i].length
1 <= m <= 8
1 <= n <= 8

tag:bitmask
*/
public class MaximumStudentsTakingExam {
    int rows, cols, totalState;
    Integer[][] memo;
    //mask represents occupancy status for a row, for example 100001 means 1st and 6th seats are taken
    //use backtrack to get all combination of seat assignment for a row based on previous row's mask
    List<Integer> masks;

    public int maxStudents(char[][] seats) {
        rows = seats.length;
        cols = seats[0].length;
        totalState = 1 << cols;
        // mem[i][j] stores the max student count for row range in [i, RowCount - 1] when the previous row's mask is j
        memo = new Integer[rows][totalState];
        return getMax(seats, 0, 0);
    }

    private int getMax(char[][] seats, int r, int preMask) {
        if (r == rows) {
            return 0;
        }
        if (memo[r][preMask] != null) {
            return memo[r][preMask];
        }
        masks = new ArrayList<>();
        int res = 0;
        dfs(seats[r], 0, preMask, 0);
        for (int m : masks) {
            res = Math.max(res, Integer.bitCount(m) + getMax(seats, r + 1, m));
        }
        return memo[r][preMask] = res;
    }

    // this returns all combination of legal seat assignment for a given row based on prevous row's mask
    private void dfs(char[] row, int c, int preMask, int curMask) {
        if (c == cols) {
            masks.add(curMask);
            return;
        }
        //current seat is not taken
        dfs(row, c + 1, preMask, curMask);
        //current seat is taken
        if (row[c] != '#' &&
                (c == 0 || (curMask & (1 << (c - 1))) == 0) && // check right
                (c == cols - 1 || (curMask & (1 << (c + 1))) == 0) && // check left
                (c == 0 || (preMask & (1 << (c - 1))) == 0) && // check up right
                (c == cols - 1 || (preMask & (1 << (c + 1))) == 0) // check up left
        ) {
            dfs(row, c + 1, preMask, curMask | (1 << c));
        }
    }
}
