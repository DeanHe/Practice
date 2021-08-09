package bfs;

import java.util.LinkedList;
import java.util.Queue;

/*
You have maps of parts of the space station, each starting at a work area exit and ending at the door to an escape pod.
The map is represented as a matrix of 0s and 1s, where 0s are passable space and 1s are impassable walls.
The door out of the station is at the top left (0,0) and the door into an escape pod is at the bottom right (w-1,h-1).

Write a function solution(map) that generates the length of the shortest path from the station door to the escape pod, where you are allowed to remove one wall as part of your remodeling plans.
The path length is the total number of nodes you pass through, counting both the entrance and exit nodes. The starting and ending positions are always passable (0).
The map will always be solvable, though you may or may not need to remove a wall.
The height and width of the map can be from 2 to 20. Moves can only be made in cardinal directions; no diagonal moves are allowed.

analysis:
same as ShortestPathInaGridWithObstaclesElimination
 */
public class ShortestPathWithOneRemoval {
    int[] dirs = {0, 1, 0, -1, 0};
    public int shortestPath(int[][] map) {
        int rows = map.length, cols = map[0].length;
        boolean[][][] visited = new boolean[rows][cols][2];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0, 0});
        visited[0][0][0] = true;
        int step = 1;
        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                int[] cur = q.poll();
                int r = cur[0];
                int c = cur[1];
                int obs = cur[2];
                if (r == rows - 1 && c == cols - 1) {
                    return step;
                }
                for (int j = 0; j + 1 < dirs.length; j++) {
                    int nb_r = r + dirs[j];
                    int nb_c = c + dirs[j + 1];
                    int nb_obs = obs;
                    if (nb_r >= 0 && nb_r < rows && nb_c >= 0 && nb_c < cols) {
                        if (map[nb_r][nb_c] == 1) {
                            nb_obs++;
                        }
                        if (nb_obs <= 1 && !visited[nb_r][nb_c][nb_obs]) {
                            q.offer(new int[]{nb_r, nb_c, nb_obs});
                            visited[nb_r][nb_c][nb_obs] = true;
                        }
                    }
                }
            }
            step++;
        }
        return -1;
    }
}

