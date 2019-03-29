package DP;
/*
Given a non-empty string, encode the string such that its encoded length is the shortest.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times.

Example
Given s = "aaa", return "aaa".
Explanation: 
There is no way to encode it such that it is shorter than the input string, so we do not encode it.

Given s = "aaaaa", return "5[a]".
Explanation: 
"5[a]" is shorter than "aaaaa" by 1 character.

Given s = "aaaaaaaaaa", return "10[a]".
Explanation: 
"a9[a]" or "9[a]a" are also valid solutions, both of them have the same length = 5, which is the same as "10[a]".

Given s = "aabcaabcd", return "2[aabc]d".
Explanation: 
"aabc" occurs twice, so one answer can be "2[aabc]d".

Given s = "abbbabbbcabbbabbbc", return "2[2[abbb]c]".
Explanation: 
"abbbabbbc" occurs twice, but "abbbabbbc" can also be encoded to "2[abbb]c", so one answer can be "2[2[abbb]c]".
Notice
k will be a positive integer and encoded string will not be empty or have extra space.
You may assume that the input string contains only lowercase English letters. The string's length is at most 160.
If an encoding process does not make the string shorter, then do not encode it. If there are several solutions, return any of them is fine.
*/

/*dp[i][j] = shortest string from index i to index j in encoded form.
We can write the following formula as:-
dp[i][j] = min(dp[i][j], dp[i][k] + dp[k+1][j]) or if we can find some pattern in string from i to j which will result in more less length.
Time Complexity = O(n^3)*/
public class EncodeStringWithShortestLength {
	/**
     * @param s: a string
     * @return: return a string
     */
    public String encode(String s) {
        // write your code here
    	int len = s.length();
    	String[][] dp = new String[len][len];
    	for(int l = 0; l < len; l++){
    		for(int i = 0; i + l < len; i++){
    			int j = i + l;
    			String sub = s.substring(i, j + 1);
    			dp[i][j] = sub;
    			// Checking if string length < 5. In that case, we know that encoding will not help.
    			if(l > 4){
    				for(int k = i; k < j; k++){
    					if(dp[i][k].length() + dp[k + 1][j].length() < dp[i][j].length()){
    						dp[i][j] = dp[i][k] + dp[k + 1][j];
    					}
    				}
    				// Loop for checking if string can itself found some pattern in it which could be repeated.
    				for(int k = 0; k < sub.length(); k++){
    					String repeat = sub.substring(0, k + 1);
    					if(sub.length() % repeat.length() == 0 && sub.replaceAll(repeat, "").length() == 0){
    						String temp = sub.length() / repeat.length() + "[" + dp[i][i + k] + "]";
    						if(temp.length() < dp[i][j].length()){
    							dp[i][j] = temp;
    						}
    					}
    				}
    			}
    		}
    	}
    	return dp[0][len - 1];
    }
}
