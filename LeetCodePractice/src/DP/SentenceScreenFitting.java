package DP;
/*Given a rows x cols screen and a sentence represented by a list of non-empty words, find how many times the given sentence can be fitted on the screen.

Example
Example 1:
	Input: rows = 4, cols = 5, sentence = ["I", "had", "apple", "pie"]
	Output: 1
	
	Explanation:
	I-had
	apple
	pie-I
	had--
	
	The character '-' signifies an empty space on the screen.
	
Example 2:
	Input:  rows = 2, cols = 8, sentence = ["hello", "world"]
	Output:  1
	
	Explanation:
	
	hello---
	world---
	
	The character '-' signifies an empty space on the screen.

Example 3:
	Input: rows = 3, cols = 6, sentence = ["a", "bcd", "e"]
	Output:  2
	
	Explanation:
	a-bcd-
	e-a---
	bcd-e-

	The character '-' signifies an empty space on the screen.
Notice
A word cannot be split into two lines.
The order of words in the sentence must remain unchanged.
Two consecutive words in a line must be separated by a single space.
Total words in the sentence won't exceed 100.
Length of each word is greater than 0 and won't exceed 10.
1 ≤ rows, cols ≤ 20,000.*/
public class SentenceScreenFitting {
	/**
     * @param sentence: a list of string
     * @param rows: an integer
     * @param cols: an integer
     * @return: return an integer, denote times the given sentence can be fitted on the screen
     */
    public int wordsTyping(String[] sentence, int rows, int cols) {
        // Write your code here
    	StringBuilder sb = new StringBuilder();
    	for(String s : sentence){
    		sb.append(s);
    		sb.append(" ");
    	}
    	int len = sb.length();
    	int count = 0;
    	for(int r = 0; r < rows; r++){
    		count += cols;
    		if(sb.charAt(count % len) == ' '){
    			count++;
    		} else {
    			while(count > 0 && sb.charAt((count - 1) % len) != ' '){
    				count--;
    			}
    		}
    	}
    	return count / len;
    }
}
