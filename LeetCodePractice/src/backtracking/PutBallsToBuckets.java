package backtracking;
/*
there are n balls and k buckets, n >= k, balls are identical, buckets are identical.
show different ways to put all balls into bucket, and all buckets are not empty.

test:
Set<List<Integer>> res = sol.putBallsToBucket(7,3);
for(List<Integer> ls : res){
	System.out.println(Arrays.toString(ls.toArray()));
}
 */

import java.util.*;

public class PutBallsToBuckets {
    Set<List<Integer>> putBallsToBucket(int n, int k) {
        Set<List<Integer>> res = new HashSet<>();
        List<Integer> buckets = new ArrayList<>(k);
        n = n - k;
        for (int i = 0; i < k; i++) {
            buckets.add(1);
        }
        dfs(n, buckets, res);
        return res;
    }

    private void dfs(int n, List<Integer> buckets, Set<List<Integer>> res) {
        if (n == 0) {
            List<Integer> snapshot = new ArrayList<>(buckets);
            Collections.sort(snapshot);
            if (!res.contains(snapshot)) {
                res.add(snapshot);
            }
            return;
        }
        for (int i = 0; i < buckets.size(); i++) {
            buckets.set(i, buckets.get(i) + 1);
            List<Integer> snapshot = new ArrayList<>(buckets);
            dfs(n - 1, buckets, res);
            buckets.set(i, buckets.get(i) - 1);
        }
    }
}
