package dividAndConquer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
Given a list of points, remove the pairs of points which distance is smaller than K

TC O(N log N)
 */
public class RemovePointsCloseThanKGoogle {
    public List<int[]> removePointsCloseThanK(List<int[]> points, int K){
        Collections.sort(points, (a, b) -> a[0] - b[0]);
        List<int[]> res = dfs(points, 0, points.size() - 1, K);
        return res;
    }

    private List<int[]> dfs(List<int[]> points, int s, int e, int K) {
        List<int[]> res = new ArrayList<>();
        if(s == e){
            return res;
        }
        int mid = s + (e - s) / 2;
        int x1 = points.get(mid)[0];
        int x2 = points.get(mid + 1)[0];
        int mid_x = (x1 + x2) / 2;
        for(int[] p : points){
            if(p[0] > mid_x - K / 2 && p[0] <mid_x - K + 2 ){
                //todo
            }
        }
        res.addAll(dfs(points, s, mid, K));
        res.addAll(dfs(points, mid + 1, e, K));
        return res;
    }
}

