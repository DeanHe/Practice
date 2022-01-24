package bfs;

import java.util.*;

public class GenerateNextParaMicrosoft {
    public TreeMap<String, List<Double>> store;
    Queue<int[]> q = new LinkedList<>();
    Set<Integer> visited = new HashSet<>();
    int paraCnt;
    List<String> paraNames;
    List<Integer> ls;
    List<TreeMap<String, Double>> dfs_result;
    int dfs_res_idx = 0;

    public GenerateNextParaMicrosoft(TreeMap<String, List<Double>> store) {
        this.store = store;
        paraCnt = store.size();
        paraNames = new ArrayList<>(store.keySet());
        int[] start = new int[paraCnt];
        q.offer(start);
        visited.add(hash(start));
        ls = new ArrayList<>();
        dfs_result = new ArrayList<>();
        dfsInit();
    }

    public TreeMap<String, Double> generateNext() throws Exception {
        if (q.isEmpty()) {
            throw new Exception("No more generation.");
        }
        int[] cur = q.poll();
        TreeMap<String, Double> res = new TreeMap<>();
        for (int i = 0; i < paraCnt; i++) {
            String name = paraNames.get(i);
            res.put(name, store.get(name).get(cur[i]));
        }
        for (int i = 0; i < paraCnt; i++) {
            int[] nb = Arrays.copyOf(cur, cur.length);
            String name = paraNames.get(i);
            if (nb[i] + 1 < store.get(name).size()) {
                nb[i]++;
                int nb_hash = hash(nb);
                if (!visited.contains(nb_hash)) {
                    q.offer(nb);
                    visited.add(nb_hash);
                }
            }
        }
        return res;
    }

    public TreeMap<String, Double> generateNextDFS() throws Exception {
        if(dfs_res_idx >= dfs_result.size()){
            throw new Exception("No more generation.");
        }
        return dfs_result.get(dfs_res_idx++);

    }

    public void dfsInit(){
        int sz = ls.size();
        if(sz == paraCnt){
            TreeMap<String, Double> res = new TreeMap<>();
            for(int i = 0; i < ls.size(); i++){
                String name = paraNames.get(i);
                int idx = ls.get(i);
                res.put(name, store.get(name).get(idx));
            }
            dfs_result.add(res);
            return;
        }
        String name = paraNames.get(sz);
        List<Double> parameters = store.get(name);
        for(int i = 0; i < parameters.size(); i++){
            ls.add(i);
            dfsInit();
            ls.remove(ls.size() - 1);
        }
    }

    private int hash(int[] arr) {
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            res = res * 10 + arr[i];
        }
        return res;
    }
}
