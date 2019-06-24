package String;
/*Given an input character array, reverse the array word by word. A word is defined as a sequence of non-space characters.

The input character array does not contain leading or trailing spaces and the words are always separated by a single space.

Example
Example1

Input: s = "the sky is blue"
Output: "blue is sky the"
Example2

Input: "a b c"
Output: "c b a"
Challenge
Could you do it in-place without allocating extra space?*/
public class ReverseWordsInaStringII {
	/**
     * @param str: a string
     * @return: return a string
     */
    public char[] reverseWords(char[] str) {
        // write your code here
    	int len = str.length; 
    	int pre = 0;
    	for(int i = 0; i <= len; i++){
    		if(i == len || str[i] == ' '){
    			swap(str, pre, i - 1);
    			pre = i + 1;
    		}
    	}
    	swap(str, 0, len - 1);
    	return str;
    }
    
    private void swap(char[] s, int start, int end){
    	for(int i = start; i <= (start + end) / 2; i++){
    		char temp = s[i];
    		s[i] = s[end + start - i];
    		s[end + start - i] = temp;	
    	}
    }
}
