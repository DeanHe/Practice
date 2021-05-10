package contest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
Given a string s of lowercase letters, you need to find the maximum number of non-empty substrings of s that meet the following conditions:

        The substrings do not overlap, that is for any two substrings s[i..j] and s[k..l], either j < k or i > l is true.
        A substring that contains a certain character c must also contain all occurrences of c.
        Find the maximum number of substrings that meet the above conditions. If there are multiple solutions with the same number of substrings, return the one with minimum total length. It can be shown that there exists a unique solution of minimum total length.

        Notice that you can return the substrings in any order.



        Example 1:

        Input: s = "adefaddaccc"
        Output: ["e","f","ccc"]
        Explanation: The following are all the possible substrings that meet the conditions:
        [
        "adefaddaccc"
        "adefadda",
        "ef",
        "e",
        "f",
        "ccc",
        ]
        If we choose the first string, we cannot choose anything else and we'd get only 1. If we choose "adefadda", we are left with "ccc" which is the only one that doesn't overlap, thus obtaining 2 substrings. Notice also, that it's not optimal to choose "ef" since it can be split into two. Therefore, the optimal way is to choose ["e","f","ccc"] which gives us 3 substrings. No other solution of the same number of substrings exist.
        Example 2:

        Input: s = "abbaccd"
        Output: ["d","bb","cc"]
        Explanation: Notice that while the set of substrings ["d","abba","cc"] also has length 3, it's considered incorrect since it has larger total length.


        Constraints:

        1 <= s.length <= 10^5
        s contains only lowercase English letters.

analysis:
greedy
Notice that it's impossible for any two valid substrings to overlap unless one is inside another.
We can start by finding the starting and ending index for each character.
From these indices, we can form the substrings by expanding each character's range if necessary
(if another character exists in the range with smaller/larger starting/ending index).
sort the valid substrings by length and greedily take those with the smallest length, discarding the ones that overlap those we took.
*/
public class MaximumNumberOfNonOverlappingSubstrings {
    public List<String> maxNumOfSubstrings(String s) {
        List<String> res = new ArrayList<>();
        char[] sc = s.toCharArray();
        Map<Character, int[]> map = new HashMap<>();
        for (int i = 0; i < sc.length; i++) {
            char c = sc[i];
            if (map.containsKey(c)) {
                map.get(c)[1] = i;
            } else {
                map.put(c, new int[]{i, i});
            }
        }
        List<int[]> ranges = extendRange(map, sc);
        Collections.sort(ranges, (a, b) -> a[1] - b[1]);
        int idx = -1;
        for (int[] r : ranges) {
            if (r[0] > idx) {
                res.add(s.substring(r[0], r[1] + 1));
                idx = r[1];
            }
        }
        return res;
    }

    private List<int[]> extendRange(Map<Character, int[]> map, char[] sc) {
        List<int[]> ranges = new ArrayList<>(map.values());
        for (int[] r : ranges) {
            Set<Character> set = new HashSet<>();
            int left = r[0], right = r[1], cnt = 0;
            for (int i = r[0]; i <= r[1]; i++) {
                char c = sc[i];
                if (set.add(c)) {
                    int[] c_r = map.get(c);
                    left = Math.min(left, c_r[0]);
                    right = Math.max(right, c_r[1]);
                }
            }
            while (set.size() != cnt) {
                cnt = set.size();
                for (int i = left; i < r[0]; i++) {
                    char c = sc[i];
                    if (set.add(c)) {
                        int[] c_r = map.get(c);
                        left = Math.min(left, c_r[0]);
                        right = Math.max(right, c_r[1]);
                    }
                }
                for (int i = r[1] + 1; i <= right; i++) {
                    char c = sc[i];
                    if (set.add(c)) {
                        int[] c_r = map.get(c);
                        left = Math.min(left, c_r[0]);
                        right = Math.max(right, c_r[1]);
                    }
                }
            }
            r[0] = left;
            r[1] = right;
        }
        return ranges;
    }
}
