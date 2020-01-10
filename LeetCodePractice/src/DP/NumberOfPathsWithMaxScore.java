package DP;

import java.util.List;

/*
        You are given a square board of characters. You can move on the board starting at the bottom right square marked with the character 'S'.
        You need to reach the top left square marked with the character 'E'. The rest of the squares are labeled either with a numeric character 1, 2, ..., 9 or with an obstacle 'X'. In one move you can go up, left or up-left (diagonally) only if there is no obstacle there.
        Return a list of two integers: the first integer is the maximum sum of numeric characters you can collect, and the second is the number of such paths that you can take to get that maximum sum, taken modulo 10^9 + 7.
        In case there is no path, return [0, 0].

        Example 1:

        Input: board = ["E23","2X2","12S"]
        Output: [7,1]
        Example 2:

        Input: board = ["E12","1X1","21S"]
        Output: [4,2]
        Example 3:

        Input: board = ["E11","XXX","11S"]
        Output: [0,0]


        Constraints:

        2 <= board.length == board[i].length <= 100
*/
public class NumberOfPathsWithMaxScore {
    public int[] pathsWithMaxScore(List<String> board) {
        int MOD = (int) (1e9 + 7);
        int rows = board.size();
        int cols = board.get(0).length();
        int[][] dpSum = new int[rows][cols];
        int[][] dpCnt = new int[rows][cols];
        int[][] dirs = {{-1, 0}, {0, -1}, {-1, -1}};
        dpCnt[rows - 1][cols - 1] = 1; // start at the bottom right square
        for (int r = rows - 1; r >= 0; r--) {
            for (int c = cols - 1; c >= 0; c--) {
                if (dpCnt[r][c] == 0) {
                    continue; // can't reach to this square
                }
                for (int[] dir : dirs) {
                    int nb_r = r + dir[0];
                    int nb_c = c + dir[1];
                    if (nb_r >= 0 && nb_c >= 0) {
                        char ch = board.get(nb_r).charAt(nb_c);
                        if (ch != 'X') {
                            int cur = 0;
                            if (ch != 'E') {
                                cur = Character.getNumericValue(ch);
                            }
                            if (dpSum[r][c] + cur > dpSum[nb_r][nb_c]) {
                                dpSum[nb_r][nb_c] = dpSum[r][c] + cur;
                                dpCnt[nb_r][nb_c] = dpCnt[r][c];
                            } else if (dpSum[r][c] + cur == dpSum[nb_r][nb_c]) {
                                dpCnt[nb_r][nb_c] = (dpCnt[nb_r][nb_c] + dpCnt[r][c]) % MOD;
                            }
                        }
                    }
                }
            }
        }
        int[] res = new int[]{dpSum[0][0], dpCnt[0][0]};
        return res;
    }
}
