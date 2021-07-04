package dp;

/*
You are given several boxes with different colors represented by different positive numbers.

You may experience several rounds to remove boxes until there is no box left. Each time you can choose some continuous boxes with the same color (i.e., composed of k boxes, k >= 1), remove them and get k * k points.

Return the maximum points you can get.



Example 1:

Input: boxes = [1,3,2,2,2,3,4,3,1]
Output: 23
Explanation:
[1, 3, 2, 2, 2, 3, 4, 3, 1]
----> [1, 3, 3, 4, 3, 1] (3*3=9 points)
----> [1, 3, 3, 3, 1] (1*1=1 points)
----> [1, 1] (3*3=9 points)
----> [] (2*2=4 points)
Example 2:

Input: boxes = [1,1,1]
Output: 9
Example 3:

Input: boxes = [1]
Output: 1


Constraints:

1 <= boxes.length <= 100
1 <= boxes[i] <= 100

denote dp[i][j][k] means the points get by removing boxes[i:j] with k pre boxes of same color
case 1: remove boxes[s] with subarray of its same color first
case 2: remove boxes[s'] which is a different color first
 */
public class RemoveBoxes {
    int len;
    Integer[][][] mem;

    public int removeBoxes(int[] boxes) {
        len = boxes.length;
        mem = new Integer[len][len][len];
        return dfs(boxes, 0, len - 1, 0);
    }

    private int dfs(int[] boxes, int s, int e, int k) {
        if (s > e) {
            return 0;
        }
        if (mem[s][e][k] != null) {
            return mem[s][e][k];
        }
        while (s + 1 <= e && boxes[s] == boxes[s + 1]) {
            k++;
            s++;
        }
        int res = (k + 1) * (k + 1) + dfs(boxes, s + 1, e, 0);
        for (int i = s + 1; i <= e; i++) {
            if (boxes[s] == boxes[i]) {
                res = Math.max(res, dfs(boxes, s + 1, i - 1, 0) + dfs(boxes, i, e, k + 1));
            }
        }
        return mem[s][e][k] = res;
    }
}

