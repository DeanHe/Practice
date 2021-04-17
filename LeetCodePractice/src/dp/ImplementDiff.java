package dp;

/*
The diff utility is a data comparison tool that calculates and displays the differences between two text.
It tries to determine the smallest set of deletions and insertions to create one text from the other.
Diff is line-oriented rather than character-oriented, unlike edit distance.

For example,
Input:
string X = "XMJYAUZ";
string Y = "XMJAATZ";
Output:

X M J -Y A -U +A +T Z

(- indicates that character is deleted from Y but it was present in X)
(+ indicates that character is inserted in Y but it was not present in X)*/

public class ImplementDiff {
    int[][] dp;

    public String diff(String source, String target) {
        dp = new int[source.length() + 1][target.length() + 1]; // dp[i][j] means LCS of src[:i] and target[:j]
        LongestCommonSubsequence(source, target);
        StringBuilder sb = new StringBuilder();
        dfs(source, target, source.length() - 1, target.length() - 1, sb);
        return sb.toString();
    }

    private void LongestCommonSubsequence(String source, String target) {
        for (int i = 0; i < source.length(); i++) {
            for (int j = 0; j < target.length(); j++) {
                if (source.charAt(i) == target.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
                System.out.print(dp[i + 1][j + 1]);
            }
            System.out.println();
        }
    }

    private void dfs(String source, String target, int sPos, int tPos, StringBuilder sb) {
        if (sPos < 0 || tPos < 0) {
            return;
        }
        System.out.println("sPos:" + sPos + source.charAt(sPos) + " tPos:" + tPos + target.charAt(tPos));
        if (source.charAt(sPos) == target.charAt(tPos)) {
            dfs(source, target, sPos - 1, tPos - 1, sb);
            sb.append(" " + source.charAt(sPos));
        } else if (dp[sPos + 1][tPos] >= dp[sPos][tPos + 1]) {
            dfs(source, target, sPos, tPos - 1, sb);
            sb.append(" +" + target.charAt(tPos));
        } else if (dp[sPos + 1][tPos] < dp[sPos][tPos + 1]) {
            dfs(source, target, sPos - 1, tPos, sb);
            sb.append(" -" + source.charAt(sPos));
        }
    }

}
