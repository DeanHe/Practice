package binarySearch;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
There is a country of n cities numbered from 0 to n - 1. In this country, there is a road connecting every pair of cities.

There are m friends numbered from 0 to m - 1 who are traveling through the country. Each one of them will take a path consisting of some cities. Each path is represented by an integer array that contains the visited cities in order. The path may contain a city more than once, but the same city will not be listed consecutively.

Given an integer n and a 2D integer array paths where paths[i] is an integer array representing the path of the ith friend, return the length of the longest common subpath that is shared by every friend's path, or 0 if there is no common subpath at all.

A subpath of a path is a contiguous sequence of cities within that path.


Example 1:
Input: n = 5, paths = [[0,1,2,3,4],
                       [2,3,4],
                       [4,0,1,2,3]]
Output: 2
Explanation: The longest common subpath is [2,3].

Example 2:
Input: n = 3, paths = [[0],[1],[2]]
Output: 0
Explanation: There is no common subpath shared by the three paths.

Example 3:
Input: n = 5, paths = [[0,1,2,3,4],
                       [4,3,2,1,0]]
Output: 1
Explanation: The possible longest common subpaths are [0], [1], [2], [3], and [4]. All have a length of 1.


Constraints:
1 <= n <= 10^5
m == paths.length
2 <= m <= 10^5
sum(paths[i].length) <= 10^5
0 <= paths[i][j] < n
The same city is not listed multiple times consecutively in paths[i].

hint:
1 If there is a common path with length x, there is for sure a common path of length y where y < x.
2 We can use binary search over the answer with the range [0, min(path[i].length)].
3 Using binary search, we want to verify if we have a common path of length m. We can achieve this using hashing.

analysis:
rolling hash
 */
public class LongestCommonSubpath {
    long MOD = (long) 1e11 + 7;
    long base = 100007;
    long[] basePow;

    public int longestCommonSubpath(int n, int[][] paths) {
        int minLen = Integer.MAX_VALUE;
        for (int[] path : paths) {
            minLen = Math.min(minLen, path.length);
        }
        basePow = new long[minLen + 1];
        basePow[0] = 1;
        for (int i = 1; i <= minLen; i++) {
            basePow[i] = mul(basePow[i - 1], base);
        }
        int s = 0, e = minLen;
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (check(paths, mid)) {
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }
        return e;
    }

    private boolean check(int[][] paths, int len) {
        Map<Long, Long> freq = new HashMap<>();
        for (int[] path : paths) {
            Set<Long> set = new HashSet<>();
            long hash = 0;
            for (int i = 0; i < len; i++) {
                hash = (hash + mul(basePow[len - i], path[i] + 1)) % MOD;
            }
            set.add(hash);
            for (int i = len; i < path.length; i++) {
                long rollingHash = (hash - mul(basePow[len], path[i - len] + 1) + MOD) % MOD;
                rollingHash = mul(rollingHash, base);
                rollingHash = (rollingHash + mul(base, path[i] + 1)) % MOD;
                hash = rollingHash;
                set.add(hash);
            }
            for (long hsh : set) {
                freq.put(hsh, freq.getOrDefault(hsh, 0L) + 1);
            }
        }
        for (long hsh : freq.keySet()) {
            if (freq.get(hsh) == paths.length) {
                return true;
            }
        }
        return false;
    }

    private long mul(long a, long b) {
        return (a * b) % MOD;
    }
}
