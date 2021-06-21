package PrefixSum;

import java.util.ArrayList;
import java.util.List;

/*
You are given an array colors, in which there are three colors: 1, 2 and 3.

You are also given some queries. Each query consists of two integers i and c, return the shortest distance between the given index i and the target color c. If there is no solution return -1.



Example 1:

Input: colors = [1,1,2,1,3,2,2,3,3], queries = [[1,3],[2,2],[6,1]]
Output: [3,0,3]
Explanation:
The nearest 3 from index 1 is at index 4 (3 steps away).
The nearest 2 from index 2 is at index 2 itself (0 steps away).
The nearest 1 from index 6 is at index 3 (3 steps away).
Example 2:

Input: colors = [1,2], queries = [[0,3]]
Output: [-1]
Explanation: There is no 3 in the array.


Constraints:

1 <= colors.length <= 5 * 10^4
1 <= colors[i] <= 3
1 <= queries.length <= 5 * 10^4
queries[i].length == 2
0 <= queries[i][0] < colors.length
1 <= queries[i][1] <= 3

analysis:
approach 1: binary search
approach 2: DP two direction sweep
 */
public class ShortestDistanceToTargetColor {
    public List<Integer> shortestDistanceColor(int[] colors, int[][] queries) {
        List<Integer> res = new ArrayList<>();
        int[][] preSum = new int[4][colors.length];
        preSum[colors[0]][0] = 1;
        for(int i = 1; i < colors.length; i++){
            for(int j = 1; j <= 3; j++){
                if(j == colors[i]){
                    preSum[j][i] = preSum[j][i - 1] + 1;
                } else {
                    preSum[j][i] = preSum[j][i - 1];
                }
            }
        }
        for(int[] query : queries){
            int idx = query[0];
            int color = query[1];
            int cnt = preSum[color][idx];
            int left = binarySearch(preSum[color], cnt, idx);
            int right = binarySearch(preSum[color], cnt + 1, idx);
            int minDist = Math.min(left, right);
            res.add(minDist != colors.length ? minDist : -1);
        }
        return res;
    }

    private int binarySearch(int[] arr, int target, int cur){
        if(target > arr[arr.length - 1] || target == 0){
            return arr.length;
        }
        int s = 0, e = arr.length - 1;
        while(s + 1 < e){
            int mid = s + (e - s) / 2;
            if(arr[mid] < target){
                s = mid;
            } else {
                e = mid;
            }
        }
        if(arr[s] == target){
            return Math.abs(cur - s);
        }
        return Math.abs(cur - e);
    }

    public List<Integer> shortestDistanceColorDP(int[] colors, int[][] queries) {
        List<Integer> res = new ArrayList<>();
        int[][] left = new int[4][colors.length];
        int[][] right = new int[4][colors.length];
        for(int j = 1; j <= 3; j++){
            if(j == colors[0]){
                left[j][0] = 0;
            } else {
                left[j][0] = -1;
            }
        }
        for(int i = 1; i < colors.length; i++){
            for(int j = 1; j <= 3; j++){
                if(j == colors[i]){
                    left[j][i] = i;
                } else {
                    left[j][i] = left[j][i - 1];
                }
            }
        }
        for(int j = 1; j <= 3; j++){
            if(j == colors[colors.length - 1]){
                right[j][colors.length - 1] = 0;
            } else {
                right[j][colors.length - 1] = -1;
            }
        }
        for(int i = colors.length - 2; i >= 0; i--){
            for(int j = 1; j <= 3; j++){
                if(j == colors[i]){
                    right[j][i] = i;
                } else {
                    right[j][i] = right[j][i + 1];
                }
            }
        }
        for(int[] query : queries){
            int idx = query[0];
            int color = query[1];
            int l = left[color][idx] != -1 ? idx - left[color][idx] : Integer.MAX_VALUE;
            int r = right[color][idx] != -1 ? right[color][idx] - idx : Integer.MAX_VALUE;
            int minDist = Math.min(l, r);
            res.add(minDist != Integer.MAX_VALUE ? minDist : -1);
        }
        return res;
    }
}

