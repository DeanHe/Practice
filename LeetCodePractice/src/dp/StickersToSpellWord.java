package dp;

import java.util.HashMap;
import java.util.Map;

/*
We are given n different types of stickers. Each sticker has a lowercase English word on it.

You would like to spell out the given string target by cutting individual letters from your collection of stickers and rearranging them. You can use each sticker more than once if you want, and you have infinite quantities of each sticker.

Return the minimum number of stickers that you need to spell out target. If the task is impossible, return -1.

Note: In all test cases, all words were chosen randomly from the 1000 most common US English words, and target was chosen as a concatenation of two random words.

Example 1:
Input: stickers = ["with","example","science"], target = "thehat"
Output: 3
Explanation:
We can use 2 "with" stickers, and 1 "example" sticker.
After cutting and rearrange the letters of those stickers, we can form the target "thehat".
Also, this is the minimum number of stickers necessary to form the target string.

Example 2:
Input: stickers = ["notice","possible"], target = "basicbasic"
Output: -1
Explanation:
We cannot form the target "basicbasic" from cutting letters from the given stickers.

Constraints:
n == stickers.length
1 <= n <= 50
1 <= stickers[i].length <= 10
1 <= target.length <= 15
stickers[i] and target consist of lowercase English letters.

hint:
1 We want to perform an exhaustive search, but we need to speed it up based on the input data being random.
For all stickers, we can ignore any letters that are not in the target word.
When our candidate answer won't be smaller than an answer we have already found, we can stop searching this path.
When a sticker dominates another, we shouldn't include the dominated sticker in our sticker collection.
[Here, we say a sticker `A` dominates `B` if `A.count(letter) >= B.count(letter)` for all letters.]
 */
public class StickersToSpellWord {
    public int minStickers(String[] stickers, String target) {
        int[][] cnt = new int[stickers.length][26];
        Map<String, Integer> mem = new HashMap<>();
        for (int i = 0; i < stickers.length; i++) {
            for (char c : stickers[i].toCharArray()) {
                cnt[i][c - 'a']++;
            }
        }
        mem.put("", 0);
        return dfs(mem, cnt, target);
    }

    private int dfs(Map<String, Integer> mem, int[][] cnt, String target) {
        if (mem.containsKey(target)) {
            return mem.get(target);
        }
        int res = Integer.MAX_VALUE;
        int[] targetCnt = new int[26];
        for (char c : target.toCharArray()) {
            targetCnt[c - 'a']++;
        }
        for (int i = 0; i < cnt.length; i++) {
            if (cnt[i][target.charAt(0) - 'a'] == 0) {
                continue;
            }
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 26; j++) {
                if (targetCnt[j] > 0) {
                    for (int k = 0; k < Math.max(0, targetCnt[j] - cnt[i][j]); k++) {
                        sb.append((char) ('a' + j));
                    }
                }
            }
            int tmp = dfs(mem, cnt, sb.toString());
            if (tmp != -1) {
                res = Math.min(res, tmp + 1);
            }
        }
        mem.put(target, res != Integer.MAX_VALUE ? res : -1);
        return mem.get(target);
    }
}
