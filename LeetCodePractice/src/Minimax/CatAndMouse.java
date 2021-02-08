package Minimax;
/*
A game on an undirected graph is played by two players, Mouse and Cat, who alternate turns.

The graph is given as follows: graph[a] is a list of all nodes b such that ab is an edge of the graph.

The mouse starts at node 1 and goes first, the cat starts at node 2 and goes second, and there is a hole at node 0.

During each player's turn, they must travel along one edge of the graph that meets where they are.  For example, if the Mouse is at node 1, it must travel to any node in graph[1].

Additionally, it is not allowed for the Cat to travel to the Hole (node 0.)

Then, the game can end in three ways:

If ever the Cat occupies the same node as the Mouse, the Cat wins.
If ever the Mouse reaches the Hole, the Mouse wins.
If ever a position is repeated (i.e., the players are in the same position as a previous turn, and it is the same player's turn to move), the game is a draw.
Given a graph, and assuming both players play optimally, return

1 if the mouse wins the game,
2 if the cat wins the game, or
0 if the game is a draw.


Example 1:


Input: graph = [[2,5],[3],[0,4,5],[1,4,5],[2,3],[0,2,3]]
Output: 0
Example 2:


Input: graph = [[1,3],[0],[3],[0,2]]
Output: 1


Constraints:

3 <= graph.length <= 50
1 <= graph[i].length < graph.length
0 <= graph[i][j] < graph.length
graph[i][j] != i
graph[i] is unique.
The mouse and the cat can always move.

analysis:
dfs + memo
dp[m][c][s] means the mouse in position m, cat in position c at step s, what is final result

There are  n nodes, so when after 2n steps if we still can't decide who wins, the chase game will continue for ever and return 0
There are totally 2n* n * n = 2n^3 status, we may not reach every position. because we only can move along the graph's edges

TC O(N ^ 3)
 */
public class CatAndMouse {
    int len;
    public int catMouseGame(int[][] graph) {
        len = graph.length;
        Integer[][][] dp = new Integer[len][len][2 * len];
        return win(graph, dp, 0, 1, 2);
    }

    private int win(int[][] graph, Integer[][][] dp, int step, int mouse, int cat){
        if(step == len * 2){
            return 0;
        }
        if(mouse == cat){
            return 2;
        }
        if(mouse == 0){
            return 1;
        }
        if(dp[mouse][cat][step] != null){
            return dp[mouse][cat][step];
        }
        if(step % 2 == 0){ // mouse turn
            boolean canDraw = false;
            for(int nb : graph[mouse]){
                int res = win(graph, dp, step + 1, nb, cat);
                if(res == 1){
                    return dp[mouse][cat][step] = 1;
                } else if(res == 0){
                    canDraw = true;
                }
            }
            if(canDraw){
                return dp[mouse][cat][step] = 0;
            }
            return dp[mouse][cat][step] = 2;
        } else { // cat turn
            boolean canDraw = false;
            for(int nb : graph[cat]){
                if(nb != 0){
                    int res = win(graph, dp, step + 1, mouse, nb);
                    if(res == 2){
                        return dp[mouse][cat][step] = 2;
                    } else if(res == 0){
                        canDraw = true;
                    }
                }
            }
            if(canDraw){
                return dp[mouse][cat][step] = 0;
            }
            return dp[mouse][cat][step] = 1;
        }
    }
}
