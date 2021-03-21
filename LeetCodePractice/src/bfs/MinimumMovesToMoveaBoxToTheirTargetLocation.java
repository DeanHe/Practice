package bfs;

import java.util.*;

/*Storekeeper is a game in which the player pushes boxes around in a warehouse trying to get them to target locations.

        The game is represented by a grid of size n*m, where each element is a wall, floor, or a box.

        Your task is move the box 'B' to the target position 'T' under the following rules:

        Player is represented by character 'S' and can move up, down, left, right in the grid if it is a floor (empy cell).
        Floor is represented by character '.' that means free cell to walk.
        Wall is represented by character '#' that means obstacle  (impossible to walk there).
        There is only one box 'B' and one target cell 'T' in the grid.
        The box can be moved to an adjacent free cell by standing next to the box and then moving in the direction of the box. This is a push.
        The player cannot walk through the box.
        Return the minimum number of pushes to move the box to the target. If there is no way to reach the target, return -1.

        Example 1:

        Input: grid = [
        ["#","#","#","#","#","#"],
        ["#","T","#","#","#","#"],
        ["#",".",".","B",".","#"],
        ["#",".","#","#",".","#"],
        ["#",".",".",".","S","#"],
        ["#","#","#","#","#","#"]]
        Output: 3
        Explanation: We return only the number of times the box is pushed.
        Example 2:

        Input: grid = [
        ["#","#","#","#","#","#"],
        ["#","T","#","#","#","#"],
        ["#",".",".","B",".","#"],
        ["#","#","#","#",".","#"],
        ["#",".",".",".","S","#"],
        ["#","#","#","#","#","#"]]
        Output: -1
        Example 3:

        Input: grid = [
        ["#","#","#","#","#","#"],
        ["#","T",".",".","#","#"],
        ["#",".","#","B",".","#"],
        ["#",".",".",".",".","#"],
        ["#",".",".",".","S","#"],
        ["#","#","#","#","#","#"]]
        Output: 5
        Explanation:  push the box down, left, left, up and up.
        Example 4:

        Input: grid = [
        ["#","#","#","#","#","#","#"],
        ["#","S","#",".","B","T","#"],
        ["#","#","#","#","#","#","#"]]
        Output: -1


        Constraints:

        1 <= grid.length <= 20
        1 <= grid[i].length <= 20
        grid contains only characters '.', '#',  'S' , 'T', or 'B'.
        There is only one character 'S', 'B' and 'T' in the grid.*/
public class MinimumMovesToMoveaBoxToTheirTargetLocation {
    private int[] dirs = {-1, 0, 1, 0, -1};
    public int minPushBox(char[][] grid) {
        int res = Integer.MAX_VALUE;
        int rows = grid.length;
        int cols = grid[0].length;
        int[] box = null, target = null, player = null;
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                if(grid[r][c] == 'B'){
                    box = new int[]{r, c};
                } else if(grid[r][c] == 'T'){
                    target = new int[]{r, c};
                } else if(grid[r][c] == 'S'){
                    player = new int[]{r, c};
                }
            }
        }
        Map<GridState, Integer> dist = new HashMap<>();
        Queue<GridState> queue = new LinkedList<>();
        GridState start = new GridState(box[0], box[1], player[0], player[1]);
        queue.offer(start);
        dist.put(start, 0);
        while(!queue.isEmpty()){
            GridState cur = queue.poll();
            if(dist.get(cur) >= res){
                continue;
            }
            if(cur.curBoxX == target[0] && cur.curBoxY == target[1]){
                res = Math.min(res, dist.get(cur));
                continue;
            }
            for(int i = 0; i < dirs.length - 1; i++){
                int nextPlayerX = cur.curPlayerX + dirs[i];
                int nextPlayerY = cur.curPlayerY + dirs[i + 1];
                if(nextPlayerX < 0 || nextPlayerX >= rows || nextPlayerY < 0 || nextPlayerY >= cols || grid[nextPlayerX][nextPlayerY] == '#'){
                    continue;
                }
                if(nextPlayerX == cur.curBoxX && nextPlayerY == cur.curBoxY){
                    int nextBoxX = cur.curBoxX + dirs[i];
                    int nextBoxY = cur.curBoxY + dirs[i + 1];
                    if(nextBoxX < 0 || nextBoxX >= rows || nextBoxY < 0 || nextBoxY >= cols || grid[nextBoxX][nextBoxY] == '#'){
                        continue;
                    }
                    GridState next = new GridState(nextBoxX, nextBoxY, nextPlayerX, nextPlayerY);
                    if(dist.containsKey(next) && dist.get(next) <= dist.get(cur) + 1){
                        continue;
                    }
                    queue.offer(next);
                    dist.put(next, dist.get(cur) + 1);
                } else {
                    GridState next = new GridState(cur.curBoxX, cur.curBoxY, nextPlayerX, nextPlayerY);
                    if(dist.containsKey(next) && dist.get(next) <= dist.get(cur)){
                        continue;
                    }
                    queue.offer(next);
                    dist.put(next, dist.get(cur));
                }
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
    class GridState {
        int curBoxX, curBoxY, curPlayerX, curPlayerY;
        GridState(int cx, int cy, int px, int py){
            curBoxX = cx;
            curBoxY = cy;
            curPlayerX = px;
            curPlayerY = py;
        }

        @Override
        public int hashCode() {
            return (curBoxX << 24) | (curBoxY << 16) | (curPlayerX << 8) | (curPlayerY);
        }

        @Override
        public boolean equals(Object obj) {
            if(obj == null){
                return false;
            }
            if(!GridState.class.isAssignableFrom(obj.getClass())){
                return false;
            }
            final GridState other = (GridState) obj;
            return this.curBoxX == other.curBoxX && this.curBoxY == other.curBoxY && this.curPlayerX == other.curPlayerX && this.curPlayerY == other.curPlayerY;
        }
    }
}
