package Contest;

import java.util.Arrays;
import java.util.List;

/*
You are given two strings word1 and word2. Merge the strings by adding letters in alternating order, starting with word1. If a string is longer than the other, append the additional letters onto the end of the merged string.

Return the merged string.



Example 1:

Input: word1 = "abc", word2 = "pqr"
Output: "apbqcr"
Explanation: The merged string will be merged as so:
word1:  a   b   c
word2:    p   q   r
merged: a p b q c r
Example 2:

Input: word1 = "ab", word2 = "pqrs"
Output: "apbqrs"
Explanation: Notice that as word2 is longer, "rs" is appended to the end.
word1:  a   b
word2:    p   q   r   s
merged: a p b q   r   s
Example 3:

Input: word1 = "abcd", word2 = "pq"
Output: "apbqcd"
Explanation: Notice that as word1 is longer, "cd" is appended to the end.
word1:  a   b   c   d
word2:    p   q
merged: a p b q c   d


Constraints:

1 <= word1.length, word2.length <= 100
word1 and word2 consist of lowercase English letters.
 */
public class MergeStringsAlternately {
    Integer[][] mem;
    public int maximumScore(int[] nums, int[] multipliers) {
        mem = new Integer[nums.length][nums.length];
        return dfs(nums, multipliers, 0, nums.length - 1, 0);
    }

    private int dfs(int[] nums, int[] multipliers, int s, int e, int i) {
        if(i == multipliers.length){
            return 0;
        }
        if(mem[s][e] != null){
            return mem[s][e];
        }
        int a1 = nums[s] * multipliers[i] + dfs(nums, multipliers, s + 1, e, i + 1);
        int a2 = nums[e] * multipliers[i] + dfs(nums, multipliers, s, e - 1, i + 1);
        return mem[s][e] = Math.max(a1, a2);
    }
}
