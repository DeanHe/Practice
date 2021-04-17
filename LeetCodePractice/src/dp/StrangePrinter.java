package dp;
/*There is a strange printer with the following two special requirements:

The printer can only print a sequence of the same character each time.
At each turn, the printer can print new characters starting from and ending at any places, and will cover the original existing characters.
Given a string consists of lower English letters only, your job is to count the minimum number of turns the printer needed in order to print it.

Example 1:
Input: "aaabbb"
Output: 2
Explanation: Print "aaa" first and then print "bbb".
Example 2:
Input: "aba"
Output: 2
Explanation: Print "aaa" first and then print "b" from the second place of the string, which will cover the existing character 'a'.
Hint: Length of the given string will not exceed 100.*/

/*The problem wants us to find the number of ways to do something without giving specific steps like how to achieve it. This can be a typical signal that dynamic programming may come to help.

mem[i][j] stands for the minimal turns we need for string from index i to index j.
So we have

mem[i][i] = 1: we need 1 turn to paint a single character.
mem[i][i + 1]
mem[i][i + 1] = 1 if s.chartAt(i) == s.charAt(i + 1)
mem[i][i + 1] = 2 if s.chartAt(i) != s.charAt(i + 1)
Then we can iteration len from 2 to possibly n. For each iteration, we iteration start index from 0 to the farthest possible.

The maximum turns for mem[start][start + len] is len + 1, i.e. print one character each time.
We can further divide the substring to two parts: start -> start+k and start+k+1 -> start+len. It is something as following:
index |start  ...  start + k| |start + k + 1 ... start + len|
char  |  a    ...       b   | |      c       ...      b     |
As shown above, if we have s.charAt(start + k) == s.charAt(start + len), we can make it in one turn when we print this character (i.e. b here)
This case we can reduce our turns to mem[start][start + k] + mem[start + k + 1][start + len] - 1
*/
public class StrangePrinter {
	public int strangePrinter(String s) {
		if(s == null || s.length() == 0){
			 return 0;
		}
		int len = s.length();
        int[][] dp = new int[len][len];
        for(int i = 0; i < len; i++){
        	dp[i][i] = 1;
        	if(i < len - 1){
        		if(s.charAt(i) == s.charAt(i + 1)){
        			dp[i][i + 1] = 1;
        		} else {
        			dp[i][i + 1] = 2;
        		}
        	}
        }
        for(int l = 2; l < len; l++){
        	for(int start = 0; start + l < len; start++){
        		dp[start][start + l] = l + 1;
        		for(int k = 0; k < l; k++){
        			int tmp = dp[start][start + k] + dp[start + k + 1][start + l];
        			if(s.charAt(start + k) == s.charAt(start + l)){
        				dp[start][start + l] = Math.min(dp[start][start + l], tmp - 1);
        			} else {
        				dp[start][start + l] = Math.min(dp[start][start + l], tmp);
        			}
        		}
        	}
        }
        return dp[0][len - 1];
    }
}
