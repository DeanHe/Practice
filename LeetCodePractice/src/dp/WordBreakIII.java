package dp;
/*
Give a dictionary of words and a sentence with all whitespace removed, return the number of sentences you can form by inserting whitespaces to the sentence so that each word can be found in the dictionary.

Example
Given a string CatMat
Given a dictionary ["Cat", "Mat", "Ca", "tM", "at", "C", "Dog", "og", "Do"]
return 3

we can form 3 sentences, as follows:
CatMat = Cat Mat
CatMat = Ca tM at
CatMat = C at Mat

Notice
Ignore case

*/
import java.util.*;

public class WordBreakIII {
	/*
     * @param : A string
     * @param : A set of word
     * @return: the number of possible sentences.
     */
    public int wordBreak3(String s, Set<String> dict) {
        int n = s.length();
        s = s.toLowerCase();
        Set<String> lowerDict = new HashSet<>();
        for(String str : dict){
        	lowerDict.add(str.toLowerCase());
        }
        int[][] dp = new int[n][n];
        //initialize
        for(int i = 0; i < n; i++){
        	for(int j = i; j < n; j++){
        		String sub = s.substring(i, j + 1);
        		if(lowerDict.contains(sub)){
        			dp[i][j] = 1;
        		}
        	}
        }
        //transfer function
        for(int i = 0; i < n; i++){
        	for(int j = i; j < n; j++){
        		for(int k = i; k < j; k++){
        			dp[i][j] += dp[i][k] * dp[k + 1][j];
        		}
        	}
        }
        return dp[0][n - 1];
    }
}
