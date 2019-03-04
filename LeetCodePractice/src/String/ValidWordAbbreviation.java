package String;
/*Given a non-empty string word and an abbreviation abbr, return whether the string matches with the given abbreviation.

A string such as "word" contains only the following valid abbreviations:

["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
Example
Example 1:

Input : s = "internationalization", abbr = "i12iz4n"
Output : true
Example 2:

Input : s = "apple", abbr = "a2e"
Output : false
Notice
Notice that only the above abbreviations are valid abbreviations of the string word. Any other string is not a valid abbreviation of word.*/
public class ValidWordAbbreviation {
	/**
     * @param word: a non-empty string
     * @param abbr: an abbreviation
     * @return: true if string matches with the given abbr or false
     */
    public boolean validWordAbbreviation(String word, String abbr) {
        // write your code here
    	if(word == null || abbr == null){
    		return false;
    	}
    	int i = 0, j = 0;
    	char[] s = word.toCharArray();
    	char[] t = abbr.toCharArray();
    	int wLen = s.length;
    	int aLen = t.length;
    	while(i < wLen && j < aLen){
    		if(Character.isDigit(t[j])){
    			if(t[j] == '0'){
    				return false;
    			}
    			int val = 0;
    			while(j < aLen && Character.isDigit(t[j])){
    				val = val * 10 + t[j++] - '0';
    			}
    			i += val;
    		} else {
    			if(s[i] != t[j]){
    				return false;
    			}
    			i++;
    			j++;
    		}
    	}
    	return i == wLen && j == aLen;  	
    }
}
