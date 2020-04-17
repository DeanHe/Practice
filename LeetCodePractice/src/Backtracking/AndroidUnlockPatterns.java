package Backtracking;
/*
Given an Android 3x3 key lock screen and two integers m and n, where 1 ≤ m ≤ n ≤ 9, count the total number of unlock patterns of the Android lock screen, 
which consist of minimum of m keys and maximum n keys.

Rules for a valid pattern:

Each pattern must connect at least m keys and at most n keys.
All the keys must be distinct.
If the line connecting two consecutive keys in the pattern passes through any other keys,
the other keys must have previously selected in the pattern. No jumps through non selected key is allowed.
The order of keys used matters.
android unlock
Explanation:
| 1 | 2 | 3 |
| 4 | 5 | 6 |
| 7 | 8 | 9 |
Invalid move: 4 - 1 - 3 - 6
Line 1 - 3 passes through key 2 which had not been selected in the pattern.

Invalid move: 4 - 1 - 9 - 2
Line 1 - 9 passes through key 5 which had not been selected in the pattern.

Valid move: 2 - 4 - 1 - 3 - 6
Line 1 - 3 is valid because it passes through key 2, which had been selected in the pattern

Valid move: 6 - 5 - 4 - 1 - 9 - 2
Line 1 - 9 is valid because it passes through key 5, which had been selected in the pattern.
Example

Example1
Input: m = 1, n = 1
Output: 9

Example2
Input: m = 1, n = 2
Output: 65
*/
public class AndroidUnlockPatterns {
	 /**
     * @param m: an integer
     * @param n: an integer
     * @return: the total number of unlock patterns of the Android lock screen
     */
	private int[][] skip = new int[10][10];

    private boolean[] visited = new boolean[10];
    
    public int numberOfPatterns(int m, int n) {
        // Write your code here
		skip[1][3] = skip[3][1] = 2;
		skip[1][7] = skip[7][1] = 4;
		skip[3][9] = skip[9][3] = 6;
		skip[7][9] = skip[9][7] = 8;
		skip[1][9] = skip[9][1] = skip[2][8] = skip[8][2] = skip[3][7] = skip[7][3] = skip[4][6] = skip[6][4] = 5;

        int count = 0;
        for(int steps = m; steps <= n; steps++){
        	count += 4 * dfs(1, steps);
        	count += 4 * dfs(2, steps);
        	count += dfs(5, steps);
        }
        return count;
    }

	private int dfs(int curPos, int steps){
    	if(steps <= 0){
    		return 0;
    	}
    	if(steps == 1){
    		return 1;
    	}
    	visited[curPos] = true;
    	int count = 0;
    	for(int nextPos = 1; nextPos <= 9; nextPos++){
    		boolean validMove = skip[curPos][nextPos] == 0 || visited[skip[curPos][nextPos]];
    		if(!visited[nextPos] && validMove){
    			count += dfs(nextPos, steps - 1);
    		}
    	}
    	visited[curPos] = false;
    	return count;
    }
}
