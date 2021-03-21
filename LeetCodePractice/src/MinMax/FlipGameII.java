package MinMax;
/*You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move and therefore the other person will be the winner.

Write a function to determine if the starting player can guarantee a win.

Example
Example1

Input:  s = "++++"
Output: true
Explanation:
The starting player can guarantee a win by flipping the middle "++" to become "+--+".
Example2

Input: s = "+++++"
Output: false 
Explanation:
The starting player can not win 
"+++--" --> "+----"
"++--+" --> "----+"
Challenge
Derive your algorithm's runtime complexity.
*/
public class FlipGameII {
	/**
     * @param s: the given string
     * @return: if the starting player can guarantee a win
     */
    public boolean canWin(String s) {
        // write your code here
    	if(s == null || s.length() < 2){
    		return false;
    	}
    	int len = s.length();
    	for(int i = 0; i < len - 1; i++){
    		if(s.startsWith("++", i)){
    			String nextStep = s.substring(0, i) + "--" + s.substring(i + 2);
    			if(!canWin(nextStep)){
    				return true;
    			}
    		}
    	}
    	return false;
    }
}
