package BFS;
/*Given a 2D array representing the coordinates on the map, there are only values 0, 1, 2 on the map. 
value 0 means that it can pass, value 1 means not passable, value 2 means target place. 
Starting from the coordinates [0,0],You can only go up, down, left and right. 
Find the shortest path that can reach the destination, and return the length of the path.

Example
Given:

[
 [0, 0, 0],
 [0, 0, 1],
 [0, 0, 2]
]
Return: 4

Notice
1.The map must exist and is not empty, there is only one target*/

import java.util.*;

public class ShortestPathToTheDestination {
	/**
     * @param targetMap: 
     * @return: nothing
     */
    public int shortestPath(int[][] targetMap) {
        // Write your code here
        int step = 0;
        boolean[][] visited = new boolean[targetMap.length][targetMap[0].length];
        int[] direct = new int[]{0, 1, 0, -1, 0};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                int[] cur = queue.poll();
                int cur_r = cur[0];
                int cur_c = cur[1];
                if(targetMap[cur_r][cur_c] == 2){
                    return step;
                }
                for(int j = 0; j < direct.length - 1; j++){
                	 int nb_r = cur_r + direct[j];
                	 int nb_c = cur_c + direct[j + 1];
                     if(isValid(targetMap, nb_r, nb_c)){
                         if(!visited[nb_r][nb_c]){
                             queue.offer(new int[]{nb_r, nb_c});
                             visited[nb_r][nb_c] = true;
                         }
                     }
                }
            }
            step++;
        }
        return -1;
    }
    
    private boolean isValid(int[][] targetMap, int x, int y){
        int rows = targetMap.length;
        int cols = targetMap[0].length;
        if(x < 0 || x >= rows || y < 0 || y >= cols || targetMap[x][y] == 1){
            return false;
        }
        return true;
    }
}
