package contest;

import java.util.*;

/*
There are n items each belonging to zero or one of m groups where group[i] is the group that the i-th item belongs to and it's equal to -1 if the i-th item belongs to no group. The items and the groups are zero indexed. A group can have no item belonging to it.

        Return a sorted list of the items such that:

        The items that belong to the same group are next to each other in the sorted list.
        There are some relations between these items where beforeItems[i] is a list containing all the items that should come before the i-th item in the sorted array (to the left of the i-th item).
        Return any solution if there is more than one solution and return an empty list if there is no solution.

        Example 1:

        Input: n = 8, m = 2, group = [-1,-1,1,0,0,1,0,-1], beforeItems = [[],[6],[5],[6],[3,6],[],[],[]]
        Output: [6,3,4,1,5,2,0,7]
        Example 2:

        Input: n = 8, m = 2, group = [-1,-1,1,0,0,1,0,-1], beforeItems = [[],[6],[5],[6],[3],[],[4],[]]
        Output: []
        Explanation: This is the same as example 1 except that 4 needs to be before 6 in the sorted list.

        Constraints:

        1 <= m <= n <= 3*10^4
        group.length == beforeItems.length == n
        -1 <= group[i] <= m-1
        0 <= beforeItems[i].length <= n-1
        0 <= beforeItems[i][j] <= n-1
        i != beforeItems[i][j]
        beforeItems[i] does not contain duplicates elements.
*/
public class SortItemsByGroupsRespectingDependencies {
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        //Item dependency graph creation
        Map<Integer, Set<Integer>> itemGraph = new HashMap<>();
        Map<Integer, Integer> itemInDegree = new HashMap<>();
        for(int i = 0; i < n; i++){
            itemGraph.putIfAbsent(i, new HashSet<>());
        }
        //
        Map<Integer, Set<Integer>> groupGraph = new HashMap<>();
        Map<Integer, Integer> groupInDegree = new HashMap<>();
        for(int i = 0; i < m; i++){
            groupGraph.putIfAbsent(i, new HashSet<>());
        }
        for(int i = 0; i < beforeItems.size(); i++){
            List<Integer> before = beforeItems.get(i);
            for(int be : before){
                itemGraph.get(be).add(i);
                itemInDegree.put(i, itemInDegree.getOrDefault(i, 0) + 1);
                // If an item i is dependent on item be then its group_i should also be dependent on group_be
                int group_i = group[i];
                int group_be = group[be];
                groupGraph.get(group_be).add(group_i);
                groupInDegree.put(group_i, groupInDegree.getOrDefault(group_i, 0) + 1);
            }
        }
        List<Integer> itemOrder = topologicalSort(itemGraph, itemInDegree, n);
        List<Integer> groupOrder = topologicalSort(groupGraph, groupInDegree, groupGraph.size());
        // find a cycle in graph
        if(itemOrder.size() == 0 || groupOrder.size() == 0){
            return new int[0];
        }
        Map<Integer, List<Integer>> groupItemMap = new HashMap<>();
        int[] res = new int[n];
        for(int item : itemOrder){
            int g = group[item];
            groupItemMap.putIfAbsent(g, new ArrayList<>());
            groupGraph.get(g).add(item);
        }
        int i = 0;
        for(int g : groupOrder){
            List<Integer> items = groupItemMap.get(g);
            for(int item : items){
                res[i++] = item;
            }
        }
        return res;
    }
    private List<Integer> topologicalSort(Map<Integer, Set<Integer>> graph, Map<Integer, Integer> indegree, int count){
        List<Integer> res = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for(int i : graph.keySet()){
            if(indegree.getOrDefault(i, 0) == 0){
                queue.offer(i);
            }
        }
        while(!queue.isEmpty()){
            int cur = queue.poll();
            count--;
            res.add(cur);
            for(int nb : graph.get(cur)){
                int nb_ind = indegree.get(nb) - 1;
                indegree.put(nb, nb_ind);
                if(nb_ind == 0){
                    queue.offer(nb);
                }
            }
        }
        Comparator<String> ctr = (String s1, String s2) -> Integer.compare(s1.length(), s2.length());
        return count == 0 ? res : new ArrayList<>();
    }
}
