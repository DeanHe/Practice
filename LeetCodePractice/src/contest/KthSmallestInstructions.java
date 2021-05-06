package contest;

/*
Bob is standing at cell (0, 0), and he wants to reach destination: (row, column). He can only travel right and down. You are going to help Bob by providing instructions for him to reach destination.

The instructions are represented as a string, where each character is either:

'H', meaning move horizontally (go right), or
'V', meaning move vertically (go down).
Multiple instructions will lead Bob to destination. For example, if destination is (2, 3), both "HHHVV" and "HVHVH" are valid instructions.

However, Bob is very picky. Bob has a lucky number k, and he wants the kth lexicographically smallest instructions that will lead him to destination. k is 1-indexed.

Given an integer array destination and an integer k, return the kth lexicographically smallest instructions that will take Bob to destination.



Example 1:



Input: destination = [2,3], k = 1
Output: "HHHVV"
Explanation: All the instructions that reach (2, 3) in lexicographic order are as follows:
["HHHVV", "HHVHV", "HHVVH", "HVHHV", "HVHVH", "HVVHH", "VHHHV", "VHHVH", "VHVHH", "VVHHH"].
Example 2:



Input: destination = [2,3], k = 2
Output: "HHVHV"
Example 3:



Input: destination = [2,3], k = 3
Output: "HHVVH"


Constraints:

destination.length == 2
1 <= row, column <= 15
1 <= k <= nCr(row + column, row), where nCr(a, b) denotes a choose b​​​​​.

analysis:
dp
 */
public class KthSmallestInstructions {
    int rows, cols;
    Integer[][] mem;
    int[] destination;

    public String kthSmallestPath(int[] destination, int k) {
        this.destination = destination;
        rows = destination[0] + 1;
        cols = destination[1] + 1;
        mem = new Integer[rows + cols][rows + cols];
        return dfs(0, 0, k);
    }

    private String dfs(int r, int c, int k) {
        if (r == rows - 1 && c == cols - 1) {
            return "";
        }
        if (r == rows - 1) {
            return "H" + dfs(r, c + 1, k);
        } else if (c == cols - 1) {
            return "V" + dfs(r + 1, c, k);
        } else if (k <= nCk(destination[0] + destination[1] - r - c - 1, destination[1] - c - 1)) {
            return "H" + dfs(r, c + 1, k);
        } else {
            return "V" + dfs(r + 1, c, k - nCk(destination[0] + destination[1] - r - c - 1, destination[1] - c - 1));
        }
    }

    private Integer nCk(int n, int k) {
        if (k == 0 || n == k) {
            return 1;
        }
        if (mem[n][k] != null) {
            return mem[n][k];
        }
        return mem[n][k] = nCk(n - 1, k - 1) + nCk(n - 1, k);
    }

    public String kthSmallestPathII(int[] destination, int k) {
        rows = destination[0] + 1;
        cols = destination[1] + 1;
        // dp[r][c] means # of ways to reach destination {rows - 1, cols - 1} start from {r, c}
        int[][] dp = new int[rows][cols];
        for (int r = rows - 1; r >= 0; r--) {
            for (int c = cols - 1; c >= 0; c--) {
                if (r == rows - 1 && c == cols - 1) {
                    dp[r][c] = 1;
                } else if (r == rows - 1) {
                    dp[r][c] = dp[r][c + 1];
                } else if (c == cols - 1) {
                    dp[r][c] = dp[r + 1][c];
                } else {
                    dp[r][c] = dp[r + 1][c] + dp[r][c + 1];
                }
            }
        }
        // all dp[r][c + 1] kinds of instructions are lexicographically smaller than the left dp[r + 1][c] kinds of instructions.
        // we can just compare k with dp[r][c + 1] to determine how to choose next step.
        StringBuilder sb = new StringBuilder();
        dfs(sb, dp, k, 0, 0);
        return sb.toString();

    }

    private void dfs(StringBuilder sb, int[][] dp, int k, int r, int c) {
        if (r == rows - 1) {
            while (c < cols - 1) {
                sb.append('H');
                c++;
            }
            return;
        }
        if (c == cols - 1) {
            while (r < rows - 1) {
                sb.append('V');
                r++;
            }
            return;
        }
        if (dp[r][c + 1] >= k) {
            sb.append('H');
            dfs(sb, dp, k, r, c + 1);
        } else {
            sb.append('V');
            dfs(sb, dp, k - dp[r][c + 1], r + 1, c);
        }
    }
}
