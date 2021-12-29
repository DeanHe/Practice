package heap;

import java.util.*;

/*
Given some points and an origin point in two-dimensional space, find k points which are nearest to the origin.
Return these points sorted by distance, if they are same in distance, sorted by the x-axis, and if they are same in the x-axis, sorted by y-axis.

Example
Example 1:

Input: points = [[4,6],[4,7],[4,4],[2,5],[1,1]], origin = [0, 0], k = 3 
Output: [[1,1],[2,5],[4,4]]
Example 2:

Input: points = [[0,0],[0,9]], origin = [3, 1], k = 1
Output: [[0,0]]
analysis:
maxHeap is O(NlogK)

quick select is O(N)
The advantage of this solution is it is very efficient.
The disadvantage of this solution are it is neither an online solution nor a stable one. And the K elements closest are not sorted in ascending order.
*/
public class KClosestPoints {
	/**
     * @param points: a list of points
     * @param k: An integer
     * @return: the k closest points
     */
    public int[][] kClosest(int[][] points, int k) {
        // write your code here
        int[][] res = new int[k][2];
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (a, b) -> {
                    int dist_a = a[0] * a[0] + a[1] * a[1];
                    int dist_b = b[0] * b[0] + b[1] * b[1];
                    return dist_b - dist_a;
                }
        );
        for(int[] p : points){
            pq.offer(p);
            if(pq.size() > k){
                pq.poll();
            }
        }
        int i = pq.size() - 1;
        while(!pq.isEmpty()){
            res[i--] = pq.poll();
        }
        return res;
    }

    //quick select
    public int[][] kClosestQS(int[][] points, int k) {
        int start = 0, end = points.length - 1;
        while(start <= end){
            int mid = partition(points, start, end);
            if(mid == k - 1){
                break;
            } else if(mid < k - 1){
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return Arrays.copyOfRange(points, 0, k);
    }

    private int partition(int[][] points, int start, int end) {
        int[] pivot = points[end];
        int i = start;
        for(int j = start; j < end; j++){
            if(compare(points[j], pivot)){
                swap(points, i++, j);
            }
        }
        swap(points, i, end);
        return i;
    }

    // point a is closer to origin or equal distance compare with point b
    private boolean compare(int[] a, int[] b) {
        return a[0] * a[0] + a[1] * a[1] <= b[0] * b[0] + b[1] * b[1];
    }

    private void swap(int[][] points, int i, int j) {
        int[] temp = points[i];
        points[i] = points[j];
        points[j] = temp;
    }
}
