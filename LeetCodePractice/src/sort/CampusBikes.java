package sort;

import java.util.ArrayList;
import java.util.List;

/*
On a campus represented as a 2D grid, there are NN workers and MM bikes, with N \leq MN≤M. Each worker and bike is a 2D coordinate on this grid.

Our goal is to assign a bike to each worker. Among the available bikes and workers,
we choose the (worker, bike) pair with the shortest Manhattan distance between each other,
and assign the bike to that worker. (If there are multiple (worker, bike) pairs with the same shortest Manhattan distance,
we choose the pair with the smallest worker index; if there are multiple ways to do that, we choose the pair with the smallest bike index).
We repeat this process until there are no available workers.

The Manhattan distance between two points p_1 and p_2
​​  is Manhattan(p_1, p_2) = |p_1.x - p_2.x| + |p_1.y - p_2.y|

Return a vector ans of length NN, where ans_i
​i
​​  is the index (0-indexed) of the bike that the i-th worker is assigned to.

0 <= workers[i][j],bikes[i][j] < 1000
All worker and bike locations are distinct.
1 <= workers.length <= bikes.length <= 1000
Example
Example 1

Input:
[[0,0],[2,1]]
[[1,2],[3,3]]
Output: [1,0]
Explanation:
Worker 1 grabs Bike 0 as they are closest (without ties), and Worker 0 is assigned Bike 1. So the output is [1, 0].


Example 2

Input:
[[0,0],[1,1],[2,0]]
[[1,0],[2,2],[2,1]]
Output: [0,2,1]
Explanation:
Worker 0 grabs Bike 0 at first. Worker 1 and Worker 2 share the same distance to Bike 2, thus Worker 1 is assigned to Bike 2,
and Worker 2 will take Bike 1. So the output is [0,2,1].

analysis:
Use PriorityQueue to sort will take O(MNlogMN) time. and O(MN) space
use Bucket sort will take O (MN) time and O(MN) space.
 */
public class CampusBikes {
    /**
     * @param workers: workers' location
     * @param bikes: bikes' location
     * @return: assign bikes
     */
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        int wl = workers.length, bl = bikes.length;
        int[] res = new int[wl];
        boolean[] vistedw = new boolean[wl];
        boolean[] vistedb = new boolean[bl];
        int[][] pairs = new int[wl * bl][3];
        int totalPairs = 0;
        for(int i = 0; i < wl; i++){
            for(int j = 0; j < bl; j++){
                pairs[totalPairs][0] = manhattan(workers[i], bikes[j]);
                pairs[totalPairs][1] = i;
                pairs[totalPairs][2] = j;
                totalPairs++;
            }
        }
        List<Integer>[] buckets = new List[2000];
        for(int i = 0; i < 2000; i++){
            buckets[i] = new ArrayList<>();
        }
        for(int i = 0; i < totalPairs; i++){
            int dist = pairs[i][0];
            buckets[dist].add(i);
        }
        for(int dist = 0; dist < 2000; dist++){
            for(int pairIdx : buckets[dist]){
                int[] pair = pairs[pairIdx];
                int worker = pair[1];
                int bike = pair[2];
                if(!vistedw[worker] && !vistedb[bike]){
                    res[worker] = bike;
                    vistedw[worker] = true;
                    vistedb[bike] = true;
                }
            }
        }
        return res;
    }

    private int manhattan(int[] a, int[] b){
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }
}

