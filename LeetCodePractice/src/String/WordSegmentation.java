package String;

import java.util.ArrayList;

/*Given a long string S, only include normal English words, words are separated by a single space, and give you a positive integer. Please divide the string into several lines and the number of lines is minimum. Requirement 1: You can only wrap between words. The same word cannot be separated; Requirement 2: Each line cannot be more than K unique characters after the division.

Example
Give s="aaaa bbb cccc ddd ee ff ggggg", k=8,return ["aaaa bbb","cccc ddd","ee ff","ggggg"]

Notice
String length does not exceed 100000
Data guarantee legal*/
public class WordSegmentation {
	/**
     * @param s: the string
     * @param k: the k
     * @return: the answer
     */
    public String[] wordSegmentation(String s, int k) {
        // Write your code here
    	int len = s.length();
    	ArrayList<String> res = new ArrayList<>();
    	String[] words =s.split("\\s+");
    	int count = 0;
    	for(String word : words){
    		
    	}
    }
}
