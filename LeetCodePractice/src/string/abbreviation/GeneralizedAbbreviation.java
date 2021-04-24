package string.abbreviation;

/*Write a function to generate the generalized abbreviations of a word.(order does not matter)
Example
Example 1:

Input: 
word = "word", 
Output: 
["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
Example 2:

Input:
word = "today"
Output:
["1o1a1","1o1ay","1o2y","1o3","1od1y","1od2","1oda1","1oday","2d1y","2d2","2da1","2day","3a1","3ay","4y","5","t1d1y","t1d2","t1da1","t1day","t2a1","t2ay","t3y","t4","to1a1","to1ay","to2y","to3","tod1y","tod2","toda1","today"]*/
//http://www.cnblogs.com/grandyang/p/5261569.html
import java.util.*;

public class GeneralizedAbbreviation {
	/**
     * @param word: the given word
     * @return: the generalized abbreviations of a word
     */
	public List<String> generateAbbreviations(String word) {
        // Write your code here
		List<String> res = new ArrayList<>();
		int len = word.length();
		for (int i = 0; i < (int) Math.pow(2, len); i++) {
			StringBuilder sb = new StringBuilder();
			int abbrCount = 0;
			for (int j = 0; j < len; j++) {
				if (((i >> j) & 1) == 1) {
					abbrCount++;
				} else {
					if (abbrCount > 0) {
						sb.append(abbrCount);
						abbrCount = 0;
					}
					sb.append(word.charAt(j));
				}
			}
			if (abbrCount > 0) {
				sb.append(abbrCount);
			}
			res.add(sb.toString());
		}
		return res;
    }
    public List<String> generateAbbreviationsDFS(String word) {
        // Write your code here
    	List<String> res = new ArrayList<>();
    	dfs(res, word, "", 0, 0);
    	return res;
    }
    private void dfs(List<String> res, String word, String temp, int pos, int abbrCount){
    	int len = word.length();
    	if(pos == len){
    		if(abbrCount > 0){
    			temp += abbrCount;
    		}
    		res.add(temp);
    		return;
    	}
    	dfs(res, word, temp, pos + 1, abbrCount + 1);
    	if(abbrCount > 0){
    		temp = temp + abbrCount + word.charAt(pos);
    	} else {
    		temp = temp + word.charAt(pos);
    	}
    	dfs(res, word, temp, pos + 1, 0);
    }
}
