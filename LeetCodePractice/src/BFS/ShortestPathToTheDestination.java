// https://www.lintcode.com/problem/shortest-path-to-the-destination/description
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
        Queue<Coordinate> queue = new LinkedList<>();
        queue.offer(new Coordinate(0, 0));
        visited[0][0] = true;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                Coordinate cur = queue.poll();
                if(targetMap[cur.x][cur.y] == 2){
                    return step;
                }
                Coordinate nb0 = new Coordinate(cur.x - 1, cur.y);
                if(isValid(targetMap, nb0.x, nb0.y)){
                    if(!visited[nb0.x][nb0.y]){
                        queue.offer(nb0);
                        visited[nb0.x][nb0.y] = true;
                    }
                }
                Coordinate nb1 = new Coordinate(cur.x + 1, cur.y);
                if(isValid(targetMap, nb1.x, nb1.y)){
                    if(!visited[nb1.x][nb1.y]){
                        queue.offer(nb1);
                        visited[nb1.x][nb1.y] = true;
                    }   
                }
                Coordinate nb2 = new Coordinate(cur.x, cur.y - 1);
                if(isValid(targetMap, nb2.x, nb2.y)){
                    if(!visited[nb2.x][nb2.y]){
                        queue.offer(nb2);
                        visited[nb2.x][nb2.y] = true;
                    }
                }
                Coordinate nb3 = new Coordinate(cur.x, cur.y + 1);
                if(isValid(targetMap, nb3.x, nb3.y)){
                    if(!visited[nb3.x][nb3.y]){
                        queue.offer(nb3);
                        visited[nb3.x][nb3.y] = true;
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
        if(x < 0 || x >= rows){
            return false;
        }
        if(y < 0 || y >= cols){
            return false;
        }
        if(targetMap[x][y] == 1){
            return false;
        }
        return true;
    }
}

class Coordinate {
    int x;
    int y;
    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }
}
