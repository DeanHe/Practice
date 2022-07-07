package bfs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/*
Given an array of integers arr, you are initially positioned at the first index of the array.
In one step you can jump from index i to index:

i + 1 where: i + 1 < arr.length.
i - 1 where: i - 1 >= 0.
j where: arr[i] == arr[j] and i != j.
Return the minimum number of steps to reach the last index of the array.

Notice that you can not jump outside of the array at any time.

Example 1:
Input: arr = [100,-23,-23,404,100,23,23,23,3,404]
Output: 3
Explanation: You need three jumps from index 0 --> 4 --> 3 --> 9. Note that index 9 is the last index of the array.

Example 2:
Input: arr = [7]
Output: 0
Explanation: Start index is the last index. You don't need to jump.

Example 3:
Input: arr = [7,6,9,6,9,6,9,7]
Output: 1
Explanation: You can jump directly from index 0 to index 7 which is last index of the array.

Example 4:
Input: arr = [6,1,9]
Output: 2

Example 5:
Input: arr = [11,22,7,7,7,7,7,7,7,22,13]
Output: 3

Constraints:
1 <= arr.length <= 5 * 10^4
-10^8 <= arr[i] <= 10^8

hint:
Build a graph of n nodes where nodes are the indices of the array and edges for node i are nodes i+1, i-1, j where arr[i] == arr[j].
Start bfs from node 0 and keep distance, answer is the distance when you reach node n-1.
*/
public class JumpGameIV {
    public int minJumps(int[] arr) {
        int len  = arr.length;
        boolean[] visited = new boolean[len];
        Map<Integer, List<Integer>> valIdxMap = new HashMap<>();
        for(int i = 0; i < len; i++){
            valIdxMap.computeIfAbsent(arr[i], x -> new LinkedList<>()).add(i);
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        visited[0] = true;
        int step = 0;
        while(!queue.isEmpty()){
            int sz = queue.size();
            for(int i = 0; i < sz; i++){
                int cur = queue.poll();
                if(cur == len - 1){
                    return step;
                }
                List<Integer> neighbors = valIdxMap.get(arr[cur]);
                neighbors.add(cur - 1);
                neighbors.add(cur + 1);
                for(int nb : neighbors){
                    if(nb >= 0 && nb < len && !visited[nb]){
                        queue.offer(nb);
                        visited[nb] = true;
                    }
                }
                neighbors.clear(); // avoid repeated lookup on arr[cur];
            }
            step++;
        }
        return -1;
    }
}
