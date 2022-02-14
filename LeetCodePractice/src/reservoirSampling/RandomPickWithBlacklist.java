package reservoirSampling;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/*
You are given an integer n and an array of unique integers blacklist. Design an algorithm to pick a random integer in the range [0, n - 1] that is not in blacklist. Any integer that is in the mentioned range and not in blacklist should be equally likely to be returned.

Optimize your algorithm such that it minimizes the number of calls to the built-in random function of your language.

Implement the Solution class:

Solution(int n, int[] blacklist) Initializes the object with the integer n and the blacklisted integers blacklist.
int pick() Returns a random integer in the range [0, n - 1] and not in blacklist.


Example 1:
Input
["Solution", "pick", "pick", "pick", "pick", "pick", "pick", "pick"]
[[7, [2, 3, 5]], [], [], [], [], [], [], []]
Output
[null, 0, 4, 1, 6, 1, 0, 4]

Explanation
Solution solution = new Solution(7, [2, 3, 5]);
solution.pick(); // return 0, any integer from [0,1,4,6] should be ok. Note that for every call of pick,
                 // 0, 1, 4, and 6 must be equally likely to be returned (i.e., with probability 1/4).
solution.pick(); // return 4
solution.pick(); // return 1
solution.pick(); // return 6
solution.pick(); // return 1
solution.pick(); // return 0
solution.pick(); // return 4


Constraints:

1 <= n <= 10^9
0 <= blacklist.length <- min(10^5, n - 1)
0 <= blacklist[i] < n
All the values of blacklist are unique.
At most 2 * 10^4 calls will be made to pick.

analysis:
use hashmap to map disallowed number(#N) to last N available numbers
 */
public class RandomPickWithBlacklist {
    int validCnt;
    Random random;
    Map<Integer, Integer> blockMap;

    public RandomPickWithBlacklist(int n, int[] blacklist) {
        random = new Random();
        blockMap = new HashMap<>();
        for (int num : blacklist) {
            blockMap.put(num, -1);
        }
        validCnt = n - blockMap.size();
        int last = n - 1;
        for (int num : blacklist) {
            if (num < validCnt) { //re-mapping
                while (blockMap.containsKey(last)) {
                    last--;
                }
                blockMap.put(num, last);
                last--;
            }
        }
    }

    public int pick() {
        int p = random.nextInt(validCnt);
        if (blockMap.containsKey(p)) {
            return blockMap.get(p);
        }
        return p;
    }
}
/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(N, blacklist);
 * int param_1 = obj.pick();
 */