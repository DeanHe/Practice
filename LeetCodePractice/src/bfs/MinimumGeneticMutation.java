package bfs;

import java.util.*;

/*
A gene string can be represented by an 8-character long string, with choices from 'A', 'C', 'G', and 'T'.
Suppose we need to investigate a mutation from a gene string start to a gene string end where one mutation is defined as one single character changed in the gene string.
For example, "AACCGGTT" --> "AACCGGTA" is one mutation.
There is also a gene bank bank that records all the valid gene mutations. A gene must be in bank to make it a valid gene string.
Given the two gene strings start and end and the gene bank, return the minimum number of mutations needed to mutate from start to end. If there is no such a mutation, return -1.
Note that the starting point is assumed to be valid, so it might not be included in the bank.

Example 1:
Input: start = "AACCGGTT", end = "AACCGGTA", bank = ["AACCGGTA"]
Output: 1

Example 2:
Input: start = "AACCGGTT", end = "AAACGGTA", bank = ["AACCGGTA","AACCGCTA","AAACGGTA"]
Output: 2

Example 3:
Input: start = "AAAAACCC", end = "AACCCCCC", bank = ["AAAACCCC","AAACCCCC","AACCCCCC"]
Output: 3


Constraints:
start.length == 8
end.length == 8
0 <= bank.length <= 10
bank[i].length == 8
start, end, and bank[i] consist of only the characters ['A', 'C', 'G', 'T'].
 */
public class MinimumGeneticMutation {
    public int minMutation(String start, String end, String[] bank) {
        char[] dirs = {'A', 'C', 'G', 'T'};
        Set<String> set = new HashSet<>(Arrays.asList(bank));
        Queue<String> q = new LinkedList<>();
        q.offer(start);
        int step = 0;
        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                String cur = q.poll();
                if (cur.equals(end)) {
                    return step;
                }
                char[] arr = cur.toCharArray();
                for (int j = 0; j < arr.length; j++) {
                    char old = arr[j];
                    for (char c : dirs) {
                        if (arr[j] != c) {
                            arr[j] = c;
                            if (set.contains(String.valueOf(arr))) {
                                q.offer(String.valueOf(arr));
                                set.remove(String.valueOf(arr));
                            }
                        }
                    }
                    arr[j] = old;
                }
            }
            step++;
        }
        return -1;
    }
}
