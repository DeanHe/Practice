package BFS;

/*
In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].

        A knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.
        Return the minimum number of steps needed to move the knight to the square [x, y].  It is guaranteed the answer exists.

        Example 1:

        Input: x = 2, y = 1
        Output: 1
        Explanation: [0, 0] → [2, 1]
        Example 2:

        Input: x = 5, y = 5
        Output: 4
        Explanation: [0, 0] → [2, 1] → [4, 2] → [3, 4] → [5, 5]


        Constraints:
        |x| + |y| <= 300

Because x and y are constrained to be in range[-300, 300], we can use BFS to find the minimum steps needed to reach target(x, y).
Furthermore, we can only consider the case that x >=0 && y >=0 since the chess board is symmetric.
The bfs implementation is pretty straightforward. There are two important points you need to be careful with.
1.  Pruning. We can limit the search dimension within 310 * 310. Any moves that lead to a position that is outside this box will not yield an optimal result.

2. Initially, you used a Set of type int[] to track visited positions.
This caused TLE because you didn't overwrite the hashCode and equals methods for int[].
As a result, Set uses the default hashCode and equals method when checking if an element is already in the set.
For equals(), The default implementation provided by the JDK is based on memory location — two objects are equal if and only if they are stored in the same memory address.
For a comprehensive reading, refer to https://dzone.com/articles/working-with-hashcode-and-equals-in-java

time complexity: O(x * y)
*/

import java.util.LinkedList;
import java.util.Queue;

public class MinimumKnightMoves {
    public int minKnightMoves(int x, int y) {
        int[][] dirs = {{1, 2}, {1, -2}, {2, 1}, {2, -1}, {-1, 2}, {-1, -2}, {-2, 1}, {-2, -1}};
        x = Math.abs(x);
        y = Math.abs(y);
        int bound = 310, step = 0;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[bound][bound];
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                int[] cur = queue.poll();
                if(cur[0] == x && cur[1] == y){
                    return step;
                }
                for(int[] dir : dirs){
                    int nb_x = cur[0] + dir[0];
                    int nb_y = cur[1] + dir[1];
                    if(nb_x >= 0 && nb_x < bound && nb_y >= 0 && nb_y < bound && !visited[nb_x][nb_y]){
                        visited[nb_x][nb_y] = true;
                        queue.offer(new int[]{nb_x, nb_y});
                    }
                }
            }
            step++;
        }
        return -1;
    }
}
