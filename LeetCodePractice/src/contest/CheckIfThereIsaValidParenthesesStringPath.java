package contest;
/*
A parentheses string is a non-empty string consisting only of '(' and ')'. It is valid if any of the following conditions is true:

It is ().
It can be written as AB (A concatenated with B), where A and B are valid parentheses strings.
It can be written as (A), where A is a valid parentheses string.
You are given an m x n matrix of parentheses grid. A valid parentheses string path in the grid is a path satisfying all of the following conditions:

The path starts from the upper left cell (0, 0).
The path ends at the bottom-right cell (m - 1, n - 1).
The path only ever moves down or right.
The resulting parentheses string formed by the path is valid.
Return true if there exists a valid parentheses string path in the grid. Otherwise, return false.



Example 1:


Input: grid = [["(","(","("],[")","(",")"],["(","(",")"],["(","(",")"]]
Output: true
Explanation: The above diagram shows two possible paths that form valid parentheses strings.
The first path shown results in the valid parentheses string "()(())".
The second path shown results in the valid parentheses string "((()))".
Note that there may be other valid parentheses string paths.
Example 2:


Input: grid = [[")",")"],["(","("]]
Output: false
Explanation: The two possible paths form the parentheses strings "))(" and ")((". Since neither of them are valid parentheses strings, we return false.


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 100
grid[i][j] is either '(' or ')'.

hint:
1 What observations can you make about the number of open brackets and close brackets for any prefix of a valid bracket sequence?
2 The number of open brackets must always be greater than or equal to the number of close brackets.
3 Could you use dynamic programming?
 */
public class CheckIfThereIsaValidParenthesesStringPath {
    int[] dirs = {1, 0, 1};
    int rows, cols;

    public boolean hasValidPath(char[][] grid) {
        rows = grid.length;
        cols = grid[0].length;
        Boolean[][][] mem = new Boolean[rows][cols][rows + cols + 1];
        if(grid[rows - 1][cols - 1] == '('){
            return false;
        }
        return dfs(grid, mem, 0, 0, 0);
    }

    private boolean dfs(char[][] grid, Boolean[][][] mem, int r, int c, int open) {
        if(mem[r][c][open] != null){
            return mem[r][c][open];
        }
        //System.out.println(r + ":" + c + ":" + open);
        open += grid[r][c] == ')' ? -1 : 1;
        if (open < 0) {
            return false;
        }
        if (r == rows - 1 && c == cols - 1) {
            return mem[r][c][open] = open == 0;
        }
        if(rows + cols - r - c < open){
            return mem[r][c][open] = false;
        }
        for (int i = 0; i + 1 < dirs.length; i++) {
            int nb_r = r + dirs[i];
            int nb_c = c + dirs[i + 1];
            if (0 <= nb_r && nb_r < rows && 0 <= nb_c && nb_c < cols) {
                if (dfs(grid, mem, nb_r, nb_c, open)) {
                    return mem[r][c][open] = true;
                }
            }
        }
        return mem[r][c][open] = false;
    }
}
