package bfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/*
Given a special tree structure represent by array, each element saves its parent_idx, given a set contains all nodes to
remove, return the tree after removal
 */
public class RemoveTreeNodesGoogle {
    public void removeNodes(int[] tree, int[] toRemove){
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int i = 0; i < tree.length; i++){
            int parent = tree[i];
            graph.computeIfAbsent(parent, x -> new ArrayList<>()).add(i);
        }
        for(int node : toRemove){
            if(node != -1){
                bfs(node, tree, graph);
            }
        }
    }

    private void bfs(int idx, int[] tree, Map<Integer, List<Integer>> graph) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(idx);
        tree[idx] = -1;
        while(!q.isEmpty()){
            int cur = q.poll();
            for(int nb : graph.getOrDefault(cur, new ArrayList<>())){
                if(tree[nb] != -1){
                    q.offer(nb);
                    tree[nb] = -1;
                }
            }
        }
    }
}

