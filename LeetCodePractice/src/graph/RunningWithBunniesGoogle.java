package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/*
The time it takes to move from your starting point to all of the bunnies and to the bulkhead will be given to you in a square matrix of integers. Each row will tell you the time it takes to get to the start, first bunny, second bunny, ..., last bunny, and the bulkhead in that order. The order of the rows follows the same pattern (start, each bunny, bulkhead). The bunnies can jump into your arms, so picking them up is instantaneous, and arriving at the bulkhead at the same time as it seals still allows for a successful, if dramatic, escape. (Don't worry, any bunnies you don't pick up will be able to escape with you since they no longer have to carry the ones you did pick up.) You can revisit different spots if you wish, and moving to the bulkhead doesn't mean you have to immediately leave -- you can move to and from the bulkhead to pick up additional bunnies if time permits.

In addition to spending time traveling between bunnies, some paths interact with the space station's security checkpoints and add time back to the clock. Adding time to the clock will delay the closing of the bulkhead doors, and if the time goes back up to 0 or a positive number after the doors have already closed, it triggers the bulkhead to reopen. Therefore, it might be possible to walk in a circle and keep gaining time: that is, each time a path is traversed, the same amount of time is used or added.

Write a function of the form solution(times, time_limit) to calculate the most bunnies you can pick up and which bunnies they are, while still escaping through the bulkhead before the doors close for good. If there are multiple sets of bunnies of the same size, return the set of bunnies with the lowest worker IDs (as indexes) in sorted order. The bunnies are represented as a sorted list by worker ID, with the first bunny being 0. There are at most 5 bunnies, and time_limit is a non-negative integer that is at most 999.

For instance, in the case of
[
  [0, 2, 2, 2, -1],  # 0 = Start
  [9, 0, 2, 2, -1],  # 1 = Bunny 0
  [9, 3, 0, 2, -1],  # 2 = Bunny 1
  [9, 3, 2, 0, -1],  # 3 = Bunny 2
  [9, 3, 2, 2,  0],  # 4 = Bulkhead
]
and a time limit of 1, the five inner array rows designate the starting point, bunny 0, bunny 1, bunny 2, and the bulkhead door exit respectively. You could take the path:

Start End Delta Time Status
    -   0     -    1 Bulkhead initially open
    0   4    -1    2
    4   2     2    0
    2   4    -1    1
    4   3     2   -1 Bulkhead closes
    3   4    -1    0 Bulkhead reopens; you and the bunnies exit

With this solution, you would pick up bunnies 1 and 2. This is the best combination for this space station hallway, so the solution is [1, 2].

analysis:
Floyd + detect negative cycle
permutations of subsets of [0:N]
 */
public class RunningWithBunniesGoogle {
    int V;

    public int[] solution(int[][] times, int times_limit) {
        //​ ​Your​ ​code​ ​here
        V = times.length;
        if (V <= 2 || V != times[0].length) {
            return new int[]{};
        }
        boolean negCycle = negCycleFloydWarshall(times);
        if (negCycle) {
            return IntStream.range(0, V - 2).toArray();
        }
        for (int save = V - 2; save > 0; save--) {
            int[] res = findPath(times, times_limit, save);
            if (res != null) {
                return res;
            }
        }
        return new int[]{};
    }

    private int[] findPath(int[][] times, int times_limit, int saveCnt) {
        List<List<Integer>> paths = pathGenerate(saveCnt);
        for (List<Integer> path : paths) {
            if (canPass(times, times_limit, path)) {
                int[] res = path.stream().mapToInt(i -> i).toArray();
                Arrays.sort(res);
                return res;
            }
        }
        return null;
    }

    private boolean canPass(int[][] times, int times_limit, List<Integer> path) {
        times_limit -= times[0][path.get(0) + 1];
        for (int i = 0; i + 1 < path.size(); i++) {
            times_limit -= times[path.get(i) + 1][path.get(i + 1) + 1];
        }
        int last = path.size() - 1;
        times_limit -= times[path.get(last) + 1][times.length - 1];
        return times_limit >= 0;
    }

    public List<List<Integer>> pathGenerate(int saveCnt) {
        List<List<Integer>> res = new ArrayList<>();
        int[] allBunnies = IntStream.range(0, V - 2).toArray();
        List<List<Integer>> subs = subsets(allBunnies, saveCnt);
        for (List<Integer> sub : subs) {
            List<List<Integer>> paths = permute(sub);
            for (List<Integer> path : paths) {
                res.add(path);
            }
        }
        return res;
    }

    public List<List<Integer>> subsets(int[] nums, int cnt) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        subsetHelper(res, nums, temp, 0, cnt);
        return res;
    }

    private void subsetHelper(List<List<Integer>> res, int[] nums, List<Integer> temp, int pos, int total) {
        if (temp.size() == total) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = pos; i < nums.length; i++) {
            temp.add(nums[i]);
            subsetHelper(res, nums, temp, i + 1, total);
            temp.remove(temp.size() - 1);
        }
    }

    public List<List<Integer>> permute(List<Integer> nums) {
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.size();
        boolean[] visited = new boolean[len];
        List<Integer> temp = new ArrayList<>();
        permuteHelper(res, temp, nums, visited);
        return res;
    }

    private void permuteHelper(List<List<Integer>> res, List<Integer> temp, List<Integer> nums, boolean[] visited) {
        if (temp.size() == nums.size()) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < nums.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                temp.add(nums.get(i));
                permuteHelper(res, temp, nums, visited);
                visited[i] = false;
                temp.remove(temp.size() - 1);
            }
        }
    }

    private boolean negCycleFloydWarshall(int[][] graph) {
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (graph[i][j] > graph[i][k] + graph[k][j]) {
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
                }
            }
        }
        for (int i = 0; i < V; i++) {
            if (graph[i][i] < 0) {
                return true;
            }
        }
        return false;
    }
}

