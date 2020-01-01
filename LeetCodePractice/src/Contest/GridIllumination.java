package Contest;

import java.util.HashMap;
import java.util.Map;

/*On a N x N grid of cells, each cell (x, y) with 0 <= x < N and 0 <= y < N has a lamp.

        Initially, some number of lamps are on.  lamps[i] tells us the location of the i-th lamp that is on.  Each lamp that is on illuminates every square on its x-axis, y-axis, and both diagonals (similar to a Queen in chess).

        For the i-th query queries[i] = (x, y), the answer to the query is 1 if the cell (x, y) is illuminated, else 0.

        After each query (x, y) [in the order given by queries], we turn off any lamps that are at cell (x, y) or are adjacent 8-directionally (ie., share a corner or edge with cell (x, y).)

        Return an array of answers.  Each value answer[i] should be equal to the answer of the i-th query queries[i].

        Example 1:

        Input: N = 5, lamps = [[0,0],[4,4]], queries = [[1,1],[1,0]]
        Output: [1,0]
        Explanation:
        Before performing the first query we have both lamps [0,0] and [4,4] on.
        The grid representing which cells are lit looks like this, where [0,0] is the top left corner, and [4,4] is the bottom right corner:
        1 1 1 1 1
        1 1 0 0 1
        1 0 1 0 1
        1 0 0 1 1
        1 1 1 1 1
        Then the query at [1, 1] returns 1 because the cell is lit.  After this query, the lamp at [0, 0] turns off, and the grid now looks like this:
        1 0 0 0 1
        0 1 0 0 1
        0 0 1 0 1
        0 0 0 1 1
        1 1 1 1 1
        Before performing the second query we have only the lamp [4,4] on.  Now the query at [1,0] returns 0, because the cell is no longer lit.


        Note:

        1 <= N <= 10^9
        0 <= lamps.length <= 20000
        0 <= queries.length <= 20000
        lamps[i].length == queries[i].length == 2*/
public class GridIllumination {
    public int[] gridIllumination(int N, int[][] lamps, int[][] queries) {
        int[] res = new int[queries.length];
        int[][] dirs = {{0, 0}, {0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, -1}, {1, 1}, {-1, -1}, {-1, 1}};
        Map<Integer, Integer> rowLampCnt = new HashMap<>();
        Map<Integer, Integer> colLampCnt = new HashMap<>();
        Map<Integer, Integer> diagLampCnt = new HashMap<>();
        Map<Integer, Integer> antiDiagLampCnt = new HashMap<>();
        Map<Integer, Boolean> lampState = new HashMap<>();
        for (int[] lamp : lamps) {
            int r = lamp[0];
            int c = lamp[1];
            rowLampCnt.put(r, rowLampCnt.getOrDefault(r, 0) + 1);
            colLampCnt.put(c, colLampCnt.getOrDefault(c, 0) + 1);
            //all the diagonals with slope= 1, can be represented by r= c+c i.e. they have r-c = constant
            diagLampCnt.put(r - c, diagLampCnt.getOrDefault(r - c, 0) + 1);
            //all the diagonals with slope= -1, can be represented by r= -c+c i.e they have r+c = constant
            antiDiagLampCnt.put(r + c, antiDiagLampCnt.getOrDefault(r + c, 0) + 1);
            lampState.put(r * N + c, true);
        }
        for (int i = 0; i < queries.length; i++) {
            int r = queries[i][0];
            int c = queries[i][1];
            if (rowLampCnt.getOrDefault(r, 0) > 0 ||
                    colLampCnt.getOrDefault(c, 0) > 0 ||
                    diagLampCnt.getOrDefault(r - c, 0) > 0 ||
                    antiDiagLampCnt.getOrDefault(r + c, 0) > 0) {
                res[i] = 1;
            }
            for (int[] dir : dirs) {
                int nb_r = r + dir[0];
                int nb_c = c + dir[1];
                if (lampState.containsKey(nb_r * N + nb_c) && lampState.get(nb_r * N + nb_c)) {
                    lampState.put(nb_r * N + nb_c, false);
                    rowLampCnt.put(nb_r, rowLampCnt.getOrDefault(nb_r, 0) - 1);
                    colLampCnt.put(nb_c, colLampCnt.getOrDefault(nb_c, 0) - 1);
                    diagLampCnt.put(nb_r - nb_c, diagLampCnt.getOrDefault(nb_r - nb_c, 0) - 1);
                    antiDiagLampCnt.put(nb_r + nb_c, antiDiagLampCnt.getOrDefault(nb_r + nb_c, 0) - 1);
                }
            }
        }
        return res;
    }
}
