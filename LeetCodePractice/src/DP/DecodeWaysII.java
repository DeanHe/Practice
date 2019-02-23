package DP;

import javax.sql.rowset.CachedRowSet;

import org.w3c.dom.css.ElementCSSInlineStyle;

/*A message containing letters from A-Z is being encoded to numbers using the following mapping way:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Beyond that, now the encoded string can also contain the character '*', which can be treated as one of the numbers from 1 to 9.

Given the encoded message containing digits and the character '*', return the total number of ways to decode it.

Also, since the answer may be very large, you should return the output mod 109 + 7.

Example 1:
Input: "*"
Output: 9
Explanation: The encoded message can be decoded to the string: "A", "B", "C", "D", "E", "F", "G", "H", "I".
Example 2:
Input: "1*"
Output: 9 + 9 = 18*/
public class DecodeWaysII {
	/**
     * @param s: a string,  encoded message
     * @return: an integer, the number of ways decoding
     */
    public int numDecodings(String s) {
    	int len = s.length();
    	int[] dp = new int[len + 1];
    	dp[0] = 1;
    	if(s.charAt(0) == '*') {
    		dp[0] = 9;
    	} else if(s.charAt(0) == '0'){
    		dp[0] = 0;
    	} else {
    		dp[0] = 1;
    	}
    	for(int i = 2; i <= len; i++) {
    		if(s.charAt(i - 1) == '*') {
    			char nam1 = s.charAt(i - 2);
    			if(nam1 == '*') {
    				dp[i] = 9 * dp[i - 1] + 15 * dp[i - 2];
    			} else if(nam1 == '1') {
    				dp[i] = 9 * dp[i - 1] + 9 * dp[i - 2];
    			} else if (nam1 == '2') {
					dp[i] = 9 * dp[i - 1] + 6 * dp[i - 2];
    			} else {
    				dp[i] = 9 * dp[i - 1];
    			}
    		} else {
    			char nam1 = s.charAt(i - 2);
    			char nam2 = s.charAt(i - 1);	
    			if(nam1 == '*') {
    				if(nam2 == '0'){
    					dp[i] = 2 * dp[i - 2];
    				} else {
    					dp[i] = dp[i - 1];
    					if(nam2 <= '6'){
    						dp[i] += 2 * dp[i - 2];
    					} else {
    						dp[i] += dp[i - 2];
    					}
    				}
    			} else {
    				if(nam2 != '0') {
    	        		dp[i] = dp[i - 1];
    	        	}
    	        	int val = (nam1 - '0') * 10  + (nam2 - '0');
    	        	if(val >= 10 && val <= 26) {
    	        		dp[i] += dp[i - 2];
    	        	}
    			}
    		}
    	}
    	return dp[len];
    }
}
