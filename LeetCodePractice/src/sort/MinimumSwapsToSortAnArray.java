package sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
Given an array of n distinct elements, find the minimum number of swaps required to sort the array.

Examples:

Input: {4, 3, 2, 1}
Output: 2
Explanation: Swap index 0 with 3 and 1 with 2 to
              form the sorted array {1, 2, 3, 4}.

Input: {1, 5, 4, 3, 2}
Output: 2

This can be easily done by visualizing the problem as a graph.
We will have n nodes and an edge directed from node i to node j if the element at i’th index must be present at j’th index in the sorted array.

TC: O(n Log n)
Auxiliary Space: O(n)
 */
public class MinimumSwapsToSortAnArray {
    public int minSwaps(int[] arr) {
        int len = arr.length, swap = 0;
        int[] sorted = Arrays.copyOf(arr, arr.length);
        Arrays.sort(sorted);
        boolean[] visited = new boolean[len];
        Map<Integer, Integer> valIdx = new HashMap<>();
        for (int i = 0; i < len; i++) {
            valIdx.put(sorted[i], i);
        }
        for (int i = 0; i < len; i++) {
            if (!visited[i] && valIdx.get(arr[i]) != i) {
                int cycleSize = dfs(arr, valIdx, visited, i);
                if (cycleSize > 0) {
                    swap += cycleSize - 1;
                }
            }
        }
        return swap;
    }

    private int dfs(int[] arr, Map<Integer, Integer> valIdx, boolean[] visited, int i) {
        if (visited[i]) {
            return 0;
        }
        visited[i] = true;
        return 1 + dfs(arr, valIdx, visited, valIdx.get(arr[i]));
    }
}

