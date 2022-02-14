package dfs.Bipartite;

import java.util.*;

/*
Given a set of N people (numbered 1, 2, ..., N), we would like to split everyone into two groups of any size.
Each person may dislike some other people, and they should not go into the same group.
Formally, if dislikes[i] = [a, b], it means it is not allowed to put the people numbered a and b into the same group.
Return true if and only if it is possible to split everyone into two groups in this way.

Example 1:

Input: N = 4, dislikes = [[1,2],[1,3],[2,4]]
Output: true
Explanation: group1 [1,4], group2 [2,3]
Example 2:

Input: N = 3, dislikes = [[1,2],[1,3],[2,3]]
Output: false
Example 3:

Input: N = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
Output: false


Note:

1 <= N <= 2000
0 <= dislikes.length <= 10000
1 <= dislikes[i][j] <= N
dislikes[i][0] < dislikes[i][1]
There does not exist i != j for which dislikes[i] == dislikes[j].
*/
public class PossibleBipartition {
    Map<Integer, List<Integer>> graph;
    Map<Integer, Integer> group;
    public boolean possibleBipartitionDFS(int N, int[][] dislikes) {
        graph = new HashMap<>();
        for (int i = 0; i < dislikes.length; i++) {
            int a = dislikes[i][0];
            int b = dislikes[i][1];
            graph.computeIfAbsent(a, x -> new ArrayList<>()).add(b);
            graph.computeIfAbsent(b, x -> new ArrayList<>()).add(a);
        }
        group = new HashMap<>(); // person : group, group value be 1 or -1
        for (int i = 1; i <= N; i++) {
            if(!group.containsKey(i)){
                if(!dfs(i, 1)){
                    return false;
                }
            }
        }
        return group.size() == N;
    }
    private boolean dfs(int idx, int color){
        group.put(idx, color);
        List<Integer> neighbors = graph.getOrDefault(idx, new ArrayList<>());
        for(int nb : neighbors){
            if(group.containsKey(nb)){
                if(group.get(nb) == color){
                    return false;
                }
            } else { // not assigned group yet
                if(!dfs(nb, -color)){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean possibleBipartitionBFS(int N, int[][] dislikes) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < dislikes.length; i++) {
            int a = dislikes[i][0];
            int b = dislikes[i][1];
            graph.computeIfAbsent(a, x -> new ArrayList<>()).add(b);
            graph.computeIfAbsent(b, x -> new ArrayList<>()).add(a);
        }
        Map<Integer, Integer> group = new HashMap<>(); // person : group, group value be 1 or -1
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (!group.containsKey(i)) {
                queue.offer(i);
                group.put(i, 1);
                while (!queue.isEmpty()) {
                    int cur = queue.poll();
                    int color = group.get(cur);
                    List<Integer> neighbors = graph.getOrDefault(cur, new ArrayList<>());
                    for (int nb : neighbors) {
                        if (group.containsKey(nb)) {
                            int nb_group = group.get(nb);
                            if (nb_group == color) {
                                return false;
                            }
                        } else {
                            group.put(nb, -color);
                            queue.offer(nb);
                        }
                    }
                }
            }
        }
        return group.size() == N;
    }
}
