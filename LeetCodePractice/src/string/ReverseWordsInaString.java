package string;
/*Given an input string, reverse the string word by word.

Example
Given s = "the sky is blue",

return "blue is sky the".

Clarification
What constitutes a word?
A sequence of non-space characters constitutes a word.
Could the input string contain leading or trailing spaces?
Yes. However, your reversed string should not contain leading or trailing spaces.
How about multiple spaces between two words?
Reduce them to a single space in the reversed string.*/
public class ReverseWordsInaString {
	/*
     * @param s: A string
     * @return: A string
     */
    public String reverseWords(String s) {
        // write your code here
    	if(s == null || s.length() == 0){
    		return "";
    	}
    	s = s.trim();
    	String[] arr = s.split(" ");
    	int len = arr.length;
    	StringBuilder sb = new StringBuilder();
    	for(int i = len - 1; i >= 0; i--){
    		arr[i] = arr[i].trim();
    		if(!arr[i].equals("")){
    			sb.append(arr[i]);
        		sb.append(" ");
    		}
    	}
    	return sb.toString().trim();
    }
}
